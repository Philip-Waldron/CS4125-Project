package Friend;

import UI.FriendListUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Pawel
 */
public class FriendListController implements ActionListener {

    private FriendListUI flui;
    private FriendListModel flm;

    public FriendListController(FriendListUI ui) {
        flui = ui;
        flm = new FriendListModel(flui);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == flui.btnEditNickname) {
            if (flui.list.getSelectedValue() != null) {
                String[] temp = flui.list.getSelectedValue().split(" - ");
                String selected = temp[0];
                String nick = JOptionPane.showInputDialog(null, "Please enter a nickname for " + selected + ".");
                flm.sendMessage("editNickname, " + flui.currentPlayer.getUsername() + ", " + selected + ", " + nick);
            }
            else {
                JOptionPane.showMessageDialog(null,
                "Please select a friend first to edit their nickname!",
                "No Friend Selected",
                JOptionPane.WARNING_MESSAGE);
            }
        } else if (source == flui.btnInviteToGame) {
            String selected = flui.list.getSelectedValue();
            flm.sendMessage("inviteToGame, " + flui.currentPlayer.getUsername() + ", " + selected);
        } else if (source == flui.btnAddFriend) {
            String addedFriend = JOptionPane.showInputDialog(null, "Who do you want to add?");
            boolean canCont = true;
            for (int i = 0; i < flui.usernames.size() && canCont == true; i++) {
                String[] temp = flui.usernames.get(i).split(" - ");
                String selected = temp[0];
                if (addedFriend.equals(selected)) {
                    canCont = false;
                }
            }
            if (canCont == false) {
                JOptionPane.showMessageDialog(null, "The user is already in your friends list.", "Invalid Friend", 0);
            } else {
                flm.sendMessage("addFriend, " + flui.currentPlayer.getUsername() + ", " + addedFriend);
            }
        } else if (source == flui.btnBack) {
            flui.frame.setVisible(false);
            flui.mmui.setVisible(true);
            flm.sendMessage("CLOSE");
            flm.interrupt();
        }
    }

}
