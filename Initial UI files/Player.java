public class Player extends Thread {
	private Player opponent;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public Player(Socket socket) {
		this.socket = socket;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
			output.println("Joined Game");
			output.println("Waiting for opponent to connect");
		} catch (IOException e) {
			System.out.println("Connection closed: " + e);
		}
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	//Only when everyone is connected does the thread run
	public void run() {
		//Code to run after everyone is connected
		//gets commands from clients and proccess them
	}
}
