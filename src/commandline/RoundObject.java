package commandline;

import java.util.ArrayList;

public class RoundObject {
	private int roundNumber;
	private ArrayList <Card> communalPile;
	private ArrayList <Card> cardsInRound;
	private String category;
	private ArrayList<Integer> categoryValues;
	private ArrayList<Card> p1EndOfRoundHand, p2EndOfRoundHand, p3EndOfRoundHand,p4EndOfRoundHand, p5EndOfRoundHand;
	protected static ArrayList<RoundObject> roundsArray = new ArrayList<RoundObject>();
	
	/**
	 * Constructor for RoundObjects - these are used to store the following details from each round in a game
	 * in order to print them to the toptrumps.log test log file. 
	 * @param roundNumber
	 * @param communalPile
	 * @param cardsInRound
	 * @param category
	 * @param categoryValues
	 * @param p1EndOfRoundHand
	 * @param p2EndOfRoundHand
	 * @param p3EndOfRoundHand
	 * @param p4EndOfRoundHand
	 * @param p5EndOfRoundHand
	 */
	public RoundObject(int roundNumber, ArrayList<Card> communalPile, ArrayList<Card> cardsInRound,
						String category, ArrayList<Integer> categoryValues, ArrayList<Card> p1EndOfRoundHand,
						ArrayList<Card> p2EndOfRoundHand, ArrayList<Card> p3EndOfRoundHand, ArrayList<Card> p4EndOfRoundHand, 
						ArrayList<Card> p5EndOfRoundHand) {
		this.roundNumber=roundNumber;
		this.communalPile=communalPile;
		this.cardsInRound=cardsInRound;
		this.category=category;
		this.categoryValues=categoryValues;
		this.p1EndOfRoundHand=p1EndOfRoundHand; 											
		this.p2EndOfRoundHand=p2EndOfRoundHand; 
		this.p3EndOfRoundHand=p3EndOfRoundHand;
		this.p4EndOfRoundHand=p4EndOfRoundHand;
		this.p5EndOfRoundHand=p5EndOfRoundHand; 
	}
	
	/**
	 * toString method to print out round details to the test log. A new RoundObject is created after each round to store the details
	 * for the round, allowing them to be printed to the toptrumps.log file.
	 */
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
	
	/**
	 * method to return the ArrayList of RoundObjects that store the values for each round
	 * @return
	 */
	public static ArrayList<RoundObject> getRoundsArray(){
		return roundsArray;
	}
	
}