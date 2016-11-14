package FileServer;

import java.net.ServerSocket;

public class FileServer extends Thread {

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
