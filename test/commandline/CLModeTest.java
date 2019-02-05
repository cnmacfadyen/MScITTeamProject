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
	protected ArrayList<Card> communalPile = new ArrayList<Card>();
	protected ArrayList<Card> currentCardsInRound = new ArrayList<Card>();
	protected ArrayList<Integer> categoryValues = new ArrayList<Integer>();

	PlayGame pg = new PlayGame();
	
	Card c = new Card();
	ArrayList<Card> deck = c.getDeck(); 
	int nPlayers = 5;
	
	ArrayList<Card> player1Deck = c.dealCards(nPlayers, 1, deck);
	ArrayList<Card> player2Deck = c.dealCards(nPlayers, 2, deck);
	ArrayList<Card> player3Deck = c.dealCards(nPlayers, 3, deck);
	ArrayList<Card> player4Deck = c.dealCards(nPlayers, 4, deck);
	ArrayList<Card> player5Deck = c.dealCards(nPlayers, 5, deck);
	
	@BeforeClass
	void beforeTest() {
			
			currentCardsInRound.add(player1Deck.get(5));
			currentCardsInRound.add(player2Deck.get(5));
			currentCardsInRound.add(player3Deck.get(5));
			currentCardsInRound.add(player4Deck.get(5));
			currentCardsInRound.add(player5Deck.get(5));
		
			for(int i=0; i<=4; i++) {
				//System.out.println(currentCardsInRound.get(i));
				categoryValues.add(currentCardsInRound.get(i).getC2());
				Collections.sort(categoryValues);
				//pg.checkDuplicate();
			}	

	}
	
	//still trying to get this right
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
				//assertTrue(isDuplicate);
			}
			else {
				//isDuplicate = false;
				assertFalse("there are no duplicates",isDuplicate);
			}
		}
		
		System.out.println(counter);
		System.out.println(categoryValues);
				
	}
	
	//checking that cards are added to the communal pile when it is empty.
	@Test
	public void testAddToCommunalPile() {
		beforeTest();
		
		assertEquals(0, communalPile.size());
		
		for(int i = 0; i < 5; i++) {
			communalPile.add(i, currentCardsInRound.get(i));
		}
		assertEquals(5, communalPile.size());
		
	}
	
	//checking that more cards the second time can be added to the communal pile when it is not empty.
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
	
	
	@Test
	public void testQuitGameOption() {
		Scanner inp = new Scanner(System.in);
		String input = inp.nextLine();
		boolean isQuit = false;
		
			if((input.equals("q"))) {
				isQuit = true;
				pg.selectOption(inp); // need to fix... it asks for input twice and it doesn't fail if input was ( q 1 ) or ( q 2).
				assertTrue(isQuit);
				
			}
			else {
				assertTrue("to quit game, expected input is: 'q' or 'Q', but was: " +input , isQuit);
			}

	}
	
	
	@Test
	public void testCategorySelectionForHumanPlayer() {
		System.out.println("\nThis is to test the selection of a category for the human Player" );

		//int indx = 0;
		int cate = pg.selectCategory();
		assertNotNull(cate);
		System.out.println("Human player: " +  " \nselected the category: " + cate);

	}
	
	
}
