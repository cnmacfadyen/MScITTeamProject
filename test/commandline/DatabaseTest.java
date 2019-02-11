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
		Database dbBefore = new Database();
		PlayGame pg = new PlayGame();
		Player p = new Player();
		
		Scanner inp = new Scanner(System.in);
		
//		@AfterClass
//		void toDeleteTable() throws SQLException {
//			java.sql.Connection c = dbBefore.getConnection();
//			c.setAutoCommit(false);
//			try {
////				
//				PreparedStatement create1 = c.prepareStatement( "DELETE FROM gameresults WHERE gameNumber=1");
//				PreparedStatement create2 = c.prepareStatement( "DELETE FROM gameresults WHERE gameNumber=2");
//				
//				create1.executeQuery();
//				create2.executeQuery();
//				//create1.getUpdateCount();
//			}
//			
//			finally {
//				c.rollback();
//				c.close();
//			}
//		}
	


		@Before
		void insertOneGameWithThirdteenRounds() {
			
			dbBefore.postResultsToDatabase(2, 1, pg.setTotalRound(15), pg.setHumanRounds(2), pg.setAi1Rounds(4), pg.setAi2Rounds(2), pg.setAi3Rounds(3), pg.setAi4Rounds(2));
		}
	
		@Test
		void testInsertOneGameMoreRounds() throws SQLException {
				//totalNumofRounds/totalnumberofDraw = totalavgDraw
				insertOneGameWithThirdteenRounds();
				dbBefore.displayResults();
				assertEquals(2.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(1, dbBefore.totalGames());
				assertEquals(15, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(0, dbBefore.computerWon());				
		}
		
		
		@Before
		void insertTWoGamesWithMoreRounds() {
			
			dbBefore.postResultsToDatabase(5, 4, pg.setTotalRound(33), pg.setHumanRounds(7), pg.setAi1Rounds(6), pg.setAi2Rounds(3), pg.setAi3Rounds(5), pg.setAi4Rounds(7));
		}
	
		@Test
		void testInsertTwoGamesMoreRounds() throws SQLException {
			
			//getAvgofallgames/totalnumofgames
				
				insertTWoGamesWithMoreRounds();
				dbBefore.displayResults();
				//check the statisctics displayed are correct.
				assertEquals(1.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(3, dbBefore.totalGames());
				assertEquals(33, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(2, dbBefore.computerWon());

//				System.out.println(dbBefore.avgDraws()/dbBefore.totalGames());

		}
		
		@Before
		void insertTWoGamesWithLessRounds() {
			
			dbBefore.postResultsToDatabase(2, 4, pg.setTotalRound(10), pg.setHumanRounds(3), pg.setAi1Rounds(2), pg.setAi2Rounds(1), pg.setAi3Rounds(1), pg.setAi4Rounds(2));
		}
	
		@Test
		void testInsertTwoGamesLessRounds() throws SQLException {
			
			//getAvgofallgames/totalnumofgames
				
				insertTWoGamesWithLessRounds();
				dbBefore.displayResults();
				//check the statisctics displayed are correct.
				assertEquals(1.0, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(2, dbBefore.totalGames());
				assertEquals(15, dbBefore.highestRoundsInAGame());
				assertEquals(1, dbBefore.humanWon());
				assertEquals(1, dbBefore.computerWon());

//				System.out.println(dbBefore.avgDraws()/dbBefore.totalGames());

		}

}
