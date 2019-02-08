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
		
		@AfterClass
		void toDeleteTable() throws SQLException {
			java.sql.Connection c = dbBefore.getConnection();
			c.setAutoCommit(false);
			try {
				
				PreparedStatement create1 = c.prepareStatement( "DELETE FROM gameresults WHERE gameNumber=1");
				PreparedStatement create2 = c.prepareStatement( "DELETE FROM gameresults WHERE gameNumber=2");
				
				create1.executeQuery();
				create2.executeQuery();
			}
			
			finally {
				c.rollback();
				c.close();
			}
		}
	


		@Before
		void insertOneGameWithThirdteenRounds() {
			
			dbBefore.postResultsToDatabase(2, 1, pg.setTotalRound(15), pg.setHumanRounds(2), pg.setAi1Rounds(4), pg.setAi2Rounds(2), pg.setAi3Rounds(3), pg.setAi4Rounds(2));
		}
	
		@Test
		void testInsertOneGameMoreRounds() throws SQLException {
			//toDeleteTable();
			java.sql.Connection c = dbBefore.getConnection();
			c.setAutoCommit(false);
			try {
				//totalNumofRounds/totalnumberofDraw = totalavgDraw
				insertOneGameWithThirdteenRounds();
				dbBefore.displayResults();
				assertEquals(2, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(1, dbBefore.totalGames());
				assertEquals(2, pg.getHumanRounds());
				assertEquals(4, pg.getAi1Rounds());
				assertEquals(2, pg.getAi2Rounds());
				assertEquals(3, pg.getAi3Rounds());
				assertEquals(2, pg.getAi4Rounds());
				//assertEquals(1, pg.getWinningPlayerNumber());
	
	
			}
			finally {
				//c.rollback();
				//toDeleteTable();
				c.close();	
			}
		}
		
		
		@Before
		void insertTWoGamesWithMoreRounds() {
			
			dbBefore.postResultsToDatabase(5, 4, pg.setTotalRound(33), pg.setHumanRounds(7), pg.setAi1Rounds(6), pg.setAi2Rounds(3), pg.setAi3Rounds(5), pg.setAi4Rounds(7));
		}
	
		@Test
		void testInsertTwoGamesMoreRounds() throws SQLException {
			
			java.sql.Connection c = dbBefore.getConnection();
			c.setAutoCommit(false);
			try {
				
				//getAvgofallgames/totalnumofgames
				
				insertTWoGamesWithMoreRounds();
				dbBefore.displayResults();
				assertEquals(1.5, dbBefore.avgDraws()/dbBefore.totalGames(),0);
				assertEquals(2, dbBefore.totalGames());
				assertEquals(7, pg.getHumanRounds());
				assertEquals(6, pg.getAi1Rounds());
				assertEquals(3, pg.getAi2Rounds());
				assertEquals(5, pg.getAi3Rounds());
				assertEquals(7, pg.getAi4Rounds());
				//assertEquals(1, pg.getWinningPlayerNumber());
				
				System.out.println(dbBefore.avgDraws()/dbBefore.totalGames());
				dbBefore.deleteRow(3);
				dbBefore.displayResults();
			}
			finally {
				//toDeleteTable();
				//c.rollback();
				c.close();	
			}
		}

		@BeforeClass
		void deletee() {
			
		}
	
	
}
