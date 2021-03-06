package FileServer;

import FileServer.UserAdd;
import FileServer.UserSearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Player.*;
/**
 *
 * @author Dean
 */
public class Query extends Thread {
    
    BufferedReader input;
    PrintWriter output;
    Socket socket;
		Search s;
     public Query(Socket so) throws Exception {
        
  
            try {
                socket = so;
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Query Failed: " + e);
            }
        }
    public void run() {
            // The thread is only started after everyone connects.
            // Tell the first player that it is her turn.
            // Repeatedly get commands from the client and process them.
            boolean running = true;
            while (running == true)  try {
                    String command = input.readLine();
                    if(command!= null)
                    {
                        System.out.println(command);
											if (command.startsWith("LOGIN"))
												 {
														 System.out.println("Handling command.");
														 String[] cA = command.substring(6).split(",");
														 s = new UserSearch(cA[0], cA[1]);
														 User tempUser = us.search();
														 if(tempUser==null)
														 {
																 output.println("FAILIURE The username and password do not match. Please try again");
														 }
														 else
														 {
																 System.out.println("Command successful");
																 output.println("SUCCESS " + tempUser.getW() + "," + tempUser.getD() + "," + tempUser.getL());
																 socket.close();
																 running = false;
														 }
												 }
												 
											else if(command.startsWith("REGISTER"))
											{
													String[] cA = command.substring(9).split(",");
														 UserSearch us = new UserSearch(cA[0], cA[1]);
														 
														 boolean check = us.exists();
														 if(check == false)
														 {
																 UserAdd ua = new UserAdd(new User(cA[0], cA[1]));
																 ua.add();
																 output.println("REGISTERED");
																 socket.close();
																 running = false;
														 }
														 else
														 {
																 output.println("FAILIURE The username already exists. Try again with a different username.");
														 }
														 
											}
											
											else if(command.startsWith("friendList"))
											{
												String[] cA = command.split(", ");
												boolean found = false;
                        Scanner sc = new Scanner(new FileReader("friends.txt"));
												while (sc.hasNext() && found == false) 
												{
													String u = sc.nextLine();
													if(u.startsWith(cA[1])
													{
														output.println(u);
														socket.close();
														found = true;
													}
												}
												sc.close();
												//s = new FriendSearch("cA[1]");
												//s.search();
												
											}
											
											else if(command.startsWith("editNickname"))
											{
												String[] cA = command.split(", ");
												List<String> fileContent = new ArrayList<>(Files.readAllLines("./friends.txt", StandardCharsets.UTF_8));

												for (int i = 0; i < fileContent.size(); i++) 
												{
														if (fileContent.get(i).startsWith(cA[1])) 
														{
																String line = fileContent.get(i);
																line.replace(selected, cA[2] + cA[3]);
																fileContent.set(i, line);
																break;
														}
												}
												Files.write("./friends.txt", fileContent, StandardCharsets.UTF_8);
											}
											
											else if(command.startsWith("inviteToGame"))
											{
												sendMessage("inviteToGame, " + currentPlayer.getUsername() + ", " + selected);
												String[] cA = command.split(", ");
												
												//GameInviteUI.newScreen();
												
											}
											
											else if(command.startsWith("addFriend"))
											{
												sendMessage("addFriend, " + currentPlayer.getUsername() + ", " + addFriend.getName());
												String[] cA = command.split(", ");
                        PrintWriter out = new PrintWriter(new FileWriter("invations.txt"));
												out.println(cA[2] + ", " + cA[1]");
												out.close();
											}
											
                    }
                       
                
            }
            
            catch (IOException e) {
                System.out.println("User died: " + e);
            }    /*(finally {
                try {socket.close(); System.out.println("Socket closed.");} catch (IOException e) {}
            }
            */
    }
   
}
