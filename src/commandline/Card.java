package commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Card {
	private String name, cat1, cat2, cat3, cat4, cat5; //name of each card plus the attribute names 
	private int c1, c2, c3, c4, c5;
	
	public Card() {		
	}
	
	public Card(String name, int a1, int a2, int a3, int a4, int a5) { //pass each card a name and 5 attributes
		this.name = name;
		this.c1 = a1;
		this.c2 = a2;
		this.c3 = a3;
		this.c4 = a4;
		this.c5 = a5;
	}
	
	public String getCat1Name() {
		return cat1;
	}
	
	public String getCat2Name() {
		return cat2;
	}
	
	public String getCat3Name() {
		return cat3;
	}
	
	public String getCat4Name() {
		return cat4;
	}
	
	public String getCat5Name() {
		return cat5;
	}
	
	public int getC1() {
		return c1;
	}
	
	public int getC2() {
		return c2;
	}
	
	public int getC3() {
		return c3;
	}
	
	public int getC4() {
		return c4;
	}
	
	public int getC5() {
		return c5;
	}
	
	
	//temporary method, to get the selected category with the corresponding value ... for testing 
	public String getCatInfo(int indx) {
		String s = "";
		if(indx == 0) {
			s = "category: " + getCat1Name() + " " + getC1();
		}
		else if(indx == 1) {
			s = "category: " + getCat2Name() + " " + getC2();
		}
		else if(indx == 2) {
			s = "category: " + getCat3Name() + " " + getC3();
		}
		else if(indx == 3) {
			s = "category: " + getCat4Name() + " " + getC4();
		}
		else if(indx == 4) {
			s = "category: " + getCat5Name() + " " + getC5();
		}
		return s;
	}
	
	
	public String toString() {
		return "\n\n" + name + ": \n" + cat1 + " - " + c1 + "\n" + cat2 + " - " 
	+ c2 + "\n" + cat3 + " - " + c3 + "\n" + cat4 + " - " + c4 + "\n" + cat5 + " - " + c5;
	}
	
	protected void setAttNames() { //change method so we can pass it a file
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
	
	protected ArrayList<Card> getDeck() { //should we change method so we can pass it a file?
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
	
	//pass it the number of players, the player number (e.g. player1), and the shuffled deck
	//IF THERE ARE 3 PLAYERS WE SHOULD ADD THE TOP CARD TO THE COMMUNAL PILE BEFORE WE CALL THE dealCards() METHOD// 
	//I.E. WE SHOULD REMOVE IT FROM THE SHUFFLED DECK SO WE CAN DIVIDE BY 3//
	//didn't add this functionality to the method because it removes a card each time it is called
	public ArrayList<Card> dealCards(int nPlayers, int playerNumber, ArrayList<Card> shuffledDeck) { 
		ArrayList<Card> playerDeck = new ArrayList<Card>();
		for(int i=0;i<shuffledDeck.size();i=i+nPlayers) {
				playerDeck.add(shuffledDeck.get(i+playerNumber-1));
			}
		return playerDeck;
	}
	

}
