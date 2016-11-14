package cs4125User;

import UI.UIFactory;
import UI.LogInUIFactory;
import java.io.IOException;

public class CS4125User {
    public static void main(String[] args) {
        UIFactory uif = new LogInUIFactory();
        try {
            uif.create();
        } catch (IOException e) {
            System.out.println("I/O Error\n" + e);
        }
    }
}
