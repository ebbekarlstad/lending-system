package model;

import java.util.ArrayList;

/**
* Represents a member of the lending system.
* A member has a unique ID, personal information, credits,
* and lists of owned items and contracts for borrowed and lent items.
*/
public class Member {
  private String memberId;
  private String name;
  private String email;
  private String mobile;
  private Time creationDate;
  private int credits;
  private ArrayList<Item> ownedItems;
  private ArrayList<Contract> borrowedContracts;
  private ArrayList<Contract> lentContracts;
  
  /**
  * Constructor to create a new member.
  *
  * @param memberId      the unique ID of the member
  * @param name          the name of the member
  * @param email         the email address of the member
  * @param mobile        the mobile phone number of the member
  * @param creationDate  the date when the member was created
  */
  public Member(String memberId, String name, String email, String mobile, Time creationDate) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.mobile = mobile;
    this.creationDate = new Time(creationDate);
    this.credits = 100;
    this.ownedItems = new ArrayList<>();
    this.borrowedContracts = new ArrayList<>();
    this.lentContracts = new ArrayList<>();
  }
  
  /**
  * Copy constructor to create a new member by copying an existing member.
  *
  * @param m the member to copy
  */
  public Member(Member m) {
    this.memberId = m.getMemberId();
    this.name = m.getName();
    this.email = m.getEmail();
    this.mobile = m.getMobile();
    this.creationDate = new Time(m.getCreationDate());
    this.credits = m.getCredits(); // Also fix this to copy the actual credits
    
    // Create new ArrayLists and copy items
    this.ownedItems = m.ownedItems;
    this.borrowedContracts = m.borrowedContracts;
    this.lentContracts = m.lentContracts;

  }
  
  // Getters and setters
  public String getMemberId() {
    return memberId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getMobile() {
    return mobile;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  
  public Time getCreationDate() {
    return new Time(creationDate);
  }
  
  public int getCredits() {
    return credits;
  }
  
  public void addCredits(int amount) {
    this.credits += amount;
  }
  
  /**
  * Deducts the specified amount of credits from the member's balance.
  *
  * @param amount the amount of credits to deduct
  * @throws IllegalStateException if the member does not have enough credits
  */
  public void deductCredits(int amount) {
    if (this.credits < amount) {
      throw new IllegalStateException("Insufficient credits");
    }
    this.credits -= amount;
  }
  
  public void addOwnedItem(Item item) {
    ownedItems.add(item);
  }
  
  public void removeOwnedItem(String itemName) {
    ownedItems.removeIf(item -> item.getName().equals(itemName));
  }
  
  public void addBorrowedContract(Contract contract) {
    borrowedContracts.add(contract);
  }
  
  public void addLentContract(Contract contract) {
    lentContracts.add(contract);
  }
  
  public ArrayList<Contract> getBorrowedContracts() {
    return new ArrayList<>(borrowedContracts);
  }
  
  public ArrayList<Contract> getLentContracts() {
    return new ArrayList<>(lentContracts);
  }
  
  public void removeBorrowedContract(Contract contract) {
    borrowedContracts.remove(contract);
  }
  
  public void removeLentContract(Contract contract) {
    lentContracts.remove(contract);
  }
  
  public ArrayList<Item> getItemsCopy() {
    return new ArrayList<Item>(ownedItems);
  }
}