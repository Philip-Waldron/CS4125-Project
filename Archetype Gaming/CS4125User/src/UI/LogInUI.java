package UI;

import LogIn.LogInController;
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
import javax.swing.SwingConstants;

public class LogInUI  {

    private JFrame frame;
    public JButton logIn, newUser;
    public JTextField uNameI;
    public JPasswordField passI;
    private JLabel info, uL, pL;
    private String username;
    private JPanel mainPanel;
    private LogInController lic;

    public LogInUI() {

        frame = new JFrame("Log In");
        lic = new LogInController(this);
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
        logIn.addActionListener(lic);
        newUser = new JButton("Register");
        newUser.addActionListener(lic);
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

   

    public void empty() {
        mainPanel.setBackground(Color.red);
        info.setText("Username/password cannot be empty or contain commas!");
    }

    public void setText(String msg) {
        mainPanel.setBackground(Color.red);
        info.setText(msg);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    
}
