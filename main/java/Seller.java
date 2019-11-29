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

  public void createItemForSale(Items item) {}

  public Seller getDetails(String userName) {

    return null;
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

  public boolean createSeller() {
    // create this as a new seller
    return true;
  }
}
