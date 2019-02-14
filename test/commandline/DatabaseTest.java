	package commandline;
	
	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotSame;
	import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
	import java.util.Scanner;
	
	import org.junit.After;
	import org.junit.AfterClass;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.jupiter.api.Test;
	
	import com.mysql.jdbc.Connection;
	import com.mysql.jdbc.Statement;
	
	class DatabaseTest {
		/*
		 * Declaration of objects for Database and PlayGame classes.
		 */
		Database dbBefore = new Database();
		PlayGame pg = new PlayGame();
				
		/**
		 * fixed data to be saved in the database:
		 * number of draws =2
		 * winner player number = 1 : the human player
		 * total rounds = 15; human rounds =2; Ai1 Rounds =4; Ai2 Rounds =2; Ai3 Rounds=3; Ai4 Rounds=2
		 * 
		 */
		@Before
		void insertOneGameWithThirdteenRounds() {
			
			dbBefore.postResultsToDatabase(2, 1, pg.setTotalRound(15), pg.setHumanRounds(2), pg.setAi1Rounds(4), pg.setAi2Rounds(2), pg.setAi3Rounds(3), pg.setAi4Rounds(2));
		}
	
		
		/**
		 * check if the expected results are the same as from the data retreived from the database.
		 * 
		 */
		@Test
		void testInsertOneGameMoreRounds() throws SQLException {
				insertOneGameWithThirdteenRounds();
				//dbBefore.displayResults();
				
				assertEquals(2.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(1, dbBefore.totalGames());
				assertEquals(15, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(0, dbBefore.computerWon());				
		}
		
		
		/**
		 * fixed data to be saved in the database:
		 * number of draws =5
		 * winner player number = 4 : Ai4 Player
		 * total rounds = 33; human rounds =7; Ai1 Rounds =6; Ai2 Rounds =3; Ai3 Rounds=5; Ai4 Rounds=7
		 * 
		 */
		@Before
		void insertTWoGamesWithMoreRounds() {
			
			dbBefore.postResultsToDatabase(5, 4, pg.setTotalRound(33), pg.setHumanRounds(7), pg.setAi1Rounds(6), pg.setAi2Rounds(3), pg.setAi3Rounds(5), pg.setAi4Rounds(7));
		}
		
		/**
		 * check if the expected results are the same as from the data retreived from the database.
		 * 
		 */	
		@Test
		void testInsertTwoGamesMoreRounds() throws SQLException {				
				insertTWoGamesWithMoreRounds();
				//dbBefore.displayResults();
				
				assertEquals(1.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(3, dbBefore.totalGames());
				assertEquals(33, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(2, dbBefore.computerWon());


		}
		
		/**
		 * fixed data to be saved in the database:
		 * number of draws =2
		 * winner player number = 3 : Ai3 Player
		 * total rounds = 10; human rounds =3; Ai1 Rounds =2; Ai2 Rounds =1; Ai3 Rounds=1; Ai4 Rounds=2
		 * 
		 */
		@Before
		void insertTWoGamesWithLessRounds() {
			
			dbBefore.postResultsToDatabase(2, 3, pg.setTotalRound(10), pg.setHumanRounds(3), pg.setAi1Rounds(2), pg.setAi2Rounds(1), pg.setAi3Rounds(1), pg.setAi4Rounds(2));
		}
	
		
		/**
		 * check if the expected results are the same as from the data retreived from the database.
		 * 
		 */	
		@Test
		void testInsertTwoGamesLessRounds() throws SQLException {
					
				insertTWoGamesWithLessRounds();
				//dbBefore.displayResults();
				
				assertEquals(1.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(2, dbBefore.totalGames());
				assertEquals(15, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(1, dbBefore.computerWon());
		}

}
