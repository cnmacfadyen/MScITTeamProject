package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static Connection c = null;
	Statement stmt = null;
	private static int totalGames;
	private static int highestRounds;
	private int totalAverageDraws;
	private static int computerWon;
	private static int humanWon;

	private String dbName = "postgres";

//	private String dbPassword = "0602452b";
//	private String dbPassword = "DTA123";

	private String dbPassword = "2138525f";

	/**
	 * Constructor for the database class, ocreateTable() is instantiated along with
	 * the instance of Database
	 */
	public Database() {
		createTable();
	}

	/**
	 * Display the results of: overall total games played, total amount of times a
	 * computer has won a game, total amount of times a human has won a game, the
	 * total average draws for overall games and the higher number of rounds in a
	 * game onto the console, if user selects to view statistics.
	 * 
	 */
	public void displayResults() {
		System.out.println("Game statistics: ");
		System.out.println("Number of Games: " + totalGames());
		System.out.println("Number of AI Wins: " + computerWon());
		System.out.println("Number of Human Wins: " + humanWon());
		System.out.println("Average number of Draws: " + avgDraws());
		System.out.println("Longest Game: " + highestRoundsInAGame());
		connectionClosed();
	}

	/**
	 * Connect to the database and make a query to get the total amount of games
	 * played in the gameresults database
	 * 
	 * @return Total amount of games played overall {@link Database#totalGames}
	 */
	public int totalGames() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM gameresults ");
			while (result.next()) {
				totalGames = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalGames;
	}

	/**
	 * Connect to the database and make a query to get the total amount of games the
	 * computer has won over the human player in the gameresults database
	 * 
	 * @return The number of games the AI has won over the human player
	 *         {@link Database#computerWon}
	 */
	public int computerWon() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT COUNT(whowon) FROM gameresults WHERE NOT whowon=1");
			while (result.next()) {
				computerWon = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerWon;
	}

	/**
	 * Connect to the database and make a query to get the total amount of games the
	 * human player has won over the computer in the gameresults database
	 * 
	 * @return Total games the human has won {@link Database#humanWon}
	 */
	public int humanWon() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT COUNT(whowon) FROM gameresults WHERE whowon=1");
			while (result.next()) {
				humanWon = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return humanWon;
	}

	/**
	 * Connect to the database and make a query to get the average drawsm of the
	 * total amount of games played in the gameresults database
	 * 
	 * @return Average draws of total games played
	 *         {@link Database#totalAverageDraws}
	 */
	public double avgDraws() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT AVG(drawrounds) FROM gameresults");
			while (result.next()) {
				totalAverageDraws = result.getInt("avg");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalAverageDraws;
	}

	/**
	 * Connect to the database and make a query to get the highest number of rounds
	 * played in a game in the gameresults database
	 * 
	 * @return Highest number of rounds in a game played
	 *         {@link Database#highestRounds}
	 */
	public int highestRoundsInAGame() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(roundsingame) FROM gameresults");
			while (result.next()) {
				highestRounds = result.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return highestRounds;
	}

	/**
	 * Connect to the database and insert results from the TopTrumps game into the
	 * gameresults database
	 * 
	 * @param drawsPerGame       The number of draws in a game
	 * @param winner             The overall winner in a game played
	 * @param totalRoundsInAGame The total amount of rounds played in a game
	 * @param humanRounds        The number of rounds the human has won in a game
	 * @param ai1                The number of rounds AI player 1 has won in a game
	 * @param ai2                The number of rounds AI player 2 has won in a game
	 * @param ai3                The number of rounds AI player 3 has won in a game
	 * @param ai4                The number of rounds AI player 4 has won in a game
	 */
	public void postResultsToDatabase(int drawsPerGame, int winner, int totalRoundsInAGame, int humanRounds, int ai1,
			int ai2, int ai3, int ai4) {
		try {
			Connection c = getConnection();
			PreparedStatement create = c.prepareStatement(
					"INSERT INTO gameresults(drawrounds,whowon,roundsingame,userrounds, ai1rounds,ai2rounds,ai3rounds,ai4rounds) "
							+ "VALUES(" + drawsPerGame + "," + winner + "," + totalRoundsInAGame + "," + humanRounds
							+ "," + ai1 + "," + ai2 + "," + ai3 + "," + ai4 + ")");

			create.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionClosed();
		}
	}

	/**
	 * Try and connect to the database and if there does not exist a gameresults
	 * table for TopTrumps then create table
	 */
	public void createTable() {
		try {
			Connection c = getConnection();
			PreparedStatement create = c.prepareStatement(
					"CREATE TABLE IF NOT EXISTS gameresults(GameNumber INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY , "
							+ "DrawRounds INT, WhoWon INT, RoundsInGame INT, UserRounds INT, AI1Rounds INT, AI2Rounds INT, AI3Rounds INT, AI4Rounds INT)");
			create.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Try and connect to the database
	 * 
	 * @return If we are able to connect then return the connection. However if
	 *         unable to connect then return null
	 */
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", dbName, dbPassword);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Delete the row from the TopTrumps game results database
	 * 
	 * @param num the row to be deleted
	 */
	public void deleteRow(int num) {
		try {
			Connection c = getConnection();
			PreparedStatement create = c.prepareStatement("DELETE FROM gameresults WHERE gameNumber=" + num);
			create.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the connection connecting to the database and if there is a
	 * SQLException then catch it and print out "Unable to close connection"
	 */
	public void connectionClosed() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to close connection");
		}
	}

}