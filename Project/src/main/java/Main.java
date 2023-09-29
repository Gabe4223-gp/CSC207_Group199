import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fruit = "Banana";
        OkHttpClient client =new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().
                url(String.format("https://www.fruityvice.com/api/fruit/" + fruit)).build();
        try{
            Response response =client.newCall(request).execute();
            System.out.println(response.body().string());
        }catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }

    }
}