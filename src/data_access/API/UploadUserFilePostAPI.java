package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import entity.TextNote;
/**
 * Upload file Dropbox Http endpoint to upload a file into the users
 * NoDraw dropbox account. Class is a subclass of the DropBoxAPI file
 */
public class UploadUserFilePostAPI extends DropBoxAPI {
    /**
     * Upload a file into the users Dropbox directory given their username and TextNote to save in the NoDraw Dropbox account.
     * @param username A string containing the user username to use in uploading a file in the users' directory.
     * @param textNote A textNote containing the user, fileName, fileTxt, and dateCreation to upload to the users' Dropbox directory.
     * @return whether a file is successfully uploaded (True) or not (False).
     */
    public boolean uploadUserFile(String username, TextNote textNote) {
        if (textNote.getFileName().isEmpty() || !username.equals(textNote.getCreatedUser())) {return false;}

        String bodyText = requestBody + String.format("%s/%s.txt\", \"mode\": {\".tag\": \"overwrite\"},\"autorename\": false, \"mute\": false}", username, textNote.getFileName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/upload"))
                .header("Authorization", this.APIToken)
                .header("Content-Type", "application/octet-stream")
                .header("Dropbox-API-Arg", bodyText)
                .POST(HttpRequest.BodyPublishers.ofString(textNote.getFileTxt()))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {logger.log(Level.WARNING, "Unsuccessful connection");}
        assert response != null;
        return response.statusCode() == 200;
    }
}
