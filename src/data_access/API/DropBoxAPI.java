package data_access.API;

import java.util.logging.Logger;
/**
 * Abstract class API is the parent class for all Dropbox API endpoints.
 */
public abstract class DropBoxAPI {
    /**
     * String containing initial path for all http requestBody used in Http request.
     */
    private String requestBody;
    /**
     * Logger logs messages for all Exceptions in Dropbox API calls.
     */
    private Logger logger;
    /**
     * String containing the access token for all Dropbox API calls.
     */
    public static String APIToken = "Bearer sl.BrB_2EKluiq1dY-uFRSsGoDgS7ao4NIzyoVgok6-wO6z8ixPOdDyo53Fo64e6poE_7JUKXwO8y9KOILk5aqGhlQrnlp0M-q3rpkAlcY0BexvfnzJQW6qsGn-LLitnElg11ZMGyaDlTX-zaNcs0tNIa0";
    /**
     * Class to initialize request body and logger for use in all API subclasses.
     */
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
    public Logger getLogger() {
        return logger;
    }
    public String getRequestBody() {
        return requestBody;
    }
}
