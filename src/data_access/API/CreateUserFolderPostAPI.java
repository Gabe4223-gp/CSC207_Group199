package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CreateUserFolderPostAPI {
    public static void main(String[] args) {
        ////sample usage without access to User Data file
        String username = "temp_user";
        String requestBody = String.format("{\"path\": \"/NoDraw_folder/%s\", \"autorename\": false}", username);

        //TODO: Note token lasts only 4 hrs.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/create_folder_v2"))
                .header("Authorization", "Bearer sl.BqOkyVZJOOt8aySCiTcunvi9NrA_88NfqOJw7fzF1RaK6GWaSJ3Zw_E7oe2vF5MqQgeAmsL2z9PciCSepia-FLVKYb-oPz48glcSoV0lHXGchLlYezWqU7SZSzTJgwvwC_zA7jdASjUBDI4YM6vCQDE")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
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

//Response reference in case needed to change: https://openjdk.org/groups/net/httpclient/recipes.html