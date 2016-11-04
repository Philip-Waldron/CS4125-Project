package cs4125.project.leaderboards;

/**
 *
 * @author Philip Waldron
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderboardModel {
    private static ArrayList<String[]> entries;
    private final File leaderboards;
    private boolean alphaSort = false;

    public LeaderboardModel() {
        entries = new ArrayList<>();
        leaderboards = new File("leaderboards.txt");
        try {
            if(!leaderboards.exists())
                leaderboards.createNewFile();
            readFile();
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String[]> getEntries(String sort) {
        if (sort == "raw") {
            return entries;
        }
        else if (sort == "Aphabetic") {
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
        else if (sort == "Wins") {
            java.util.Collections.sort(entries, new Comparator<String[]>() {    
                @Override
                public int compare(String[] array1, String[] array2) {
                return Integer.parseInt(array2[1]) - Integer.parseInt(array1[1]);
                }
            });
            alphaSort = false;
        }
        else if (sort == "WLRatio") {
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
    
    private void readFile() throws FileNotFoundException, IOException {
        FileReader input = new FileReader("leaderboards.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        String wl;
        entries.clear();
        while ((myLine = bufRead.readLine()) != null)
        {    
            String[] temp = myLine.split(",");
            wl = Double.toString(computeWLRatio(temp));
            String[] line = {temp[0], temp[1], temp[2], wl};
            entries.add(line);
        }
    }
}
