package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField signUpName;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField signUpCountry;

    @FXML
    private CheckBox signUpCheckBoxMaile;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private TextField sighUpLastname;

    @FXML
    void initialize() throws Exception {

        DatabaseHandler dbHandler = new DatabaseHandler();
        signUpButton.setOnAction(event -> {
             String firstName = signUpName.getText();
             String lastname = sighUpLastname.getText();
             String username = loginField.getText();
             String password = PasswordField.getText();
             String location = signUpCountry.getText();
             String gender = "";
             if(signUpCheckBoxMaile.isSelected())
                 gender = "Male";
             else
                 gender="Female";

             User user = new User(firstName,lastname,username,password,location,gender);

            dbHandler.signUpUser(user);
            signUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/app.fxml"));
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

}
