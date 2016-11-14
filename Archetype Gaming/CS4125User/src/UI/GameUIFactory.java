package UI;

import Player.User;
import java.io.IOException;

public class GameUIFactory extends UIFactory {

    User currentUser;
    MainMenuUI mmui;

    public GameUIFactory(MainMenuUI ui, User cu) {
        currentUser = cu;
        mmui = ui;
    }

    public void create() throws IOException {
        GameUI gui = new GameUI(mmui, currentUser);
    }
}
