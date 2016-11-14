package FileServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Player.*;
import java.util.ArrayList;

public class Query extends Thread {

    BufferedReader input;
    PrintWriter output;
    Socket socket;

    public Query(Socket so) throws Exception {
        socket = so;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                String command = input.readLine();
                if (command != null) {
                    if (command.startsWith("LOGIN")) {
                        String[] cA = command.substring(6).split(",");
                        UserSearch us = new UserSearch(cA[0], cA[1]);
                        User tempUser = us.search();
                        if (tempUser == null) {
                            output.println("FAILIURE The username and password do not match. Please try again");
                        } else {

                            output.println("SUCCESS " + tempUser.getW() + "," + tempUser.getD() + "," + tempUser.getL());
                            socket.close();
                            interrupt();
                        }
                    } else if (command.startsWith("REGISTER")) {
                        String[] cA = command.substring(9).split(",");
                        UserSearch us = new UserSearch(cA[0], cA[1]);

                        boolean check = us.exists();
                        if (check == false) {
                            UserAdd ua = new UserAdd(new User(cA[0], cA[1]));
                            ua.add();
                            output.println("REGISTERED");
                            socket.close();
                            interrupt();
                        } else {
                            output.println("FAILIURE The username already exists. Try again with a different username.");
                        }

                    } else if (command.startsWith("LEADERBOARDDATA")) {
                        LeaderboardSearch board = new LeaderboardSearch();
                        ArrayList<User> temp = board.getLeaderboardItems();
                        String data = "";
                        for (int i = 0; i < temp.size(); i++) {
                            data += temp.get(i).getUsername() + " " + temp.get(i).getW() + " " + temp.get(i).getL() + ",";
                        }
                        output.println(data);
                        socket.close();
                        interrupt();
                    } else if (command.startsWith("friendList")) {
                        String[] cA = command.split(", ");
                        FriendSearch s = new FriendSearch(cA[1]);
                        String list = s.getFriends();
                        output.println(list);
                    } else if (command.startsWith("editNickname")) {
                        String[] cA = command.split(", "); //editNickname,  currentPlayer.getUsername(), selected, nick
                        FriendSearch s = new FriendSearch(cA[1]);
                        s.editNick(cA[2], cA[3]);
                        String list = s.getFriends();
                        output.println(list);
                    } else if (command.startsWith("inviteToGame")) {
                        String[] cA = command.split(", "); //inviteToGame, currentPlayer.getUsername(),  selected
                    } else if (command.startsWith("addFriend")) {
                        String[] cA = command.split(", "); // addFriend, currentPlayer.getUsername(), addedFriend
                        System.out.println("Adding friend");
                        FriendSearch s = new FriendSearch(cA[1]);
                        boolean added = s.addFriend(cA[2]);
                        if (added == true) {
                            output.println("SUCCESS");
                            String list = s.getFriends();
                            output.println(list);
                        } else {
                            output.println("FAILIURE");
                        }
                    } else if (command.startsWith("CLOSE")) {
                        socket.close();
                        interrupt();
                    }
                }

            } catch (IOException e) {
                System.out.println("User died: " + e);
                try {
                    socket.close();
                } catch (IOException ex) {
                }
                interrupt();
            }
        }
        System.out.println("Query closed.");
    }

}
