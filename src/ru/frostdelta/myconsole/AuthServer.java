package ru.frostdelta.myconsole;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import sample.LoginKeyPageController;
import sample.LoginPageController;
import sample.UserPanelPageController;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public class AuthServer {

    private boolean isCorrect;

    public Label label;

    public final String version = "1.1";

    Updater update = new Updater();


    public void authPassConnect(String name, String hash, LoginPageController obj, UserPanelPageController uppc) throws InterruptedException, ClassNotFoundException {

        GroupPermissions gp = new GroupPermissions();

        try(Socket socket = new Socket("5.9.74.186", 3345);


            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("Client connected to socket.");
            while(!socket.isOutputShutdown()){


                oos.writeUTF("passAuth");
                oos.flush();

                oos.writeUTF(name);

                oos.writeUTF(hash);

               isCorrect = ois.readBoolean();
                break;

            }


            ois.close();
            oos.close();
            socket.close();

            if (isCorrect == true){


                update.checkUpdate(version);

                obj.goUserPanelPage(name);

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка авторизации!");
                alert.setContentText("Неверный логин/пароль!");
                alert.showAndWait();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void authKeyConnect(String name, LoginKeyPageController obj, UserPanelPageController uppc, Label label) throws InterruptedException, ClassNotFoundException {

        try(Socket socket = new Socket("", 3345);


            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("Client connected to socket.");
            while(!socket.isOutputShutdown()){
                GetKey gk = new GetKey();

                oos.writeUTF("keyAuth");
                oos.flush();


                gk.getMotherboardSN();
                oos.writeUTF(gk.getHWID());

                oos.writeUTF(name);

                isCorrect = ois.readBoolean();

                if (isCorrect == true){


                    update.checkUpdate(version);

                    obj.goUserPanelPage(name);


                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка авторизации!");
                    alert.setContentText("Неверный ключ! Попробуйте вход с помощью пароля!");
                    alert.showAndWait();
                }

                break;

            }


            //Метод на авторизацию/отказ авторизации




        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void changeEmail(String email, String username) throws InterruptedException {

        try (Socket socket = new Socket("", 3345);

             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {

                oos.writeUTF("changeEmail");

                oos.writeUTF(username);

                oos.writeUTF(email);

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


    public void addKey(String username) {

        try (Socket socket = new Socket("", 3345);

             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {

                oos.writeUTF("addKey");

                oos.writeUTF(username);

                GetKey getKey = new GetKey();

                getKey.getMotherboardSN();

                oos.writeUTF(getKey.getHWID());


                if (ois.readBoolean() == true){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Успешно!");
                    alert.setContentText("Ключ записан! Авторизация по ключу активирована!");
                    alert.showAndWait();

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setContentText("Вы уже привязывали ключ, если хотите сменить - обратитесь к администратору!");
                    alert.showAndWait();
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String username, String msg, String theme) throws InterruptedException {

        try (Socket socket = new Socket("", 3345);

             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {

                oos.writeUTF("sendMsg");

                oos.writeUTF(username);

                oos.writeUTF(msg);

                oos.writeUTF(theme);

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

    public void changePass(String passHash, String username) throws InterruptedException {

        try (Socket socket = new Socket("", 3345);

             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {

                oos.writeUTF("changePass");

                oos.writeUTF(username);

                oos.writeUTF(passHash);

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