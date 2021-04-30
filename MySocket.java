import java.io.*;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author oussama
 */
public class MySocket extends Socket {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public MySocket(String hostName, int port) {
        try {
            this.socket = new Socket(hostName, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.err.println("Couldn't create the socket");
            e.printStackTrace();
        }
    }

    public MySocket(Socket s) {
        try {
            this.socket = s;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.err.println("Couldn't create the socket");
            e.printStackTrace();
        }
    }


    public String readLine() {
        String s = null;
        try {
            s = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void printLine(String s) {
        output.println(s);
        output.flush();
    }

    @Override
    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Couldn't close the socket");
        }
    }
}
    

