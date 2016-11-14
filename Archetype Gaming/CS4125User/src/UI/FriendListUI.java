package UI;

import Friend.*;

import javax.swing.*;
import java.io.*;
import Player.User;
import java.util.ArrayList;

/**
 *
 * @author Pawel
 */
public class FriendListUI extends Thread {

    public JFrame frame;
    public JButton btnEditNickname, btnInviteToGame, btnAddFriend, btnBack;
    public JList<String> list;
    public User currentPlayer;
    public String[] listOfFriends;
    public DefaultListModel<String> listModel;
    public MainMenuUI mmui;
    private FriendListController flc;
    public ArrayList<String> usernames;

    /**
     * @throws IOException
     */
    public FriendListUI(User cp, MainMenuUI ui) {
        currentPlayer = cp;
        mmui = ui;
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        list.setBounds(22, 42, 252, 348);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
     

        frame = new JFrame("Friend List");
        frame.setBounds(100, 100, 451, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblYourFriends = new JLabel("Your friends:");
        lblYourFriends.setBounds(22, 13, 151, 16);
        frame.getContentPane().add(lblYourFriends);

        // friends list display
        
        frame.getContentPane().add(list);
        flc = new FriendListController(this);
        btnEditNickname = new JButton("Edit nickname");
        btnEditNickname.setBounds(286, 144, 124, 48);
        btnEditNickname.addActionListener(flc);
        frame.getContentPane().add(btnEditNickname);

        btnInviteToGame = new JButton("Invite to game");
        btnInviteToGame.setBounds(286, 205, 124, 48);
        btnInviteToGame.addActionListener(flc);
        frame.getContentPane().add(btnInviteToGame);

        btnAddFriend = new JButton("Add new friend");
        btnAddFriend.setBounds(286, 39, 124, 49);
        btnAddFriend.addActionListener(flc);
        frame.getContentPane().add(btnAddFriend);

        btnBack = new JButton("Back");
        btnBack.setBounds(313, 358, 97, 32);
        btnBack.addActionListener(flc);
        frame.getContentPane().add(btnBack);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.setVisible(false);
                mmui.setVisible(true);
            }
        });
        
        frame.setVisible(true);
    }
    
    public void clear()
    {
        listModel.clear();
        usernames = new ArrayList<String>();
    }
    public void addElement(String s)
    {
        listModel.addElement(s);
        usernames.add(s);
    }
}
