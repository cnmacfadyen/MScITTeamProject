package commandline;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
	
	private String log = "toptrumps.log";
	FileWriter writeToLog;
	FakeData fd = new FakeData();
	Card c = new Card();
	PlayGame gp = new PlayGame();
	public void returnTrumpsLog() {
		try {
			 writeToLog = new FileWriter(log, false);
			 writeToLog.write("Top Trumps Test Log\n\nContents of unshuffled deck:\n\t" + c.getDeck() +
					 		  "\n---------------------------------------------------\n" +
					 		  "Contents of shuffled deck:\n\t" + gp.getShuffledDeck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 1 deck (Human):\n\t" + gp.getP1Deck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 2 deck (AI):\n\t" + gp.getP2Deck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 3 deck (AI):\n\t" + gp.getP3Deck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 4 deck (AI):\n\t" + gp.getP4Deck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 5 deck (AI):\n\t" + gp.getP5Deck() +
					 		 "\n---------------------------------------------------\n");
//			while (gameOver = false) { //I think this stuff might have to come from the database, but I'm not 100%
//			 Might make sense to use a boolean for this bit and the winner as well
				writeToLog.write(fd.getRoundNumber()+ ":\n Communal Contents:\n\t" + fd.getCommunalContents() +
								"\n---------------------------------------------------\nCards in play:\n\t" + fd.cardsInPlay() +
								"\n---------------------------------------------------\nCategory selected:\n\t" + fd.categorySelected() +
								"\n---------------------------------------------------\nValues of selected category\n\t" + gp.getNewA()+
								"\n---------------------------------------------------\nNew deck contents:\n\t" + fd.newDeckContents() +
								"\n---------------------------------------------------\n");
//			}
			
//			if (gameOver = true) {
				writeToLog.write("Winner of game:\n\t" + fd.returnWinner() +
								"\n---------------------------------------------------\n");
//			}
		}catch (IOException e) { 
			e.printStackTrace();
		}finally {
			if(writeToLog!=null) {
				try {
					writeToLog.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
