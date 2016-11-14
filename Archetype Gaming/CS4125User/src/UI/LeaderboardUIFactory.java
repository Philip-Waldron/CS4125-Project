package UI;

import Player.User;

public class LeaderboardUIFactory extends UIFactory {

    LeaderboardUI lbui;
    User currentUser;

    public LeaderboardUIFactory(User cu) {
        currentUser = cu;
    }

    @Override
    public void create() {
         lbui = new LeaderboardUI(currentUser);
    }
}
