package sample;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import ru.frostdelta.myconsole.auth.PasswordEncryption;
import ru.frostdelta.myconsole.AuthServer;


public class ChangepassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField change_pass;

    @FXML
    private PasswordField new_pass;

    @FXML
    private PasswordField old_pass;

    @FXML
    private Button updateButton;

    public String nick;


    @FXML
    void initialize() {

    }




    public void changePassword() throws InterruptedException, NoSuchAlgorithmException, ClassNotFoundException {

        if (change_pass.getText().equals(new_pass.getText())){
            if(change_pass.getText().length() > 6){

                AuthServer changePass = new AuthServer();

                PasswordEncryption encryption = new PasswordEncryption(change_pass.getText());


                changePass.changePass(encryption.passwordEncrypt(), nick);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Успешно!");
                alert.setContentText("Пароль успешно изменён!");
                alert.showAndWait();


            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setContentText("Пароль должен быть длиннее 6 символов!");
                alert.showAndWait();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Введенные пароли не совпадают!");
            alert.showAndWait();
        }

    }


}
