package commandline;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DealCardsTest {
	public static void main(String[] args) {
		ArrayList<Card> deck = new ArrayList<Card>();
		Card c = new Card();
		deck = c.getDeck();		
		int nPlayers = 5;
		
		if(nPlayers == 3) {
			deck.remove(deck.size()-1);
		}
		
		ArrayList<Card> player1Deck = dealCards(nPlayers, 1, deck);
		ArrayList<Card> player2Deck = dealCards(nPlayers, 2, deck);
		ArrayList<Card> player3Deck = dealCards(nPlayers, 3, deck);
		ArrayList<Card> player4Deck = dealCards(nPlayers, 4, deck);
		ArrayList<Card> player5Deck = dealCards(nPlayers, 5, deck);

		System.out.println(player1Deck);
		System.out.println();
		System.out.println(player1Deck.size());
		System.out.println("--------------------------------------------------");
		System.out.println(player2Deck);
		System.out.println();
		System.out.println(player2Deck.size());
		System.out.println("--------------------------------------------------");
		System.out.println(player3Deck);
		System.out.println();
		System.out.println(player3Deck.size());
		System.out.println("--------------------------------------------------");
		System.out.println(player4Deck);
		System.out.println();
		System.out.println(player4Deck.size());
		System.out.println("--------------------------------------------------");
		System.out.println(player5Deck);
		System.out.println();
		System.out.println(player5Deck.size());		
	}
	
	//pass it the number of players, the player number (e.g. player1), and the shuffled deck
	//IF THERE ARE 3 PLAYERS WE SHOULD ADD THE TOP CARD TO THE COMMUNAL PILE BEFORE WE CALL THE dealCards() METHOD// 
	//I.E. WE SHOULD REMOVE IT FROM THE SHUFFLED DECK SO WE CAN DIVIDE BY 3//
	//didn't add this functionality to the method because it removes a card each time it is called
	public static ArrayList<Card> dealCards(int nPlayers, int playerNumber, ArrayList<Card> shuffledDeck) { 
		ArrayList<Card> playerDeck = new ArrayList<Card>();
		for(int i=0;i<shuffledDeck.size();i=i+nPlayers) {
				playerDeck.add(shuffledDeck.get(i+playerNumber-1));
			}
		return playerDeck;
	}
}
