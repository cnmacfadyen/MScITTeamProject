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
	private  double totalAverageDraws;
	private static int computerWon;
	private static int humanWon;
	private String dbName = "postgres";
<<<<<<< HEAD
	private String dbPassword = "DTA123";
=======
	private String dbPassword = "2138525f";
>>>>>>> ebd030477980e1d2279f74e1c36f8f3c1cb47e67

	
	public Database() {
		createTable();
	}

	public int getAverageDraws() {
		return (int) totalAverageDraws;
	}
	
	public int setAverageDraws(int avg) {
		return (int) (totalAverageDraws =  avg);
	}
	
	public int setTotalGames(int tgame) {
		return totalGames = tgame;
	}
	
	public int getTotalGames() {
		return totalGames;
	}
	public void displayResults() {
		System.out.println("Game statistics: ");
		System.out.println("Number of Games: " + totalGames());
		System.out.println("Number of AI Wins: " + computerWon());
		System.out.println("Number of Human Wins: " + humanWon());
		System.out.println("Average number of Draws: " + avgDraws());
		System.out.println("Longest Game: " + highestRoundsInAGame());
		connectionClosed();
	}

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

	public int computerWon() {
		Statement statement = null;
		try {
			Connection c = getConnection();
			statement = c.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT COUNT(whowon) FROM gameresults WHERE NOT whowon=1");
			while (result.next()) {
				computerWon = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerWon;
	}

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

	// get connection//
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
	
	
	public void deleteRow(int num) {
		try {
			Connection c = getConnection();
			PreparedStatement create = c.prepareStatement(
					"DELETE FROM gameresults WHERE gameNumber="+ num);
			create.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void connectionClosed() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to close connection");
		}
	}

}