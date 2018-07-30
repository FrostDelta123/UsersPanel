package ru.frostdelta.myconsole.auth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EndSession {


    public void endSession(String username){

        try (Socket socket = new Socket("", 3345);

             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");

            while (!socket.isOutputShutdown()) {

                oos.writeUTF("EndSession");

                oos.writeUTF(username);

                break;
            }

            ois.close();
            oos.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
