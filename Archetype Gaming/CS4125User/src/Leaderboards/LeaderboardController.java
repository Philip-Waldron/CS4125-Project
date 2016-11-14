package Leaderboards;

/**
 *
 * @author Philip Waldron
 */
import Player.User;
import UI.LeaderboardUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardController {

    private LeaderboardUI component;
    private LeaderboardModel model;
    private User currentUser;

    public LeaderboardController(LeaderboardUI lui, User currentUserTemp) {
        currentUser = currentUserTemp;
        component = lui;
        model = new LeaderboardModel();
        component.setVisible(true);
        
        component.addAlphabeticButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                populateTable("Alphabetic");
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
