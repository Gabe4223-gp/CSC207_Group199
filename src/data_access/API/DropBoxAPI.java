package data_access.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.Bq6hTBIi25Xd0FCRkSBGb5PTIYbtiowMkxPaot_P0fsDVV4OP9vufBPS4pfaTJwNlhpbq7tDQC9XkpGedLQCI1jsUl9AMbWkdUV6KcOlmh0HDvU7YIn1MuV8RPR9WDXVRyCiAFWi1saSP3f5n-QDX-I";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
