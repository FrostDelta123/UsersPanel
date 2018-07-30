package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.frostdelta.myconsole.AuthServer;


public class MsgController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea msgTextArea;

    @FXML
    private Button sendMsgButton;

    @FXML
    private TextField subjectTextField;

    public String nick;


    @FXML
    void initialize() {

    }

    public void sendMessage() throws InterruptedException {
        AuthServer sendMsg = new AuthServer();

        if(msgTextArea.getLength() < 1000 && msgTextArea.getLength() > 50){

            sendMsg.sendMessage(nick, msgTextArea.getText(), subjectTextField.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Успешно!");
            alert.setContentText("Обращение отправлено!");
            alert.showAndWait();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Текст должен быть длиннее 50 и короче 1000 символов!");
            alert.showAndWait();
        }
    }

}
