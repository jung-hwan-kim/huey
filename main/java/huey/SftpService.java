package huey;


import com.jcraft.jsch.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;


public class SftpHandler implements Closeable {

    private ChannelSftp channel;
    private Session session;
    private SftpHandler(Session session) {
        this.session = session;
    }
    private void open() throws Exception {
        session.connect();
        channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
    }
    public ChannelSftp getChannel() {
        return channel;
    }
    @Override
    public void close() throws IOException {
        try {
            channel.exit();
        } finally {
            session.disconnect();
        }
    }

    private static JSch createJSch() throws Exception {
        String userHome = System.getProperty("user.home");
        JSch jSch = new JSch();
        jSch.setKnownHosts(userHome + "/.ssh/known_hosts");
        //  ssh-keyscan -H -t rsa REMOTE_HOSTNAME >> known_hosts
        return jSch;
    }

    public static SftpHandler connectWithPw(String hostname, String username, String pw) throws Exception {
        JSch jSch = createJSch();


        Session jschSession = jSch.getSession(username, hostname);
        jschSession.setPassword(pw);
        jschSession.setConfig("server_host_key", "ssh-ed25519,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521,rsa-sha2-512,rsa-sha2-256,ssh-rsa");

        SftpHandler s = new SftpHandler(jschSession);
        s.open();
        return s;
    }

    public static SftpHandler connectWithKeyPair(String hostname, String username) throws Exception {
        JSch jSch = createJSch();
        String userHome = System.getProperty("user.home");
        jSch.addIdentity(userHome + "/.ssh/" + username);

        Session jschSession = jSch.getSession(username, hostname);

        SftpHandler s = new SftpHandler(jschSession);
        s.open();
        return s;
    }


    public String put(String content, String path) throws SftpException {
        channel.put(new ByteArrayInputStream(content.getBytes()), path);
        return path;
    }

    public String get(String path) throws SftpException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        channel.get(path, out);
        return out.toString();
    }

    public List<ChannelSftp.LsEntry> ls(String path) throws SftpException {
        return channel.ls(path);
    }

    public boolean exists(String path) {
        try {
            channel.ls(path);
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

}
