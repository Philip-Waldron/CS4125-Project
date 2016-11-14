package Leaderboards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardModel extends Thread {
    private static ArrayList<String[]> entries;
    private boolean alphaSort = false;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public LeaderboardModel() {
        entries = new ArrayList<>();
        readFile();
    }

    public ArrayList<String[]> getEntries(String sort) {
        if ("raw".equals(sort)) {
            return entries;
        }
        else if ("Alphabetic".equals(sort)) {
            if (alphaSort == false) {
                java.util.Collections.sort(entries, new Comparator<String[]>() {    
                    @Override
                    public int compare(String[] array1, String[] array2) {
                    return array1[0].compareTo(array2[0]);
                    }
                });
                alphaSort = true;
            }
            else {
                java.util.Collections.sort(entries, new Comparator<String[]>() {    
                    @Override
                    public int compare(String[] array1, String[] array2) {
                    return array2[0].compareTo(array1[0]);
                    }
                });
                alphaSort = false;
            }
        }
        else if ("Wins".equals(sort)) {
            java.util.Collections.sort(entries, new Comparator<String[]>() {    
                @Override
                public int compare(String[] array1, String[] array2) {
                return Integer.parseInt(array2[1]) - Integer.parseInt(array1[1]);
                }
            });
            alphaSort = false;
        }
        else if ("WLRatio".equals(sort)) {
            java.util.Collections.sort(entries, new Comparator<String[]>() {    
                @Override
                public int compare(String[] array1, String[] array2) {
                return Double.compare(Double.parseDouble(array2[3]), Double.parseDouble(array1[3]));
                }
            });
            alphaSort = false;
        }
        else {
            if (sort.length() < 1) {
                return entries;
            }
            else {
                String[] filter = sort.split(",");
                String[] temp;
                ArrayList<String[]> entriesTemp = new ArrayList<>();
                for(int i = 0;i < entries.size();i++) {
                    temp = entries.get(i);
                    if (java.util.Arrays.asList(filter).contains(temp[0])) {
                        entriesTemp.add(temp);
                    }
                }
                alphaSort = false;
                return entriesTemp;
            }
        }

        return entries;
    }
    
    private double computeWLRatio(String[] temp) {
        double number = ((Double.parseDouble(temp[1]) / (Double.parseDouble(temp[1]) + Double.parseDouble(temp[2]))));
        number = Math.round(number * 100);
        number = number/100;
        return number;
    }
    
    private void readFile() {
        try {
            socket = new Socket("localhost", 5555);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost.eng");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("\nNo I/O\n" + e);
            System.exit(1);
        }

        out.println("LEADERBOARDDATA");
        entries.clear();
        start();
    }
    
    @Override
    public void run() {
        String response;
        String wl;
        try {
            response = in.readLine();
            String[] temp = response.split(",");
            for (int i = 0; i < temp.length; i++) {
                String[] line = temp[i].split(" ");
                wl = Double.toString(computeWLRatio(line));
                String[] entryLine = {line[0], line[1], line[2], wl};
                entries.add(entryLine);
            }
            socket.close();
            interrupt();
        }
        catch (IOException e) {
            System.out.println("\nNo I/O\n" + e);
        }  
    }
}
