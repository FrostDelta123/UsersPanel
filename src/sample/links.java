package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class links {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button forum;

    @FXML
    private Button moders;

    @FXML
    private Button discord;

    @FXML
    private Button vk;

    @FXML
    private Button lk;

    @FXML
    private Button key;


    public String nick;


    @FXML
    void initialize() {

    }

    public void lk() {
        try {
            Desktop.getDesktop().browse(new URI("http://sv.ru-m.org/cabinet"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void discord() {
        try {
            Desktop.getDesktop().browse(new URI("https://discordapp.com/invite/EqYUMy8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void vk() {
        try {
            Desktop.getDesktop().browse(new URI("https://vk.com/svrumorg"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void moderTable() {
        try {
            Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/1Xkj6U5wJFRj0k407MUbGn5fWhsZe1hywFO6VBAlLkMc"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setForum() {
        try {
            Desktop.getDesktop().browse(new URI("http://sv.ru-m.org/forum/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void keyInfo() {
        try {
            Desktop.getDesktop().browse(new URI("http://sv.ru-m.org/forum/index.php?/topic/9110-важно-гайд-по-использованию-панели-игрока/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
