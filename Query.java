/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                           UserSearch us = new UserSearch(cA[0], cA[1]);
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

