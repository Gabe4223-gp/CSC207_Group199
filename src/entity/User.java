package entity;

public class User {
    private String username;
    private String password;


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String newUsername){
        username = newUsername;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public String getPassword(){
        return password;
    }

}
