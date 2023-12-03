package data_access.API;

import java.util.logging.Logger;

public abstract class DropBoxAPI {
    public String requestBody;
    public Logger logger;
    public String APIToken = "Bearer sl.BrAZ6fPAfUm5bDj_N4E2Nd_tEPHfToW0e5zRj1q0kWmYUlgPKfxu0RCZMBG3o8buAMtT3mNnE2xOzQhFmF-2399R04tDdbEaSNPOw8_ojmcuoHU0pKeNwRDe1DcQXEZJrOmRzVK21o4wyoo5KXKWNiU";
    protected DropBoxAPI(){
        requestBody = "{\"path\": \"/NoDraw_folder/";
        logger = Logger.getLogger("APIConnectionLog");
    }
}
