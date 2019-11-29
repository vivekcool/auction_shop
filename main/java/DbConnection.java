import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {
  private static DbConnection db = null;
  public Statement stmt = null;

  DbConnection() {

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con =
          DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/auction", "auction", "burningtrain");
      this.stmt = con.createStatement();
      //      int n = stmt.executeUpdate("INSERT INTO test_table values (2,'Anuj Pancholi')");
      //      System.out.println("The number of results are: " + n);
      //      ResultSet rs = stmt.executeQuery("SELECT * FROM test_table");
      //      while (rs.next()) System.out.println(rs.getInt(1) + "  " + rs.getString(2));
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static DbConnection getDBStatement() {

    if (db == null) {
      db = new DbConnection();

      System.out.println("HIIIII " + db.stmt);
      return db;
    }

    return db;
  }
}
