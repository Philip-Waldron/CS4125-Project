/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            output.println("MESSAGE All players connected");
            // Tell the first player that it is her turn.
            // Repeatedly get commands from the client and process them.
            while (true)  try {
               // The thread is only started after everyone connects.
               while (true) {
                    String command = input.readLine();
                    if (command.startsWith("LOGIN"))
                       {
                           String[] cA = command.substring(6).split(",");
                           UserSearch us = new UserSearch(cA[0], cA[1]);
                           User tempUser = us.search(false);
                           if(tempUser==null)
                           {
                               output.println("FAILIURE The username and password do not match. Please try again");
                           }
                           else
                           {
                               output.println("SUCCESS " + tempUser.wins + "," + tempUser.draws + "," + tempUser.losses);
                           }
                           socket.close();
                       }
                    else if(command.startsWith("REGISTER"))
                    {
                        String[] cA = command.substring(6).split(",");
                           UserSearch us = new UserSearch(cA[0], cA[1]);
                           User tempUser = us.search(true);
                           if(tempUser==null)
                           {
                               UserAdd ua = new UserAdd(new User(cA[0], cA[1]));
                               ua.add();
                           }
                           else
                           {
                               output.println("FAILIURE The username already exists. Try again with a different username.");
                               
                           }
                           socket.close();
                           
                       }
                       
                       
                }
                
            } catch (Exception e) {
                System.out.println("Player died: " + e);
            }    finally {
                try {socket.close();} catch (IOException e) {}
            }
    }
   
}

