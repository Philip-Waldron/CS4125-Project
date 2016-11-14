package UI;

import Player.User;

public class MainMenuUIFactory extends UIFactory {

    User currentUser;

    public MainMenuUIFactory(User cu) {
        currentUser = cu;
    }

    public void create() {
        MainMenuUI gui = new MainMenuUI(currentUser);
    }
}
