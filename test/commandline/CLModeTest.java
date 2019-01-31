package commandline;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class CLModeTest {
	private ArrayList<Card> communalPile = new ArrayList<Card>();
	private ArrayList<Card> currentCardsInRound = new ArrayList<Card>();

	PlayGame pg = new PlayGame();
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testGetHighestValue() {
		
	}
	
	@Test
	public void testGetDuplicated() {
		
	}
	
	
	@Test
	public void testAddToCommunalPile() {

		
	}
	
	@Test
	public void testWinnerToPlayNextRound() {
		
	}
	
	@Test
	public void testSelectingOptionForHumanPlayer() {
//		System.out.println("select an option test: ");
//		Scanner in = new Scanner(System.in);
//		PlayGame.selectOption(in);
//		assertTrue(true);
	}
	
	
	@Test
	public void testQuitGameOption() {
		System.out.println("this is a test for quit game option");
		//Scanner in = new Scanner(System.in);
	
		//assertEquals(System.exit(1), PlayGame.quit(in));
	}
	
	
	@Test
	public void testViewStatisticsOption() {
		
		pg.viewStatistics(true);
	}
	
	@Test
	public void testCategorySelectionForHumanPlayer() {
		System.out.println("\nThis is to test the selection of a category for the human Player" );

		pg.indx = 0;
		pg.selectedCategoryValue = pg.selectCategory();
		assertNotNull(pg.selectedCategoryValue);
		System.out.println("Human player: " +  " \nselected the category: " + pg.selectedCategoryValue);

	}
	
	
}
