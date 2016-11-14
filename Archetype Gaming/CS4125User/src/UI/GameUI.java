package UI;

import Game.GameController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Player.*;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GameUI extends Thread {

    private JFrame frame;
    public JButton rock, paper, scissors;
    private JTextField uNameI;
    private JPasswordField passI;
    public JLabel info, winsP, drawsP, lossesP;
    private User currentUser;
    private boolean canMove = false;
    private MainMenuUI mmui;
    private GameController gc;

    public GameUI(MainMenuUI ui, User u) throws IOException {
        currentUser = u;
        mmui = ui;
        gc = new GameController(this);

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
        rock.addActionListener(gc);
        rock.setForeground(Color.WHITE);
        rock.setFont(new Font("Quikhand", Font.BOLD, 22));
        paper = new JButton("Paper");
        paper.addActionListener(gc);
        paper.setForeground(Color.WHITE);
        paper.setFont(new Font("Quikhand", Font.BOLD, 22));
        scissors = new JButton("Scissors");
        scissors.addActionListener(gc);
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

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    gc.closing();
                } catch (IOException e) {
                    System.out.println("I/O Error\n" + e);
                }

            }
        });
        frame.setVisible(true);
        start();
    }

    public void setText(String text) {
        info.setText(text);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void move() {
        gc.move();
        
    }

    public void close() {
        frame.setVisible(false);
        mmui.setVisible(true);
    }
}
