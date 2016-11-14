package UI;

import Player.User;
import java.io.IOException;

public class ChatRoomUIFactory extends UIFactory {

    User currentUser;
    MainMenuUI mmui;

    public ChatRoomUIFactory(User cu, MainMenuUI ui) {
        currentUser = cu;
        mmui = ui;
    }

    public void create() throws IOException {
        ChatRoomUI afui = new ChatRoomUI(currentUser, mmui);
    }
}
