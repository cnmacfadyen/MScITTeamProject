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
		
	}
	
	@Test
	public void testCategorySelection() {
//		PlayGame pg = new PlayGame();
//		int sC = pg.selectCategory();
//
//		System.out.println("the category you selected is : " + sC);
//		assertEquals(sC, sC);

	}
	
	@Test
	public void testSelectingCategoryForAIPlayer() {
		Random random = new Random(5);
		//PlayGame.selectCategory();
		assertNotNull(PlayGame.selectCategory());
	}
	
	@Test
	public void testShufflingCards() {
		
	}

	
}
