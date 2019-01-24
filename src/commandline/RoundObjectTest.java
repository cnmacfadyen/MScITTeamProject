package commandline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RoundObjectTest {
	public static void main(String[] args) {
		String catName = "Speed";
		int roundNumber = 5;
		int numPlayers = 5;
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> communalPile = new ArrayList<Card>(); //doesn't matter if communalPile is empty (just prints an empty array)
		ArrayList<Card> deck1 = new ArrayList<Card>();
		ArrayList<Card> deck2 = new ArrayList<Card>();
		ArrayList<Card> deck3 = new ArrayList<Card>();
		ArrayList<Card> deck4 = new ArrayList<Card>();
		ArrayList<Card> deck5 = new ArrayList<Card>();
		ArrayList<Card> cardsInPlay = new ArrayList<Card>();
		ArrayList<Integer> catValues = new ArrayList<Integer>();
		
		Card c = new Card();
		deck = c.getDeck();
		
		for(int i=0;i<numPlayers;i++) {
			cardsInPlay.add(deck.get(i)); //just to get a random set of 5 cards for testing!!
		}
		
		Collections.shuffle(deck);
		
		deck1 = c.dealCards(5, 1, deck); //should we move deal cards to card class? wouldn't have to be static
		deck2 = c.dealCards(5, 2, deck);
		deck3 = c.dealCards(5, 3, deck);
		deck4 = c.dealCards(5, 4, deck);
		
		catValues.add(cardsInPlay.get(0).getC2()); //select the card in play and use get C2 to get the individual value for speed
		catValues.add(cardsInPlay.get(1).getC2());
		catValues.add(cardsInPlay.get(2).getC2());
		catValues.add(cardsInPlay.get(3).getC2()); //didn't include get(4) because player 5's hand is empty (for testing)

				
		RoundObject r = new RoundObject(roundNumber, communalPile, cardsInPlay, catName, catValues, deck1, deck2, deck3, deck4, deck5); 
		System.out.println(r);
		File output = new File("testLog.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(output, true);
			fw.write(r.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fw!=null) {
				try {
					fw.close();  //close the file
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
