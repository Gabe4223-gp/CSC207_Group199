package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
/**
 * Delete Dropbox Http endpoint to remove a user specified file in the users account
 * or remove the entire user directory in the NoDraw dropbox account.
 * Class is a subclass of the DropBoxAPI file
 */
public class DeleteDataPostAPI extends DropBoxAPI {
    /**
     * Private Http Request to send a delete request using the Dropbox delete endpoint.
     * @param body A string containing the path for HttpRequest
     * @return whether a file or directory is successfully deleted (True) or not (False).
     */
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
        } catch (IOException | InterruptedException e) {logger.log(Level.WARNING, e.getMessage());}
        assert response != null;
        return response.statusCode() == 200;
    }
    /**
     * Deletes a specified file in the users Dropbox directory.
     * @param username A string containing the user username to use in deleting files within the users directory.
     * @param filename A string containing the user file to delete in user directory.
     * @return whether the file is successfully deleted (True) or not (False).
     */
    public boolean deleteUserFile(String username, String filename) {
        String body = requestBody + String.format("%s/%s.txt\"}", username, filename);
        return HttpRequest(body);
    }
    /**
     * Deletes user Dropbox directory.
     * @param username A string containing the user username to use in deleting files within the users directory.
     * @return whether the directory is successfully deleted (True) or not (False).
     */
    public boolean deleteUser(String username) {
        String body = requestBody + String.format("%s\"}", username);
        return HttpRequest(body);
    }
}