package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.lang.String;
import java.util.logging.Level;
/**
 * List folder Dropbox Http endpoint to get all user file names in the users NoDraw dropbox account.
 * Class is a subclass of the DropBoxAPI file
 */
public class ListContentsUserFolderPostAPI extends DropBoxAPI {
    /**
     * List all the files in a user directory given their username in the NoDraw Dropbox account.
     * @param username A string containing the user username to use in getting files within user directory.
     * @return an ArrayList containing all the names of files within the users directory.
     */
    public ArrayList<String> listContentsUserFolder(String username){

        ArrayList<String> userFiles = new ArrayList<>();
        String body = requestBody + String.format("%s\", \"recursive\": false, \"include_media_info\": false, \"include_deleted\": false, \"include_has_explicit_shared_members\": false, \"include_mounted_folders\": true, \"include_non_downloadable_files\": true}", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/list_folder"))
                .header("Authorization", this.APIToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {logger.log(Level.WARNING, "Unsuccessful connection");}
        assert response != null;
        String result = response.body();
        String[] temp_list = result.trim().split("\"");
        for (int line = 0; line < temp_list.length; line++) {
            if (temp_list[line].equals("name")) {
                userFiles.add(temp_list[line+2]);
            }
        }
        return userFiles;
    }
}
