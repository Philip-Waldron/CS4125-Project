/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Player.*;
import java.io.IOException;
/**
 *
 * @author Dean
 */
public class ResultWriter {
    User user;
    public ResultWriter(User u){
            user = u;
}
    public void write() throws IOException
    {
    ArrayList<String> users = new ArrayList<String>();
                                Scanner sc = new Scanner(new FileReader ("Usernames.txt"));
                                boolean found = false;
                                while (sc.hasNext()) {
                                        String temp = sc.nextLine();
                                        users.add(temp);
                                }
                                //Edit the user's records
                                for(int c = 0; c < users.size()&& found == false; c++)
                                {
                                    String[] temp = (users.get(c)).split(",");
                                    if(user.getUsername().toLowerCase().equals(temp[0].toLowerCase()))
                                    {
                                        user.setPassword(temp[1]);
                                        users.set(c, user.getUsername() + "," + user.getPassword() + "," + user.getW() + "," + user.getD() + "," + user.getL());
                                        found = true;
                                    }
                                    
                                }
                                //Rewrite the data back to the file
                                BufferedWriter bw = new BufferedWriter(new FileWriter("Usernames.txt"));
                                int counter = 0;
                                while (counter < users.size()) {
                                        bw.write(users.get(counter));
                                        bw.newLine();
                                        counter++;
                                }
                                bw.close();
}
}