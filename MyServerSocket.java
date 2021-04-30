import java.io.IOException;
import java.net.ServerSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author oussama
 */
public class MyServerSocket {
    private ServerSocket serverSocket;

    public MyServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket accept() {
        MySocket client = null;
        try {
            client = new MySocket(this.serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;

    }
}
