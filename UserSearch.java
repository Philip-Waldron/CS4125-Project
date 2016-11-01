/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115server;

import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Dean
 */
public class UserSearch {
    String username = "", password = "";
    int wins =0 , draws = 0, losses = 0;
    public UserSearch(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public User search(boolean justUsername) throws Exception
    {
                        boolean found = false;
                        Scanner sc = new Scanner(new FileReader("Usernames.txt"));
			while (sc.hasNext() && found == false) {
				String u = sc.nextLine();
				String uA[] = u.split(",");
				String uTemp = uA[0].toLowerCase();
                                String pTemp = uA[1].toLowerCase();
				if (username.toLowerCase().equals(uTemp))
                                {
                                 if(justUsername == false)
                                 {
                                     if(password.toLowerCase().equals(pTemp))
                                         {
                                        wins = Integer.parseInt(uA[1]);
                                        draws = Integer.parseInt(uA[2]);
                                        losses = Integer.parseInt(uA[3]);
                                        
					found = true;
                                         }
                                 }
                                else
                                {
                                        wins = Integer.parseInt(uA[1]);
                                        draws = Integer.parseInt(uA[2]);
                                        losses = Integer.parseInt(uA[3]);
                                        
					found = true;
				}
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
}
