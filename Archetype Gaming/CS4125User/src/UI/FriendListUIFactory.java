package UI;

import Player.User;

/**
 *
 * @author Dean
 */
public class FriendListUIFactory extends UIFactory {

    User currentUser;
    MainMenuUI mmui;

    public FriendListUIFactory(User cu, MainMenuUI ui) {
        mmui = ui;
        currentUser = cu;
    }

    public void create() {
        FriendListUI flui = new FriendListUI(currentUser, mmui);
    }
}
