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
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.SwingConstants;

/**
 *
 * @author Dean
 */
public class LogInUI implements ActionListener, Runnable {

    public JFrame frame;
    public JButton logIn, newUser;
    public JTextField uNameI;
    public JPasswordField passI;
    public JLabel info, uL, pL;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String username;
    JPanel mainPanel;
    

    public LogInUI() {
        try {
            socket = new Socket("localhost", 5555);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("\nNo I/O\n" + e);
            System.exit(1);
        }
        frame = new JFrame("Log In");
        frame.setSize(500, 400);
        mainPanel = new JPanel(new BorderLayout());        
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPanel uPanel = new JPanel(new GridLayout(2, 1));
        JPanel pPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        info = new JLabel("Welcome. Please enter your username and password.", SwingConstants.CENTER);
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
         run();
        
        
        
       
      
    }
     
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == logIn) {
            username = uNameI.getText();
            String password = String.valueOf(passI.getPassword());
            if(!(username.equals("")) && !(password.equals("")))
            {
            User uTemp = new User(username, password);
            out.println("LOGIN "+ username + "," + password);
            }
             else
            {
                mainPanel.setBackground(Color.red);
                info.setText("Username/password cannot be empty!");
        }
        } 
        
        else if (source == newUser) {
            //Verify with server whether user exists with the same username.
            username = uNameI.getText();
            String password = String.valueOf(passI.getPassword());
            if(!(username.equals("")) && !(password.equals("")))
            {
            User uTemp = new User(username, password);
            out.println("REGISTER "+ username + "," + password);
            
            }
            else
            {
                mainPanel.setBackground(Color.red);
                info.setText("Username/password cannot be empty!");
        }
        }
    
    }
    public void run(){
        String response;
        boolean running = true;
        while(running == true)
      try {
                response = in.readLine();
                  if(response.startsWith("SUCCESS"))
                {
                    String[] uA = response.substring(8).split(",");
                    User currentUser = new User(username, Integer.parseInt(uA[0]), Integer.parseInt(uA[1]), Integer.parseInt(uA[2]));
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!\n\nYou have " + currentUser.getW() + " wins, " + currentUser.getD() + " draws and " + currentUser.getL() + " losses.", "Login Successful", 1);
                    frame.setVisible(false);
                    MainMenuUI mmui = new MainMenuUI(currentUser);
                    running = false;
                    socket.close();
                }
                  else if(response.startsWith("FAILIURE"))
                  {
                      mainPanel.setBackground(Color.red);
                      info.setText(response.substring(9));
            }
                  else if(response.startsWith("REGISTERED"))
                {
                    User currentUser = new User(username, 0, 0, 0);
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!\n\nYou have " + currentUser.getW() + " wins, " + currentUser.getD() + " draws and " + currentUser.getL() + " losses.", "Login Successful", 1);
                    frame.setVisible(false);
                    MainMenuUI mmui = new MainMenuUI(currentUser);
                    running = false;
                    socket.close();
                }
            }
      
        
        catch (IOException e) {
            System.out.println("\nNo I/O\n" + e);
        }       
    }
}
