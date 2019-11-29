public class Items {
  private String name;
  private int min_bid;
  private int max_bid_so_far;
  private String category;
  private String sellerName;
  private boolean sold;

  Items(String name, int min_bid, String category, String sellerName) {
    this.min_bid = min_bid;
    this.name = name;
    this.max_bid_so_far = min_bid;
    this.category = category;
    this.sellerName = sellerName;
    this.sold = false;
  }

  public void createItem(){
    // create this as a new item..

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

  public boolean isSold() {
    return this.sold;
  }
}
