package Game;

import javax.swing.JOptionPane;

public class Draw extends GameReport {

    public Draw(String[] r) {
        results = r;
    }

    public boolean report() {
        int check = JOptionPane.showConfirmDialog(null, results[0] + " chose " + results[1] + ".\n"
                + results[2] + " chose " + results[3] + ".\n\nIt was a draw!\n\nWould you like to find a new game?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
