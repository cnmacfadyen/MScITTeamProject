package commandline;

import java.util.ArrayList;

public class Player {

	public boolean gameWinner(ArrayList<Player> p) {
		if (p.size() >= 2) {
			return false;
		} else {
			return true;
		}

	}
	public boolean isEmpty(ArrayList<Card> x) {
		
		return false;
	}
	public boolean removeLoser(ArrayList<Integer> p) {
		boolean lost = true;
		if (lost) {
			for (int i = 0; i < p.size(); i++) {
				if (p.get(i) == 0) {
					p.remove(i);
					return true;
				}

			}
		}
		/*
		 * in game play put this in if (Player.gameWinner(){ arraylist.get(0).getName();
		 * - to get the winner })
		 * 
		 */
		return false;
	}

	private int playerNumber;
	
	 public Player(int pNumber) {
		 this.playerNumber = pNumber;
	 }
	 
	 public int getPlayerNumber() {
		 return playerNumber;
	 }
	 
	 public void setPlayerNumber(int num) {
		 playerNumber=num;  
	 }
//<<<<<<< HEAD
//=======
//
//>>>>>>> e15554c0b8a5951bfafd8ef3fab7cb7b19e379b6
}
