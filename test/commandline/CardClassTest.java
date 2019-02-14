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
	
	/**
	 * this is to test that the deck is not empty.
	 */
	@Test
	void testDeckHasCards() {
		assertNotNull("there are no cards in deck", deck);
	}
	
	/**
	 * this is to test that the getDeck() method is not returning null. 
	 */
	@Test
	void testGettingDeck() {
		assertNotNull(c.getDeck());
	}
	
	/**
	 * this is to test that a card in a deck is being returned.
	 */
	@Test
	void testToGetCategoryNameAndValue() {	
			for(int i =0; i < deck.size(); i++) {
				assertNotNull(deck.get(i));
			}	
	}
	
	/**
	 * this is to test that the cards in deck are shuffled.
	 */
	@Test
	void TestDeckIsShuffled() {
		Collections.shuffle(deck);
		ArrayList<Card> shuffledDeck = c.getDeck();

		assertFalse("deck is not shuffled", deck.equals(shuffledDeck));
	}
	
	/**
	 * this is to check that the cards are being evenly distributed between the players.
	 * 40 cards in deck.
	 * expected: 8 cards for each player
	 */
	@Test
	void TestDealingCards() {
		assertEquals(deck.size()/nPlayers, player1Deck.size() );
		assertEquals(deck.size()/nPlayers, player2Deck.size() );
		assertEquals(deck.size()/nPlayers, player3Deck.size() );
		assertEquals(deck.size()/nPlayers, player4Deck.size() );
		assertEquals(deck.size()/nPlayers, player5Deck.size() );
	}

	
	
}
