package data_access.API;

import data_access.file_read_write.FileAccessDAO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import entity.TextNote;

public class UploadUserFilePostAPI extends DropBoxAPI {
    public boolean uploadUserFile(String username, TextNote textNote) {

        String bodyText = requestBody + String.format("%s/%s.txt\", \"mode\": {\".tag\": \"overwrite\"},\"autorename\": false, \"mute\": false}", username, textNote.getFileName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/upload"))
                .header("Authorization", "Bearer sl.Bqq-rMbDu9127dPnp1TuACKCB5NSOtoVcOSYT1A-ZcKIMB00AhnfEXWVjkLOtWZRXXx9Dd5_VaBAScvK14jU7OSCdxWAJzU0nJCSeqaWMNoheITdBG-TPrF6hkNImnDMnD-BsThIiQTZjafDl-33vB4")
                .header("Content-Type", "application/octet-stream")
                .header("Dropbox-API-Arg", bodyText)
                .POST(HttpRequest.BodyPublishers.ofString(textNote.getFileTxt()))
                .build();
        HttpResponse<?> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
            logger.log(Level.INFO, "%s file successfully uploaded.", username);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.WARNING, "Unsuccessful connection");
        }
        assert response != null;
        return response.statusCode() == 200;
    }
}
