package commandline;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class CardClassTest {
	
	Card c = new Card();
	ArrayList<Card> deck = c.getDeck(); 
	int nPlayers = 5;
		
	ArrayList<Card> player1Deck = c.dealCards(nPlayers, 1, deck);
	ArrayList<Card> player2Deck = c.dealCards(nPlayers, 2, deck);
	ArrayList<Card> player3Deck = c.dealCards(nPlayers, 3, deck);
	ArrayList<Card> player4Deck = c.dealCards(nPlayers, 4, deck);
	ArrayList<Card> player5Deck = c.dealCards(nPlayers, 5, deck);
	
	
	@Test
	void testDeckHasCards() {
		assertNotNull("there are no cards in deck", deck);
	}
	
	
	@Test
	void testToGetCategoryNameAndValue() {	
			for(int i =0; i < deck.size(); i++) {
				assertNotNull(deck.get(i));
				//System.out.println(deck.get(i));
			}	
	}
		
	@Test
	void TestDeckIsShuffled() {
		Collections.shuffle(deck);
		ArrayList<Card> shuffledDeck = c.getDeck();

		assertFalse("deck is not shuffled", deck.equals(shuffledDeck));
	}
		
	@Test
	void TestDealingCards() {
		assertEquals(deck.size()/nPlayers, player1Deck.size() );
		assertEquals(deck.size()/nPlayers, player2Deck.size() );
		assertEquals(deck.size()/nPlayers, player3Deck.size() );
		assertEquals(deck.size()/nPlayers, player4Deck.size() );
		assertEquals(deck.size()/nPlayers, player5Deck.size() );
	}

	
	@Test
	void testGettingDeck() {
		//System.out.println(c.getDeck());
		assertNotNull(c.getDeck());
	}
}
