package huey;

import com.google.api.gax.paging.Page;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import com.google.common.io.ByteStreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 */
public class GcloudStorageHandler {

    public static Page<Blob> listBlob(String bucketName) {
        Bucket bucket = StorageOptions.getDefaultInstance().getService().get(bucketName);
        Page<Blob> page =bucket.list(Storage.BlobListOption.currentDirectory());
        return page;
    }
    public static Page<Blob> listBlob(String bucketName, String prefix) {
        Bucket bucket = StorageOptions.getDefaultInstance().getService().get(bucketName);
        Page<Blob> page = bucket.list(Storage.BlobListOption.prefix(prefix));
        return page;
    }

    public static Blob create(InputStream in, String bucketName, String nameName) throws Exception {
        BlobId blobId = BlobId.of(bucketName, nameName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        return StorageOptions.getDefaultInstance().getService().createFrom(blobInfo, in, Storage.BlobWriteOption.detectContentType());
    }

    public static Blob update(String content, Blob blob) throws Exception {
        WriteChannel w = blob.writer();
        w.write(ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)));
        w.close();
        return blob;
    }


    public static Bucket getBucket(String bucketName) {
        return StorageOptions.getDefaultInstance().getService().get(bucketName);
    }
    public static Blob getBlob(String bucketName, String blobName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        return storage.get(bucketName, blobName);
    }

    public static String readBlob(Blob blob) {
        ReadChannel r = blob.reader();

        StringBuilder sb = new StringBuilder();
        ByteBuffer b = ByteBuffer.allocate(10);
        try {
            int i = r.read(b);
            while (i != -1) {
                b.flip();
                byte[] _b = new byte[i];
                b.get(_b);
                sb.append(new String(_b));
                b.clear();
                i = r.read(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            r.close();
        }
        return sb.toString();
    }
    public static Page<Bucket> listBuckets() {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Page<Bucket> buckets = storage.list();
        return buckets;
    }
}
