package GameServer;

public class Match extends Thread{

    GamePlayer player1, player2;
    public Match(GamePlayer gp1, GamePlayer gp2)
    {
        System.out.println("Match started");
        player1 = gp1;
        player2 = gp2;
        player1.inGame(player2.getUsername());
        player2.inGame(player1.getUsername());
        System.out.println("Game begin.");
        start();
    }
        public void run() {
            while (!Thread.interrupted())  
            {
                System.out.print("");
                if(!(player1.getMove().equals(""))&&!(player2.getMove().equals("")))
                {
                    Result result = new Result(player1.getUsername(), player1.getMove(),player2.getUsername(), player2.getMove());
                    player1.sendResult(result);
                    player2.sendResult(result);
                    interrupt();
                    System.out.println("Game over.");
                }
            }
            return;
        }
}
