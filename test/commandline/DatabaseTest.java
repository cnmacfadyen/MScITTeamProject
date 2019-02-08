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
		
//		@BeforeClass
//		void toDeleteTable() throws SQLException {
//			java.sql.Connection c = dbBefore.getConnection();
//			c.setAutoCommit(false);
//			try {
//				PreparedStatement create = c.prepareStatement( "DELETE FROM gameresults");
//				//int deletedRows = statement.executeUpdate(query);
//				create.executeUpdate();
//				
//					//System.out.println("Rows deleted successfully");
//			}
//			
//			finally {
//				c.rollback();
//				c.close();
//			}
//		}
//	


		@Before
		void insertOneGameWithThirdteenRounds() {
			
			dbBefore.postResultsToDatabase(dbBefore.setAverageDraws(2), 1, pg.setTotalRound(13), pg.setHumanRounds(2), pg.setAi1Rounds(4), pg.setAi2Rounds(2), pg.setAi3Rounds(3), pg.setAi4Rounds(2));
		}
	
		@Test
		void testInsertOneGameMoreRounds() throws SQLException {
		//	toDeleteTable();
			java.sql.Connection c = dbBefore.getConnection();
			c.setAutoCommit(false);
			try {
				
				insertOneGameWithThirdteenRounds();
				dbBefore.displayResults();
				assertEquals(2, dbBefore.getAverageDraws());
				//assertEquals(, dbBefore.getTotalGames());
				assertEquals(2, pg.getHumanRounds());
				assertEquals(4, pg.getAi1Rounds());
				assertEquals(2, pg.getAi2Rounds());
				assertEquals(3, pg.getAi3Rounds());
				assertEquals(2, pg.getAi4Rounds());
				//assertEquals(1, pg.getWinningPlayerNumber());
	
	
			}
			finally {
				c.rollback();
				c.close();	
			}
		}
		
		
		@Before
		void insertTWoGamesWithMoreRounds() {
			
			dbBefore.postResultsToDatabase(dbBefore.setAverageDraws(5), 4, pg.setTotalRound(28), pg.setHumanRounds(7), pg.setAi1Rounds(6), pg.setAi2Rounds(3), pg.setAi3Rounds(5), pg.setAi4Rounds(7));
		}
	
		@Test
		void testInsertTwoGamesMoreRounds() throws SQLException {
		//	toDeleteTable();
			java.sql.Connection c = dbBefore.getConnection();
			c.setAutoCommit(false);
			try {
				
				insertTWoGamesWithMoreRounds();
				dbBefore.displayResults();
				assertEquals(5, dbBefore.getAverageDraws());
				//assertEquals(, dbBefore.getTotalGames());
				assertEquals(7, pg.getHumanRounds());
				assertEquals(6, pg.getAi1Rounds());
				assertEquals(3, pg.getAi2Rounds());
				assertEquals(5, pg.getAi3Rounds());
				assertEquals(7, pg.getAi4Rounds());
				//assertEquals(1, pg.getWinningPlayerNumber());

			}
			finally {
				c.rollback();
				c.close();	
			}
		}
	
}
