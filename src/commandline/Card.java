package commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Card {
	private String name, cat1, cat2, cat3, cat4, cat5; 
	private int c1, c2, c3, c4, c5;
	
	/**
	 *  Default constructor for Card
	 */
	public Card() {		
	}
	
	/**
	 * Card constructor
	 * @param name - name of the Card (e.g. Vanguard)
	 * @param a1 - integer value of 1st attribute
	 * @param a2 - integer value of 2nd attribute
	 * @param a3 - integer value of 3rd attribute
	 * @param a4 - integer value of 4th attribute
	 * @param a5 - integer value of 5th attribute
	 */
	public Card(String name, int a1, int a2, int a3, int a4, int a5) { 
		this.name = name;
		this.c1 = a1;
		this.c2 = a2;
		this.c3 = a3;
		this.c4 = a4;
		this.c5 = a5;
	}
	
	/**
	 * 
	 * @param catNumber
	 * @return the given category number if it exists, else return -1
	 */
	public int getChosenCategory(int catNumber) {
		if(catNumber == 0) {
			return c1;
		}else if(catNumber == 1){
			return c2;
		}else if(catNumber == 2){
			return c3;
		}else if(catNumber == 3){
			return c4;
		}else if(catNumber == 4){
			return c5;
		}else {
			return -1;
		}
	}
	
	/**
	 * 
	 * category 1 name getter
	 * @return {@link Card#cat1}
	 */
	public String getCat1Name() {
		return cat1;
	}
	
	/**
	 * category 2 name getter
	 * @return {@link Card#cat2}
	 */
	public String getCat2Name() {
		return cat2;
	}
	
	/**
	 * category 3 name getter
	 * @return {@link Card#cat3}
	 */
	public String getCat3Name() {
		return cat3;
	}
	
	/**
	 * category 4 name getter
	 * @return {@link Card#cat4}
	 */
	public String getCat4Name() {
		return cat4;
	}
	
	/**
	 * category 5 name getter
	 * @return {@link Card#cat5}
	 */
	public String getCat5Name() {
		return cat5;
	}	
	
	/**
	 * category 1 value getter
	 * @return {@link Card #c1}
	 */
	public int getC1() {
		return c1;
	}
	
	/**
	 * category 2 value getter
	 * @return {@link Card #c2}
	 */
	public int getC2() {
		return c2;
	}
	
	/**
	 * category 3 value getter
	 * @return {@link Card #c3}
	 */
	public int getC3() {
		return c3;
	}
	
	/**
	 * category 4 value getter
	 * @return {@link Card #c4}
	 */
	public int getC4() {
		return c4;
	}
	
	/**
	 * category 5 value getter
	 * @return {@link Card #c5}
	 */
	public int getC5() {
		return c5;
	}
	
	/**
	 * toString method to return the card details for printing
	 * @return String containing card name, category names and category values
	 */
	public String toString() {
		return "\n\n" + name + ": \n" + cat1 + " - " + c1 + "\n" + cat2 + " - " 
	+ c2 + "\n" + cat3 + " - " + c3 + "\n" + cat4 + " - " + c4 + "\n" + cat5 + " - " + c5;
	}
	
	/**
	 * method to set the names for each category in the card deck
	 * reads in the first line of the deck text file to get category names
	 * e.g. "Size", "Speed"
	 */
	public void setAttNames() { 
		FileReader fr = null;		
		String firstLine = "";
		try {
			fr = new FileReader("StarCitizenDeck.txt");		
			Scanner s = new Scanner(fr);
			firstLine = s.nextLine();
			String[] tokens = firstLine.split(" "); //each word is separated by a space
			String description = tokens[0]; //first word in file is "description"
			cat1 = tokens[1];
			cat2 = tokens[2];
			cat3 = tokens[3];
			cat4 = tokens[4];
			cat5 = tokens[5];			
		}catch(FileNotFoundException e) {
			System.out.println("Could not locate file");
			e.printStackTrace();
		}finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } 
	}
	
	/**
	 * method to return an ArrayList (deck) of cards, setting 
	 * the card name and values for each of the attributes
	 * by reading from the given file ("StarCitizenDeck.txt")
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getDeck() { 
		FileReader fr = null;
		ArrayList<Card> deck = new ArrayList<Card>();
		String firstLine = "";
		String nextLine = "";
		try {
			fr = new FileReader("StarCitizenDeck.txt");		
			Scanner s = new Scanner(fr);
			firstLine = s.nextLine(); //ignore first line
			while(s.hasNextLine()) {
				nextLine = s.nextLine();
				String[] tokens = nextLine.split(" ");
				name = tokens[0]; //each line starts with the name 
				c1 = Integer.parseInt(tokens[1]);
				c2 = Integer.parseInt(tokens[2]);
				c3 = Integer.parseInt(tokens[3]);
				c4 = Integer.parseInt(tokens[4]);
				c5 = Integer.parseInt(tokens[5]);
				Card c = new Card(name,c1,c2,c3,c4,c5);
				c.setAttNames();
				deck.add(c);
			}		
		}catch(FileNotFoundException e) {
			System.out.println("Could not locate file");
			e.printStackTrace();
		}finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }return deck; 
	}
	

	/**
	 * Method to split the ArrayList of Cards between the players
	 * by dividing them between the number of players in a game. The method must be called for
	 * each player in the game in order to give them a different set of cards.
	 * @param nPlayers - the number of players in the game
	 * @param playerNumber - the number of the player that is being dealt to
	 * @param shuffledDeck - the full deck, after it has been randomly shuffled
	 * @return the deck assigned to the given player
	 */
	public ArrayList<Card> dealCards(int nPlayers, int playerNumber, ArrayList<Card> shuffledDeck) { 
		ArrayList<Card> playerDeck = new ArrayList<Card>();
		for(int i=0;i<shuffledDeck.size();i=i+nPlayers) {
				playerDeck.add(shuffledDeck.get(i+playerNumber-1));
			}
		return playerDeck;
	}
}