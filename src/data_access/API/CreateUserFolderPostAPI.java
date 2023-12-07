package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
/**
 * Create folder Dropbox Http endpoint to create a directory for new sign up users in the
 * NoDraw dropbox account. Class is a subclass of the DropBoxAPI file
 */
public class CreateUserFolderPostAPI extends DropBoxAPI {
    /**
     * Initialize CreateUserFolderPostAPI to call the requestBody, logger, and APIToken of DropBoxAPI
     * used in the http request.
     */
    public CreateUserFolderPostAPI() {super();}
    /**
     * Create a new directory for the new user given their username in the NoDraw Dropbox account.
     * @param username A string containing the user username to use as the name of directory.
     * @return whether a new user directory is created (True) or not (False).
     */
    public boolean createUserFolder(String username) {
        String body = this.getRequestBody() + String.format("%s\", \"autorename\": false}", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/create_folder_v2"))
                .header("Authorization", this.APIToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {this.getLogger().log(Level.WARNING, e.getMessage());}
        assert response != null;
        return response.statusCode() == 200;
    }
}