package model;

import java.util.ArrayList;
import java.util.UUID;

/**
* Represents a lending system that manages members and items.
* It facilitates the addition of members and items, contract creation, and date management.
*/
public class LendingSystemEntry {
  private ArrayList<Member> members;
  private ArrayList<Item> items;
  private Time currentDate;
  
  /**
  * Constructs a new LendingSystemEntry with an empty list of members and items
  * and initializes the current date to today.
  */
  public LendingSystemEntry() {
    this.members = new ArrayList<>();
    this.items = new ArrayList<>();
    this.currentDate = new Time(0, 0, 0);
    
    // Hardcoded data
    Member m = new Member("123456", "Bob", "bob@example.com", "1234567890", currentDate);
    members.add(m);
    
    Item screwdriver = new Item("Screwdriver", "Very good useful epic!", m, Item.Type.TOOL, 5, currentDate);
    Item book = new Item("Book", "Very good book!", m, Item.Type.BOOK, 2, currentDate);
    
    // Add to both lists
    items.add(screwdriver);
    items.add(book);
    m.addOwnedItem(screwdriver);
    m.addOwnedItem(book);
  }
  
  
  /**
  * Copy constructor for deep copying an existing LendingSystemEntry instance.
  *
  * @param other the LendingSystemEntry to copy
  */
  public LendingSystemEntry(LendingSystemEntry other) {
    this.members = new ArrayList<>();
    for (Member member : other.members) {
      this.members.add(new Member(member)); 
    }
    
    this.items = new ArrayList<>();
    for (Item item : other.items) {
      this.items.add(new Item(item)); 
    }
    
    this.currentDate = other.currentDate;
  }
  
  /**
  * Returns a copy of the list of members.
  *
  * @return a new ArrayList containing the members
  */
  public ArrayList<Member> getMemberCopy() {
    ArrayList<Member> memberCopies = new ArrayList<>();
    for (Member member : this.members) {
      memberCopies.add(new Member(member)); // Return copies of members
    }
    return memberCopies;
  }
  
  /**
  * Returns a copy of the list of items.
  *
  * @return a new ArrayList containing the items
  */
  public ArrayList<Item> getItemCopy() {
    ArrayList<Item> itemCopies = new ArrayList<>();
    for (Item item : this.items) {
      itemCopies.add(new Item(item)); // Return copies of items
    }
    return itemCopies;
  }
  
  /**
  * Adds a new member with the specified details to the system.
  * The method checks for unique email and phone number.
  *
  * @param name   the name of the member
  * @param email  the email of the member
  * @param mobile the mobile number of the member
  * @return the ID of the newly added member
  */
  public String addMember(String name, String email, String mobile) {
    for (Member member : members) {
      if (member.getEmail().equals(email)) {
        throw new IllegalArgumentException("Email is already used");
      }
      if (member.getMobile().equals(mobile)) {
        throw new IllegalArgumentException("Phone number is already used");
      }
    }
    String memberId = UUID.randomUUID().toString();
    memberId = memberId.substring(0, 6);
    Member newMember = new Member(memberId, name, email, mobile, currentDate); // Start with 100 credits
    members.add(newMember);
    return newMember.getMemberId();
  }
  
  /**
  * Adds an existing member to the system.
  *
  * @param m the Member object to add
  * @return the ID of the added member
  */
  public String addMember(Member m) {
    members.add(new Member(m));
    return m.getMemberId();
  }
  
  /**
  * Adds a new item to the system, associated with a specified owner.
  *
  * @param ownerId     the ID of the owner of the item
  * @param name        the name of the item
  * @param description a description of the item
  * @param typeString  the type of the item as a string
  * @param cost        the cost of the item
  * @throws IllegalArgumentException if the owner is not found or the item type is invalid
  */
  public void addItem(String ownerId, String name, String description, String typeString, int cost) {
    Member owner = findMemberById(ownerId);
    if (owner == null) {
      throw new IllegalArgumentException("Owner not found");
    }
    
    Item.Type itemType;
    try {
      
      itemType = Item.Type.valueOf(typeString.toUpperCase());
      
    } catch (IllegalArgumentException e) {
      
      throw new IllegalArgumentException("Invalid item type" + typeString);
      
    }
    
    Item newItem = new Item(name, description, owner, itemType, cost, currentDate);
    items.add(newItem);
    owner.addOwnedItem(newItem);
  }
  
  /**
  * Adds an existing item to the system.
  *
  * @param i the Item object to add
  */
  public void addItem(Item i) {
    items.add(i);
    i.getOwner().addOwnedItem(i);
  }
  
  /**
  * Creates a contract for borrowing an item, involving a borrower and a duration.
  *
  * @param i             the item being borrowed
  * @param borrowerId    the ID of the borrower
  * @param durationDays  the number of days for the contract duration
  * @throws IllegalArgumentException if the borrower or item is not found
  * @throws IllegalStateException if the item is not available or borrower does not have enough credits
  */
  public void createContract(Item i, String borrowerId, int durationDays) {
    Member borrower = findMemberById(borrowerId);
    if (borrower == null || !items.contains(i)) {
      throw new IllegalArgumentException("Borrower or item not found");
    }
    if (!i.getIsAvailable()) {
      throw new IllegalStateException("Item is not available");
    }
    int totalCost = i.getCost() * durationDays;
    if (borrower.getCredits() < totalCost) {
      throw new IllegalStateException("Borrower does not have enough credits");
    }
    Time endDate = new Time(currentDate);
    endDate.addDays(durationDays);
    Contract newContract = new Contract(borrower, i, currentDate, endDate, totalCost);
    newContract.setStatus(Contract.Status.ACTIVE);
    i.addContract(newContract);
    i.setIsAvailable(false);
    Member lender = i.getOwner();
    borrower.addBorrowedContract(newContract);
    lender.addLentContract(newContract);
    borrower.deductCredits(totalCost);
    lender.addCredits(totalCost);
  }
  
  /**
  * Increments the current date by one day and checks for expired contracts,
  * updating their status if necessary.
  */
  public void incrementDay() {
    currentDate.increment();
    // Check for expired contracts
    for (Member member : members) {
      for (Contract contract : member.getBorrowedContracts()) {
        if (currentDate.isAfter(contract.getEndDate())) {
          contract.getItem().setIsAvailable(true);
          contract.setStatus(Contract.Status.COMPLETED);
        }
      }
    }
  }
  
  /**
  * Retrieves a member by their ID.
  *
  * @param memberId the ID of the member to retrieve
  * @return the Member object associated with the ID
  * @throws IllegalArgumentException if the member is not found
  */
  public Member getMember(String memberId) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found");
    }
    return new Member(member);
  }
  
  /**
  * Deletes member.
  */
  public void deleteMember(String memberId) {
    try {
      Member member = findMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found");
      }
      members.remove(member);
    } catch (Exception e) {
      throw new IllegalArgumentException("An error occurred. Please try again.");
    }
  }
  
  /**
  * Deletes Item.
  */
  public void deleteItem(String memberId, String itemName) {
    try {
      Member member = findMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found");
      }
      
      // Find and remove the item from both lists
      Item itemToRemove = null;
      for (Item item : items) {
        if (item.getName().equals(itemName) && item.getOwner().getMemberId().equals(memberId)) {
          itemToRemove = item;
          break;
        }
      }
      
      if (itemToRemove != null) {
        items.remove(itemToRemove);
        member.removeOwnedItem(itemName);
      } else {
        throw new IllegalArgumentException("Item not found or not owned by this member");
      }
      
    } catch (Exception e) {
      throw new IllegalArgumentException("An error occurred: " + e.getMessage());
    }
  }
  
  /**
  * Retrieves information about a specific item.
  *
  * @param item the Item object to retrieve information about
  * @return a string representation of the item's information
  * @throws IllegalArgumentException if the item is not found
  */
  public String getItemInfo(Item item) {
    if (!items.contains(item)) {
      throw new IllegalArgumentException("Item not found");
    }
    return item.toString();
  }
  
  /**
  * Retrieves a formatted string containing information about all items in the system.
  *
  * @return a formatted string representing information about all items
  */
  public String getItemInfo() {
    StringBuilder ret = new StringBuilder();
    for (Item item : items) {
      ret.append(String.format("Name: %s%nDescription: %s%nType: %s%nCost: %s%nOwner: %s%n",
          item.getName(),
          item.getDescription(),
          item.getType(),
          item.getCost(),
          item.getOwner().getName()));
    }
    return ret.toString();
  }
  
  /**
  * Retrieves a formatted string containing information about all items in the system.
  *
  * @return a formatted string representing information about all items
  */
  public String getItemInfo(String memberId) {
    Member member = findMemberById(memberId);
    StringBuilder ret = new StringBuilder();
    for (Item item : member.getItemsCopy()) {
      ret.append(String.format("Name: %s%nDescription: %s%nType: %s%nCost: %s%nOwner: %s%n",
          item.getName(),
          item.getDescription(),
          item.getType(),
          item.getCost(),
          item.getOwner().getName()));
    }
    return ret.toString();
  }
  
  /**
  * Edits the details of a member.
  *
  * @param memberId the ID of the member to edit
  * @param name     the new name of the member
  * @param email    the new email of the member
  * @param mobile   the new mobile number of the member
  * @throws IllegalArgumentException if the member is not found
  */
  public void editMember(String memberId, String name, String email, String mobile) {
    Member member = findMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found");
    }
    member.setName(name);
    member.setEmail(email);
    member.setMobile(mobile);
  }
  
  /**
  * Edits the details of an item.
  *
  * @param item        the Item object to edit
  * @param name        the new name of the item
  * @param description the new description of the item
  * @param cost        the new cost of the item
  * @throws IllegalArgumentException if the item is not found
  */
  public void editItem(Item item, String name, String description, int cost) {
    if (!items.contains(item)) {
      throw new IllegalArgumentException("Item not found");
    }
    item.setName(name);
    item.setDescription(description);
    item.setCost(cost);
  }
  
  /**
  * Finds a member by their ID.
  *
  * @param memberId the ID of the member to find
  * @return the Member object associated with the ID, or null if not found
  */
  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }
  
  /**
  * Finds an item by its name.
  *
  * @param itemName the name of the item to find
  * @return the Item object associated with the name, or null if not found
  */
  public Item findItemByName(String itemName) {
    for (Item item : items) {
      if (item.getName().equals(itemName)) {
        return item;
      }
    }
    return null;
  }
  
  /**
  * Formats brief information about a member.
  *
  * @param member the Member object to format information for
  * @return a formatted string containing brief member information
  */
  public String formatMemberInfoBrief(Member member) {
    return String.format("Name: %s%nEmail: %s%nMobile: %s%nCredits: %s%nNumber of Items: %s%nID: %s",
    member.getName(),
    member.getEmail(),
    member.getMobile(),
    member.getCredits(),
    member.getItemsCopy().size(),
    member.getMemberId());  // Change to Number of owned Items
  }
  
  /**
  * Formats verbose information about a member.
  *
  * @param member the Member object to format information for
  * @return a formatted string containing verbose member information
  */
  public String formatMemberInfoVerbose(Member member) {
    String ret = "";
    ret = String.format("Name: %s%nEmail: %s%nMobile: %s%nCredits: %s%nNumber of Items: %s%nID: %s",
    member.getName(),
    member.getEmail(),
    member.getMobile(),
    member.getCredits(),
    member.getItemsCopy().size(),
    member.getMemberId());  // Change to Number of owned Items
    ret += "\nItem information: \n" + getItemInfo(member.getMemberId());
    return ret;
  }
  
}
