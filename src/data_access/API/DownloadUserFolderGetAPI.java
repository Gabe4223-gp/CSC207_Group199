package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DownloadUserFolderGetAPI {
    public static void main(String[] args) {
        //TODO: Note token lasts only 4 hrs.

        String username = "temp_user";
        String requestBody = String.format("{\"path\": \"/NoDraw_folder/%s\"}", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/download_zip"))
                .header("Authorization", "Bearer sl.BqOkyVZJOOt8aySCiTcunvi9NrA_88NfqOJw7fzF1RaK6GWaSJ3Zw_E7oe2vF5MqQgeAmsL2z9PciCSepia-FLVKYb-oPz48glcSoV0lHXGchLlYezWqU7SZSzTJgwvwC_zA7jdASjUBDI4YM6vCQDE")
                .header("Dropbox-API-Arg", requestBody)
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }
}
