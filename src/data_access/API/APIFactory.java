package data_access.API;

public class APIFactory {
    private APIFactory(){}
    public static CreateUserFolderPostAPI createUserAPI() {
        return new CreateUserFolderPostAPI();
    }
    public static DeleteDataPostAPI deleteAPI() {
        return new DeleteDataPostAPI();
    }
    public static DownloadUserFileGetAPI downloadFilesAPI() {
        return new DownloadUserFileGetAPI();
    }
    public static ListContentsUserFolderPostAPI getFilesAPI() {
        return new ListContentsUserFolderPostAPI();
    }
    public static UploadUserFilePostAPI uploadAPI() {
        return new UploadUserFilePostAPI();
    }
}
