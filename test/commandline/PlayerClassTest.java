package commandline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerClassTest {

	//given
	String playerName = "player 1";
	int playerNumber = 1;
	//when
	Player player = new Player(playerName, playerNumber);
	
	/*
	 * to test that the expected name: player 1, is the same as the player name returned.
	 */
	@Test
	void testToGetPlayerName() {
		//then
		assertTrue(player.getPlayerName().equals(playerName));
		
	}
	
	/*
	 * to test that the expected number: 1, is the same as the player number returned.
	 */
	@Test
	void testToGetPlayerNumber() {
		
		assertTrue(player.getPlayerNumber() == playerNumber);
	}
}
