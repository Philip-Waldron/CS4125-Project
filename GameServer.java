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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Thread {

        /**
         * Constructs a handler thread for a given socket and mark
         * initializes the stream fields, displays the first two
         * welcoming messages.
         */
        public GameServer() throws Exception {
        ServerSocket listener = new ServerSocket(4444);
        System.out.println("Rock Paper Scissors Server is Running");
        try {
            while (true) {
                Lobby lobby = new Lobby();
                LobbyPlayer lplayer = new LobbyPlayer(listener.accept());
                lplayer.start();
                while(lplayer.username == "" && lplayer.score == 0);
                lobby.lobbyA.add(lplayer);
            }
        } finally {
            listener.close();
        }
    }
        
        
}
