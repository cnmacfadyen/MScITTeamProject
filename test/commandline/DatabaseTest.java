package commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;

class DatabaseTest {
	Database db = new Database();
	Database dbBefore = new Database();
	PlayGame pg = new PlayGame();
	Scanner inp = new Scanner(System.in);
	
	@BeforeClass
	void displayBefore() {
//		dbBefore.createTable();
//		dbBefore.displayResults();
		
		
	}
	
	@BeforeClass
	void setUp() {
		pg.selectOption(inp);
		pg.playFirstRound();
		//pg.playRemainingRounds();
		
		
	}
	
	@Test
	void testInsertingData() throws SQLException {
		int expected = dbBefore.setAverageDraws(3);
		setUp();
		java.sql.Connection c = db.getConnection();
		c.setAutoCommit(false);
		//db.createTable();
;		try {
//			db.postResultsToDatabase(db.getAverageDraws(), pg.getWinningPlayer().getPlayerNumber() , pg.getTotalRound(), pg.getHumanRounds(), pg.getAi1Rounds(), pg.getAi2Rounds(), pg.getAi3Rounds(), pg.getAi4Rounds());
//			//db.postResultsToDatabase(drawsPerGame, winner, totalRoundsInAGame, humanRounds, ai1, ai2, ai3, ai4);
			db.displayResults();
//			//assert(db.drawrounds == 3);		
			assertEquals(dbBefore.getAverageDraws(), db.getAverageDraws(), 0);
			System.out.println(dbBefore.getAverageDraws() +" "+ db.getAverageDraws());
//			System.out.println(dbBefore.getTotalGames() +" "+ db.getTotalGames());

		}
		finally {
			c.rollback();
			c.close();
		}
	}
	
	
	@Test
	void testIfDataSavedIsTheSameAsDataRetreived() {
		//setUp();
		//assertEquals(db.displayResults(), db.equals(obj))
		//db.highestRoundsInAGame();
		
		//db.displayResults();
		
	}

}
