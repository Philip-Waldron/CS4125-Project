/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import Player.GamePlayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Player.*;

public class GameServer extends Thread {
ServerSocket listener;
        public GameServer() throws Exception {
        listener = new ServerSocket(4444);
        start();
        
    }
        public void run()
        {
           System.out.println("Rock Paper Scissors Server is Running");
           MatchMaker lobby = new MatchMaker();
            while (true) try {
                GamePlayer lplayer = new GamePlayer(listener.accept());
                lplayer.start();
                System.out.println("Player joined queue.");
                lobby.addPlayer(lplayer);
            }
            catch(Exception e)
          {
              System.out.println("Player died: " + e);
          }
          /*finally {try{
            listener.close();
        }
          catch(IOException e)
          {
              System.out.println("No I/O: " + e);
          }
        }*/
        
        
}
}