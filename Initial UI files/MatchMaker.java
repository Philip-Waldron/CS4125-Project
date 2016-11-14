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
import java.util.ArrayList;

/**
 *
 * @author Dean
 */
public class MatchMaker extends Thread {

    ArrayList<GamePlayer> lobbyA;

    public MatchMaker() {
        lobbyA = new ArrayList<GamePlayer>();
    }

    public void addPlayer(GamePlayer gp) {
        lobbyA.add(gp);
        if (lobbyA.size() >= 2) {
            System.out.println("Begin");
            start();
        }
    }

    public void run() {
        while (lobbyA.size() >= 2) {
            for (int i = 0; i < lobbyA.size() - 1; i++) {
                if (lobbyA.get(i).getStatus() == 0) {
                    int lowest = 10000, lowestJ = -1;
                    for (int j = i + 1; j < lobbyA.size(); j++) {

                        if (lobbyA.get(j).getStatus() == -1) {
                            lobbyA.remove(j);
                        }
                        else if (lobbyA.get(j).getStatus() == 0) {
                            int temp = Math.abs(lobbyA.get(i).getScore() - lobbyA.get(j).getScore());
                            System.out.println(temp);
                            if (temp < lowest) {
                                lowestJ = j;
                                lowest = temp;
                            }
                        }

                    }
                    if(lowestJ!=-1)
                    {
                        System.out.println("Match made");
                    Match m = new Match(lobbyA.get(i), lobbyA.get(lowestJ));
                    }
                } else if (lobbyA.get(i).getStatus() == -1) {
                    lobbyA.remove(i);
                }
            }
        }
    }
}
