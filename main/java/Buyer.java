import java.util.ArrayList;
import java.util.List;

public class Buyer {
  private String name;
  private ArrayList<Items> items;
  private String userName;

  Buyer()
  {

  }
  Buyer(String name, String userName) {
    this.name = name;
    this.items = new ArrayList<>();
    this.userName = userName;
  }
  public List<Buyer> getList(){

    return null;
  }

  public void makeBidForItem(Items item, int price){

  }
  public void addItemToBuyer(Items item){

  }
  public Buyer getDetails(String userName){

    return null;
  }

  public boolean createBuyer(){
    // create this as a new buyer
    return true;
  }

}
