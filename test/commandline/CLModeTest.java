package commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class CLModeTest {
	/*
	 * declaration of objects and arraylists that will be needed for the testing.
	 */
	protected ArrayList<Card> communalPile = new ArrayList<Card>();
	protected ArrayList<Card> currentCardsInRound = new ArrayList<Card>();
	protected ArrayList<Integer> categoryValues = new ArrayList<Integer>();

	PlayGame pg = new PlayGame();
	Card c = new Card();
	ArrayList<Card> deck = c.getDeck(); 
	
	//number of players
	int nPlayers = 5;
	//declaring of the 5 players' decks.
	ArrayList<Card> player1Deck = c.dealCards(nPlayers, 1, deck);
	ArrayList<Card> player2Deck = c.dealCards(nPlayers, 2, deck);
	ArrayList<Card> player3Deck = c.dealCards(nPlayers, 3, deck);
	ArrayList<Card> player4Deck = c.dealCards(nPlayers, 4, deck);
	ArrayList<Card> player5Deck = c.dealCards(nPlayers, 5, deck);
	
	/*
	 * The current cards in round are: player1: 5th card, AI1: 4th card , AI2: 3th card, AI3: 2nd card and AI4:  1st card.
	 * selected category : 4

	 */
	@BeforeClass
	void beforeTest() {
			
			currentCardsInRound.add(player1Deck.get(5));
			currentCardsInRound.add(player2Deck.get(4));
			currentCardsInRound.add(player3Deck.get(3));
			currentCardsInRound.add(player4Deck.get(2));
			currentCardsInRound.add(player5Deck.get(1));
		
			for(int i=0; i<=4; i++) {
				categoryValues.add(currentCardsInRound.get(i).getC4());
				Collections.sort(categoryValues);
			}	

	}
	
	/*
	 * test to check if there is a draw.
	 */
	@Test
	public void testGetDuplicated() {
		beforeTest();
		
		int counter = currentCardsInRound.size();
		int cb = categoryValues.size()-1;
		boolean isDuplicate =false;
		for(int i=counter; i> 0; i--) {
			if(categoryValues.get(cb).equals(categoryValues.get(cb-1))) {
				pg.checkDuplicate();
				isDuplicate = true;
				assertTrue("there are not duplicates", isDuplicate);
			}

		}
		
		//System.out.println("The values of the selected category:" + categoryValues);
				
	}
	
	/*
	 * test to check that cards are added to the communal pile when it is empty.
	 */
	@Test
	public void testAddToCommunalPile() {
		beforeTest();
		
		assertEquals(0, communalPile.size());
		
		for(int i = 0; i < 5; i++) {
			communalPile.add(i, currentCardsInRound.get(i));
		}
		assertEquals(5, communalPile.size());
		
	}
	
	/*
	 * test to check that more cards the second time can be added to the communal pile when it is not empty.
	 */
	@Test
	public void testAddToCommunalPile2() {
		beforeTest();
		for(int i = 0; i < 5; i++) {
			communalPile.add(i, currentCardsInRound.get(i));
		}
		assertEquals(5, communalPile.size());
		
		for(int i = 0; i < 5; i++) {
			communalPile.add(i, currentCardsInRound.get(i));
		}
		assertEquals(10, communalPile.size());
	}
	
	/*
	 * test the quit game option
	 */
	@Test
	public void testQuitGameOption() {
		System.out.println("\nenter input 'q' or 'Q' to quit game");
		Scanner inp = new Scanner(System.in);
		String input = inp.nextLine();
		boolean isQuit = false;
		
			if((input.equals("q"))) {
				isQuit = true;
				assertTrue(isQuit);
				
			}
			else {
				assertTrue("to quit game, expected input is: 'q' or 'Q', but was: " +input , isQuit);
			}

	}
	
	/*
	 * test that the returned value for category selection by human player is not null.
	 */
	@Test
	public void testCategorySelectionForHumanPlayer() {
		System.out.println("\nselect a category: " );

		//int indx = 3;
		int cate = pg.selectCategory();
		assertNotNull(cate);
		System.out.println("Human player: " +  " \nselected the category: " + cate);

	}
	
}
