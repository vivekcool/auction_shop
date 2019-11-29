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

  public void createItem(String userName) {
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

  }

  public List<Items> getItemsForSeller(String userName) {

    return null;
  }

  public List<Items> getItemsForBuyer(String userName) {

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

  public void setMax_bid_so_far(int max_bid_so_far) {
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

  public void setBuyerName(String userName) {}

  public boolean isSold() {
    return this.sold;
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
