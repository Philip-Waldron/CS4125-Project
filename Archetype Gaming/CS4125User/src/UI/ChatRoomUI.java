package UI;

import Player.*;
import Chat.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.IOException;
import javax.swing.*;

public class ChatRoomUI {

    public JFrame chatWindow;
    public JButton jbSend;
    private JLabel jlMessage, jlConversation, jlOnline;
    public JTextField jtfMessage;
    public JTextArea jtaConversation;
    private JScrollPane jspConversation, jspOnline;
    public JList jliOnline;

    public User currentUser;
    private ChatRoomController crc;
    private String[] onlineUsers;
    private MainMenuUI mmui;

    public ChatRoomUI(User u, MainMenuUI ui) throws IOException {
        currentUser = u;
        mmui = ui;
        
        jlOnline = new JLabel("Currently Online");
        jlOnline.setHorizontalAlignment(SwingConstants.CENTER);

        jliOnline = new JList();
        jliOnline.setForeground(Color.blue);
        
        jtaConversation = new JTextArea();
        jtaConversation.setColumns(20);
        jtaConversation.setForeground(Color.blue);
        jtaConversation.setLineWrap(true);
        jtaConversation.setRows(5);
        jtaConversation.setEditable(false);
        
        crc = new ChatRoomController(this);

        chatWindow = new JFrame("Chat Room");
        chatWindow.setSize(650, 450);
        chatWindow.setBackground(Color.lightGray);
        chatWindow.setLayout(null);

        jbSend = new JButton("Send");
        jbSend.addActionListener(crc);
        jbSend.setBackground(Color.blue);
        jbSend.setForeground(Color.white);

        jlMessage = new JLabel("Message: ");

        jtfMessage = new JTextField(20);
        jtfMessage.requestFocus();

        jlConversation = new JLabel("Conversation");
        jlConversation.setHorizontalAlignment(SwingConstants.CENTER);

        

        jspConversation = new JScrollPane();
        jspConversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspConversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspConversation.setViewportView(jtaConversation);

        

        jspOnline = new JScrollPane();
        jspOnline.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspOnline.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspOnline.setViewportView(jliOnline);

        chatWindow.add(jbSend);
        chatWindow.add(jlMessage);
        chatWindow.add(jtfMessage);
        chatWindow.add(jlConversation);
        chatWindow.add(jspConversation);
        chatWindow.add(jlOnline);
        chatWindow.add(jspOnline);

        jbSend.setBounds(250, 40, 71, 10);
        jlMessage.setBounds(10, 10, 60, 20);
        jtfMessage.setBounds(70, 4, 260, 30);
        jlConversation.setBounds(100, 70, 140, 16);
        jspConversation.setBounds(10, 90, 330, 180);
        jlOnline.setBounds(350, 70, 150, 16);
        jspOnline.setBounds(350, 90, 150, 180);

        chatWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        chatWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mmui.chatClosed();
                chatWindow.setVisible(false);
                crc.quit();
            }
        });

        chatWindow.setVisible(true);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getMessage() {
        return jtfMessage.getText();
    }

    public void recieveMessage(Message message) {
        String[] temp = message.get();
        jtaConversation.append(temp[0] + ": " + temp[1] + "\n");
    }

    public void clearTF() {
        jtfMessage.setText("");
        jtfMessage.requestFocus();
    }

    public void updateUsers(String[] usernames) {
        jliOnline.setListData(usernames);
    }
}
