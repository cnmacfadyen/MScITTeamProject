package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class CardClassTest {

	
	Card c = new Card();
	ArrayList<Card> deck = c.getDeck(); 
	
	
	@Test
	void testDeckHasCards() {

		if(deck != null) {
			System.out.println("Pass test");
			System.out.println("cards in the deck : " + deck.size());
		}
	}
	
	
	@Test
	void testToGetCategoryNameAndValue() {
		
		if(deck != null) {
			for(int i =0; i < deck.size(); i++) {
				//System.out.println(deck.get(i).getCatInfo(i));
				
			}
		}
	}
	
	
	
	@Test
	void TestDeckIsShuffled() {
		Collections.shuffle(deck);
		ArrayList<Card> shuffledDeck = c.getDeck();
		if(!deck.equals(shuffledDeck) ) {
			System.out.println("Pass");
			System.out.println();
			//System.out.println(deck);
			//System.out.println("--------------------------");
			//System.out.println(shuffledDeck);
		}
	}
	
	
	@Test
	void TestDealingCards() {
		
	}

}
