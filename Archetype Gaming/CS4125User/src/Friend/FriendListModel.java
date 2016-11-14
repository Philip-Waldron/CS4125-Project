package Friend;

import UI.FriendListUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class FriendListModel extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FriendListUI flui;
    private String username;

    public FriendListModel(FriendListUI ui) {
        try {
            flui = ui;
            // socket connecting to server and input/output streams
            username = flui.currentPlayer.getUsername();
            socket = new Socket("localhost", 5555);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.flush();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("friendList, " + username);
            out.flush();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
        start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        String received;

        while (!Thread.interrupted()) {
            try {
                received = in.readLine();
                if (received != null) {
                    if (received.startsWith(username)) {
                        flui.clear();
                        /*flui.listOfFriends*/ String[] temp = received.split(", ");
                        if (temp.length > 1) {
                            for (int i = 1; i < temp.length; i += 2) {
                                String elem = temp[i] + " - " + temp[i + 1];
                                flui.addElement(elem);
                            }
                        }
                    } else if (received.startsWith("FAILIURE")) {
                        JOptionPane.showMessageDialog(null, "The user does not exist in the friends list.", "Invalid User", 0);
                    } else if (received.startsWith("inviteToGame")) {
                        out.println(received);
                        out.flush();
                        socket.close();
                    } else if (received.startsWith("addFriend")) {
                        out.println(received);
                        out.flush();
                        socket.close();
                    } else if (received.startsWith("CLOSE")) {
                        interrupt();
                        socket.close();
                    }
                }
            } catch (IOException e) {
                System.out.println("I/O Error\n" + e);
            }
        }
    }
}
