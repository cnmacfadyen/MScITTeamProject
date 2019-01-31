package commandline;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
	
	private String log = "toptrumps.log";
	FileWriter writeToLog;
	Card c = new Card();
	PlayGame gp = new PlayGame();
	
	public void returnTrumpsLog() {
		try {
			 writeToLog = new FileWriter(log, false);
			 writeToLog.write("Top Trumps Test Log\n\nContents of unshuffled deck:\n\t" + c.getDeck() +
					 		  "\n---------------------------------------------------\n" +
					 		  "Contents of shuffled deck:\n\t" + gp.getShuffledDeck() +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 1 deck (Human):\n\t" + PlayGame.h1 +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 2 deck (AI):\n\t" + PlayGame.h2 +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 3 deck (AI):\n\t" + PlayGame.h3 +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 4 deck (AI):\n\t" + PlayGame.h4 +
					 		 "\n---------------------------------------------------\n" +
					 		  "Player 5 deck (AI):\n\t" + PlayGame.h5 +
					 		 "\n---------------------------------------------------\n");
			 
			 
			 for (int i = 0; i< RoundObject.getRoundsArray().size(); i++) {
				 writeToLog.write(RoundObject.getRoundsArray().get(i).toString());
			 }
			
				writeToLog.write("\n---------------------------------------------------\n" +
								"Winner of game:\n\t" + PlayGame.getWinningPlayer().getPlayerName() +
								"\n---------------------------------------------------\n");

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
