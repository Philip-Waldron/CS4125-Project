/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115User;

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

/**
 *
 * @author Dean
 */
public class LogInUI implements ActionListener {

    public JFrame frame;
    public JButton logIn, newUser;
    public JTextField uNameI;
    public JPasswordField passI;
    public JLabel info, uL, pL;

    public LogInUI() {
        frame = new JFrame("Log In");
        frame.setSize(500, 400);
        JPanel mainPanel = new JPanel(new BorderLayout());        
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPanel uPanel = new JPanel(new GridLayout(2, 1));
        JPanel pPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        info = new JLabel("Welcome. Please enter your username and password.");
        uL = new JLabel("Username");
        pL = new JLabel("Password");
        uNameI = new JTextField();
        passI = new JPasswordField();
        info.setForeground(Color.WHITE);
        mainPanel.setBackground(Color.BLUE);
        info.setFont(new Font("Quikhand", Font.BOLD, 17));

        logIn = new JButton("Log In");
        logIn.addActionListener(this);
        newUser = new JButton("Register");
        newUser.addActionListener(this);
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);

        mainPanel.setPreferredSize(new Dimension(500, 100));
        buttons.setPreferredSize(new Dimension(500, 100));
        uPanel.setPreferredSize(new Dimension(500, 100));
        pPanel.setPreferredSize(new Dimension(500, 100));
        inputPanel.setPreferredSize(new Dimension(500, 200));

        mainPanel.add(info, BorderLayout.CENTER);
        uPanel.setBackground(Color.lightGray);
        pPanel.setBackground(Color.lightGray);
        uPanel.add(uL);
        uPanel.add(uNameI);
        pPanel.add(pL);
        pPanel.add(passI);
        inputPanel.add(uPanel, BorderLayout.NORTH);
        inputPanel.add(pPanel, BorderLayout.SOUTH);
        buttons.add(logIn, BorderLayout.EAST);
        buttons.add(newUser, BorderLayout.WEST);
        logIn.setBackground(Color.lightGray);
        newUser.setBackground(Color.lightGray);

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == logIn) {
            //Verify with server whether user exists and the password matches.
            MainMenuUI  mmui = new MainMenuUI();
        } 
        
        else if (source == newUser) {
            //Verify with server whether user exists with the same username.
        }
    }
}
