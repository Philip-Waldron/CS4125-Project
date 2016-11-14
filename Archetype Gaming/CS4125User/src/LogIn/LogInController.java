package LogIn;

import UI.LogInUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {
    
    private LogInUI liui;
    private LogInModel lim;
    
    public LogInController(LogInUI ui)
    {
        liui = ui;
        lim = new LogInModel(liui);
        
    }
    public void logIn(String[] credentials) {
            
            if (!(credentials[0].equals("")) && !(credentials[1].equals(""))) {
                lim.sendMessage("LOGIN " + credentials[0] + "," + credentials[1]);
                lim.setUsername(credentials[0]);
            } else {
                liui.empty();
            }
    }
    public void register(String[] credentials)
    {
        if (!(credentials[0].equals("")) && !(credentials[1].equals(""))) {
                lim.sendMessage("REGISTER " + credentials[0] + "," + credentials[1]);
                lim.setUsername(credentials[0]);
            } else {
                liui.empty();
            }
    }
     public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == liui.logIn) {
            String[] credentials = {liui.uNameI.getText(), String.valueOf(liui.passI.getPassword())};
            logIn(credentials);
        } else if (source == liui.newUser) {
            String[] credentials = {liui.uNameI.getText(), String.valueOf(liui.passI.getPassword())};
            register(credentials);
        }
    }
}
