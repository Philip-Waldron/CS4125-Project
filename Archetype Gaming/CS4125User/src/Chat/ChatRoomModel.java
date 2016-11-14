package Chat;

import Player.User;
import UI.ChatRoomUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fergal
 */
public class ChatRoomModel extends Thread {

    private final int port = 1234;
    private ChatRoomUI crui;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private User currentUser;
    private static ArrayList<String> ConnectedUsers = new ArrayList<String>();

    public ChatRoomModel(ChatRoomUI ui) throws IOException {
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to chat server.");
            crui = ui;
            currentUser = crui.getCurrentUser();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("\nNo I/O\n" + e);
            System.exit(1);
        }
        start();

    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                String recievedMessage = in.readLine();
                if (recievedMessage.startsWith("CONNECTED")) {
                    System.out.println("Connected");
                    out.println("USER: " + currentUser.getUsername());
                    System.out.println("Username sent");
                } else if (recievedMessage.startsWith("USERS: ")) {
                    System.out.println(recievedMessage);
                    recievedMessage = recievedMessage.substring(7);
                    recievedMessage = recievedMessage.replace("[", "");
                    recievedMessage = recievedMessage.replace("]", "");
                    System.out.println(recievedMessage);
                    String[] usernames = recievedMessage.split(" ");
                    crui.updateUsers(usernames);
                } else if (recievedMessage.startsWith("MESSAGE: ")) {
                    String temp = recievedMessage.substring(9);
                    String[] temp2 = temp.split("~~");
                    Message msg = new Message(temp2[0], temp2[1]);
                    crui.recieveMessage(msg);
                }
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(null, "I/O Error\n" + exc, "Error", 0);
                System.exit(1);
            }
        }
        return;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void updateUsers(String[] usernames) {
        crui.jliOnline.setListData(usernames);
    }
    
    public void quit()
    {
        out.println("QUIT");
        interrupt();
    }

}
