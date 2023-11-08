package data_access;
import java.sql.*;

public class DBConnector {
    Connection connection;
    public DBConnector(){
        String url = "jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5659838";
        String username = "sql5659838";
        String password = "VesMSHc6rx";
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users");
            while (result.next()){
                System.out.println(result.getString(1) + " " + result.getString(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        DBConnector db = new DBConnector();
    }
}
