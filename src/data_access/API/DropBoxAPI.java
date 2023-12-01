package data_access.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.Bq7Ry7iW_8fiNf9plDCG3dFyEuAM5Rient9uGpb0d3W59bUI_zI511G8uECW_NKfBAe874hA8-gaEgxPLMR-K33htTP8GXlFy-NoTDzpXDA1EuFJuAedpAzpdeUfqNR5wy99wwUnJScmhoTjbJwJHEM";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
