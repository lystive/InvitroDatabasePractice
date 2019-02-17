package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
try {
    Class.forName("com.mysql.jdbc.Driver");
    try {
        String connectionString = "jdbc:mysql://localhost:3306/mydbtest";

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
    } catch (SQLException e) {
        System.out.println("Failed to create the database connection.");
    }
} catch (ClassNotFoundException e)
{
    System.out.println("Driver not found.");
}
return dbConnection;
    }


    public void signUpUser(User user){

String insert = "INSERT INTO "+ Const.USER_TABLE+" ("+ Const.USER_FIRSTNAME+"," +Const.USER_LASTNAME+","+Const.USER_USERNAME+
        ","+Const.USER_PASSWORD+","+Const.USER_LOCATION+","+Const.USER_GENDER+")"+" VALUES(?,?,?,?,?,?)";



        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstName());
            prSt.setString(2,user.getLastname());
            prSt.setString(3,user.getUsername());
            prSt.setString(4,user.getPassword());
            prSt.setString(5,user.getLocation());
            prSt.setString(6,user.getGender());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

   public ResultSet getUser(User user){
ResultSet resSet = null;

String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "  + Const.USER_USERNAME + " =? AND " + Const.USER_PASSWORD
        + " =?";

       try {

           PreparedStatement prSt = getDbConnection().prepareStatement(select);
           prSt.setString(1,user.getUsername());
           prSt.setString(2,user.getPassword());


  resSet = prSt.executeQuery();
       }  catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
       return resSet;
   }
}
