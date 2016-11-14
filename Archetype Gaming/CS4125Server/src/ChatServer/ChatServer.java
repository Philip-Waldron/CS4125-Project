package ChatServer;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Fergal
 */
public class ChatServer extends Thread {

    public ArrayList<Socket> ConnectedSockets = new ArrayList<>();
    public ArrayList<String> ConnectedUsers = new ArrayList<>();

    private ServerSocket server;

    public ChatServer() throws IOException {
        server = new ServerSocket(1234);
        start();

    }

    @Override
    public void run() {
        System.out.println("Chat Server Is Running");
        while (true) {
            try {
                Socket sock = server.accept();
                ConnectedSockets.add(sock);
                System.out.println("New Chat User: " + sock.getLocalAddress().getHostName());
                ChatRoom chat = new ChatRoom(this, sock);
                chat.start();
            } catch (Exception x) {
                System.out.println("Player died: " + x);
            }
        }
    }

    public void newUser(String username) throws IOException {
        System.out.println("Adding new user");
        ConnectedUsers.add(username);
        String output = "";
        for (int c = 0; c < ConnectedUsers.size(); c++) {
            output += ConnectedUsers.get(c) + " ";
        }
        for (int i = 0; i < ConnectedSockets.size(); i++) {
            Socket tempSock = (Socket) ConnectedSockets.get(i);
            PrintWriter userListOut = new PrintWriter(tempSock.getOutputStream());
            System.out.println("Message to " + tempSock + " sent.");
            userListOut.println("USERS: " + output);
            userListOut.println("MESSAGE: SERVER~~" + username + " has joined.");
            userListOut.flush();
        }
    }

   public void sendMessage(String username, String message) throws IOException {
        System.out.println("Message received from " + username + ": " + message);
        for (int i = 0; i < ConnectedSockets.size(); i++) {
            Socket tempSock = (Socket) ConnectedSockets.get(i);
            PrintWriter userListOut = new PrintWriter(tempSock.getOutputStream());
            userListOut.println("MESSAGE: " + username + "~~" + message);
            userListOut.flush();
        }
    }


    public void deleteUser(String username) throws IOException {
        System.out.println("Adding new user");
        ConnectedUsers.add(username);
        String output = "";
        for (int c = 0; c < ConnectedUsers.size(); c++) {
            if (ConnectedUsers.get(c).equals(username)) {
                ConnectedUsers.remove(c);
            } else {
                output += ConnectedUsers.get(c) + " ";
            }
        }
        for (int i = 0; i < ConnectedSockets.size(); i++) {
            Socket tempSock = (Socket) ConnectedSockets.get(i);
            PrintWriter userListOut = new PrintWriter(tempSock.getOutputStream());
            userListOut.println("USERS: " + output);
            userListOut.println("MESSAGE: SERVER~~" + username + " has left.");
            userListOut.flush();
        }
    }
}
