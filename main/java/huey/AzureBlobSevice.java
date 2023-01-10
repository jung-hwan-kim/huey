package huey;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobContainerItem;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.specialized.BlobInputStream;

public class AzureBlobHandler {
    public static BlobContainerClient getBlobContainerClient(String containerName) {
        String storageSasEndpoint = System.getenv("STORAGE_SAS_ENDPOINT");
        String storageSasToken = System.getenv("STORAGE_SAS_TOKEN");

        return new BlobContainerClientBuilder()
                .endpoint(storageSasEndpoint)
                .sasToken(storageSasToken)
                .containerName(containerName)
                .buildClient();
    }
    public static BlobServiceClient getBlobServiceClient() {
        String storageSasEndpoint = System.getenv("STORAGE_SAS_ENDPOINT");
        String storageSasToken = System.getenv("STORAGE_SAS_TOKEN");

        return new BlobServiceClientBuilder()
                .endpoint(storageSasEndpoint)
                .sasToken(storageSasToken)
                .buildClient();
    }
    public static String getContent(String containerName, String blobName) throws Exception {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        try (BlobInputStream in = blobClient.openInputStream()) {
            return (new String(in.readAllBytes()));
        }
    }
    public static void uploadContent(String content, String containerName, String blobName, boolean overwrite) throws Exception {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        BinaryData data = BinaryData.fromBytes(content.getBytes());
        blobClient.upload(data, overwrite);
    }
}
