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
import java.util.ArrayList;

/**
 *
 * @author Dean
 */
public class Lobby {
    ArrayList<LobbyPlayer> lobbyA;
    public Lobby(){
    lobbyA = new ArrayList<LobbyPlayer>();
    for(int i = 0; i<lobbyA.size()-1; i++)
                           {
                               if(Math.abs(lobbyA.get(i).getScore() - lobbyA.get(i+1).getScore())<=50)
                               {
                                   Match m = new Match();
                                   
                               }
                           }
}
}
