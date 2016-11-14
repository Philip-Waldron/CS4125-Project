package Game;

import UI.GameUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dean
 */
public class GameController implements ActionListener {

    private GameUI gui;
    private GameModel gm;
    public boolean canMove = false;

    public GameController(GameUI ui) throws IOException{
        gui = ui;
        gm = new GameModel(gui);
    }
    
    public void closing() throws IOException
    {
        int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the match?\n\nThe round will be forfeited.", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    gm.finish();
                    gui.close();
                }
    }
    
    public void move()
    {
        canMove = true;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (canMove == true) {
            if (source == gui.rock) {
                gui.setText("Please wait...");
                gm.sendMessage("MOVE rock");
                canMove = false;
            } else if (source == gui.paper) {
                gui.setText("Please wait...");
                gm.sendMessage("MOVE paper");
                canMove = false;
            } else if (source == gui.scissors) {
                gui.setText("Please wait...");
                gm.sendMessage("MOVE scissors");
                canMove = false;
            }
        }
    }
}
