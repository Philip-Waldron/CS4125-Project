/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115server;

/**
 *
 * @author Dean
 */
    public class User {
    int wins = 0, draws = 0, losses = 0;
    String username = "", password = "";
    
    
    public User(String name, String pass)
    {
        username = name;
        password = pass;
    }
    public User(String name, String pass, int w, int d, int l)
    {
        username = name;
        wins = w;
        draws = d;
        losses = l;   
    }
    
   
}

