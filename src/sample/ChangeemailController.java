package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.frostdelta.myconsole.AuthServer;


public class ChangeemailController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField new_email;

    @FXML
    private TextField old_email;

    @FXML
    private Button updateButton;

    public String nick;


    @FXML
    void initialize() {

    }


    public void changeMail() throws InterruptedException {

        AuthServer changeMail = new AuthServer();

        changeMail.changeEmail(new_email.getText(), nick);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Успешно!");
        alert.setContentText("Почта изменена!");
        alert.showAndWait();

    }
}
