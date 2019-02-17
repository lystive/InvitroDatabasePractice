package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    public static int ID;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button RegisterButton;

    @FXML
    void initialize() throws Exception{

        signUpButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = PasswordField.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")){

                loginUser(loginText,loginPassword);

            }
            else
                System.out.println("Login and Password is Empty!");
        });

        RegisterButton.setOnAction(event -> {
            RegisterButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
accController acc = new accController();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
       ResultSet result =  dbHandler.getUser(user);
String id= null;
       int counter = 0;

  try {
      while (result.next()) {
          counter++;
          ID = result.getInt(1);

      }

  }catch (SQLException e){
      e.printStackTrace();
  }

    if (counter >= 1) {
        System.out.println("Success!");
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/acc.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();


    }



    }

    public int getID () {
        return this.ID;
    }





}