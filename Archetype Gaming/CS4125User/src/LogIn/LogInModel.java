package LogIn;

import Player.User;
import UI.LogInUI;
import UI.MainMenuUIFactory;
import UI.UIFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.net.Socket;

/**
 *
 * @author Dean
 */
public class LogInModel extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private LogInUI liui;

    public LogInModel(LogInUI ui) {
        liui = ui;
        try {
            socket = new Socket("localhost", 5555);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The server is currently offline. Please try connecting later.", "Server Offline", 1);
            System.exit(1);
        }
        start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }
    
    public void setUsername(String user)
    {
        username = user;
    }

    public void run() {
        String response;
        while (!Thread.interrupted()) {
            try {
                response = in.readLine();
                if (response.startsWith("SUCCESS")) {
                    String[] uA = response.substring(8).split(",");
                    User currentUser = new User(username, Integer.parseInt(uA[0]), Integer.parseInt(uA[1]), Integer.parseInt(uA[2]));
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!\n\nYou have " + currentUser.getW() + " wins, " + currentUser.getD() + " draws and " + currentUser.getL() + " losses.", "Login Successful", 1);
                    liui.setVisible(false);
                    UIFactory mmui = new MainMenuUIFactory(currentUser);
                    mmui.create();
                    interrupt();
                    socket.close();
                } else if (response.startsWith("FAILIURE")) {
                    liui.setText(response.substring(9));
                } else if (response.startsWith("REGISTERED")) {
                    User currentUser = new User(username, 0, 0, 0);
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!\n\nYou have " + currentUser.getW() + " wins, " + currentUser.getD() + " draws and " + currentUser.getL() + " losses.", "Login Successful", 1);
                    liui.setVisible(false);
                    UIFactory mmui = new MainMenuUIFactory(currentUser);
                    mmui.create();
                    interrupt();
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("\nNo I/O\n" + e);
            }
        }
        System.out.println("Thread stopped.");
        return;
    }
}
