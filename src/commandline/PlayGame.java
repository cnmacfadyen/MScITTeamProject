package commandline;
	import java.util.ArrayList;
import java.util.Arrays;
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
		private static ArrayList<Integer> categoryValues = new ArrayList<Integer>();
		private static boolean cardsToCommunal;
		private static String selectedCategoryName;
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
		static Player x = new Player("Player X", 10);
		static Player p1, p2, p3, p4, p5;
		
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
				p1 = new Player("Player 1", 1);
				p2 = new Player("Player 2", 2);
				p3 = new Player("Player 3", 3);
				p4 = new Player("Player 4", 4);
				p5 = new Player("Player 5", 5);
				players.add(p1);
				players.add(p2);
				players.add(p3);
				players.add(p4);
				players.add(p5);
				c.setAttNames();
				p1Hand = c.dealCards(5, 1, getShuffledDeck());
				p2Hand = c.dealCards(5, 2, getShuffledDeck());
				p3Hand = c.dealCards(5, 3, getShuffledDeck());
				p4Hand = c.dealCards(5, 4, getShuffledDeck());
				p5Hand = c.dealCards(5, 5, getShuffledDeck());
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
			int r1SelectedCat = 0;
			String catName = "";
			System.out.println("Player " + (selectStartingPlayer() +1) + " will select the category for the first round.");
			System.out.println("Here is your card: " + p1Hand.get(0).toString());
			System.out.println("The categories are as follows:\n0. " + c.getCat1Name() + "\n1. " + c.getCat2Name() + "\n2. " +
								c.getCat3Name() + "\n3. " + c.getCat4Name() + "\n4. " + c.getCat5Name());
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
					winnerPlayer = p1;
				}
				else {
					System.out.println("category number should be from 0-4. \n Re-enter category number.");
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
		
		public static ArrayList<Integer> getCategoryValues() { //creating newA instead of returning highest value
			categoryValues.clear();
			if(selectedCat == 0) {
				for (int i = 0; i < players.size()-1; i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC1());
					
				}
			}
			
			if(selectedCat == 1) {
				for (int i = 0; i < players.size()-1; i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC2());
				}
			}

			if(selectedCat == 2) {
				for (int i = 0; i < players.size()-1; i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC3());
				}
				
			}

			if(selectedCat == 3) {
				for (int i = 0; i < players.size()-1; i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC4());
				}
			}
	
			if(selectedCat == 4) {
				for (int i = 0; i < players.size()-1; i++) {
					categoryValues.add(getCurrentCardsInRound().get(i).getC5());
				}
		
			}
		
			return categoryValues;
		}
		
		public static void cardsRemaining() {
			if(p1Hand.size() == 0) {
				players.remove(0);
			}
			if(p2Hand.size() == 0) {
				players.remove(1);
			}
			if(p3Hand.size() == 0) {
				players.remove(2);
			}
			if(p4Hand.size() == 0) {
				players.remove(3);
			}
			if(p5Hand.size() == 0) {
				players.remove(4);
			}
		}
//		public static ArrayList<Integer> getNewA() throws OutOfCardsException{ //creating newA instead of returning highest value
//			newA.clear();
//			try {
//				newA.add(0, getCurrentCardsInRound().get(0).getChosenCategory(selectedCat)); //add selected category of first card to the array
//				if(!getCurrentCardsInRound().contains(getCurrentCardsInRound().get(0))) { //if the current cards array does not contain a first element
//					throw new OutOfCardsException();
//				}
//			}catch(OutOfCardsException e) {
//				newA.add(0,	-1);
//			}			try {
//				newA.add(1, getCurrentCardsInRound().get(1).getChosenCategory(selectedCat)); //add selected category of first card to the array
//				if(!getCurrentCardsInRound().contains(getCurrentCardsInRound().get(1))) {
//					throw new OutOfCardsException();
//				}
//			}catch(OutOfCardsException e) {
//				newA.add(1,	-1);
//			}			try {
//				newA.add(2, getCurrentCardsInRound().get(2).getChosenCategory(selectedCat)); //add selected category of first card to the array
//				if(!getCurrentCardsInRound().contains(getCurrentCardsInRound().get(2))) {
//					throw new OutOfCardsException();
//				}
//			}catch(OutOfCardsException e) {
//				newA.add(2,	-1);
//			}			try {
//				newA.add(3, getCurrentCardsInRound().get(3).getChosenCategory(selectedCat)); //add selected category of first card to the array
//				if(!getCurrentCardsInRound().contains(getCurrentCardsInRound().get(3))) {
//					throw new OutOfCardsException();
//				}
//			}catch(OutOfCardsException e) {
//				newA.add(3,	-1);
//			}			try {
//				newA.add(4, getCurrentCardsInRound().get(4).getChosenCategory(selectedCat)); //add selected category of first card to the array
//				if(!getCurrentCardsInRound().contains(getCurrentCardsInRound().get(4))) {
//					throw new OutOfCardsException();
//				}
//			}catch(OutOfCardsException e) {
//				newA.add(4,	-1);
//			}
//			return newA;
//		}
		
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
			 return currentCardsInRound.get(returnHighestIndex(getCategoryValues()));
		}
		
		public static void checkDuplicate() {
			int arraySize = categoryValues.size();
			Collections.sort(categoryValues);
			if(categoryValues.get(arraySize-1).equals(categoryValues.get(arraySize-2))) {
				//round is a draw
				System.out.println("This round has resulted in a draw.");
				cardsToCommunal = true;
				for (int i = 0; i < currentCardsInRound.size(); i++) {
					addToCommunalPile(currentCardsInRound.get(i));
				} 
			} else {
				cardsToCommunal = false;
				System.out.println("The winner of the round is Player " + (returnHighestIndex(getCategoryValues()) + 1));
				winnerPlayer = new Player(players.get(returnHighestIndex(getCategoryValues())).getPlayerName(), players.get(returnHighestIndex(getCategoryValues())).getPlayerNumber());			 
			}

		}
		
		public static ArrayList<Card> getShuffledDeck() {
			Collections.shuffle(deck);
			shuffledDeck = new ArrayList<Card>();
			shuffledDeck = deck;
			return shuffledDeck;
			
		}
		
//		//DEAL CARDS METHOD -- pass it the number of players, the player number (e.g. player1), and the shuffled deck
//		//IF THERE ARE 3 PLAYERS WE SHOULD ADD THE TOP CARD TO THE COMMUNAL PILE BEFORE WE CALL THE dealCards() METHOD// 
//		//I.E. WE SHOULD REMOVE IT FROM THE SHUFFLED DECK SO WE CAN DIVIDE BY 3//
//		//didn't add this functionality to the method because it removes a card each time it is called, rather than only once
//		public static ArrayList<Card> dealCards(int nPlayers, int playerNumber, ArrayList<Card> shuffledDeck) { 
//			ArrayList<Card> playerDeck = new ArrayList<Card>();
//			for(int i=0;i<shuffledDeck.size();i=i+nPlayers) { //iterate over the deck increasing by the number of players each time (deal cards one at a time)
//					playerDeck.add(shuffledDeck.get(i+playerNumber-1));
//				}
//			return playerDeck; 
//		}
	
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
			if(p1Hand.size()>0) {
				currentCardsInRound.add(getP1Deck().get(0));
			}
			if(p2Hand.size()>0) {
				currentCardsInRound.add(getP2Deck().get(0));
			}
			if(p3Hand.size()>0) {
				currentCardsInRound.add(getP3Deck().get(0));
			}
			if(p4Hand.size()>0) {
				currentCardsInRound.add(getP4Deck().get(0));
			}
			if(p5Hand.size()>0) {
				currentCardsInRound.add(getP5Deck().get(0));
			}
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
			if(p1Hand.size()>0) {
				updatedP1Deck();
			}
			if(p2Hand.size()>0) {
				updatedP2Deck();
			}
			if(p3Hand.size()>0) {
				updatedP3Deck();
			}
			if(p4Hand.size()>0) {
				updatedP4Deck();
			}
			if(p5Hand.size()>0) {
				updatedP5Deck();
			}
		}
		
		public static void addWinnerCards() {
			if(cardsToCommunal == false) {		
				if(winnerPlayer.getPlayerName().equals(p1.getPlayerName())) {
					for(int i=0;i<currentCardsInRound.size();i++) {
						p1Hand.add(currentCardsInRound.get(i));
					}
				}
				if(winnerPlayer.getPlayerName().equals(p2.getPlayerName())) {
					for(int i=0;i<currentCardsInRound.size();i++) {
						p2Hand.add(currentCardsInRound.get(i));
					}
				}
				if(winnerPlayer.getPlayerName().equals(p3.getPlayerName())) {
					for(int i=0;i<currentCardsInRound.size();i++) {
						p3Hand.add(currentCardsInRound.get(i));
					}
				}
				if(winnerPlayer.getPlayerName().equals(p4.getPlayerName())) {
					for(int i=0;i<currentCardsInRound.size();i++) {
						p4Hand.add(currentCardsInRound.get(i));
					}
				}
				if(winnerPlayer.getPlayerName().equals(p5.getPlayerName())) {
					for(int i=0;i<currentCardsInRound.size();i++) {
						p5Hand.add(currentCardsInRound.get(i));
					}
				}
			}
		}
		
		public static void playRemainingRounds() {
			int counter = 2;
			String catName = "";
			while (x.gameWinner(players) == false) {
				System.out.println("\nPlayer " + (getWinningPlayer().getPlayerNumber()) + " will select the next category.");
				if (!p1Hand.isEmpty()) {
					System.out.println("\nYour card is as follows:" + p1Hand.get(0)); //pick the top card from their hand
				}else {
					System.out.println("\nYou are out of cards. You lose!\n");
				}
				if (getWinningPlayer().getPlayerNumber()+1 == 1) {
					System.out.println("Please select a number from the following categories:\n0." + c.getCat1Name() + "\n1." + c.getCat2Name() + "\n2." + 
															c.getCat3Name() +"\n3." + c.getCat4Name() + "\n4." + c.getCat5Name());
					Scanner scan = new Scanner(System.in);
					int selection = scan.nextInt();  // to select a category from 1 to 5.
					//will probably need an exception here to allow user to enter the correct input number.
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
						System.out.println("Category number should be from 0-4. \n Re-enter category number.");
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
//			**********Need to look at this so that pressing q doesn't cause an error
				System.out.println("\nThe chosen category is " + selectedCat + ": " + selectedCategoryName);
				getCategoryValues();
				checkDuplicate();
				if (cardsToCommunal == false) {
					System.out.println("\nThis was the winning card:" + winningCard());
					System.out.println("\nThe winner of the round is Player " + (returnHighestIndex(getCategoryValues()) + 1));
					//winnerPlayer = new Player(players.get(returnHighestIndex(getCategoryValues())).getPlayerName(), players.get(returnHighestIndex(getCategoryValues())).getPlayerNumber());
				}
				else {
					System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
				}
				addWinnerCards();
				RoundObject roundDetails = new RoundObject(counter, communalPile, currentCardsInRound, selectedCategoryName, categoryValues, p1Hand, p2Hand, p3Hand, p4Hand, p5Hand);
				cardsRemaining();
				cardsRemaining();
				updateHands();
				
				System.out.println(p1Hand.size() + " " + p2Hand.size() + " " + p3Hand.size()+ " " + p4Hand.size()+ " " + p5Hand.size());
				System.out.println();
				counter++;
				
			}
			
		}
		
		public static Player getWinningPlayer() {
			return winnerPlayer;
		}
		
		public static void main(String[] args) {
			openApplication();
			if (start == true) {
				if(players.size()>=2) {
					playRemainingRounds();
				}
			}else {
				System.out.println("The winner of the game is: " + winnerPlayer.getPlayerName());
				System.exit(0);
			}
		}
		
		
	
	}