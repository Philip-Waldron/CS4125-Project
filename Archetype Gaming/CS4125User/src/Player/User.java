package Player;

/**
 *
 * @author Dean
 */
public class User {

    private int wins = 0, draws = 0, losses = 0;
    private String username = "", password = "";

    public User(String name, String pass) {
        username = name;
        password = pass;
    }

    public User(String name, String pass, int w, int d, int l) {
        username = name;
        password = pass;
        wins = w;
        draws = d;
        losses = l;
    }

    public User(String name, int w, int d, int l) {
        username = name;
        wins = w;
        draws = d;
        losses = l;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pass) {
        password = pass;
    }

    public int getW() {
        return wins;
    }

    public int getD() {
        return draws;
    }

    public int getL() {
        return losses;
    }

    public void newW() {
        wins++;
    }

    public void newD() {
        draws++;
    }

    public void newL() {
        losses++;
    }

}
