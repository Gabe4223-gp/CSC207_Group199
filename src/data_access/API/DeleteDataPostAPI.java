package data_access.API;

import data_access.file_read_write.FileAccessDAO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;

public class DeleteDataPostAPI extends DropBoxAPI {
    public boolean deleteUserFile(String username, String filename) {
        String body = requestBody + String.format("%s/%s.txt\"}", username, filename);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/delete_v2"))
                .header("Authorization", "Bearer sl.BqOkyVZJOOt8aySCiTcunvi9NrA_88NfqOJw7fzF1RaK6GWaSJ3Zw_E7oe2vF5MqQgeAmsL2z9PciCSepia-FLVKYb-oPz48glcSoV0lHXGchLlYezWqU7SZSzTJgwvwC_zA7jdASjUBDI4YM6vCQDE")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
            logger.log(Level.INFO, "%s folder successfully deleted.", username);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        assert response != null;
        return response.statusCode() == 200;
    }
}