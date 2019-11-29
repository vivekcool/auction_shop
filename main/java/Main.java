import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

import java.security.PrivateKey;
import java.util.ArrayList;
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

    if (stmt == null) {
      System.out.println("good byeeeee");
      return;
    }
    System.out.println(stmt);

    ResultSet rs = stmt.executeQuery("SELECT user_name FROM seller");
    while (rs.next()) {
      sellerUserNames.put(rs.getString(0), "seller");
    }
    ResultSet rsb = stmt.executeQuery("SELECT userName FROM buyer");
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

          System.out.println("Showing you the available categories of items..");
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
        toContinue = false;
        System.out.println("GoodBye.");
      }
    }
    con.close();
  }

  private static void showItemsBought(String userName) throws Exception {
    List<Items> items = new Items().getItemsForBuyer(userName);
    for (Items item : items) {
      item.toString();
    }
  }

  private static Items showAllItems(String category) throws Exception {
    ResultSet rs = stmt.executeQuery("SELECT * FROM items as it where it.category = " + category);

    System.out.println("Items from selected category are. ");
    List<Items> items = new ArrayList<>();
    while (rs.next()) {
      Items item = new Items(rs.getString(0), rs.getInt(1), rs.getString(3), rs.getString(5));
      item.setMax_bid_so_far(rs.getInt(2));
      item.setSold(rs.getBoolean(6));

      items.add(item);
    }
    System.out.println("Enter the Serial Number of category you want to select the items.");
    int serial = 1;
    for (Items item : items) {
      System.out.println(serial + ".  " + item.toString());
      serial++;
    }
    int select = in.nextInt();

    return items.get(select - 1);
  }

  private static String showCategories() throws Exception {
    ResultSet rs = stmt.executeQuery("SELECT DISTINCT category FROM items");
    List<String> categoryList = new ArrayList<>();
    while (rs.next()) {
      categoryList.add(rs.getString(0));
    }
    System.out.println("Enter the Serial Number of category you want to select the items.");
    int serial = 1;
    for (String cat : categoryList) {
      System.out.println(serial + ".  " + cat);
      serial++;
    }
    int select = in.nextInt();

    return categoryList.get(select - 1);
  }

  private static void createNewUser() throws Exception {
    System.out.println("Okay, we will register you as a new user.");
    System.out.println("Type S for seller or B for buyer");
    String input = in.next();
    if (input.charAt(0) == 'S') {
      System.out.println("Enter your details....");
      System.out.println();
      System.out.println("Enter your userName");
      String username = in.next();
      System.out.println("Enter your Full Name.");
      String name = in.next();

      Seller s = new Seller(name, username);
      boolean b = s.createSeller();

      if (b) {
        System.out.println("Seller created successfully");
        System.out.println("Please create an item to sell");
        new Items().createItem(s.getUserName());
      } else {
        System.out.println("Something Went Wrong");
      }

    } else {
      System.out.println("Enter your details....");
      System.out.println();
      System.out.println("Enter your userName");
      String username = in.next();
      System.out.println("Enter your Full Name.");
      String name = in.next();

      Buyer b = new Buyer(name, username);
      boolean bool = b.createBuyer();
      if (bool) {
        System.out.println("Buyer created successfully");
        System.out.println("Please proceed");
        System.out.println("Showing you the available categories of items..");
        String category = showCategories();
        Items itemId = showAllItems(category);
        System.out.println("Enter the bid you want to make");
        int bid = in.nextInt();
        if (bid > itemId.getMax_bid_so_far()) {
          itemId.setMax_bid_so_far(bid);
          itemId.setBuyerName(b.userName);
          System.out.println("Your bid has been marked up. Thanks");
        }
      } else {
        System.out.println("Something went wrong.");
      }
    }
  }
}
