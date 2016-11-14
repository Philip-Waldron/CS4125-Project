package FileServer;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class FriendSearch extends Search {

    public FriendSearch(String user) {
        username = user;
    }

    public String getFriends() throws IOException {

        boolean found = false;
        String u = "You have no friends :(";;
        Scanner sc = new Scanner(new FileReader("friends.txt"));
        while (sc.hasNext() && found == false) {
            String temp = sc.nextLine();
            if (temp.startsWith(username)) {
                u = temp;
                found = true;
            }
        }
        sc.close();
        
        return u;

    }

    public boolean addFriend(String friend) throws IOException {
        ArrayList<String> users = new ArrayList<String>();
        Scanner sc = new Scanner(new FileReader("friends.txt"));
        boolean found1 = false, found2 = false;
        int index1 = 0, index2 = 0;
        String line1 = "", line2 = "";
        while (sc.hasNext()) {
            String temp = sc.nextLine();
            users.add(temp);
        }
        
        System.out.println(users.size());
        
        for (int c = 0; (c < users.size()); c++) {
            String lineTemp = users.get(c);
            String[] temp = lineTemp.split(", ");
            System.out.println(friend + "\n" + temp[0]);
            if (username.toLowerCase().equals(temp[0].toLowerCase())) {
                line1 = lineTemp + ", " + friend + ", ()";
                index1 = c;
                found1 = true;
                System.out.println("found1 = true");
            } else if (friend.toLowerCase().equals(temp[0].toLowerCase())) {
                line2 = lineTemp + ", " + username + ", ()";
                index2 = c;
                found2 = true;
                System.out.println("found2 = true");
            }
        }
        if (found1 == true && found2 == true) {
            users.set(index1, line1);
            users.set(index2, line2);

            //Rewrite the data back to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter("friends.txt"));
            int counter = 0;
            while (counter < users.size()) {
                bw.write(users.get(counter));
                bw.newLine();
                counter++;
            }
            bw.close();
            return true;
        } else {
            return false;
        }

    }

    public void editNick(String selected, String nick) throws IOException {
        ArrayList<String> users = new ArrayList<String>();
        Scanner sc = new Scanner(new FileReader("friends.txt"));
        boolean found = false;
        while (sc.hasNext()) {
            String temp = sc.nextLine();
            users.add(temp);
        }
        //Edit the user's records
        for (int c = 0; c < users.size() && found == false; c++) {
            String[] temp = (users.get(c)).split(", ");
            if (username.toLowerCase().equals(temp[0].toLowerCase())) {
                boolean found2 = false;
                for (int co = 1; co < temp.length && found2 == false; co += 2) {
                    if (selected.equals(temp[co])) {
                        found2 = true;
                        String temp2 = username + ", ";
                        temp[co + 1] = nick;
                        for (int c2 = 1; c2 < temp.length; c2++) {
                            temp2 += temp[c2] + ", ";
                        }
                        users.set(c, temp2);
                    }
                }
                found = true;
            }
        }
        //Rewrite the data back to the file
        BufferedWriter bw = new BufferedWriter(new FileWriter("friends.txt"));
        for (int counter = 0; counter < users.size(); counter++) {
            bw.write(users.get(counter));
            bw.newLine();
        }
        bw.close();
    }
}
