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
public class Match {

    Player currentPlayer;

    
    public void tellOpponent(String msg)
    {
        currentPlayer = currentPlayer.opponent;
        currentPlayer.report(msg);
    }
    
    public void makeMove(int location, Player player) {
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
        }
    class Player extends Thread {
    
     char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
    
    public Player(LobbyPlayer lp) throws Exception {
        
            this.socket = lp.socket;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        /**
         * Accepts notification of who the opponent is.
         */
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        /**
         * Handles the otherPlayerMoved message.
         */
        public void otherPlayerMoved(int location) {
            output.println("OPPONENT_MOVED " + location);
        }
        
        public void report(String msg)
        {
            output.println(msg);
        }

        /**
         * The run method of this thread.
         */
        public void run() {
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");
            // Tell the first player that it is her turn.
            if (mark == 'X') {
                output.println("MESSAGE Your move");
            }
            // Repeatedly get commands from the client and process them.
            while (true)  try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is her turn.
                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.endsWith("WIN"))
                       {
                        tellOpponent("LOSS");
                    }
                    else if (command.endsWith("TIE"))
                         {
                        tellOpponent("TIE");
                    }
                    else if (command.endsWith("LOSS"))
                    {
                        tellOpponent("WIN");
                    }
                    else if (command.startsWith("MOVE")) {
                        int location = Integer.parseInt(command.substring(5));
                        makeMove(location, this);
                    }
                    
                    else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }}}
