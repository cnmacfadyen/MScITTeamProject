package commandline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class PlayerClassTest {

	//given
	String playerName = "player 1";
	int playerNumber = 1;
	//when
	Player player = new Player(playerName, playerNumber);
	
	@Before
	void init() {
		
	}
	
	@Test
	void testToGetPlayerName() {
		//then
		assertTrue(player.getPlayerName().equals(playerName));
		
	}
	
	@Test
	void testToGetPlayerNumber() {
		
		assertTrue(player.getPlayerNumber() == playerNumber);
	}
}
