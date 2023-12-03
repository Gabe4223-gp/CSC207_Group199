package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import entity.TextNote;

public class UploadUserFilePostAPI extends DropBoxAPI {
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
