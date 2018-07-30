package ru.frostdelta.myconsole.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

    public static String pass;


    public PasswordEncryption(String text) {

        PasswordEncryption.pass = text;

    }


    public String passwordEncrypt() throws NoSuchAlgorithmException, ClassNotFoundException, InterruptedException {

        //Криптуем пассворд, если выбрана авторизация по паролю
        String original = pass;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();

        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }



        String or = sb.toString();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(or.getBytes());
        byte[] dig = md5.digest();

        StringBuffer sbf = new StringBuffer();

        for (byte x : dig) {
            sbf.append(String.format("%02x", x & 0xff));
        }

        return  sbf.toString();

    }

}
