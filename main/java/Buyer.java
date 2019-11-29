import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Buyer {
  public String name;
  private ArrayList<Items> items;
  public String userName;

  Buyer() {}

  Buyer(String name, String userName) {
    this.name = name;
    this.items = new ArrayList<>();
    this.userName = userName;
  }

  public List<Buyer> getList() {

    return null;
  }

  public void makeBidForItem(Items item, int price) {}

  public void addItemToBuyer(Items item) {}

  public Buyer getDetails(String userName) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    ResultSet rs = stmt.executeQuery("SELECT * FROM buyer as it where it.userName = " + userName);
    Buyer buyer = new Buyer(rs.getString(1), rs.getString(0));

    return buyer;
  }

  public boolean createBuyer() {
    // create this as a new buyer
    return true;
  }
}
