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
import java.net.Socket;

/**
 *
 * @author Dean
 */
public class Match implements Runnable{

    GamePlayer player1, player2;
    public Match(GamePlayer gp1, GamePlayer gp2)
    {
        System.out.println("Match started");
        player1 = gp1;
        player2 = gp2;
        player1.inGame(player2.getUsername());
        player2.inGame(player1.getUsername());
        run();
    }
        public void run() {
            boolean gameOn = true;
            while (gameOn == true)  
            {
                System.out.print("");
                if(!(player1.getMove().equals(""))&&!(player2.getMove().equals("")))
                {
                    Result result = new Result(player1.getUsername(), player1.getMove(),player2.getUsername(), player2.getMove());
                    player1.sendResult(result);
                    player2.sendResult(result);
                }
            }    
        }
}
