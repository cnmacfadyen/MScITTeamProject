package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CardTest {	
	public static void main(String[] args) {
		Card c = new Card();
		ArrayList<Card> deck = c.getDeck(); 
		Collections.shuffle(deck);
		System.out.println(deck);
		for(Card card : deck) {
			System.out.println(card.getCat1Name() + " - " + card.getC1()); //just testing out the methods!
		}

	}
}
