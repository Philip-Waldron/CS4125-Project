package Server;

import GameServer.GameServer;
import FileServer.FileServer;
import ChatServer.ChatServer;

public class ServerFactory {
    public static void main(String[] args) throws Exception {
        FileServer fs = new FileServer();
        GameServer gs = new GameServer();
        ChatServer cs = new ChatServer();
    }
}
