package data_access.API;

import use_case.signup.SignupDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;

public class CreateUserFolderPostAPI extends DropBoxAPI {
    public CreateUserFolderPostAPI() {super();}
    public boolean createUserFolder(String username) {
        String body = requestBody + String.format("%s\", \"autorename\": false}", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/create_folder_v2"))
                .header("Authorization", "Bearer sl.Bqq-rMbDu9127dPnp1TuACKCB5NSOtoVcOSYT1A-ZcKIMB00AhnfEXWVjkLOtWZRXXx9Dd5_VaBAScvK14jU7OSCdxWAJzU0nJCSeqaWMNoheITdBG-TPrF6hkNImnDMnD-BsThIiQTZjafDl-33vB4")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
            logger.log(Level.INFO, "%s folder successfully created.", username);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        assert response != null;
        return response.statusCode() == 200;
    }
}