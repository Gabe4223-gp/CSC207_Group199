package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;

public class DownloadUserFileGetAPI extends DropBoxAPI {
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
