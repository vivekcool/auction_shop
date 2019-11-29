import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.util.function.DoubleToIntFunction;

public class Main {
  private static Statement stmt;
  private static Connection con;
  private static HashMap<String, String> sellerUserNames;
  private static HashMap<String, String> buyerUserNames;
  private static Scanner in = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    stmt = DbConnection.getDBStatement().stmt;
    boolean toContinue = true;
    int ct = 0;

    ResultSet rs = stmt.executeQuery("SELECT user_name FROM seller");
    while (rs.next()) {
      sellerUserNames.put(rs.getString(0), "seller");
    }
    ResultSet rsb = stmt.executeQuery("SELECT user_name FROM buyer");
    while (rsb.next()) {
      buyerUserNames.put(rs.getString(0), "buyer");
    }

    while (toContinue && ct++ < 3) {
      System.out.println("Hii, Welcome to auction Shop.");
      System.out.println("Please type Y if you are new user, else type N");
      String input = in.next();
      if (input.charAt(0) == 'Y') {
        createNewUser();
      } else {
        System.out.println(
            "Okay then, type your valid userName, that you have used at the time of registration");
      }
      String userName = in.next();
      if (sellerUserNames.containsKey(userName)) {
        System.out.println("User found.. Fetching the details...");
        Seller s = new Seller().getDetails(userName);
        System.out.println(
            "Details are: Full Name => " + s.getName() + " , User_Name => " + s.getUserName());
        System.out.println("Items on sale by you are : ");
        List<Items> itemsList = new Items().getItemsForSeller(userName);
        for (Items items : itemsList) {
          System.out.println(items.toString());
        }
        System.out.println("Do you want to create another item for sale? type Y or N ");
        String inp = in.next();
        if (inp.charAt(0) == 'Y') {
          System.out.println("Okay, wait a second");
          Items item = new Items();
          item.createItem(userName);
          s.createItemForSale(item);
          System.out.println("Item Registered, ");

        } else {
          System.out.println("Okay, goodBye");
        }

      } else if (buyerUserNames.containsKey(userName)) {
        System.out.println("User identified as buyer.");
        Buyer buyer = new Buyer().getDetails(userName);
        System.out.println(
            "Details are : Type => Buyer\n"
                + " User Name => "
                + buyer.userName
                + ", FullName => "
                + buyer.name);

        showItemsBought(userName);

        System.out.println("Showing you the available catrgories of items..");
        String category = showCategories();
        Items itemId = showAllItems(category);
        System.out.println("Enter the bid you want to make");
        int bid = in.nextInt();
        if (bid > itemId.getMax_bid_so_far()) {
          itemId.setMax_bid_so_far(bid);
          itemId.setBuyerName(userName);
          System.out.println("Your bid has been marked up. Thanks");
        }

      } else {
        System.out.println("Invalid username, goodBye.");
      }
    }

    con.close();
  }

  private static void showItemsBought(String userName) {
    List<Items> items = new Items().getItemsForBuyer(userName);
    for (Items item : items) {
      item.toString();
    }
  }

  private static Items showAllItems(String category) {

    return null;
  }

  private static String showCategories() throws Exception {
    String category = "";
    ResultSet rs = stmt.executeQuery("SELECT DISTINCT category FROM items");
    while (rs.next()) {
      sellerUserNames.put(rs.getString(0), "seller");
    }
    return category;
  }

  private static void createNewUser() {
    System.out.println("Okay, we will register you as a new user.");
    System.out.println("Type S for seller or B for buyer");
    String input = in.next();
    if (input.charAt(0) == 'S') {

    } else {

    }
  }
}
