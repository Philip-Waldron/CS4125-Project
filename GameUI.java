/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Player.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Dean
 */
public class GameUI extends Thread implements ActionListener {

    public JFrame frame;
    public JButton rock, paper, scissors;
    public JTextField uNameI;
    public JPasswordField passI;
    public JLabel info, winsP, drawsP, lossesP;
    User currentUser;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    boolean canMove = false;// running = true;

    public GameUI(User u) {
        currentUser = u;
        try {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
            out.println("DETAILS " + currentUser.getUsername() + "," + currentUser.getW() + "," + currentUser.getD() + "," + currentUser.getL());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("\nNo I/O\n" + e);
            System.exit(1);
        }

        frame = new JFrame("Main Menu");
        frame.setSize(600, 539);
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttons = new JPanel(new GridLayout(3, 1));
        JPanel stats = new JPanel(new GridLayout(3, 1));

        info = new JLabel("Waiting on opponent...", SwingConstants.CENTER);
        winsP = new JLabel("Wins: " + currentUser.getW(), SwingConstants.CENTER);
        drawsP = new JLabel("Draws: " + currentUser.getD(), SwingConstants.CENTER);
        lossesP = new JLabel("Losses: " + currentUser.getL(), SwingConstants.CENTER);
        infoPanel.setBackground(Color.lightGray);
        info.setForeground(Color.WHITE);
        info.setFont(new Font("Quikhand", Font.BOLD, 17));
        winsP.setFont(new Font("Quikhand", Font.BOLD, 17));
        drawsP.setFont(new Font("Quikhand", Font.BOLD, 17));
        lossesP.setFont(new Font("Quikhand", Font.BOLD, 17));

        rock = new JButton("Rock");
        rock.addActionListener(this);
        rock.setForeground(Color.WHITE);
        rock.setFont(new Font("Quikhand", Font.BOLD, 22));
        paper = new JButton("Paper");
        paper.addActionListener(this);
        paper.setForeground(Color.WHITE);
        paper.setFont(new Font("Quikhand", Font.BOLD, 22));
        scissors = new JButton("Scissors");
        scissors.addActionListener(this);
        scissors.setForeground(Color.WHITE);
        scissors.setFont(new Font("Quikhand", Font.BOLD, 22));
        infoPanel.setPreferredSize(new Dimension(600, 150));
        buttons.setPreferredSize(new Dimension(600, 200));
        stats.setPreferredSize(new Dimension(600, 150));
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);

        stats.add(winsP);
        stats.add(drawsP);
        stats.add(lossesP);
        infoPanel.add(info);
        infoPanel.add(stats);
        buttons.add(rock);
        buttons.add(paper);
        buttons.add(scissors);
        rock.setBackground(Color.red);
        paper.setBackground(Color.blue);
        scissors.setBackground(Color.green);

        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?\n\nThe round will be forfeited.", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    out.println("FORFEIT");
                    System.exit(0);
                }

            }
        });
        start();
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (canMove == true) {
            if (source == rock) {
                info.setText("Please wait...");
                out.println("MOVE rock");
                canMove = false;
            } else if (source == paper) {
                info.setText("Please wait...");
                out.println("MOVE paper");
                canMove = false;
            } else if (source == scissors) {
                info.setText("Please wait...");
                out.println("MOVE scissors");
                canMove = false;
            } 
        }
    }

    public void run() {
        String response;
        while (true) {
            try {
                response = in.readLine();
                
                if (response.startsWith("MAKEMOVE")) {
                    JOptionPane.showMessageDialog(null, "You are playing against " + response.substring(9)
                            + ".\n\nBest of luck!", "Match Briefing", 1);
                    info.setText("Make a move!");
                    canMove = true;
                } 
                
                else if (response.startsWith("RESULT")) {
                    String[] results = response.substring(7).split(",");
                    String resultStr = "";
                    if (results[4].equals(currentUser.getUsername())) {
                        currentUser.newW();
                        resultStr = "You won! Congratulations!";
                        winsP.setText("Wins: " + currentUser.getW());
                    } else if (results[4].equals("draw")) {
                        currentUser.newD();
                        resultStr = "It was a draw!";
                        drawsP.setText("Draws: " + currentUser.getD());
                    } else {
                        currentUser.newL();
                        lossesP.setText("Losses: " + currentUser.getL());
                        resultStr = "You lost! Hard luck!";
                    }
                    int check = JOptionPane.showConfirmDialog(null, results[0] + " chose " + results[1] + ".\n"
                            + results[2] + " chose " + results[3] + ".\n\n"
                            + resultStr + "\n\nWould you like to find a new game?", "Game Over", JOptionPane.YES_NO_OPTION);
                    if (check == JOptionPane.YES_OPTION) {
                        out.println("NEWGAME");
                    } else {
                        System.out.println("Disconnecting from server.");
                        out.println("QUIT");
                        frame.setVisible(false);
                        socket.close();
                        MainMenuUI mmui = new MainMenuUI(currentUser);
                    }

                }
            } catch (IOException e) {
            }
        }
    }
}
