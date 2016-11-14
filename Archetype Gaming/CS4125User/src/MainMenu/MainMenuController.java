package MainMenu;

import UI.ChatRoomUIFactory;
import UI.FriendListUIFactory;
import UI.GameUIFactory;
import UI.LeaderboardUIFactory;
import UI.LogInUIFactory;
import UI.MainMenuUI;
import UI.UIFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author Dean
 */
public class MainMenuController implements ActionListener {

    private MainMenuUI mmui;
    public boolean inChat = false;
    UIFactory uif;

    public MainMenuController(MainMenuUI ui) {
        mmui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Object source = event.getSource();
            if (source == mmui.joinGame) {
                uif = new GameUIFactory(mmui, mmui.currentUser);
                uif.create();
                mmui.setVisible(false);
            } else if (source == mmui.friendList) {
                uif = new FriendListUIFactory(mmui.currentUser, mmui);
                uif.create();
                //mmui.setVisible(false);
            } else if (source == mmui.leaderboards) {
                uif = new LeaderboardUIFactory(mmui.currentUser);
                uif.create();
            } else if (source == mmui.chatRoom && inChat == false) {
                uif = new ChatRoomUIFactory(mmui.currentUser, mmui);
                uif.create();
                inChat = true;
            } else if (source == mmui.logOut) {
                uif = new LogInUIFactory();
                uif.create();
                mmui.setVisible(false);
            }

        } catch (IOException e) {
            System.out.println("I/O Error\n" + e);
        }
    }
}
