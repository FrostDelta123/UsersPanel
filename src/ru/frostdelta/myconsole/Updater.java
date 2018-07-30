package ru.frostdelta.myconsole;

import javafx.scene.control.Alert;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class Updater {


    public void checkUpdate(String version) throws InterruptedException, ClassNotFoundException {

        GroupPermissions gp = new GroupPermissions();

        try(Socket socket = new Socket("", 3345);


            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("Client connected to socket.");
            while(!socket.isOutputShutdown()){


                oos.writeUTF("checkUpdate");
                oos.flush();

                oos.writeUTF(version);

                final boolean isUpdate = ois.readBoolean();

                while (true) {
                    if (isUpdate == false) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка авторизации!");
                        alert.setContentText("Вы используете устаревшую версию программы! Нажмите OK для скачивания новой версии! ");
                        alert.showAndWait();

                        if (alert.getResult().getText().equalsIgnoreCase("OK")) {

                            try {
                                Desktop.getDesktop().browse(new URI("http://sv.ru-m.org/launch/UserPanel.jar"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }

                            System.exit(0);

                        }

                    }else break;
                }
                break;
            }


            ois.close();
            oos.close();
            socket.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
