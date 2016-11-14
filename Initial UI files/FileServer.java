/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileServer;

import java.net.ServerSocket;

/**
 *
 * @author Dean
 */
public class FileServer extends Thread {

    /**
     * @param args the command line arguments
     */
    ServerSocket listener;

    public FileServer() throws Exception {
        listener = new ServerSocket(5555);
        start();
    }

    public void run() {
        System.out.println("File Server is Running");
        while (true) {
            try {
                Query q = new Query(listener.accept());
                q.start();
            } catch (Exception e) {
                System.out.println("Player died: " + e);
            } 
        }
    }
}
