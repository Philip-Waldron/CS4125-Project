public class Leaderboard {
	private ArrayList players;
	private ArrayList ranks;
	private ArrayList wins;
	private ArrayList losses;
	private ArrayList winLossRatio;
	private File leaderboards;
	
	public Leaderboard() {
		players = new ArrayList();
		Ranks = new ArrayList();
		wins = new ArrayList();
		losses = new ArrayList();
		winLossRatio = new ArrayList();
		leaderboards = new File("leaderboards.txt");
		if(!leaderboards.exists())
			leaderboards.createNewFile();
	}
	
	public static void addPlayer(String name, String rank) {
		//Add player to player arraylist and text file if not already there, adding 0 to all other arraylists and a default rank.
	}
	public static void saveLeaderboards() {
		//save all arraylists into text file
	}
	public static void loadLeaderboards() {
		//load leaderboards from text file
	}
	
	public static void editRank(String name, String rank) {
		//edit rank of player
	}
	public static void editWins(String name, String wins) {
		//edit wins of player
	}
	public static void editLosses(String name, String losses) {
		//edit losses of player
	}
	public static void editWinLossRatio(String name, String wins, String losses) {
		//edit win/loss ratio of player
	}
	
	public static ArrayList getPlayers() {
		return players;
	}
	public static ArrayList getRanks() {
		return ranks;
	}
	public static ArrayList getWins() {
		return wins;
	}
	public static ArrayList getLosses() {
		return losses;
	}
	public static Arraylist getWinLossRatio() {
		return winLossRatio;
	}
}
