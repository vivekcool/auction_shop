import java.util.ArrayList;

public class Seller {
  private String name;
  private String userName;
  private ArrayList<Items> itemsToBeSold;

  Seller(String name, String userName) {
    this.name = name;
    this.userName = userName;
    this.itemsToBeSold = new ArrayList<>();
  }

}
