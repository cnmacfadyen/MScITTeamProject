package commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class CLModeTest {
	
	

	@Test
	public void testStartingPlayerSelection() {
		
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
	public void testRemoveFromCommunalPile() {
		
	}
	
	@Test
	public void testWinnerToPlayNextRound() {
		
	}
	
	
	@Test
	public void testOptionSelectionForHuman() {
		
	}
	
	@Test
	public void testSelectingOptionForHumanPlayer() {
		
	}
	
	
	@Test
	public void testQuitGameOption() {
//		Scanner in = new Scanner(System.in);
//		String s = "q";
//		PlayGame.selectOption(in);
		//assertEquals(s );
	}
	
	
	
	@Test
	public void testPlayNewGameOption() {
		//Scanner in = new Scanner(System.in);
		//assertEquals(2, PlayGame.selectOption(in));

	}
	
	
	@Test
	public void testViewStatisticsOption() {
		PlayGame pg = new PlayGame();
		pg.viewStatistics(true);
	}
	
	@Test
	public void testCategorySelection() {
		System.out.println("\nThis is to test the selection of a category for the human Player" );
		PlayGame pg = new PlayGame();
		pg.indx = 0;
		pg.selectedCategoryValue = pg.selectCategory();
		assertNotNull(pg.selectedCategoryValue);
		System.out.println("Human player: " +  " \nselected the category: " + pg.selectedCategoryValue);

	}
	
	//should there be a method for each AI player to test this? or can we keep changing the number of the indx which represent the AI player everytime we test?
	@Test
	public void testSelectingCategoryForAIPlayer() {
		System.out.println("\nThis is to test the selection of a category for any of the AI Players" );
		PlayGame pg = new PlayGame();
		pg.indx = 3; // AI Player number 1-4
		pg.selectedCategoryValue = pg.selectCategory();
		assertNotNull(pg.selectedCategoryValue);
		System.out.println("AI player:  " + pg.indx +  " \nselected the category: " + pg.selectedCategoryValue);
	}
	
	@Test
	public void testShufflingCards() {
		
	}

	
}
