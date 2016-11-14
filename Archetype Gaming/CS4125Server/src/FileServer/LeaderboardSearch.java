package FileServer;

import Player.User;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Philip Waldron
 */
public class LeaderboardSearch extends Search{
    public LeaderboardSearch(){
        
    }
    
    public ArrayList<User> getLeaderboardItems() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader("Usernames.txt"))) {
            User uFromFile;
            while (sc.hasNext()) {
                String uArray[] = sc.nextLine().split(",");
                username = uArray[0];
                wins = Integer.parseInt(uArray[2]);
                losses = Integer.parseInt(uArray[4]);
                
                uFromFile = new User(username, wins, losses);
                users.add(uFromFile);
            }
        }
        return users;
    }
}
