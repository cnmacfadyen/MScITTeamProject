package commandline;

	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Random;
	import java.util.Scanner;

	public class GameplayEdits {
		static Card c = new Card();
		 //this is to test the methods in this class...
		static ArrayList<Card> deck =  (ArrayList<Card>) c.getDeck().clone();
		static FileWriterClass f = new FileWriterClass();
		
		public static void main(String[] args) {
//			Card c = new Card();
//			ArrayList<Card> deck = c.getDeck(); 
//			Collections.shuffle(deck);
			//System.out.println(deck);
			//System.out.println("card 1: " + deck.get(2)+ "\n\ncard 2: " + deck.get(4) + "\n\n result");
			//getHighestValue();
			deal();
//			System.out.println(getP1Deck().get(0));
//			System.out.println(getP5Deck().get(0));
//			System.out.println(getNewA());
			getNewA();
			checkDuplicate();
			if (cardsToCommunal == false) {
				System.out.println(winningCard());
			}
			else {
				System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
			}
//			updateHands();
			getNewA();
			checkDuplicate();
			if (cardsToCommunal == false) {
				System.out.println(winningCard());
			}
			else {
				System.out.println("The following cards have been added to the communal pile: " + getCurrentCardsInRound());
			}
			f.returnTrumpsLog();
			
			
//			for(Card card : deck) {
//				//viewCardData(card);
//			}
		}
		
		
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
		private ArrayList<Player> players = new ArrayList<Player>(5);
		private static ArrayList<Integer> newA = new ArrayList<Integer>();
		private static boolean cardsToCommunal;
		
		
		static int selectedCat;
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
			selectedCat =0;
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
//			selectedCategory = playerCard.getCategoryName();
//			selectedCategoryValue = playerCard.getCategoryValue();
			
			return selectedCat;
		}

		
		public static void addToCommunalPile(Card c) {
			
			//adding the specified card to the communalPile arraylist.
			communalPile.add(c);
			
		}
		
		public void playRound() {
			
		}
		
		//public void viewCardData(Player p)
		public static void viewCardData(Card c)
		{
			c.toString();
		}
		
		public static ArrayList<Integer> getNewA() { //creating newA instead of returning highest value
//			int highestVal =0;
			newA.clear();
//			ArrayList<Integer> newA = new ArrayList<Integer>();
			
			if(selectedCat == 0) {
				//currentCardsInRound.get(1);
				newA.add(0, getCurrentCardsInRound().get(0).getC1());
				newA.add(1,getCurrentCardsInRound().get(1).getC1());
				newA.add(2, getCurrentCardsInRound().get(2).getC1());
				newA.add(3, getCurrentCardsInRound().get(3).getC1());
				newA.add(4, getCurrentCardsInRound().get(4).getC1());
				
//				highestVal = Collections.max(newA);
				

			}
			
			if(selectedCat == 1) {
				//currentCardsInRound.get(1);
				newA.add(0, getCurrentCardsInRound().get(0).getC2());
				newA.add(1, getCurrentCardsInRound().get(1).getC2());
				newA.add(2, getCurrentCardsInRound().get(2).getC2());
				newA.add(3, getCurrentCardsInRound().get(3).getC2());
				newA.add(4, getCurrentCardsInRound().get(4).getC2());
				
//				highestVal = Collections.max(newA);
				

			}
		
			
			if(selectedCat == 2) {
				//currentCardsInRound.get(1);
				newA.add(0, getCurrentCardsInRound().get(0).getC3());
				newA.add(1, getCurrentCardsInRound().get(1).getC3());
				newA.add(2, getCurrentCardsInRound().get(2).getC3());
				newA.add(3, getCurrentCardsInRound().get(3).getC3());
				newA.add(4, getCurrentCardsInRound().get(4).getC3());
				
//				highestVal = Collections.max(newA);
				

			}
			
			
			if(selectedCat == 3) {
				//currentCardsInRound.get(1);
				newA.add(0, getCurrentCardsInRound().get(0).getC4());
				newA.add(1, getCurrentCardsInRound().get(1).getC4());
				newA.add(2, getCurrentCardsInRound().get(2).getC4());
				newA.add(3, getCurrentCardsInRound().get(3).getC4());
				newA.add(4, getCurrentCardsInRound().get(4).getC4());
				
//				highestVal = Collections.max(newA);
				

			}
			
			
			if(selectedCat == 4) {
				//currentCardsInRound.get(1);
				newA.add(0, getCurrentCardsInRound().get(0).getC5());
				newA.add(1, getCurrentCardsInRound().get(1).getC5());
				newA.add(2, getCurrentCardsInRound().get(2).getC5());
				newA.add(3, getCurrentCardsInRound().get(3).getC5());
				newA.add(4, getCurrentCardsInRound().get(4).getC5());
				
//				highestVal = Collections.max(newA);
				

			}
		
			return newA;
			
			
//			if(c1.getC1() > c2.getC1()) {
//				System.out.println(c1.getCat1Name() + ": " + c1.getC1() + "  > " + c2.getCat1Name() + ": " + c2.getC1());
//				highestVal = c1.getC1();
//			}
//			else if(c1.getC2() > c2.getC2()) {
//				System.out.println(c1.getCat2Name() + ": " + c1.getC2() + "  > " + c2.getCat2Name() + ": " + c2.getC2());
//				highestVal = c1.getC2();
//			}
//			else if(c1.getC3() > c2.getC3()) {
//				System.out.println(c1.getCat3Name() + ": " + c1.getC3() + "  > " + c2.getCat3Name() + ": " + c2.getC3());
//				highestVal = c1.getC3();
//			}
//			else if(c1.getC4() > c1.getC4()) {
//				System.out.println(c1.getCat4Name() + ": " + c1.getC4() +"  > " + c2.getCat4Name() + ": " + c2.getC4());
//				highestVal = c1.getC4();
//			}
//			else if(c1.getC5() > c2.getC5()) {
//				System.out.println(c1.getCat5Name() + ": " + c1.getC5() + "  > " + c2.getCat5Name() + ": " + c2.getC5());
//				highestVal = c1.getC5();
//			}
//			else if(c1.getC1() < c2.getC1()) {
//				System.out.println(c1.getCat1Name() + ": " + c1.getC1() + "  < " + c2.getCat1Name() + ": " + c2.getC1());
//				highestVal = c2.getC1();
//			}
//			else if(c1.getC2() < c2.getC2()) {
//				System.out.println(c1.getCat2Name() + ": " + c1.getC2() + "  < " + c2.getCat2Name() + ": " + c2.getC2());
//				highestVal = c2.getC2();
//			}
//			else if(c1.getC3() < c2.getC3()) {
//				System.out.println(c1.getCat3Name() + ": " + c1.getC3() + "  < " + c2.getCat3Name() + ": " + c2.getC3());
//				highestVal = c2.getC3();
//			}
//			else if(c1.getC4() < c1.getC4()) {
//				System.out.println(c1.getCat4Name() + ": " + c1.getC4() +"  < " + c2.getCat4Name() + ": " + c2.getC4());
//				highestVal = c2.getC4();
//			}
//			else if(c1.getC5() < c2.getC5()) {
//				System.out.println(c1.getCat5Name() + ": " + c1.getC5() + "  < " + c2.getCat5Name() + ": " + c2.getC5());
//				highestVal = c2.getC5();
//			}
//			else {
//				System.out.println("it is a draw");
//			}
			//System.out.println(highestVal);
//			return highestVal;
			
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
		//*********** Need to work on this method to ensure it only prints the card details if there's a winner
		public static Card winningCard() {
//			if(cardsToCommunal == false) {
				//This means there's a winner
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
				Player winnerPlayer = new Player((returnHighestIndex(getNewA()) + 1));
			 
			}

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
		
		public static ArrayList<Card> getShuffledDeck() {
			Collections.shuffle(deck);
			shuffledDeck = new ArrayList<Card>();
			shuffledDeck = deck;
			return shuffledDeck;
			
		}
		
		public static void deal() {
			p1Hand.add(getShuffledDeck().get(0));
			p2Hand.add(getShuffledDeck().get(1));
			p3Hand.add(getShuffledDeck().get(2));
			p4Hand.add(getShuffledDeck().get(3));
			p5Hand.add(getShuffledDeck().get(4));
			p1Hand.add(getShuffledDeck().get(5));
			p2Hand.add(getShuffledDeck().get(6));
			p3Hand.add(getShuffledDeck().get(7));
			p4Hand.add(getShuffledDeck().get(8));
			p5Hand.add(getShuffledDeck().get(9));
			p1Hand.add(getShuffledDeck().get(10));
			p2Hand.add(getShuffledDeck().get(11));
			p3Hand.add(getShuffledDeck().get(12));
			p4Hand.add(getShuffledDeck().get(13));
			p5Hand.add(getShuffledDeck().get(14));
			p1Hand.add(getShuffledDeck().get(15));
			p2Hand.add(getShuffledDeck().get(16));
			p3Hand.add(getShuffledDeck().get(17));
			p4Hand.add(getShuffledDeck().get(18));
			p5Hand.add(getShuffledDeck().get(19));
			p1Hand.add(getShuffledDeck().get(20));
			p2Hand.add(getShuffledDeck().get(21));
			p3Hand.add(getShuffledDeck().get(22));
			p4Hand.add(getShuffledDeck().get(23));
			p5Hand.add(getShuffledDeck().get(24));
			p1Hand.add(getShuffledDeck().get(25));
			p2Hand.add(getShuffledDeck().get(26));
			p3Hand.add(getShuffledDeck().get(27));
			p4Hand.add(getShuffledDeck().get(28));
			p5Hand.add(getShuffledDeck().get(29));
			p1Hand.add(getShuffledDeck().get(30));
			p2Hand.add(getShuffledDeck().get(31));
			p3Hand.add(getShuffledDeck().get(32));
			p4Hand.add(getShuffledDeck().get(33));
			p5Hand.add(getShuffledDeck().get(34));
			p1Hand.add(getShuffledDeck().get(35));
			p2Hand.add(getShuffledDeck().get(36));
			p3Hand.add(getShuffledDeck().get(37));
			p4Hand.add(getShuffledDeck().get(38));
			p5Hand.add(getShuffledDeck().get(39));
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
	
	}

