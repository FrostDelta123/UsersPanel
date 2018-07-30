package ru.frostdelta.myconsole;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class Network {

    DataOutputStream out;
    DataInputStream in;
    Socket s;
    HashMap<Boolean, String> auth = new HashMap<Boolean, String>();

    public static String clientCommand;
    public static String type;
    public static String key;
    public static String rule;
    public static String name;

    GetKey gk = new GetKey();



    public Network(String command, String id, String command1) {

        Network.type = command;
        Network.key = id;
        Network.clientCommand = command1;
    }

    public Network() {

    }

    public Network(String ban, String key, String rule, String player) {

        Network.type = ban;
        Network.key = key;
        Network.rule = rule;
        Network.name = player;

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



    public void connect(String host, int port) throws InterruptedException {

        System.out.println("called");
        try {
            try(Socket socket = new Socket(host, port);

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream())
            )
            {
                in = ois;
                out = oos;
                s = socket;
                System.out.println("Client connected to socket." + "\n");
                System.out.println("Client writing channel = oos & reading channel = ois initialized.");

                oos.writeUTF(getUserKey());
                oos.flush();

                /*
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ВНИМАНИЕ, Я УБРАЛ oos.flush(), ЕСЛИ НЕ РАБОТАЕТ - ДОБАВИТЬ
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                 */

                while(!socket.isOutputShutdown()) {

                    Thread.sleep(1000);
                    if(type != null) {
                        System.out.println("debug");
                        switch (type) {
                            case "ban":
                                oos.writeUTF(type);
                                oos.writeUTF(key);
                                oos.writeUTF(rule);
                                oos.writeUTF(name);
                                JOptionPane.showMessageDialog(null, "Комманда успешно выполнена", "Успешно!", JOptionPane.INFORMATION_MESSAGE);
                                type = null;
                                break;
                            case "unban":
                                oos.writeUTF(type);
                                oos.writeUTF(key);
                                oos.writeUTF(clientCommand);
                                JOptionPane.showMessageDialog(null, "Комманда успешно выполнена", "Успешно!", JOptionPane.INFORMATION_MESSAGE);
                                type = null;
                                break;
                            case "command":
                                oos.writeUTF(type);
                                oos.writeUTF(key);
                                oos.writeUTF(clientCommand);
                                JOptionPane.showMessageDialog(null, "Комманда успешно выполнена", "Успешно!", JOptionPane.INFORMATION_MESSAGE);
                                type = null;
                                break;
                        }
                    }
                }
                System.out.println("Server denied this connection!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void closeConnection(DataInputStream in, DataOutputStream out, Socket socket){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Connection closed!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
