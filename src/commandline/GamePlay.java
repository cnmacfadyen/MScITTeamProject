package commandline;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
	int numOfGamesPlayed =0; // number of games a player played.
	int totalScore; 	// total score of each game for a player.
	
	// in player class we'll need score count for each player.
	
	boolean playNewGame; // when user select to play again/new game
	boolean quitGame;	//when user quits the game.
	//boolean veiwStatistics; //when user wants to view statistics of previous games.
	
	int indx =0; // this will be used in selectStartingPlayer method and selectCategory method.
	Random random = new Random();
	int selectRandom = random.nextInt(5); //for selecting a starting player.
	
	String selectedCategory;
	int selectedCategoryValue;
	
	int maxSize = 40; //size of the ArrayList
	Player winnerPlayer = new Player();
	
	private ArrayList<Card> unshuffledDeck = new ArrayList<Card>(maxSize);
	private ArrayList<Card> shuffledDeck = new ArrayList<Card>(maxSize);
	private ArrayList<Card> playerDeck = new ArrayList<Card>(maxSize);
	private ArrayList<Card> communalPile = new ArrayList<Card>(maxSize);
	private ArrayList<Card> currentCardsInRound = new ArrayList<Card>(maxSize);
	private ArrayList<Player> players = new ArrayList<Player>(5);
	
	
	//-------------------------------------------------------------
	
	public void getNumberOfGames() {
		
		this.numOfGamesPlayed++;
	}
	
	//viewStatisticsOrPlay();
	//ask the user to type 1 for viewing statistics of previous games. or 2 for starting a new game.
	public void selectOption(Scanner in)
	{
		int inputForOption = in.nextInt();
		if(inputForOption == 1) {
			viewStatistics(true);	
		}
		else if(inputForOption == 2) {
			playNewGame();
		}
		else {
			System.out.println("Your input is incorrect");
		}
	}
	
	
	public void playNewGame() {
		//start new game
	}
	
	// if user typed "q" or "Q" for input then the game is terminated. data is saved???
	//we can also put this into the selectOption method as another condition for input.
	public void quit(Scanner in) {
		String inputForQ = in.next();
		if(inputForQ == "q" || inputForQ == "Q") {
			System.exit(1);
		}
	}
	
	public void viewStatistics(boolean selectView) {
		//connect to database for statistics ??
	}
	
	
	public void getWinner(Player p) {
		
		
		
	}
	
	//should it return the object element from the players arraylist or
	//better return the indext of the player element??
	public int selectStartingPlayer() {
		
		Object startPlayer = null;
			//get player 1 and set current player to player 1  (Human Player)
			if(selectRandom == 0) {
				startPlayer = players.get(0);
				indx = players.indexOf(startPlayer);
			}
			//get player 2 and set current player to (AI player 1)
			else if(selectRandom == 1) {
				startPlayer = players.get(1);
				indx = players.indexOf(startPlayer);
			}
			//get player 3 and set current player to (AI player 2)
			else if(selectRandom == 2) {
				startPlayer = players.get(2);
				indx = players.indexOf(startPlayer);
			}
			//get player 4 and set current player to (AI player 3)
			else if(selectRandom == 3) {
				startPlayer = players.get(3);
				indx = players.indexOf(startPlayer);
			}
			//get player 5 and set current player to (AI player 4)
			else if(selectRandom == 4) {
				startPlayer = players.get(4);
				indx = players.indexOf(startPlayer);
			}
		
		//return startPlayer;
		return indx;
		
	}

	public int selectCategory() {
		int selectRandomCategory = random.nextInt(5);
		int selectedCat =0;
		//human player
		if(indx == 0) {
			Scanner in = new Scanner(System.in);
			int indx = in.nextInt();  // to select a category from 1 to 5.
			//will probably need an exception here to allow user to enter the correct input number.
			if(indx >=0 && indx <5) {
				selectedCat = indx;
			}
			else {
				System.out.println("category number should be from 0-4. \n Re-enter category number");
			}
		}
		//AI player 1
		else if(indx == 1) {
			selectedCat = selectRandomCategory;
		}
		//AI player 2
		else if(indx== 2) {
			selectedCat = selectRandomCategory;
		}
		//AI player 3
		else if(indx == 3) {
			selectedCat = selectRandomCategory;
		}
		//AI player 4
		else if(indx == 4) {
			selectedCat = selectRandomCategory;
		}
		
		// might need to move the following two lines to a seperate method.
//		selectedCategory = playerCard.getCategoryName();
//		selectedCategoryValue = playerCard.getCategoryValue();
		
		return selectedCat;
	}

	
	public void addToCommunalPile() {
		
	}
	
	public void playRound() {
		
	}
	
	public void viewCardData(Player p)
	{
		
	}
	
	public void getHighestValue() {
		
	}
	//will be needed for keeping track of stats
	public void getCount() {
		
	}
	
	
	public void getRoundNumber() {
		
	}
	

	public void getPlayerNumber() {
		
	}

	public void viewWinningCard() {
		
	}

	
}
