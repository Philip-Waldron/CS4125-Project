package cs4125.project.leaderboards;

/**
 *
 * @author Philip Waldron
 */
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        new Main();
    }
    
    private Main() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderboardController();
            }
        });
    }
}
