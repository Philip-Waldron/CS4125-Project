/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Dean
 */
public class UserAdd {
    User user;
    public UserAdd(User u)
    {
        user = u;
    }
    public void add() throws IOException
    {
         BufferedWriter x = new BufferedWriter(new FileWriter("Usernames.txt", true));

						x.write(user.username + "," + user.password + ",0,0,0");
						x.newLine();
						x.close();
    }
}
