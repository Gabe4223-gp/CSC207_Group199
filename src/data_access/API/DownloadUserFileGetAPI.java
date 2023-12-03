package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;
/**
 * Download Dropbox Http endpoint to download user files in the users NoDraw dropbox account.
 * Class is a subclass of the DropBoxAPI file
 */
public class DownloadUserFileGetAPI extends DropBoxAPI {
    /**
     * Downloads all files within the user Dropbox directory.
     * @param username A string containing the user username to use in downloading files within the users directory.
     * @return an ArrayList containing the data of all files in users directory.
     */
    public ArrayList<String> downloadUserFile(String username) {

        ListContentsUserFolderPostAPI user_files = APIFactory.getFilesAPI();
        ArrayList<String> files = user_files.listContentsUserFolder(username);
        ArrayList<String> fileData = new ArrayList<>();
        for (String file : files) {
            String body = requestBody + String.format("%s/%s\"}", username, file);
            fileData.add(HttpRequest(body));
        }
        return fileData;
    }
    /**
     * Private Http Request to send a download request using the Dropbox download endpoint.
     * @param body A string containing the path for HttpRequest
     * @return a string containing the data of a specified file.
     */
    private String HttpRequest(String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/download"))
                .header("Authorization", this.APIToken)
                .header("Dropbox-API-Arg", body)
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {logger.log(Level.WARNING, "Unsuccessful connection");}
        assert response != null;
        return response.body();
    }
}
