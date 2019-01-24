package commandline;

import java.util.ArrayList;

public class RoundObject {
	private int roundNumber;
	private ArrayList <Card> communalPile;
	private ArrayList <Card> cardsInRound;
	private String category;
	private ArrayList<Integer> categoryValues;
	private ArrayList<Card> p1EndOfRoundHand, p2EndOfRoundHand, p3EndOfRoundHand,p4EndOfRoundHand, p5EndOfRoundHand;
	
	//complete deck, shuffled deck, starting player hands and winner of the game do not need to be inside this class, they are defined elsewhere
	
	public RoundObject(int roundNumber, ArrayList<Card> communalPile, ArrayList<Card> cardsInRound,
						String category, ArrayList<Integer> categoryValues, ArrayList<Card> p1EndOfRoundHand,
						ArrayList<Card> p2EndOfRoundHand, ArrayList<Card> p3EndOfRoundHand, ArrayList<Card> p4EndOfRoundHand, 
						ArrayList<Card> p5EndOfRoundHand) {
		this.roundNumber=roundNumber;
		this.communalPile=communalPile;
		this.cardsInRound=cardsInRound;
		this.category=category;
		this.categoryValues=categoryValues;
		this.p1EndOfRoundHand=p1EndOfRoundHand; //these will be the players' hands after each round												
		this.p2EndOfRoundHand=p2EndOfRoundHand; //(at the start of the game it will be their starting hand)
		this.p3EndOfRoundHand=p3EndOfRoundHand;
		this.p4EndOfRoundHand=p4EndOfRoundHand;
		this.p5EndOfRoundHand=p5EndOfRoundHand; 
	}
	
	public String toString() {
		String output = "\n\n\n\t~~~~~ Round " + roundNumber + " ~~~~~" 
				+"\n---------------------------------------------------\nCommunal Pile Contents: " + communalPile
				+"\n---------------------------------------------------\nCurrent Cards in Play: " + cardsInRound 	
				+"\n---------------------------------------------------\n\nCategory selected:\n\n\t" + category 
				+"\n\n---------------------------------------------------\n\nValues of selected category:\n\n\t" + categoryValues
				+"\n\n---------------------------------------------------\nPlayer 1 deck at end of round " + roundNumber + ":\n\t" + p1EndOfRoundHand
				+"\n---------------------------------------------------\nPlayer 2 deck at end of round " + roundNumber + ":\n\t" + p2EndOfRoundHand
				+"\n---------------------------------------------------\nPlayer 3 deck at end of round " + roundNumber + ":\n\t" + p3EndOfRoundHand
				+"\n---------------------------------------------------\nPlayer 4 deck at end of round " + roundNumber + ":\n\t" + p4EndOfRoundHand
				+"\n---------------------------------------------------\nPlayer 5 deck at end of round " + roundNumber + ":\n\t" + p5EndOfRoundHand;

		return output;
	}
}