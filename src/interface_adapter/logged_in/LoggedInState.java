package interface_adapter.logged_in;

public class LoggedInState {

    private String username = "";

    public LoggedInState(LoggedInState loggedInState){
        username = loggedInState.username;
    }

    public LoggedInState(){}

    public String getUsername(){return username;}

    public void setUsername(String username){
        this.username = username;
    }
}
