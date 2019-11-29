import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Seller {
  private String name;
  private String userName;
  private ArrayList<Items> itemsToBeSold;

  Seller(String name, String userName) {
    this.name = name;
    this.userName = userName;
    this.itemsToBeSold = new ArrayList<>();
  }

  Seller() {}

  public void createItemForSale(Items item) {
    new Items().createItem(this.userName);
  }

  public Seller getDetails(String userName) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    ResultSet rs = stmt.executeQuery("SELECT * FROM seller as it where it.user_name = " + userName);
    Seller seller = new Seller(rs.getString(1), rs.getString(0));

    return seller;
  }

  public String getName() {
    return this.name;
  }

  public String getUserName() {
    return this.userName;
  }

  public boolean markSold(int itemId) {

    return true;
  }

  public boolean createSeller() throws Exception {

    // create this as a new seller
    Statement stmt = DbConnection.getDBStatement().stmt;
    int rs =
        stmt.executeUpdate(
            "INSERT INTO seller (user_name, full_name) VALUES ('this.userName','this.name')");

    return true;
  }
}
