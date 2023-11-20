package data_access.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UploadUserFilePostAPI {
    public static void main(String[] args) {
        //sample usage without access to User Data file
        String username = "temp_user";
        String filename = "temp_file";
        String requestBodyText = String.format("{\"path\": \"/NoDraw_folder/%s/%s.txt\", \"mode\": {\".tag\": \"overwrite\"},\"autorename\": false, \"mute\": false}", username, filename);
        String requestBodyImg = String.format("{\"path\": \"/NoDraw_folder/%s/%s.png\", \"mode\": {\".tag\": \"overwrite\"},\"autorename\": false, \"mute\": false}", username, filename);

        //TODO: delete later (only to show working connection with DropBox), Note token lasts only 4 hrs.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/upload"))
                .header("Authorization", "Bearer sl.BqOkyVZJOOt8aySCiTcunvi9NrA_88NfqOJw7fzF1RaK6GWaSJ3Zw_E7oe2vF5MqQgeAmsL2z9PciCSepia-FLVKYb-oPz48glcSoV0lHXGchLlYezWqU7SZSzTJgwvwC_zA7jdASjUBDI4YM6vCQDE")
                .header("Content-Type", "application/octet-stream")
                .header("Dropbox-API-Arg", requestBodyText)
                .POST(HttpRequest.BodyPublishers.ofString("Hello")) //TODO Replace body with a file reader
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());

        //convert filename into string
        /*
        String fileName = file.toString();
        int index  = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
        }

         */
        //temp string variable
        /*
        String extension = "txt";
        if (extension.equals("txt")) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.dropboxapi.com/2/files/upload"))
                    .header("Authorization", "Bearer sl.BqKinTOcybd1f_ahULf1oxDWZEk-bDtxnB8yP0deF3HvDbdeuSLaMe-tcVtNwFCriKvUEdajHRXKjDBHuriqNae9hmJtJmnb-kikGlQQ8bBqE70H9VkA1vxtrDTcDCQcZOiRH_yvrVimwkNOPPz4BzE")
                    .header("Content-Type", "application/octet-stream")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBodyText))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
        else {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.dropboxapi.com/2/files/upload"))
                    .header("Authorization", "Bearer sl.BqKinTOcybd1f_ahULf1oxDWZEk-bDtxnB8yP0deF3HvDbdeuSLaMe-tcVtNwFCriKvUEdajHRXKjDBHuriqNae9hmJtJmnb-kikGlQQ8bBqE70H9VkA1vxtrDTcDCQcZOiRH_yvrVimwkNOPPz4BzE")
                    .header("Content-Type", "application/octet-stream")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBodyImg))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }

         */
    }
}
