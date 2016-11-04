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
                populateTable("Aphabetic");
            }
        });
        
        component.addWinButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                populateTable("Wins");
            }
        });
        
        component.addWLRatioButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                populateTable("WLRatio");
            }
        });
        
        component.addFilterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String text = component.filterTextArea.getText();
                populateTable(text);
            }
        });
        populateTable("raw");
    }

    
    private void populateTable(String sort) {
        component.populateTable(model.getEntries(sort));
    }
}
