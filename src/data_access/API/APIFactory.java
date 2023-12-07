package data_access.API;
/**
 * Class using factory design pattern for Dropbox API calls, allowing use-cases and
 * tests to create a call to the necessary API.
 */
public class APIFactory {
    /**
     * Initializes API factory class.
     */
    private APIFactory(){}
    /**
     * Method creates a new CreateUserFolderPostAPI.
     * @return a CreateUserFolderPostAPI.
     */
    public static CreateUserFolderPostAPI createUserAPI() {
        return new CreateUserFolderPostAPI();
    }
    /**
     * Method creates a new DeleteDataPostAPI.
     * @return a DeleteDataPostAPI.
     */
    public static DeleteDataPostAPI deleteAPI() {
        return new DeleteDataPostAPI();
    }
    /**
     * Method creates a new DownloadUserFileGetAPI.
     * @return a DownloadUserFileGetAPI.
     */
    public static DownloadUserFileGetAPI downloadFilesAPI() {
        return new DownloadUserFileGetAPI();
    }
    /**
     * Method creates a new ListContentsUserFolderPostAPI.
     * @return a ListContentsUserFolderPostAPI.
     */
    public static ListContentsUserFolderPostAPI getFilesAPI() {
        return new ListContentsUserFolderPostAPI();
    }
    /**
     * Method creates a new UploadUserFilePostAPI.
     * @return a UploadUserFilePostAPI.
     */
    public static UploadUserFilePostAPI uploadAPI() {
        return new UploadUserFilePostAPI();
    }
}
