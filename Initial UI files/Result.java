/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

/**
 *
 * @author Dean
 */
public class Result {
    String player1, player2, move1, move2, winner;
    public Result(String p1, String m1, String p2, String m2)
    {
        player1 = p1;
        player2 = p2;
        move1 = m1;
        move2 = m2;
        if(move1.equals(move2))
        winner = "draw";
        else if((move2.equals("rock")&&move1.equals("paper"))
                ||(move2.equals("paper")&&move1.equals("scissors"))
                ||(move2.equals("scissors")&&move1.equals("rock")))
        winner = player1;
        else if((move1.equals("rock")&&move2.equals("paper"))
                ||(move1.equals("paper")&&move2.equals("scissors"))
                ||(move1.equals("scissors")&&move2.equals("rock")))
        winner = player2;
    }
   
    public String getResults()
    {
        return player1 + "," + move1 + "," + player2 + "," + move2 + "," + winner;
    }
    public String getWinner()
    {
        return winner;
    }
}
