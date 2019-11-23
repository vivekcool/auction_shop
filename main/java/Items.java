public class Items {
  private String name;
  private String description;
  private int min_bid;
  public int max_bid_so_far;


  Items(String name, String desc, int min_bid) {
    this.description = desc;
    this.min_bid = min_bid;
    this.name = name;
    this.max_bid_so_far = min_bid;
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
}
