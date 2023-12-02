package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;

public class DeleteDataPostAPI extends DropBoxAPI {
    private boolean HttpRequest(String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/delete_v2"))
                .header("Authorization", this.APIToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        assert response != null;
        return response.statusCode() == 200;
    }
    public boolean deleteUserFile(String username, String filename) {
        String body = requestBody + String.format("%s/%s.txt\"}", username, filename);
        return HttpRequest(body);
    }
    public void deleteUser(String username) {
        String body = requestBody + String.format("%s\"}", username);
        HttpRequest(body);
    }
}