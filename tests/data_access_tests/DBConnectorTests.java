package data_access_tests;

import data_access.DBConnector;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DBConnectorTests {
    private DBConnector dbConnector;

    @Before
    public void init(){
        dbConnector = new DBConnector();
    }

    @Test
    public void testGetUserObj(){
        JSONObject obj = dbConnector.getUserObj("abc");
        assertEquals("abc", obj.get("Username"));
    }

    @Test
    public void createUserPass(){
        String testUser = "TestUser";
        String password = "TestPassword";
        assertTrue(dbConnector.createUser(testUser, password));
    }

    @Test
    public void createUserFail(){
        assertFalse(dbConnector.createUser("abc", "password"));
    }

    @Test
    public void testPasswordPass(){
        assertTrue(dbConnector.checkLoginCredentials("abc", "abc"));
    }

    @Test
    public void testPasswordFail(){
        assertFalse(dbConnector.checkLoginCredentials("abc", "sdjgfdsvsd"));
    }
    @Test
    public void testUserExists(){
        assertTrue(dbConnector.existsByName("abc"));
    }
    @Test
    public void testUserNotExists(){
        assertFalse(dbConnector.existsByName("dsfugkfg,flhadslkhjfd"));
    }

    @After
    public void clearDatabase(){
        try{
            Statement insertStatement = dbConnector.getConnection().createStatement();
            String sqlQuery = "DELETE FROM `Users` WHERE Users.Username = \"TestUser\"";
            insertStatement.execute(sqlQuery);
            dbConnector.dbClose();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
