import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Items {
  private String name;
  private int min_bid;
  private int max_bid_so_far;
  private String buyerName;
  private String category;
  private String sellerName;
  private boolean sold;

  Items() {}

  Items(String name, int min_bid, String category, String sellerName) {
    this.min_bid = min_bid;
    this.name = name;
    this.max_bid_so_far = min_bid;
    this.category = category;
    this.sellerName = sellerName;
    this.sold = false;
  }

  public void createItem(String userName) throws Exception{
    Scanner in = new Scanner(System.in);
    System.out.println("Type the name of Item.");
    String name = in.next();
    System.out.println("Type the category of Item.");
    String category = in.next();
    System.out.println("Type the starting Price of Item.");
    int minPrice = in.nextInt();

    this.sold = false;
    this.name = name;
    this.category = category;
    this.min_bid = minPrice;
    this.max_bid_so_far = minPrice;
    this.sellerName = userName;
    this.buyerName = null;
    // create this as a new item..
    Statement stmt = DbConnection.getDBStatement().stmt;
    int rs =
            stmt.executeUpdate(
                    "INSERT INTO items (name, min_bid, max_bid, category, seller_name,sold,) VALUES (this.name,this.min_bid,this.maxbid, category, this.seller_name, false)");


  }

  public List<Items> getItemsForSeller(String userName) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    ResultSet rs =
        stmt.executeQuery("SELECT * FROM items as it where it.seller_name = " + userName);

    List<Items> items = new ArrayList<>();
    while (rs.next()) {
      Items item = new Items(rs.getString(0), rs.getInt(1), rs.getString(3), rs.getString(5));
      item.setMax_bid_so_far(rs.getInt(2));
      item.sold = rs.getBoolean(6);
      items.add(item);
    }
    return items;
  }

  public List<Items> getItemsForBuyer(String userName) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    ResultSet rs = stmt.executeQuery("SELECT * FROM items as it where it.buyer_name = " + userName);

    List<Items> items = new ArrayList<>();
    while (rs.next()) {
      Items item = new Items(rs.getString(0), rs.getInt(1), rs.getString(3), rs.getString(5));
      item.setMax_bid_so_far(rs.getInt(2));
      item.sold = rs.getBoolean(6);
      items.add(item);
    }
    return null;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.name;
  }

  public int getMin_bid() {
    return this.min_bid;
  }

  public void setMax_bid_so_far(int max_bid_so_far) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    int rs =
        stmt.executeUpdate(
            "UPDATE items SET max_bid = " + max_bid_so_far + " WHERE name = " + this.name);

    this.max_bid_so_far = max_bid_so_far;
  }

  public int getMax_bid_so_far() {
    return this.getMax_bid_so_far();
  }

  public String getCategory() {
    return this.category;
  }

  public String getSellerName() {
    return this.sellerName;
  }

  public String getBuyerName() {
    return this.buyerName;
  }

  public void setBuyerName(String userName) throws Exception {
    Statement stmt = DbConnection.getDBStatement().stmt;
    int rs =
        stmt.executeUpdate(
            "UPDATE items SET buyer_name = " + userName + " WHERE name = " + this.name);

    this.max_bid_so_far = max_bid_so_far;
  }

  public boolean isSold() {
    return this.sold;
  }

  public void setSold(boolean sold) {
    this.sold = sold;
  }

  @Override
  public String toString() {
    String ret = "";
    ret +=
        "Item Name => "
            + this.name
            + ", Category => "
            + this.category
            + ", StartPrice => "
            + this.min_bid
            + ", Max_Bid_So_Far => "
            + this.max_bid_so_far
            + ", sold_status => "
            + this.isSold();

    return ret;
  }
}
