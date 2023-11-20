package data_access;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private Connection connection;
    private final JSONArray allUsers = new JSONArray();
    private Logger logger;
    public DBConnector(){
        logger = Logger.getLogger("DBConnectorLog");
        String url = "jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5659838";
        String username = "sql5659838";
        String password = "VesMSHc6rx";
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            /*
            * Convert the JDBC resultset to a JSONArray to be able to access data easily
            *
            * */
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()){
                int numColumns = resultSetMetaData.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= numColumns ; i++) {
                    String colName = resultSetMetaData.getColumnName(i);
                    obj.put(colName, resultSet.getObject(colName));
                }
                allUsers.put(obj);
            }

        }catch (Exception e){
            this.logger.log(Level.WARNING, e.getMessage());
        }
    }
    public JSONObject getUserObj(String username){
        for(Object obj: this.allUsers){
            JSONObject thisObj = (JSONObject)obj;
            String thisUsername = (String) thisObj.get("Username");
            if (thisUsername.equals(username)){
                return thisObj;
            }
        }
        return null;
    }

    public boolean checkLoginCredentials(String username, String password){
        JSONObject userObj = getUserObj(username);
        if(userObj == null){
            return false;
        }else {
            return userObj.get("Password").equals(password);
        }
    }

    public boolean createUser(String username, String password){
        JSONObject userObj = getUserObj(username);
        if(userObj != null){
            return false;
        }
        try{
            Statement insertStatement = this.connection.createStatement();
            String sqlQuery = "INSERT INTO `Users`(`Username`, `Password`, `Created_On`) VALUES ('"+
                    username + "','" +
                    password + "', " +
                    "CURRENT_TIMESTAMP())";
            insertStatement.execute(sqlQuery);
            return true;
        }catch (Exception e){
            this.logger.log(Level.WARNING, e.getMessage());
            return false;
        }
    }

    public boolean existsByName(String username){
        JSONObject userObj = getUserObj(username);
        return (userObj != null);
    }

    public void dbClose() throws SQLException {
        this.connection.close();
    }
}
