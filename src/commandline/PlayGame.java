package commandline;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Random;
	import java.util.Scanner;

	public class PlayGame {
		static Card c = new Card();
		 //this is to test the methods in this class...
		static ArrayList<Card> deck =  (ArrayList<Card>) c.getDeck().clone();
		static FileWriterClass f = new FileWriterClass();
		static int maxSize = 40; //size of the ArrayList
		private ArrayList<Card> unshuffledDeck = new ArrayList<Card>(maxSize);
		private static ArrayList<Card> shuffledDeck = new ArrayList<Card>(maxSize);
		private ArrayList<Card> playerDeck = new ArrayList<Card>(maxSize);
		private static ArrayList<Card> communalPile = new ArrayList<Card>(maxSize);
		private static ArrayList<Card> p1Hand = new ArrayList<Card>();
		private static ArrayList<Card> p2Hand = new ArrayList<Card>();
		private static ArrayList<Card> p3Hand = new ArrayList<Card>();
		private static ArrayList<Card> p4Hand = new ArrayList<Card>();
		private static ArrayList<Card> p5Hand = new ArrayList<Card>();
		private static ArrayList<Card> currentCardsInRound = new ArrayList<Card>();
		private static ArrayList<Player> players = new ArrayList<Player>(5);
		private static ArrayList<Integer> newA = new ArrayList<Integer>();
		private static boolean cardsToCommunal;
		int numOfGamesPlayed =0; // number of games a player played.
		int totalScore; 	// total score of each game for a player.
		
		// in player class we'll need score count for each player.
		static boolean start = false;
		boolean playNewGame; // when user select to play again/new game
		boolean quitGame;	//when user quits the game.
		//boolean veiwStatistics; //when user wants to view statistics of previous games.
		
		static int indx =0; // this will be used in selectStartingPlayer method and selectCategory method.
		static Random random = new Random();
		static int selectRandom = random.nextInt(5); //for selecting a starting player.
		static Player x = new Player(10);
		
		String selectedCategory;
		int selectedCategoryValue;
		static Player winnerPlayer;
		
		static int selectedCat;
		
		//-------------------------------------------------------------
		public static void openApplication() {
			System.out.println("Welcome to Top Trumps, Player 1. Would you like to play a game or view statistics from previous games?");
			System.out.println("Press 1 to view statistics, 2 to play a game. Press q at any time to quit.");
			Scanner s = new Scanner(System.in);
			selectOption(s);
		}

		//viewStatisticsOrPlay();
		//ask the user to type 1 for viewing statistics of previous games. or 2 for starting a new game.
		public static void selectOption(Scanner in) {
			while(start==false) {
			try {
			String inputForOption = in.next();
			if(inputForOption.equals("1")) {
				viewStatistics(true);
				start = false;
				//do database stuff
			}
			else if(inputForOption.equals("2")) {
				Player p1 = new Player(1);
				Player p2 = new Player(2);
				Player p3 = new Player(3);
				Player p4 = new Player(4);
				Player p5 = new Player(5);
				players.add(p1);
				players.add(p2);
				players.add(p3);
				players.add(p4);
				players.add(p5);
				c.setAttNames();
				p1Hand = dealCards(5, 1, getShuffledDeck());
				p2Hand = dealCards(5, 2, getShuffledDeck());
				p3Hand = dealCards(5, 3, getShuffledDeck());
				p4Hand = dealCards(5, 4, getShuffledDeck());
				p5Hand = dealCards(5, 5, getShuffledDeck());
				playFirstRound();
				
			}
			else if (inputForOption.equals("q") || inputForOption.equals("Q")) {
				quit(in);
			}
			else {
				throw new InvalidInputException();
				}
			}
			catch (InvalidInputException e) {
				System.out.println("Your input is incorrect. Please enter 1 to view statistics, 2 to play a game, or q to quit.");
				start = false;
			}
		}
		}
		
		public static void playFirstRound() {
			//start new game
			System.out.println("Player " + (selectStartingPlayer() +1) + " will select the category for the next round.");
			System.out.println("Here is your card: " + p1Hand.get(0).toString());
			System.out.println("The categories are as follows:\n0. " + c.getCat1Name() + "\n1. " + c.getCat2Name() + "\n2. " +
								c.getCat3Name() + "\n3. " + c.getCat4Name() + "\n4. " + c.getCat5Name());
			selectCategory();
			System.out.println("The selected category is " + selectCategory() + ".");
			getNewA();
			checkDuplicate();
			if (cardsToCommunal == false) {
				System.out.println("This was the winning card:" + winningCard());
			}
			else {
				System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
			}
			//add round object here
			start = true;
			
		}
		
		// if user typed "q" or "Q" for input then the game is terminated. data is saved???
		//we can also put this into the selectOption method as another condition for input.
		public static void quit(Scanner in) {
				System.out.println("Goodbye.");
				in.close();
				System.exit(1);
			}
		
		
		public static void viewStatistics(boolean selectView) {
			//connect to database for statistics ??
		}
		
		//should it return the object element from the players arraylist or
		//better return the indext of the player element??
		public static int selectStartingPlayer() {
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

		public static int selectCategory() {
			int selectRandomCategory = random.nextInt(5);
			selectedCat =1;
			//human player
			if(indx == 0) {
				Scanner in = new Scanner(System.in);
				System.out.println("Please select a category.");
				int index = in.nextInt();  // to select a category from 1 to 5.
				//will probably need an exception here to allow user to enter the correct input number.
				if(index >=0 && index <=4) {
					selectedCat = index;
					winnerPlayer = new Player(0);
				}
				else {
					System.out.println("category number should be from 0-4. \n Re-enter category number.");
				}
			}
			//AI player 1
			else if(indx == 1) {
				selectedCat = selectRandomCategory;
				winnerPlayer = new Player(1);
			}
			//AI player 2
			else if(indx== 2) {
				selectedCat = selectRandomCategory;
				winnerPlayer = new Player(2);
			}
			//AI player 3
			else if(indx == 3) {
				selectedCat = selectRandomCategory;
				winnerPlayer = new Player(3);
			}
			//AI player 4
			else if(indx == 4) {
				selectedCat = selectRandomCategory;
				winnerPlayer = new Player(4);
			}
			
			// might need to move the following two lines to a separate method.
//			selectedCategory = playerCard.getCategoryName();
//			selectedCategoryValue = playerCard.getCategoryValue();
			
			return selectedCat;
		}

		
		public static void addToCommunalPile(Card c) {	
			//adding the specified card to the communalPile ArrayList.
			communalPile.add(c);
			
		}
		
//		//public void viewCardData(Player p)
//		public static void viewCardData(Card c)
//		{
//			c.toString();
//		}
		
		public static ArrayList<Integer> getNewA() { //creating newA instead of returning highest value
			newA.clear();
			if(selectedCat == 0) {
				newA.add(0, getCurrentCardsInRound().get(0).getC1());
				newA.add(1,getCurrentCardsInRound().get(1).getC1());
				newA.add(2, getCurrentCardsInRound().get(2).getC1());
				newA.add(3, getCurrentCardsInRound().get(3).getC1());
				newA.add(4, getCurrentCardsInRound().get(4).getC1());
			}
			
			if(selectedCat == 1) {
				newA.add(0, getCurrentCardsInRound().get(0).getC2());
				newA.add(1, getCurrentCardsInRound().get(1).getC2());
				newA.add(2, getCurrentCardsInRound().get(2).getC2());
				newA.add(3, getCurrentCardsInRound().get(3).getC2());
				newA.add(4, getCurrentCardsInRound().get(4).getC2());

			}

			if(selectedCat == 2) {
				newA.add(0, getCurrentCardsInRound().get(0).getC3());
				newA.add(1, getCurrentCardsInRound().get(1).getC3());
				newA.add(2, getCurrentCardsInRound().get(2).getC3());
				newA.add(3, getCurrentCardsInRound().get(3).getC3());
				newA.add(4, getCurrentCardsInRound().get(4).getC3());
				
			}

			if(selectedCat == 3) {
				newA.add(0, getCurrentCardsInRound().get(0).getC4());
				newA.add(1, getCurrentCardsInRound().get(1).getC4());
				newA.add(2, getCurrentCardsInRound().get(2).getC4());
				newA.add(3, getCurrentCardsInRound().get(3).getC4());
				newA.add(4, getCurrentCardsInRound().get(4).getC4());	

			}
	
			if(selectedCat == 4) {
				newA.add(0, getCurrentCardsInRound().get(0).getC5());
				newA.add(1, getCurrentCardsInRound().get(1).getC5());
				newA.add(2, getCurrentCardsInRound().get(2).getC5());
				newA.add(3, getCurrentCardsInRound().get(3).getC5());
				newA.add(4, getCurrentCardsInRound().get(4).getC5());
		
			}
		
			return newA;
		}
		
		public static int returnHighestIndex(ArrayList<Integer> a) {
			int element = 0;
			int max = a.get(element);
			for (int i=0; i<a.size(); i++) {
				for(int j = 1; j< a.size(); j++) {
					if(max < a.get(i)) {
						max = a.get(i);
						element = i;
				}
					
			}
		}
			return element;
	}
		
		public static Card winningCard() {
			 return currentCardsInRound.get(returnHighestIndex(getNewA()));
		}
		
		public static void checkDuplicate() {
			Collections.sort(newA);
			if(newA.get(4).equals(newA.get(3))) {
				//round is a draw
				System.out.println("This round has resulted in a draw.");
				cardsToCommunal = true;
				for (int i = 0; i < currentCardsInRound.size(); i++) {
					addToCommunalPile(currentCardsInRound.get(i));
				} 
			} else {
				cardsToCommunal = false;
				System.out.println("The winner of the round is Player " + (returnHighestIndex(getNewA()) + 1));
				winnerPlayer = new Player(returnHighestIndex(getNewA()));
				
			 
			}

		}
		
		public static ArrayList<Card> getShuffledDeck() {
			Collections.shuffle(deck);
			shuffledDeck = new ArrayList<Card>();
			shuffledDeck = deck;
			return shuffledDeck;
			
		}
		//DEAL CARDS METHOD -- pass it the number of players, the player number (e.g. player1), and the shuffled deck
		//IF THERE ARE 3 PLAYERS WE SHOULD ADD THE TOP CARD TO THE COMMUNAL PILE BEFORE WE CALL THE dealCards() METHOD// 
		//I.E. WE SHOULD REMOVE IT FROM THE SHUFFLED DECK SO WE CAN DIVIDE BY 3//
		//didn't add this functionality to the method because it removes a card each time it is called, rather than only once
		public static ArrayList<Card> dealCards(int nPlayers, int playerNumber, ArrayList<Card> shuffledDeck) { 
			ArrayList<Card> playerDeck = new ArrayList<Card>();
			for(int i=0;i<shuffledDeck.size();i=i+nPlayers) { //iterate over the deck increasing by the number of players each time (deal cards one at a time)
					playerDeck.add(shuffledDeck.get(i+playerNumber-1));
				}
			return playerDeck; 
		}
	
		public static ArrayList<Card> getP1Deck() {
			return p1Hand;
		}
		public static ArrayList<Card> getP2Deck() {
			return p2Hand;
		}
		public static ArrayList<Card> getP3Deck() {
			return p3Hand;
		}
		public static ArrayList<Card> getP4Deck() {
			return p4Hand;
		}
		public static ArrayList<Card> getP5Deck() {
			return p5Hand;
		}
		
		public static ArrayList<Card> getCurrentCardsInRound() {
			currentCardsInRound.clear();
			currentCardsInRound.add(getP1Deck().get(0));
			currentCardsInRound.add(getP2Deck().get(0));
			currentCardsInRound.add(getP3Deck().get(0));
			currentCardsInRound.add(getP4Deck().get(0));
			currentCardsInRound.add(getP5Deck().get(0));
			return currentCardsInRound;
			
		}
		
		public static ArrayList<Card> updatedP1Deck() {
			p1Hand.remove(0);
			return p1Hand;
		}
		public static ArrayList<Card> updatedP2Deck() {
			p2Hand.remove(0);
			return p2Hand;
		}
		public static ArrayList<Card> updatedP3Deck() {
			p3Hand.remove(0);
			return p3Hand;
		}
		public static ArrayList<Card> updatedP4Deck() {
			p4Hand.remove(0);
			return p4Hand;
		}
		public static ArrayList<Card> updatedP5Deck() {
			p5Hand.remove(0);
			return p5Hand;
		}
		public static void updateHands() {
			updatedP1Deck();
			updatedP2Deck();
			updatedP3Deck();
			updatedP4Deck();
			updatedP5Deck();
		}
		
		public static void playRemainingRounds() {
			int counter = 2;
			while (x.gameWinner(players) == false) {
				System.out.println("Player " + (getWinningPlayer().getPlayerNumber()+1) + " will select the next category.");
				updateHands();
				System.out.println("Your card is as follows:" + p1Hand.get(0));
				if (getWinningPlayer().getPlayerNumber()+1 == 1) {
					System.out.println("Please select a number from the following categories:\n0." + c.getCat1Name() + "\n1." + c.getCat2Name() + "\n2." + 
															c.getCat3Name() +"\n3." + c.getCat4Name() + "\n4." + c.getCat5Name());
					Scanner scan = new Scanner(System.in);
					int selection = scan.nextInt();  // to select a category from 1 to 5.
					//will probably need an exception here to allow user to enter the correct input number.
					if(selection >=0 && selection <=4) {
						selectedCat = selection;
					}
					else {
						System.out.println("Category number should be from 0-4. \n Re-enter category number.");
					}
				} else {
					int randomCategory = random.nextInt(5);
					selectedCat = randomCategory;	
				}
//			**********Need to look at this so that pressing q doesn't cause an error
				System.out.println("The chosen category is " + selectedCat + ".");
				getNewA();
				checkDuplicate();
				if (cardsToCommunal == false) {
					System.out.println("This was the winning card:" + winningCard());
					System.out.println("The winner of the round is Player " + (returnHighestIndex(getNewA()) + 1));
					winnerPlayer = new Player(returnHighestIndex(getNewA()));
				}
				else {
					System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
				}
				//add Round object here
				counter++;
				
			}
			
		}
		
		public static Player getWinningPlayer() {
			return winnerPlayer;
		}
		
		public static void main(String[] args) {
			openApplication();
			if (start == true) {
				playRemainingRounds();
			}
			//
		}
		
		
	
	}



