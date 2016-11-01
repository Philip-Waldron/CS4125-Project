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
public class ServerFactory {
    public static void main(String[] args) throws Exception {
        FileServer fs = new FileServer();
        GameServer gs = new GameServer();
    }
}
