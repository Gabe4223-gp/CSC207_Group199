package data_access.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.Bq3b-ggyXotr6NnKZBSnuDlmrjc_UWaD8RxRO2w46q1otAIFvlIRXnzWlU5FjgtVypb-d6BJdUGNKLTkl8nwghQAGGsAQu34Co0ZX6PEQnPWwWGAXl8O_h9d0sPpkxCz18s1IQ-vvpEsWKy4C5aOlbE";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
