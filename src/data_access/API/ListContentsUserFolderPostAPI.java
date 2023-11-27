package data_access.API;

import data_access.file_read_write.FileAccessDAO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.lang.String;
import java.util.logging.Level;

public class ListContentsUserFolderPostAPI extends DropBoxAPI {
    public ArrayList<String> listContentsUserFolder(String username){

        ArrayList<String> userFiles = new ArrayList<>();
        String body = requestBody + String.format("%s\", \"recursive\": false, \"include_media_info\": false, \"include_deleted\": false, \"include_has_explicit_shared_members\": false, \"include_mounted_folders\": true, \"include_non_downloadable_files\": true}", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/list_folder"))
                .header("Authorization", "Bearer sl.BqhAJ_VCdKcbrgUdOiGT3q6YhtQxEfZzq_b4gQgOyka1_KjM28NAKNsJPKaxrecuBJRTqKDeuKQBDhadUOPI1iraLnOng8SRfB8BIu40kzZiTQaGy2WRNFfmBvf9IIslpv6UkgwfkYTpE65noY8VZrA")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            logger.log(Level.INFO, "%s folder successfully created.", username);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, "Unsuccessful connection");
        }
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
