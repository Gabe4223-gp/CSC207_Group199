package data_access;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class DBConnector {
    private Connection connection;
    private JSONArray allUsers = new JSONArray();
    public DBConnector(){
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
            System.out.println(e);
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
            if (userObj.get("Password").equals(password)){
                return true;
            }
            else {
                return false;
            }
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
            System.out.println(e);
            return false;
        }

    }

    public boolean existsByName(String username){
        JSONObject userObj = getUserObj(username);
        return !(userObj == null);
    }

    public void DBClose() throws SQLException {
        this.connection.close();
    }
}
