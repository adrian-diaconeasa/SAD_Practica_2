import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author oussama
 */

public class Client {
    public static final int PORT = 1234;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        MySocket clientSocket = new MySocket(HOST, PORT);
        //INPUT
        new Thread(() -> {
            try {
                String line;
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                while ((line = input.readLine()) != null) {
                    clientSocket.printLine(line);
                }
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

        //OUTPUT    
        new Thread(() -> {
            String line;
            while ((line = clientSocket.readLine()) != null) {
                System.out.println(line);
            }
        }).start();

    }
}
