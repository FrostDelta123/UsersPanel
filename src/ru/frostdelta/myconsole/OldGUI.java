package ru.frostdelta.myconsole;


import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class OldGUI extends JComponent {


    //Бесполезно вдвойне
    AuthServer a = new AuthServer();
    Network net = new Network();
    public static String ip;
    public static int port;
    private static ArrayList<String> list;
    public String dev;

    public OldGUI(String ip, int port) {
        OldGUI.ip = ip;
        OldGUI.port = port;
    }

    public OldGUI() {

    }


    public OldGUI(ArrayList<String> list) {
        OldGUI.list = list;
    }

    public static void main(String args[]) throws InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException {


        //Открыть гуишку с майн менюшкой


    }

    buttonFunc bf = new buttonFunc();





    public void openModerWindow() throws InterruptedException {

        dev = (String)JOptionPane.showInputDialog(null,"Выберите сервер","Список",JOptionPane.PLAIN_MESSAGE,null, list.toArray(),null);

        //a.selectServer(dev);

        runAsynchronouslyConnect(net,ip, port);

        String nick = JOptionPane.showInputDialog(null, "Ник игрока под бан","NickName",JOptionPane.INFORMATION_MESSAGE);
        String punkt = JOptionPane.showInputDialog(null, "Правило","Rule",JOptionPane.INFORMATION_MESSAGE);
        bf.banButton(punkt, nick);

    }

    public void openGrandModerWindow() throws InterruptedException {


        dev = (String)JOptionPane.showInputDialog(null,"Выберите сервер","Список",JOptionPane.PLAIN_MESSAGE,null, list.toArray(),null);

        //a.selectServer(dev);

        runAsynchronouslyConnect(net,ip, port);

        String select_num = JOptionPane.showInputDialog("Выберите: " + "\n"

                + "1)Бан" + "\n" + "2)Разбан");
        switch (select_num) {
            case "1":
                String nick = JOptionPane.showInputDialog(null, "Ник игрока под бан","NickName",JOptionPane.INFORMATION_MESSAGE);
                String punkt = JOptionPane.showInputDialog(null, "Правило","Rule",JOptionPane.INFORMATION_MESSAGE);
                bf.banButton(punkt, nick);
                break;


            case "2":
                String nick2 = JOptionPane.showInputDialog(null, "Ник игрока под разбан","NickName",JOptionPane.INFORMATION_MESSAGE);
                bf.unbanButton(nick2);
                break;
        }

    }

    public void openAdminWindow() throws InterruptedException {

        dev = (String)JOptionPane.showInputDialog(null,"Выберите сервер","Список",JOptionPane.PLAIN_MESSAGE,null, list.toArray(),null);

        //a.selectServer(dev);

        runAsynchronouslyConnect(net,ip, port);

        String in = JOptionPane.showInputDialog(null, "Команда","Command",JOptionPane.INFORMATION_MESSAGE);
        bf.adminCommand(in);
    }



    static void runAsynchronouslyConnect(final Network obj, final String host, final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.connect(host, port);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
