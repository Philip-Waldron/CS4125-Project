package UI;

import MainMenu.MainMenuController;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Dean
 */
public class MainMenuUI {

    private JFrame frame;
    public JButton joinGame, friendList, leaderboards, chatRoom, logOut;
    private JTextField uNameI;
    private JPasswordField passI;
    private JLabel info, uL, pL;
    public User currentUser;
    private MainMenuController mmc;

    public MainMenuUI(User u) {
        currentUser = u;
        frame = new JFrame("Main Menu");
        frame.setSize(500, 439);
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JPanel topBar = new JPanel(new GridLayout(1, 2));
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        JLabel usernameL = new JLabel("Welcome, " + currentUser.getUsername() + "!", SwingConstants.CENTER);
        info = new JLabel("Please choose how you wish to proceed.", SwingConstants.CENTER);
        uNameI = new JTextField();
        passI = new JPasswordField();
        info.setForeground(Color.WHITE);
        //usernameL.setForeground(Color.WHITE);
        infoPanel.setBackground(Color.BLUE);
        info.setFont(new Font("Quikhand", Font.BOLD, 17));
        usernameL.setFont(new Font("Quikhand", Font.BOLD, 17));

        mmc = new MainMenuController(this);
        
        logOut = new JButton("Log Out");
        logOut.addActionListener(mmc);
        joinGame = new JButton("Join Game");
        joinGame.addActionListener(mmc);
        friendList = new JButton("Friend List");
        friendList.addActionListener(mmc);
        leaderboards = new JButton("Leaderboards");
        leaderboards.addActionListener(mmc);
        chatRoom = new JButton("Chat Room");
        chatRoom.addActionListener(mmc);
        infoPanel.setPreferredSize(new Dimension(500, 100));
        topBar.setPreferredSize(new Dimension(500, 100));
        buttons.setPreferredSize(new Dimension(500, 200));
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.SOUTH);

        mainPanel.add(topBar);
        mainPanel.add(infoPanel);
        infoPanel.add(info);
        topBar.add(usernameL);
        topBar.add(logOut);
        buttons.add(joinGame);
        buttons.add(friendList);
        buttons.add(leaderboards);
        buttons.add(chatRoom);
        logOut.setBackground(Color.lightGray);
        joinGame.setBackground(Color.lightGray);
        friendList.setBackground(Color.lightGray);
        leaderboards.setBackground(Color.lightGray);
        chatRoom.setBackground(Color.lightGray);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public void chatClosed() {
        mmc.inChat = false;
    }
}
