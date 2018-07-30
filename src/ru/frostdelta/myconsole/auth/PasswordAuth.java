package ru.frostdelta.myconsole.auth;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class PasswordAuth {

    public static String username;
    public static String password;


    public PasswordAuth(String text) {

        PasswordAuth.username = text;

    }

    public static void authConnect() throws InterruptedException, ClassNotFoundException {

        System.out.println(password);

        //Сообщаем серверу введенный пароль, ждём проверки и ответа

        //Метод авторизции по паролю
        try(Socket socket = new Socket("", 3345);


            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("Client connected to socket.");
            while(!socket.isOutputShutdown()){

                oos.writeUTF("passauth");
                //oos.flush();

                oos.writeUTF(username);
                oos.writeUTF(password);
                boolean isCorrect = ois.readBoolean();

                if(isCorrect == true){

                    //Результат, если пароль верный.
                    JOptionPane.showMessageDialog(null, "Хуй там, менюшки пока нет, зато есть подсказка: подключаешься к 5.9.74.186 с портом 3345 и отсылаешь true. Подсказка твоя", "лул", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else {JOptionPane.showMessageDialog(null, "Неправильные имя пользователя или пароль!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    break;
                }

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
