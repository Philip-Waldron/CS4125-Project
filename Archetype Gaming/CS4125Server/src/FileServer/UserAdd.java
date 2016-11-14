package FileServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Player.*;

public class UserAdd {
    User user;
    public UserAdd(User u)
    {
        user = u;
    }
    public void add() throws IOException
    {
         BufferedWriter x = new BufferedWriter(new FileWriter("Usernames.txt", true));

        x.write(user.getUsername() + "," + user.getPassword() + ",0,0,0");
        x.newLine();
        x.close();
        
        BufferedWriter y = new BufferedWriter(new FileWriter("friends.txt", true));

        y.write(user.getUsername());
        y.newLine();
        y.close();
    }
}
