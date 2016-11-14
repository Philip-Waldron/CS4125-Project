package FileServer;

import java.io.FileReader;
import java.util.Scanner;
import Player.*;
import java.io.IOException;

public class UserSearch extends Search{
    public UserSearch(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public User search() throws IOException
    {
        boolean found = false;
        Scanner sc = new Scanner(new FileReader("Usernames.txt"));
        while (sc.hasNext() && found == false) {
                String u = sc.nextLine();
                String uA[] = u.split(",");
                String uTemp = uA[0].toLowerCase();
                String pTemp = uA[1].toLowerCase();
                if (username.toLowerCase().equals(uTemp)&&(password.toLowerCase().equals(pTemp)))
                {
                    wins = Integer.parseInt(uA[2]);
                    draws = Integer.parseInt(uA[3]);
                    losses = Integer.parseInt(uA[4]);
                    found = true;
                 }
        }
        sc.close();
        if (found == false)
                return null;
        else
        {
            User uFromFile = new User(username, password, wins, draws, losses);
            return uFromFile;
        }
                    
    }
    public boolean exists() throws IOException
    {
        boolean found = false;
        Scanner sc = new Scanner(new FileReader("Usernames.txt"));
        while (sc.hasNext() && found == false) {
            String u = sc.nextLine();
            String uA[] = u.split(",");
            String uTemp = uA[0].toLowerCase();
            if (username.toLowerCase().equals(uTemp))
                found = true;
        }
        sc.close();
        if (found == false)
            return false;
        else
            return true;
                        
    }
    
}
