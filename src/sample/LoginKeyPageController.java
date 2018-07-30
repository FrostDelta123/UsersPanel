package sample;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.frostdelta.myconsole.AuthServer;
import ru.frostdelta.myconsole.GetKey;


public class LoginKeyPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button auth_button;

    @FXML
    private Button back_button;

    @FXML
    private Label error_label;

    @FXML
    private TextField key_textField;

    @FXML
    private Button view_button;


    public void goLoginPage() {
        Stage stage = (Stage) auth_button.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("SV.RU-M.ORG");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void keyAuth() throws ClassNotFoundException, InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException {
        AuthServer auth = new AuthServer();
        UserPanelPageController uupc = new UserPanelPageController();


        GetKey gk = new GetKey();
        gk.getMotherboardSN();
        System.out.println(gk.getHWID());

        Label label = new Label();


        auth.authKeyConnect(key_textField.getText(), this, uupc, label);

    }




    public void goUserPanelPage(String name) {

        Stage stage = (Stage) auth_button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPanelPage.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserPanelPageController controller = (UserPanelPageController) loader.getController();
        controller.usernick.setText(controller.usernick.getText() + " " + name);
        controller.nickname = name;
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("SV.RU-M.ORG | Panel");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
