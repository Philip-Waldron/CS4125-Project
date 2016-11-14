import java.util.ArrayList;
public class ChatRoom{
	private String roomName;
	public ChatRoom(){
		
	}
	public ChatRoom(String roomName){
		//create a chat room with the name 'roomName'
		this.roomName = roomName;
	}
	public void RetrieveLog(String roomName){
		//finds the chat log for the current roon in a corresponding file
		
		//if no log is found then nothing is returned
		
		//if log is found then the last 20 messages are posted automatically
		//into the chat window
	}
	public ArrayList<Message> populateChat(Message recievedMessage){
		//function called by the chat room whenever a message is recievedMessage
		
		//passed Message is posted into the chat log and into the chat window
		//in order of timestamp, username, contents.
	}
	public void addUser(String username){
		//function called when a user connects to the chat room
		
		//adds username to the list of users in the chat room
	}
	public void removeUser(String username){
		//function called when a user disconnects from the chat room
		
		//removes username from the list of users in the chat room
	}
	public void updateUserList(){
		//function called by addUser and removeUser
		
		//clears the displayed userlist and prints the new list
	}
}
