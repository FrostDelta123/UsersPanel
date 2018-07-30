package ru.frostdelta.myconsole;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class GroupPermissions {


    public String id;
    GetKey gk = new GetKey();
    OldGUI OldGUI = new OldGUI();

    public String type;
    public String name;



    //Получаем версию программы для нужной группы пльзователя

    public void auth() throws InterruptedException {
        gk.getMotherboardSN();
        try {

            //На клиенте получаем его группу и выдаём нужную версию
            final String key = gk.getHWID();
            id = key;

            switch (type){

                case "moder":
                    JOptionPane.showMessageDialog(null, "Добро пожаловать, " + name, "Авторизация", JOptionPane.INFORMATION_MESSAGE);
                    OldGUI.openModerWindow();
                    break;
                case "grandmoder":
                    JOptionPane.showMessageDialog(null, "Добро пожаловать, " + name, "Авторизация", JOptionPane.INFORMATION_MESSAGE);
                    OldGUI.openGrandModerWindow();
                    break;
                case "admin":
                    JOptionPane.showMessageDialog(null, "Добро пожаловать, " + name, "Авторизация", JOptionPane.INFORMATION_MESSAGE);
                    OldGUI.openAdminWindow();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "У вас нет доступа!", "Авторизация", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
