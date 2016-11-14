package GameServer;

import java.util.ArrayList;

public class MatchMaker extends Thread {

    ArrayList<GamePlayer> lobbyA;

    public MatchMaker() {
        lobbyA = new ArrayList<GamePlayer>();
        start();
    }

    public void addPlayer(GamePlayer gp) {
        lobbyA.add(gp);

    }

    public void run() {
        while (true) {
            System.out.print("");
            for (int i = 0; i < lobbyA.size() - 1; i++) {
                if (lobbyA.get(i).getStatus() == 0) {
                    int lowest = 10000, lowestJ = -1;
                    for (int j = i + 1; j < lobbyA.size(); j++) {

                        if (lobbyA.get(j).getStatus() == -1) {
                            lobbyA.remove(j);
                        } else if (lobbyA.get(j).getStatus() == 0) {
                            int temp = Math.abs(lobbyA.get(i).getScore() - lobbyA.get(j).getScore());
                            System.out.println(temp);
                            if (temp < lowest) {
                                lowestJ = j;
                                lowest = temp;
                            }
                        }

                    }
                    if (lowestJ != -1) {
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
