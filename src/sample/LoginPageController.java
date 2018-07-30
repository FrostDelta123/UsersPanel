package sample;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.frostdelta.myconsole.auth.PasswordEncryption;
import ru.frostdelta.myconsole.AuthServer;


public class LoginPageController {

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
    private PasswordField key_textField;

    @FXML
    private TextField login_textField;

    @FXML
    private  PasswordField password_textField;

    public final String version = "1.0";



    public void goAuthkey() {
        Stage stage = (Stage) auth_button.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginKeyPage.fxml"));
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

    public void passwordAuth() throws ClassNotFoundException, InterruptedException, NoSuchAlgorithmException {
        AuthServer auth = new AuthServer();
        UserPanelPageController uupc = new UserPanelPageController();

        PasswordEncryption encryption = new PasswordEncryption(password_textField.getText());

        System.out.println(encryption.passwordEncrypt());
        auth.authPassConnect(login_textField.getText(), encryption.passwordEncrypt(), this, uupc);
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
