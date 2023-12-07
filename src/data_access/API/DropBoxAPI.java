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
    public static String APIToken = "Bearer sl.BrSCgUAdTkZzNumKpvk0Htr2oAhK9YoyYI2yT_cWQyKuF4sFuoHNYflOiG7mwdWVr0MGxacsqJs6Ryl31GyRcdIp1p3bRbl9sAB3K_VjXzdmMMIgCVRHf8d2ZuXuIvoU24bsT7a-a1knkX4JGlPQWmQ";
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
