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
                .header("Authorization", "Bearer sl.BqS7NdIinYe3jAWStgz7TaKaHE9qWU1Oo5449PUI2OQgGhepcRS6ziaKjpSIpV94x5pH-tPClT40XOITQH7UhrDCkQOVapDkWj7-pa1iSN343YsqI5gh8hCLNI69ZyoFnRwcyZIeba3yCxfn4yuivog")
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
