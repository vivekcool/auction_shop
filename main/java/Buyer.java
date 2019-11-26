import java.util.ArrayList;

public class Buyer {
  private String name;
  private ArrayList<Items> items;
  private String userName;

  Buyer(String name, String userName) {
    this.name = name;
    this.items = new ArrayList<>();
    this.userName = userName;
  }
}
