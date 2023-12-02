package data_access.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.Bq_9zFA1OM9Nt1fpdUkpL7Nm4A5xLTVQWgFhexWaggUIZvY4hm-sfzFNpfB3QyCAz7sLLKSZcJjtbkuvBZdaMsngTPTS4s1lSl8wGKIrBrm-zJySYrvKDXuXSLC-UVlH-mFU94O9bnRY4Wc46Gs0RD0";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
