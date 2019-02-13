package commandline;
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
	import java.util.Random;
	import java.util.Scanner;

	public class PlayGame {	
		protected Database db = new Database();
		private Card c = new Card();
		private final int maxSize = 40; //size of the ArrayList
		protected ArrayList<Card> deck =  (ArrayList<Card>) c.getDeck().clone();
		private ArrayList<Card> unshuffledDeck = new ArrayList<Card>(maxSize);
		private ArrayList<Card> shuffledDeck = new ArrayList<Card>(maxSize);
		private ArrayList<Card> playerDeck = new ArrayList<Card>(maxSize);
		private ArrayList<Card> communalPile = new ArrayList<Card>(maxSize);
		protected ArrayList<Card> p1Hand = new ArrayList<Card>();
		protected ArrayList<Card> p2Hand = new ArrayList<Card>();
		protected ArrayList<Card> p3Hand = new ArrayList<Card>();
		protected ArrayList<Card> p4Hand = new ArrayList<Card>();
		protected ArrayList<Card> p5Hand = new ArrayList<Card>();
		protected static ArrayList<Card> h1 = new ArrayList<Card>();
		protected static ArrayList<Card> h2 = new ArrayList<Card>();
		protected static ArrayList<Card> h3 = new ArrayList<Card>();
		protected static ArrayList<Card> h4 = new ArrayList<Card>();
		protected static ArrayList<Card> h5 = new ArrayList<Card>();
		protected static ArrayList<Card> cp = new ArrayList<Card>();
		private ArrayList<Card> currentCardsInRound = new ArrayList<Card>();
		private ArrayList<Player> players = new ArrayList<Player>(5);
		private ArrayList<Integer> categoryValues = new ArrayList<Integer>();
		private boolean cardsToCommunal;
		protected boolean gameOver = false;
		protected boolean start = false;
		protected boolean keepPlaying = true;
		private String selectedCategoryName;		
		private int numOfGamesPlayed =0; // number of games a player played.
		private int totalScore; 	// total score of each game for a player.
		private int drawRound;
		protected static int totalRounds;
		private boolean playNewGame, quitGame; // when user select to play again/new game
		private int humanRounds, ai1Rounds, ai2Rounds, ai3Rounds, ai4Rounds;
		protected int totalDrawRounds;
		protected int indx = 0; // this will be used in selectStartingPlayer method and selectCategory method.
		private Random random = new Random();
		private int selectRandom = random.nextInt(5); //for selecting a starting player.
		private Player x = new Player("Player X", 10);
		protected Player p1, p2, p3, p4, p5;		
		protected String selectedCategory;
		protected int selectedCategoryValue;
		protected static Player winnerPlayer;		
		private int selectedCat;
		
	//---------------------------------------------------------------------------------------------------------------------------------------------//
		
		public void openApplication() {
			System.out.println("Welcome to Top Trumps, Player 1. Would you like to play a game or view statistics from previous games?");
			System.out.println("Press 1 to view statistics, 2 to play a game. Press q at any time to quit.");
			Scanner s = new Scanner(System.in);
			selectOption(s);
		}

		/**
		 * Total round setters for use with the database tests
		 * @param totalR
		 */
		
		public int setTotalRound(int totalR) {
			return this.totalRounds = totalR;
		}
		/**
		 * Human round setter for use with database tests
		 * @param humanR
		 */
		
		public int setHumanRounds(int humanR) {
			return this.humanRounds = humanR;
		}
		
		/**
		 * First AI round setter(for database)
		 * @param ai1
		 */
		
		public int setAi1Rounds(int ai1) {
			return this.ai1Rounds = ai1;
		}
		
		/**
		 * Second AI round setter(for database)
		 * @param ai2
		 */	
		public int setAi2Rounds(int ai2) {
			return this.ai2Rounds = ai2;
		}
		
		/**
		 * Third AI round setter(for database)
		 * @param ai3
		 * 
		 */
		public int setAi3Rounds(int ai3) {
			return this.ai3Rounds = ai3;
		}
		
		/**
		 * Fourth AI round setter (for database)
		 * @param ai4
		 * 
		 */
		public int setAi4Rounds(int ai4) {
			return this.ai4Rounds = ai4;
		}
		
		
		/**
		 * totalRounds getter for database tests
		 * @return totalRounds 
		 */	
		public int getTotalRound() {
			return totalRounds;
		}
		
		/**
		 * humanRounds getter for database tests
		 * @return humanRounds
		 */
		
		public int getHumanRounds() {
			return humanRounds;
		}
		
		/**
		 * ai1Rounds getter  for database tests
		 * @return ai1Rounds
		 */
		
		public int getAi1Rounds() {
			return ai1Rounds;
		}
		
		/**
		 * ai2Rounds getter for database tests
		 * @return ai2Rounds
		 */
		public int getAi2Rounds() {
			return ai2Rounds;
		}
		
		/**
		 * ai3Rounds getter for database tests
		 * @return ai3Rounds
		 */
		public int getAi3Rounds() {
			return ai3Rounds;
		}
		
		/**
		 * ai4Rounds getter for database tests
		 * @return ai4Rounds
		 */
		public int getAi4Rounds() {
			return ai4Rounds;
		}
		/**
		 * 
		 * @param in
		 */
		
		@SuppressWarnings("unchecked")
		public void selectOption(Scanner in) {
			while(start==false) {
				try {
				String inputForOption = in.nextLine();
				if(inputForOption.equals("1")) {
					db.displayResults();
					System.out.println("Press 1 to view statistics, 2 to play a game. Press q at any time to quit.");
					start = false;
			}
			else if(inputForOption.equals("2")) {
				getPlayers().clear();
				RoundObject.roundsArray.clear();
				p1 = new Player("Player 1", 1);
				p2 = new Player("Player 2", 2);
				p3 = new Player("Player 3", 3);
				p4 = new Player("Player 4", 4);
				p5 = new Player("Player 5", 5);
				getPlayers().add(p1);
				getPlayers().add(p2);
				getPlayers().add(p3);
				getPlayers().add(p4);
				getPlayers().add(p5);
				c.setAttNames();
				p1Hand = c.dealCards(5, 1, getShuffledDeck());
				p2Hand = c.dealCards(5, 2, getShuffledDeck());
				p3Hand = c.dealCards(5, 3, getShuffledDeck());
				p4Hand = c.dealCards(5, 4, getShuffledDeck());
				p5Hand = c.dealCards(5, 5, getShuffledDeck());
				h1 = (ArrayList<Card>) p1Hand.clone();
				h2 = (ArrayList<Card>) p2Hand.clone();
				h3 = (ArrayList<Card>) p3Hand.clone();
				h4 = (ArrayList<Card>) p4Hand.clone();
				h5 = (ArrayList<Card>) p5Hand.clone();
				playFirstRound();
				
			}
			else if (inputForOption.equals("q") || inputForOption.equals("Q")) {
				quit(in);
			}
			else {
				throw new InvalidInputException();
				}
			}catch (InvalidInputException e) {
				System.out.println("Your input is incorrect. Please enter 1 to view statistics, 2 to play a game, or q to quit.");
				start = false;
				}
			}
		}
		
		
		/**
		 * 
		 */
		
		@SuppressWarnings("unchecked")
		public void playFirstRound() {
			//start new game
			int r1SelectedCat = 0;
			String catName = "";
			System.out.println("Player " + (selectStartingPlayer() +1) + " will select the category for the first round.");
			System.out.println("Here is your card: " + p1Hand.get(0).toString());
			System.out.println("The categories are as follows:\n0. " + c.getCat1Name() + "\n1. " + c.getCat2Name() + "\n2. " +
								c.getCat3Name() + "\n3. " + c.getCat4Name() + "\n4. " + c.getCat5Name());
			//throws a null pointer exception when the input is incorrect and there is a draw in the first round
			r1SelectedCat = selectCategory();
			if(r1SelectedCat == 0) {
				catName = c.getCat1Name();						
			}else if (r1SelectedCat == 1) {
				catName = c.getCat2Name();
			}else if (r1SelectedCat == 2) {
				catName = c.getCat3Name();
			}else if (r1SelectedCat == 3) {
				catName = c.getCat4Name();
			}else if (r1SelectedCat == 4) {
				catName = c.getCat5Name();
			}
			System.out.println("The selected category is " + r1SelectedCat + ": " + catName);
			getCategoryValues();
			checkDuplicate();
			if (cardsToCommunal == false) {
				System.out.println("This was the winning card:" + winningCard());
				
			}
			else {
				System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
			}
			ArrayList<Integer> v = (ArrayList<Integer>)getCategoryValues().clone();
			cp = (ArrayList<Card>)getCommunalPile().clone();
			cardsRemaining();
			updateHands();
			ArrayList<Card>h1 = (ArrayList<Card>) p1Hand.clone();
			ArrayList<Card>h2 = (ArrayList<Card>) p2Hand.clone();
			ArrayList<Card>h3 = (ArrayList<Card>) p3Hand.clone();
			ArrayList<Card>h4 = (ArrayList<Card>) p4Hand.clone();
			ArrayList<Card>h5 = (ArrayList<Card>) p5Hand.clone();
			ArrayList<Card>cC = (ArrayList<Card>) getCurrentCardsInRound().clone();
			RoundObject firstRound = new RoundObject(1, cp, cC, catName, v, h1, h2, h3, h4, h5);
			RoundObject.roundsArray.add(firstRound);
			start = true;
			
		}
		
		/**
		 * Method to quit the game
		 * @param in
		 */

		public void quit(Scanner in) {
			keepPlaying = false;
				System.out.println("Goodbye.");
				in.close();
				System.exit(1);
			}
		/**
		 * Method to select starting player at random
		 * @return startPlayer
		 */

		public int selectStartingPlayer() {
			Object startPlayer = null;
				//get player 1 and set current player to player 1  (Human Player)
				if(selectRandom == 0) {
					startPlayer = getPlayers().get(0);
					indx = getPlayers().indexOf(startPlayer);
				}
				//get player 2 and set current player to (AI player 1)
				else if(selectRandom == 1) {
					startPlayer = getPlayers().get(1);
					indx = getPlayers().indexOf(startPlayer);
				}
				//get player 3 and set current player to (AI player 2)
				else if(selectRandom == 2) {
					startPlayer = getPlayers().get(2);
					indx = getPlayers().indexOf(startPlayer);
				}
				//get player 4 and set current player to (AI player 3)
				else if(selectRandom == 3) {
					startPlayer = getPlayers().get(3);
					indx = getPlayers().indexOf(startPlayer);
				}
				//get player 5 and set current player to (AI player 4)
				else if(selectRandom == 4) {
					startPlayer = getPlayers().get(4);
					indx = getPlayers().indexOf(startPlayer);
				}
			
			//return startPlayer;
			return indx;
			
		}
		
		/**
		 * Method to select a random category for the AIs 
		 * and for the human player to choose  their category
		 * @return selectedCat
		 */

		public int selectCategory() {
			int selectRandomCategory = random.nextInt(5);
			selectedCat =1;
			//human player
			if(indx == 0) {
				Scanner in = new Scanner(System.in);
				String userInput = in.nextLine();
				System.out.println("Please select a category.");
				if(userInput.equals("0") || userInput.equals("1") ||userInput.equals("2") ||userInput.equals("3") ||userInput.equals("4")) {
					selectedCat = Integer.parseInt(userInput);
					winnerPlayer = p1;
				}else if(userInput.equals("q") || userInput.equals("Q")) {
					quit(in);
				}
				else {
					System.out.println("Incorrect Category Number. Category has been chosen for you.");
				}
			}
			//AI player 1
			else if(indx == 1) {
				selectedCat = selectRandomCategory;
				winnerPlayer = p2;
			}
			//AI player 2
			else if(indx== 2) {
				selectedCat = selectRandomCategory;
				winnerPlayer = p3;
			}
			//AI player 3
			else if(indx == 3) {
				selectedCat = selectRandomCategory;
				winnerPlayer = p4;
			}
			//AI player 4
			else if(indx == 4) {
				selectedCat = selectRandomCategory;
				winnerPlayer = p5;
			}
			
			return selectedCat;
		}

		/**
		 * Adding the specified card to the communalPile ArrayList.
		 * @param c
		 */
		public void addToCommunalPile(Card c) {	
			communalPile.add(c);
			
		}
		
		/**
		 * Method to get the values of the cards in play for the selected category
		 * @return categoryValues
		 */
		
		public ArrayList<Integer> getCategoryValues() { //creating an array of integers for the selected category instead of returning highest value
			categoryValues.clear();
			if(selectedCat == 0) {
				for (int i = 0; i < getCurrentCardsInRound().size(); i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC1());					
				}
			}			
			if(selectedCat == 1) {
				for (int i = 0; i < getCurrentCardsInRound().size(); i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC2());
				}
			}
			if(selectedCat == 2) {
				for (int i = 0; i < getCurrentCardsInRound().size(); i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC3());
				}	
			}
			if(selectedCat == 3) {
				for (int i = 0; i < getCurrentCardsInRound().size(); i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC4());
				}
			}
			if(selectedCat == 4) {
				for (int i = 0; i < getCurrentCardsInRound().size(); i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC5());
				}		
			}		
			return categoryValues;
		}
		
		/**
		 * Method which calls all the check methods at once
		 */
		public void cardsRemaining() {
			p1CardCheck();
			p2CardCheck();
			p3CardCheck();
			p4CardCheck();
			p5CardCheck();
		}
		
		/**
		 * Check method for player one's hand - player will be removed from
		 * players array if they have no cards remaining
		 */
		
		public void p1CardCheck() {
			if (getPlayers().contains(p1)) {
				if(getP1Deck().size() == 0) {
					getPlayers().remove(p1);
				}
			}
		}
		
		/**
		 * Check method for player two's hand - player will be removed from
		 * players array if they have no cards remaining
		 */
		
		public void p2CardCheck() {
			if (getPlayers().contains(p2)) {
				if(getP2Deck().size() == 0) {
					getPlayers().remove(p2);
				}
			}
		}
		
		/**
		 * Check method for player three's hand - player will be removed from
		 * players array if they have no cards remaining
		 */
		
		public void p3CardCheck() {
			if (getPlayers().contains(p3)) {
				if(getP3Deck().size() == 0) {
					getPlayers().remove(p3);
				}
			}
		}
		
		/**
		 * Check method for player four's hand - player will be removed from
		 * players array if they have no cards remaining
		 */
		
		public void p4CardCheck() {
			if (getPlayers().contains(p4)) {
				if(getP4Deck().size() == 0) {
					getPlayers().remove(p4);
				}
			}
		}
		
		/**
		 * Check method for player five's hand - player will be removed from
		 * players array if they have no cards remaining
		 */
		
		public void p5CardCheck() {
			if(getPlayers().contains(p5)) {
				if(getP5Deck().size() == 0) {
					getPlayers().remove(p5);
				}
			}
		}
		
		/**
		 * Method to work out the highest index in an ArrayList, which will be used to determine the winner
		 * @param a
		 * @return element
		 */
		
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
		
		/**
		 * Method to establish which card won
		 * @return currentCardsInRound.get(returnHighestIndex(getCategoryValues()));
		 */
		public Card winningCard() {
			 return currentCardsInRound.get(returnHighestIndex(getCategoryValues()));
		}
		
		/**
		 * Method to check if there is a draw
		 * Sorts the categoryValues ArrayList using Collections.sort and checks if the last two numbers 
		 * in the ArrayList are the same. If they are,  it's a draw.
		 */
		
		public void checkDuplicate() {
			int arraySize = categoryValues.size() - 1 ;
			Collections.sort(categoryValues);
			if(categoryValues.get(arraySize).equals(categoryValues.get(arraySize-1))) {
				//round is a draw
				drawRound++;
				System.out.println("This round has resulted in a draw.");
				cardsToCommunal = true;
				for (int i = 0; i < currentCardsInRound.size(); i++) {
					addToCommunalPile(currentCardsInRound.get(i));
				} 
			} else {
				cardsToCommunal = false;
				System.out.println("The winner of the round is " + players.get(returnHighestIndex(getCategoryValues())).getPlayerName());
				winnerPlayer = new Player(players.get(returnHighestIndex(getCategoryValues())).getPlayerName(), players.get(returnHighestIndex(getCategoryValues())).getPlayerNumber());	
				addWinnerCards();
			}
		}
		
		/**
		 * Method to shuffle the deck
		 * @return shuffledDeck
		 */
		
		public ArrayList<Card> getShuffledDeck() {
			Collections.shuffle(deck);
			shuffledDeck = new ArrayList<Card>();
			shuffledDeck = deck;
			return shuffledDeck;		
		}
	
		/**
		 * Method to return Player One's hand
		 * @return p1Hand
		 */
		public ArrayList<Card> getP1Deck() {
			return p1Hand;
		}
		
		/**
		 * Method to return Player two's hand
		 * @return p2Hand
		 */
		
		public ArrayList<Card> getP2Deck() {
			return p2Hand;
		}
		
		/**
		 * Method to return Player Three's hand
		 * @return p3Hand
		 */
		
		public ArrayList<Card> getP3Deck() {
			return p3Hand;
		}
		
		/**
		 * Method to return Player Four's hand
		 * @return p4Hand
		 */
		
		public ArrayList<Card> getP4Deck() {
			return p4Hand;
		}
		
		/**
		 * Method to return Player Five's hand
		 * @return p5Hand
		 */
		
		public ArrayList<Card> getP5Deck() {
			return p5Hand;
		}
		
		/**
		 * Checks if the each player still has cards. 
		 * If they do, the top card is added to the currentCardsInRound ArrayLists
		 * @return  currentCardsInRound
		 */
		
		public ArrayList<Card> getCurrentCardsInRound() {
			currentCardsInRound.clear();
			if(getP1Deck().size()>0) {
				currentCardsInRound.add(getP1Deck().get(0));
			}
			if(getP2Deck().size()>0) {
				currentCardsInRound.add(getP2Deck().get(0));
			}
			if(getP3Deck().size()>0) {
				currentCardsInRound.add(getP3Deck().get(0));
			}
			if(getP4Deck().size()>0) {
				currentCardsInRound.add(getP4Deck().get(0));
			}
			if(getP5Deck().size()>0) {
				currentCardsInRound.add(getP5Deck().get(0));
			}
			return currentCardsInRound;			
		}
		
		/**
		 * Method to update player one's deck after rounds
		 * @return p1Hand
		 */
		public ArrayList<Card> updatedP1Deck() {
			if (getP1Deck().size() > 0) {
				getP1Deck().remove(0);
			}
			return p1Hand;
		}
		
		/**
		 * Method to update player two's deck after rounds
		 * @return p2Hand
		 */
		public ArrayList<Card> updatedP2Deck() {
			if (getP2Deck().size() > 0) {
				getP2Deck().remove(0);
			}
			return p2Hand;
		}
		/**
		 * Method to update player three's deck after rounds
		 * @return p3Hand
		 */
		public ArrayList<Card> updatedP3Deck() {
			if (getP3Deck().size() > 0) {
				getP3Deck().remove(0);
			}
			return p3Hand;
		}
		/**
		 * Method to update player four's deck after rounds
		 * @return p4Hand
		 */
		public ArrayList<Card> updatedP4Deck() {
			if (getP4Deck().size() > 0) {
				getP4Deck().remove(0);
			}
			return p4Hand;
		}
		/**
		 * Method to update player five's deck after rounds
		 * @return p5Hand
		 */
		public ArrayList<Card> updatedP5Deck() {
			if (getP5Deck().size() > 0) {
				getP5Deck().remove(0);
			}
			return p5Hand;
		}
		/**
		 * Method that calls all the updated hands methods at once,
		 * checking that the size of the hand arrays are greater than 0.
		 */
		public void updateHands() {
			if(getP1Deck().size()>0) {
				updatedP1Deck();
			}
			if(getP2Deck().size()>0) {
				updatedP2Deck();
			}
			if(getP3Deck().size()>0) {
				updatedP3Deck();
			}
			if(getP4Deck().size()>0) {
				updatedP4Deck();
			}
			if(getP5Deck().size()>0) {
				updatedP5Deck();
			}
		}
		
		/**
		 * Method which, if a player has won the round, adds the cards from the communal pile
		 * into the winner's hand
		 */
		public void addWinnerCards() {
			if(cardsToCommunal == false) {		
				if(getWinningPlayer().getPlayerName().equals(p1.getPlayerName())) {
					p1.getPlayerWonRound();
					for(int i=0;i<getCurrentCardsInRound().size();i++) {
						getP1Deck().add(getCurrentCardsInRound().get(i));
					}
				}
				if(getWinningPlayer().getPlayerName().equals(p2.getPlayerName())) {
					p2.getPlayerWonRound();
					for(int i=0;i<getCurrentCardsInRound().size();i++) {
						getP2Deck().add(getCurrentCardsInRound().get(i));
					}
				}
				if(getWinningPlayer().getPlayerName().equals(p3.getPlayerName())) {
					p3.getPlayerWonRound();
					for(int i=0;i<getCurrentCardsInRound().size();i++) {
						getP3Deck().add(getCurrentCardsInRound().get(i));
					}
				}
				if(getWinningPlayer().getPlayerName().equals(p4.getPlayerName())) {
					p4.getPlayerWonRound();
					for(int i=0;i<getCurrentCardsInRound().size();i++) {
						getP4Deck().add(getCurrentCardsInRound().get(i));
					}
				}
				if(getWinningPlayer().getPlayerName().equals(p5.getPlayerName())) {
					p5.getPlayerWonRound();
					for(int i=0;i<getCurrentCardsInRound().size();i++) {
						getP5Deck().add(getCurrentCardsInRound().get(i));
					}
				}
			}
		}
		
		/**
		 * Method that plays the remaining rounds of the game.
		 * Allows us to create a loop as the print statements are different
		 * in the first round. Contains gameOver boolean to jump out  of loop 
		 */
		
		@SuppressWarnings("unchecked")
		public void playRemainingRounds() {
			int counter = 2;
			while (players.size() > 1) {
				System.out.println("\nPlayer " + (getWinningPlayer().getPlayerNumber()) + " will select the next category.");
				if (!p1Hand.isEmpty()) {
					System.out.println("\nYour card is as follows:" + p1Hand.get(0)); //pick the top card from their hand
				}else {
					System.out.println("\nYou are out of cards. You lose!\n");
				}
				if (getWinningPlayer().getPlayerName().equals(p1.getPlayerName())) {
					System.out.println("Please select a number from the following categories:\n0." + c.getCat1Name() + "\n1." + c.getCat2Name() + "\n2." + 
															c.getCat3Name() +"\n3." + c.getCat4Name() + "\n4." + c.getCat5Name());
					Scanner scan = new Scanner(System.in);
					String stringSelection = scan.nextLine();
					try {
						int selection = Integer.parseInt(stringSelection);  // to select a category from 1 to 5.
						if(selection >=0 && selection <=4) {
							selectedCat = selection;
							if(selection == 0) {
								selectedCategoryName = c.getCat1Name();						
							}else if (selection == 1) {
								selectedCategoryName = c.getCat2Name();
							}else if (selection == 2) {
								selectedCategoryName = c.getCat3Name();
							}else if (selection == 3) {
								selectedCategoryName = c.getCat4Name();
							}else if (selection == 4) {
								selectedCategoryName = c.getCat5Name();
							}
						}
						else {
							System.out.println("Incorrect Category Number. Category has been chosen for you.");
						}
					}catch(NumberFormatException e) {
						if(stringSelection.equals("Q") || stringSelection.equals("q")) {
							quit(scan);
						}else {
							System.out.println("Incorrect Category Number. Category has been chosen for you.");	
						}
					}

				} else {
					int randomCategory = random.nextInt(5);
					selectedCat = randomCategory;	
					if(randomCategory == 0) {
						selectedCategoryName = c.getCat1Name();						
					}else if (randomCategory == 1) {
						selectedCategoryName = c.getCat2Name();
					}else if (randomCategory == 2) {
						selectedCategoryName = c.getCat3Name();
					}else if (randomCategory == 3) {
						selectedCategoryName = c.getCat4Name();
					}else if (randomCategory == 4) {
						selectedCategoryName = c.getCat5Name();
					}
				}
				System.out.println("\nThe chosen category is " + selectedCat + ": " + selectedCategoryName);
				getCategoryValues();
				checkDuplicate();
				if (cardsToCommunal == false) {
					System.out.println("\nThis was the winning card:" + winningCard());
				}
				else {
					System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
				}
				ArrayList<Card>cC = (ArrayList<Card>) getCurrentCardsInRound().clone();
				ArrayList<Integer> v = (ArrayList<Integer>)getCategoryValues().clone();
				updateHands();
				cardsRemaining();
				ArrayList<Card>h1 = (ArrayList<Card>) getP1Deck().clone();
				ArrayList<Card>h2 = (ArrayList<Card>) getP2Deck().clone();
				ArrayList<Card>h3 = (ArrayList<Card>) getP3Deck().clone();
				ArrayList<Card>h4 = (ArrayList<Card>) getP4Deck().clone();
				ArrayList<Card>h5 = (ArrayList<Card>) getP5Deck().clone();
				ArrayList<Card>cp = (ArrayList<Card>)getCommunalPile().clone();
				RoundObject roundDetails = new RoundObject(counter, cp, cC, getCategoryName(), v, h1, h2, h3, h4, h5);
				RoundObject.roundsArray.add(roundDetails);
				System.out.println("\nNumber of cards left in play:\nPlayer 1: " + p1Hand.size() + "\nPlayer 2: " + p2Hand.size() + "\nPlayer 3: " + p3Hand.size()+ "\nPlayer 4: " + p4Hand.size()+ " \nPlayer 5: " + p5Hand.size());
				System.out.println();
				counter++;
				if (getPlayers().size()==1) {
					totalDrawRounds = drawRound;
					totalRounds = counter;	
					gameOver = true;			
				}
			}
		}
		
		/**
		 * Getter for players array
		 * @return players
		 */
		public ArrayList<Player> getPlayers() {
			return players;
		}
		/**
		 * Getter for the winning playing
		 * @return winnerPlayer
		 */
		
		public static Player getWinningPlayer() {
			return winnerPlayer;
		}
		
		/**
		 * Method to set t he winning player's number
		 * @param playerNumber
		 * @return winnerPlayer.setPlayerNumber(playerNumber)
		 */

		public int setWinningPlayerNumber(int playerNumber) {
			return winnerPlayer.setPlayerNumber(playerNumber);
		}
		
		
		/**
		 * Getter for the winning player's number
		 * @return winnerPlayer.getPlayerNumber()
		 */
		
		public int getWinningPlayerNumber() {
			return winnerPlayer.getPlayerNumber();
		}
		
		/**
		 * Getter for the communal pile
		 * @return communalPile
		 */
		public ArrayList<Card> getCommunalPile() {
			return communalPile;
		}
		
		/**
		 * Getter for the name of the selected category
		 * @return selectedCategoryName
		 */
		public String getCategoryName() {
			return selectedCategoryName;
		}
		
}
