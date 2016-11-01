/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4115server;

import java.net.ServerSocket;

/**
 *
 * @author Dean
 */
public class FileServer implements Runnable {

    /**
     * @param args the command line arguments
     */
    ServerSocket listener;
     public FileServer() throws Exception {
        listener = new ServerSocket(5555);
        System.out.println("File Server is Running");
        
    }
    public void run() {
        while(true) try {
            while (true) {
                Query lplayer = new Query(listener.accept());
                lplayer.start();
            }
        } 
        catch(Exception e)
        {
        }
        finally {
            try{
            
            listener.close();
            }
            catch(Exception e){}
        }
    }
}
