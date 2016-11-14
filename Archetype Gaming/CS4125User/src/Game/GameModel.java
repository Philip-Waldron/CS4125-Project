package Game;

import Player.User;
import UI.GameUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dean
 */
public class GameModel extends Thread {

    private GameUI gui;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private User currentUser;
    private GameReport gr;

    public GameModel(GameUI ui) throws IOException {
        gui = ui;
        try {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
            currentUser = gui.getCurrentUser();
            out.println("DETAILS " + currentUser.getUsername() + "," + currentUser.getW() + "," + currentUser.getD() + "," + currentUser.getL());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("\nNo I/O\n" + e);
            System.exit(1);
        }
        start();
    }

    public void finish()
    {
        out.println("FORFEIT");
        interrupt();
    }
    
    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void run() {
        String response;
        while (!Thread.interrupted()) {
            try {
                response = in.readLine();
                if (response != null) {
                    if (response.startsWith("MAKEMOVE")) {
                        JOptionPane.showMessageDialog(null, "You are playing against " + response.substring(9)
                                + ".\n\nBest of luck!", "Match Briefing", 1);
                        gui.setText("Make a move!");
                        gui.move();
                    } else if (response.startsWith("RESULT")) {
                        String[] results = response.substring(7).split(",");
                        if (results[4].equals(currentUser.getUsername())) {
                            currentUser.newW();
                            gr = new Win(results);
                            gui.winsP.setText("Wins: " + currentUser.getW());
                        } else if (results[4].equals("draw")) {
                            gr = new Draw(results);
                            currentUser.newD();
                            gui.drawsP.setText("Draws: " + currentUser.getD());
                        } else {
                            gr = new Loss(results);
                            currentUser.newL();
                            gui.lossesP.setText("Losses: " + currentUser.getL());
                        }
                        boolean cont = gr.report();
                        if (cont == true) {
                            out.println("NEWGAME");
                        } else {
                            System.out.println("Disconnecting from server.");
                            out.println("QUIT");
                            gui.close();
                            socket.close();
                            interrupt();
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "I/O Error\n" + e, "Error", 0);
                System.exit(1);
            }
        }
        return;
    }

    public void close() throws IOException {
        socket.close();
        interrupt();
    }
}
