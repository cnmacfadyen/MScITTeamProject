package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile=true; // Command line selection	
		}
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
//		while (!userWantsToQuit) {
//
//			// ----------------------------------------------------
//			// Add your game logic here based on the requirements
//			// ----------------------------------------------------
//			
//			userWantsToQuit=true; // use this when the user wants to exit the game
		
		PlayGame game = new PlayGame();	  
		   game.openApplication();
		   while (game.keepPlaying) {
		   if (game.start == true) {
		     game.playRemainingRounds();     
		    if(game.gameOver == true) {
		     System.out.println("The winner of the game is: " + game.winnerPlayer.getPlayerName());
		     game.db.postResultsToDatabase(game.totalDrawRounds, game.winnerPlayer.getPlayerNumber(), game.totalRounds, game.p1.getWonRound(), game.p2.getWonRound(),
		       game.p3.getWonRound(), game.p4.getWonRound(), game.p5.getWonRound());
		     if(writeGameLogsToFile == true) {
		      FileWriterClass f = new FileWriterClass();
		      f.returnTrumpsLog();
		     }
		     game.start = false;
		     System.out.println("Press 1 to view statistics, 2 to play a game. Press q at any time to quit.");
		     Scanner s = new Scanner(System.in);
		     game.selectOption(s);

				}
			}
		}
	}			
}