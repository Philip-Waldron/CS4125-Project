package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendListUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendListUI window = new FriendListUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FriendListUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblYourFriends = new JLabel("Your friends:");
		lblYourFriends.setBounds(22, 13, 151, 16);
		frame.getContentPane().add(lblYourFriends);
		
		JList<String> list = new JList<String>();
		list.setBounds(22, 42, 252, 348);
		frame.getContentPane().add(list);
		
		JButton btnEditNickname = new JButton("Edit nickname");
		btnEditNickname.setBounds(286, 144, 124, 48);
		frame.getContentPane().add(btnEditNickname);
		btnEditNickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// edit nickname for selected player
				EditNickUI.newScreen();
			}
		});
		
		JButton btnInviteToGame = new JButton("Invite to game");
		btnInviteToGame.setBounds(286, 205, 124, 48);
		frame.getContentPane().add(btnInviteToGame);
		btnInviteToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// send game invite to selected friend
				
			}
		});
		
		JButton btnAddFriend = new JButton("Add new friend");
		btnAddFriend.setBounds(286, 39, 124, 49);
		frame.getContentPane().add(btnAddFriend);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriendUI.newScreen2();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(313, 358, 97, 32);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//go back to main menu
				
			}
		});
	}
}
