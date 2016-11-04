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
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderboardModel {
    private static ArrayList<String[]> entries;
    private final File leaderboards;

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
    
    public ArrayList<String[]> getEntries() {
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
        while ((myLine = bufRead.readLine()) != null)
        {    
            String[] temp = myLine.split(",");
            wl = Double.toString(computeWLRatio(temp));
            String[] line = {temp[0], temp[1], temp[2], wl};
            entries.add(line);
        }
    }
}
