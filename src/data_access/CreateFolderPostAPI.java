package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CreateFolderPostAPI {
    public static void main(String[] args) throws Exception
    {
        //Host url
        //String host = "https://dropboxstefan-skliarovv1.p.rapidapi.com";
        //String charset = "UTF-8";
        //Headers for a request
        //String rapidapi_host = "dropboxstefan-skliarovv1.p.rapidapi.com";
        //String rapidapi_key = "hntjv6w1xb5a251";
        //Param
        //String s = "Pulp";
        //Formatting for encoding problems
        //String query = String.format("s=%s", URLEncoder.encode(s, charset));
        String requestBody = "{\"path\": \"/NoDraw_folder\", \"autorename\": false}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/create_folder_v2"))
                .header("Authorization", "Bearer uat.AB3Ok7pNBJEtRIQmsrM-aNQaOaptNp2VUFeW34hq1eBx_ONL4vzWgOZj0rF8gL48oE33o0XGRf9yxOxSQpEHnNiHvUw3c0D0hshpawTZid5PnFl4pPaV8-mBaGqEVHSqSBLN91w1gG38FTl6iynsZnAnB7LzqB5WDo_AZHRvlFSuuew8WBsOMPUiRWJckcF5KmrFn93NtefrptQ1joFNXvLXMHeN7D2REI82n1s4kW8a1mJ8-azAl469RfTZplDEz1wt6j32q-6f_9E-RbBtqsimNRrOyCCp-TQ6YADTe6XEP_ZjF4dR5UOpfW85UwgoV1hTSiXkCp2csJeGG0-tDNKil44_7GFGySN3KEEMTbp54OHPLtsBSeYTlACJi0GVOPjB8Lx86BBFlrrhgxLSBQyB12GKHyVxQUmMBNOtmpkOLTg-wU-5uMexPtJxoTqo2cBBGt6Uk58_l5RyB2r52a1VRZE5Rrx_AWeMeKOKWn7YCUKpEXTSq8JIF2pMvzpHcg-m-xk-N_apY9TVbYcBte3mfgdR1t7JBKZ6byrOsW1EKXihl94I6bzFgtKeErumPzg4IZ5pg152LlZTYE3Nt2q8NbeiUWlthommejiVJfMGAvCHVE8zc8J4pN8n3J4FF1OCoKDiHRqD_GgVMV1PZwAGaIddJVWULtjCIv1I4sGYskH0p9XJurxQtXuUgmF5bHz3oeWTcex9AzKJ45wi1uePyr6Brh1wIXm2DVka0RCfB0czFmOkSEo1-bTe7Cv9ZtFQW8zZGZ2WFxP327oAsD7sYylCXmqMqosfP2jAoyjm3f166Jr8TFkW_IzAIOe_wmBO4y6YKcMCRbyhuway8jCczk_Ln9ggYNCbyjJYQNjAKX2RkhuEEmPum6Y8wEghwhEsuRbSgW5fWEoP8bBYNr-6Lf9Y4qqAhLxcvdz1mRSGMcDLJZoCRXGaA8LNuw3McCzsrlrFggJ-3niTbMfrQ9iQzhUj22uJrHomhYBvs3zifsdIBaRlPIyl3B1lRjA5kZ4157ROs8JwU9vo4Xaah5Q86qJjk7y-zpBFOBPYc5cxvg5HU9fuVqNzc0lnuLNKT0pgrECmWhgbJllk2pn61-Wq5k3oxb-3IrukeH48EnzgsNzGu5znWXZXj-wltb4RrfD4jWRge6mXZ-9zNKrps_4q_iH-ifCFq-8MkP59Nr71crS_uRycmZaaeqIE3Z-jYOQhWTusLeNv7NpxzG-2uSBul608JhH_mfFnapVnAitupsrrbHF45Xk5uFAbKysEYtQ")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                //.header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
                //.header("X-RapidAPI-Host", "Dropboxstefan-skliarovV1.p.rapidapi.com")
                //.method("POST", HttpRequest.BodyPublishers.ofString("folderPath=%3CREQUIRED%3E&accessToken=%3CREQUIRED%3E"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}