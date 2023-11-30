package data_access.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.BqtQ_1DsOpfKtW0fE7-0pzCOLztdFicieH8kwKMRNsvcVeZD-yQRNRTRAb1wsE0jARh7v0JEWYEcDtNHxUzzNguPFV_llnkJNONI04JblgMgfkVtOWtV6DCBsyzyFMUzUuyJ3AS2Mmrdj2sI8B3Sfx4";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
