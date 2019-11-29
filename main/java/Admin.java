public class Admin {
  public Seller getSellerDetails(String userName) {

    Seller s = new Seller();

    return s.getDetails(userName);
  }

  public Buyer getBuyerDetails(String userName) {

    Buyer s = new Buyer();

    return s.getDetails(userName);
  }

  public boolean deleteSeller(String userName) {
    return true;
  }

  public boolean deleteBuyer(String userName) {
    return true;
  }
}
