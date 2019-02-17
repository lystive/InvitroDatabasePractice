package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accController {

public int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @FXML
    public Label yourName;

    @FXML
    private Label lastName;

    @FXML
    private Label accname;

    @FXML
    private Button show;

    @FXML
    public void initialize (){

        showInfo();


    }

    void showInfo (){
DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet resSet = null;

ID=MainController.ID;

        String select = "SELECT * FROM users WHERE idusers=?";

        try {

            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(select);
            prSt.setInt(1,ID);



            resSet = prSt.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

String name = null;
        String yourname=null;
        String lastname = null;

        try {
            while (resSet.next()) {
                name = resSet.getString(4);
                yourname = resSet.getString(2);
                lastname = resSet.getString(3);
                accname.setText(name);
                yourName.setText(yourname);
                lastName.setText(lastname);
            }
        }catch (SQLException e) {

        }
        System.out.println(name);
    }


}



