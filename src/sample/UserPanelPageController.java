package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.frostdelta.myconsole.auth.EndSession;
import ru.frostdelta.myconsole.AuthServer;


public class UserPanelPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Changeemail;

    @FXML
    private Button Changepass;

    @FXML
    private Button addkey;

    @FXML
    private Label ban;

    @FXML
    private Button goback;

    @FXML
    private Button lk_button;

    @FXML
    private Button msgadmin;

    @FXML
    private Label unban;

    @FXML
    private Label usergamemoney;

    @FXML
    private Label usermoney;


    AuthServer auth = new AuthServer();

    @FXML
    public Label usernick;


    public String nickname;



    public void links(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("links.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        links controller = (links) loader.getController();
        controller.nick = nickname;
        stage.setScene(new Scene(root1));
        stage.setTitle("Полезные сслыки");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();

    }


    public void lksite() {
        try {
            Desktop.getDesktop().browse(new URI("http://sv.ru-m.org/cabinet"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void modelpass(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changepass.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangepassController controller = (ChangepassController) loader.getController();
        controller.nick = nickname;
        stage.setScene(new Scene(root1));
        stage.setTitle("Change Password");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();

    }

    public void modelemail(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeemail.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangeemailController controller = (ChangeemailController) loader.getController();
        controller.nick = nickname;
        stage.setScene(new Scene(root1));
        stage.setTitle("Change Email");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();
    }


    public void modelAdmin(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("msg.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MsgController controller = (MsgController) loader.getController();
        controller.nick = nickname;
        stage.setScene(new Scene(root1));
        stage.setTitle("Обращение к администрации ");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();

    }

    public void gobacktoHome() {

        EndSession endSession = new EndSession();
        endSession.endSession(nickname);

        Stage stage = (Stage) goback.getScene().getWindow();
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
        stage.setResizable(false);
        stage.setTitle("SV.RU-M.ORG | Panel");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    public void addkey() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ВНИМАНИЕ!");
        alert.setContentText("Вы уверенны, что хотите добавить ключ? Изменить его уже будет нельзя!");
        alert.showAndWait();

        if(alert.getResult().getText().equals("OK")){

            AuthServer addKey = new AuthServer();

            addKey.addKey(nickname);

        }else alert.close();

    }

    @FXML
    void initialize() {

    }
}
