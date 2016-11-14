package cs4125User;

import UI.UIFactory;
import UI.LogInUIFactory;
import java.io.IOException;

/**
 *
 * @author Dean
 */
public class CS4115User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UIFactory uif = new LogInUIFactory();
        try {
            uif.create();
        } catch (IOException e) {
            System.out.println("I/O Error\n" + e);
        }
    }

}
