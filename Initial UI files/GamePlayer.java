/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import GameServer.Result;
import GameServer.ResultWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Dean
 */
public class GamePlayer extends Thread {

    BufferedReader input;
    PrintWriter output;
    Socket socket;
    String username;
    int score;
    int wins, draws, losses;
    String move = "";
    int status = 0;

    public GamePlayer(Socket socket) throws Exception {
        this.socket = socket;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    public int getScore() {
        return score;
    }
    
    public String getUsername() {
        return username;
    }

    public String getMove() {
        return move;
    }

    public void sendResult(Result result) {
        output.println("RESULT " + result.getResults());
        move = "";
        if (result.getWinner().equals(username)) {
            wins++;
        } else if (result.getWinner().equals("draw")) {
            draws++;
        } else {
            losses++;
        }
    }

    public int getStatus() {
        return status;
    }

    public void inGame(String oppUsername) {
        status = 1;
        output.println("MAKEMOVE " + oppUsername);
    }

    public void run() {
        while (true) {
                try {
                    String command = input.readLine();
                    if (command.startsWith("DETAILS")) {
                        String[] details = command.substring(8).split(",");
                        username = details[0];
                        wins = Integer.parseInt(details[1]);
                        draws = Integer.parseInt(details[2]);
                        losses = Integer.parseInt(details[3]);
                        score = 1000 + (25 * wins) - (25 * losses);
                        output.println("MESSAGE Finding another user...");
                    } else if (command.startsWith("QUIT")) {
                        ResultWriter rw = new ResultWriter(new User(username, wins, draws, losses));
                        rw.write();
                        socket.close();
                        status = -1;
                    } else if (command.startsWith("MOVE")) {
                        move = command.substring(5);
                        System.out.println(command.substring(5));
                    } else if (command.startsWith("FORFEIT")) {
                        losses++;
                        ResultWriter rw = new ResultWriter(new User(username, wins, draws, losses));
                        rw.write();
                        socket.close();
                        status = -1;
                    } else if (command.startsWith("NEWGAME")) {
                        status = 0;
                     
                    }

                } catch (IOException e) {
                }
            }
        }
    }

