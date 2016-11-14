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

/**
 *
 * @author Dean
 */
public  class LobbyPlayer extends Thread
    {
        BufferedReader input;
        PrintWriter output;
        Socket socket;
        String username;
        int score;
        public LobbyPlayer(Socket socket) throws Exception
        {
            this.socket = socket;
        
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }
        public int getScore()
        {
            return score;
        }
        public String getUsername()
        {
            return username;
        }
        public void run() {
            while (true)  try {
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("USERNAME"))
                       {
                           this.username = command.substring(9);
                       }
                    else if(command.startsWith("SCORE"))
                    {
                           this.score = Integer.parseInt(command.substring(6));
                           output.println("MESSAGE Finding another user...");
                       }
                       
                       
                    else if (command.startsWith("QUIT")) {
                        score = -1;
                        socket.close();
                    }
                }
                
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
    }
}
