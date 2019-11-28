import java.util.Scanner;
import java.sql.*;

public class Main {
  private static Statement stmt;
  private static Connection con;

  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(System.in);
    buildConnectionToDB();

    con.close();
  }

  private static void buildConnectionToDB() {

    try {
      Class.forName("com.mysql.jdbc.Driver");
      con =
          DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/auction", "auction", "burningtrain");
      stmt = con.createStatement();
      //      int n = stmt.executeUpdate("INSERT INTO test_table values (2,'Anuj Pancholi')");
      //      System.out.println("The number of results are: " + n);
      //      ResultSet rs = stmt.executeQuery("SELECT * FROM test_table");
      //      while (rs.next()) System.out.println(rs.getInt(1) + "  " + rs.getString(2));
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
