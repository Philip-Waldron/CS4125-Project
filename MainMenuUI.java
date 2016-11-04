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
import javax.swing.JOptionPane;

/**
 *
 * @author Dean
 */
public class MainMenuUI implements ActionListener {

    public JFrame frame;
    public JButton joinGame, friendList, leaderboards, chatRoom;
    public JTextField uNameI;
    public JPasswordField passI;
    public JLabel info, uL, pL;
    User currentUser;

    public MainMenuUI(User u) {
        currentUser = u;
        frame = new JFrame("Main Menu");
        frame.setSize(500, 339);
        JPanel mainPanel = new JPanel(new BorderLayout());         
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        info = new JLabel("Please choose how you wish to proceed.");
        uNameI = new JTextField();
        passI = new JPasswordField();
        info.setForeground(Color.WHITE);
        mainPanel.setBackground(Color.BLUE);
        info.setFont(new Font("Quikhand", Font.BOLD, 17));

        joinGame = new JButton("Join Game");
        joinGame.addActionListener(this);
        friendList = new JButton("Friend List");
        friendList.addActionListener(this);
        leaderboards = new JButton("Leaderboards");
        leaderboards.addActionListener(this);
        chatRoom = new JButton("Chat Room");
        chatRoom.addActionListener(this);
        mainPanel.setPreferredSize(new Dimension(500, 100));
        buttons.setPreferredSize(new Dimension(500, 200));
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.SOUTH);

        

        mainPanel.add(info);
        buttons.add(joinGame);
        buttons.add(friendList);
        buttons.add(leaderboards);
        buttons.add(chatRoom);
        joinGame.setBackground(Color.lightGray);
        friendList.setBackground(Color.lightGray);
        leaderboards.setBackground(Color.lightGray);
        chatRoom.setBackground(Color.lightGray);

        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        int check = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }
        });
    }
        
        
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == joinGame) {
        frame.setVisible(false);
        GameUI gui = new GameUI(currentUser);   
        } 
        
        else if (source == friendList) {
        }
        else if (source == leaderboards) {
          
        }
        else if (source == chatRoom) {
           
        }
    }
}
