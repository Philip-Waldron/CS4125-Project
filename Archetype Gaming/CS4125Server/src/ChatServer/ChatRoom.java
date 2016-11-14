package ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Fergal
 */
public class ChatRoom extends Thread {

    BufferedReader input;
    PrintWriter output;
    Socket socket;
    ChatServer cs;
    String username = "";

    public ChatRoom(ChatServer s, Socket so) throws Exception {
        socket = so;
        cs = s;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        output.println("CONNECTED");
        while (!Thread.interrupted()) {
            try {
                String command = input.readLine();
                if (command != null) {
                    System.out.println(command);
                    if (command.startsWith("USER")) {
                        System.out.println("Username received.");
                        username = command.substring(6);
                        cs.newUser(username);
                    } else if (command.startsWith("QUIT")) {
                        cs.deleteUser(username);
                        interrupt();
                    } else {
                        System.out.println("Message received.");
                        cs.sendMessage(username, command);
                    }
                }

            } catch (IOException e) {
                System.out.println("User died: " + e);
                interrupt();
            }
        }
        System.out.println("Query closed.");
        return;
    }

}
