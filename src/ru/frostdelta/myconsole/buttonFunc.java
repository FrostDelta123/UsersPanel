package ru.frostdelta.myconsole;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class buttonFunc {

    GetKey gk = new GetKey();


    //вообще useless
    public void banButton(String rule, String player) throws InterruptedException {
        String key = getUserKey();
        Thread.sleep(1000);
        Network nw = new Network("ban",key, rule, player);

    }

    public void  unbanButton(String player) throws InterruptedException {
        String key = getUserKey();
        Thread.sleep(1000);
        Network nw = new Network("unban", key, player);
    }

    public void adminCommand(String command) throws InterruptedException {
        String key = getUserKey();
        Thread.sleep(1000);
        Network nw = new Network("command", key, command);
    }


    public String getUserKey(){
        gk.getMotherboardSN();
        try {
            return gk.getHWID();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
