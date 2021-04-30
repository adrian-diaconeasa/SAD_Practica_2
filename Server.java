/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author oussama
 */
public class Server implements Runnable {
    public static ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<>();
    public static boolean state = true;
    public MySocket clientSocket;
    public String nick;

    public Server(String nick, MySocket socket) {
        this.clientSocket = socket;
        this.nick = nick;
    }

    public static void main(String[] args) {
        MyServerSocket server = new MyServerSocket(1234);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        System.out.println(dateFormat.format(new Date()) + " The server is running");

        while (true) {
            MySocket client = server.accept();
            while (state) {
                client.printLine("Introduce your nick: ");
                String nick = client.readLine();
                if (clients.containsKey(nick)) {
                    client.printLine(dateFormat.format(new Date()) + " The nick " + nick + " already exists");
                } else {
                    System.out.println(dateFormat.format(new Date()) + " Welcome " + nick);
                    clients.put(nick, client);
                    new Thread(new Server(nick, client)).start();
                    state = false;
                }
            }
            state = true;

        }
    }

    @Override
    public void run() {
        String line;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        while ((line = clients.get(nick).readLine()) != null) {
            for (ConcurrentHashMap.Entry<String, MySocket> entry : clients.entrySet()) {
                if (!entry.getKey().equals(nick)) {
                    entry.getValue().printLine(dateFormat.format(new Date()) + " [" + nick + "]: " + line);
                }
            }
        }
        clients.get(nick).close();
        clients.remove(nick);
        System.out.println(dateFormat.format(new Date()) + " " + nick + " has left.");
    }

}
