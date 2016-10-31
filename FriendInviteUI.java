package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FriendInviteUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendInviteUI window = new FriendInviteUI();
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
	public FriendInviteUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWantsToAdd = new JLabel("wants to add you as a friend.");
		lblWantsToAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblWantsToAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWantsToAdd.setBounds(121, 79, 193, 28);
		frame.getContentPane().add(lblWantsToAdd);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(58, 131, 121, 42);
		frame.getContentPane().add(btnAccept);
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.setBounds(253, 131, 121, 42);
		frame.getContentPane().add(btnDecline);
		
		JLabel lblPutanamehere = new JLabel("PutANameHere");
		lblPutanamehere.setHorizontalAlignment(SwingConstants.CENTER);
		lblPutanamehere.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPutanamehere.setBounds(121, 31, 193, 35);
		frame.getContentPane().add(lblPutanamehere);
	}
}
