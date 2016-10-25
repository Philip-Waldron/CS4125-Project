public class Game {
	private Player[] playerMoves = {null, null};
	
    public synchronized boolean legalMove(int move, Player player) {
		//Check if move entered is legal
	}

	//Return result of game
	public String getResult() {
		// 1 == Rock, 2 == Paper, 3 == Scissors
		if(playerMoves[0] != null && playerMoves[1] != null) {
			switch (playerMoves[0]) {
				case 1: if (playerMoves[1] == 1)
							return "Draw";
						else if (playerMoves[1] == 2)
							return "Player1Win";
						else
							return "Player0Win";
						break;
				case 2: if (playerMoves[1] == 1)
							return "Player0Win";
						else if (playerMoves[1] == 2)
							return "Draw";
						else
							return "Player1Win";
						break;
				case 3: if (playerMoves[1] == 1)
							return "Player1Win";
						else if (playerMoves[1] == 2)
							return "Player0Win";
						else
							return "Draw";
						break;
			}
		}
		else
			return "Wait"
	}
}
