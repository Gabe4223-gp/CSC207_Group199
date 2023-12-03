package data_access.API;

import java.util.logging.Logger;
/**
 * Abstract class API is the parent class for all Dropbox API endpoints.
 */
public abstract class DropBoxAPI {
    /**
     * String containing initial path for all http requestBody used in Http request.
     */
    public String requestBody;
    /**
     * Logger logs messages for all Exceptions in Dropbox API calls.
     */
    public Logger logger;
    /**
     * String containing the access token for all Dropbox API calls.
     */
    public String APIToken = "Bearer sl.BrAZ6fPAfUm5bDj_N4E2Nd_tEPHfToW0e5zRj1q0kWmYUlgPKfxu0RCZMBG3o8buAMtT3mNnE2xOzQhFmF-2399R04tDdbEaSNPOw8_ojmcuoHU0pKeNwRDe1DcQXEZJrOmRzVK21o4wyoo5KXKWNiU";
    /**
     * Class to initialize request body and logger for use in all API subclasses.
     */
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
