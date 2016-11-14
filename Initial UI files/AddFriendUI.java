package FriendList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddFriendUI {

	private String name;
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void newScreen2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFriendUI window = new AddFriendUI();
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
	public AddFriendUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 239);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(57, 66, 317, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(277, 123, 97, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(137, 123, 106, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblAddAFriend = new JLabel("Add a friend");
		lblAddAFriend.setBounds(57, 34, 117, 16);
		frame.getContentPane().add(lblAddAFriend);
		this.name = textField.getText();
	}
	
	public String getName(){
		return name;
	}
}
