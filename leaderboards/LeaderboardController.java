package cs4125.project.leaderboards;

/**
 *
 * @author Philip Waldron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardController {
    private LeaderboardUI component = new LeaderboardUI();
    private LeaderboardModel model = new LeaderboardModel();
    
    public LeaderboardController() {
        component.setVisible(true);
        component.addAlphabeticButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Alphabetic sort button pressed");
            }
        });
        
        component.addWinButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Win sort button pressed");
            }
        });
        
        component.addWLRatioButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("W/L sort button pressed");
            }
        });
        
        component.addFilterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Filter button pressed");
            }
        });
        populateTable();
    }

    
    private void populateTable() {
        component.populateTable(model.getEntries());
    }
}
