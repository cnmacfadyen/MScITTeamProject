package commandline;

public class dbTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Database db = new Database();
db.displayResults();
db.deleteRow(2);
db.displayResults();

	}

}
