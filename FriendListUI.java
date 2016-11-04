package test;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;

public class FriendListUI {

	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String message;
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
	 * 
	 * @throws IOException
	 */
	public FriendListUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblYourFriends = new JLabel("Your friends:");
		lblYourFriends.setBounds(22, 13, 151, 16);
		frame.getContentPane().add(lblYourFriends);

		try {
			// socket connecting to server and input/output streams
			socket = new Socket("localhost", 4444);
			out = new PrintWriter(socket.getOutputStream(), true);
			out.flush();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			sendMessage("friendList " + currentPlayer);
			message = (String) in.readLine();
			System.out.println("server>" + message);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: localhost.eng");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
		// friends list display
		String[] listOfFriends = new String[0];
		listOfFriends = message.split(", ");
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);
		if (listOfFriends.length > 0) {
			for (int i = 1; i < listOfFriends.length; i++)
				listModel.addElement(listOfFriends[i]);
		}
		list.setBounds(22, 42, 252, 348);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(list);

		JButton btnEditNickname = new JButton("Edit nickname");
		btnEditNickname.setBounds(286, 144, 124, 48);
		frame.getContentPane().add(btnEditNickname);
		String selected = list.getSelectedValue();
		btnEditNickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// edit nickname for selected player
				EditNickUI changeNick = new EditNickUI();
				changeNick.newScreen();
				String nick = changeNick.getNick();
				sendMessage("editNickname " + currentPlayer + selected + nick);
			}
		});

		JButton btnInviteToGame = new JButton("Invite to game");
		btnInviteToGame.setBounds(286, 205, 124, 48);
		frame.getContentPane().add(btnInviteToGame);
		btnInviteToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// send game invite to selected friend
				sendMessage("inviteToGame " + currentPlayer + selected);
				
			}
		});

		JButton btnAddFriend = new JButton("Add new friend");
		btnAddFriend.setBounds(286, 39, 124, 49);
		frame.getContentPane().add(btnAddFriend);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriendUI addFriend = new AddFriendUI();
				addFriend.newScreen2();
				sendMessage("addFriend " + currentPlayer + addFriend.getName());
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(313, 358, 97, 32);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// go back to main menu

			}
		});
	}

	void sendMessage(String msg) {
		out.println(msg);
		out.flush();
		System.out.println("client>" + msg);
	}
}
