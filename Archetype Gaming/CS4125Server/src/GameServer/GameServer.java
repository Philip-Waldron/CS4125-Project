package GameServer;

import java.net.ServerSocket;

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