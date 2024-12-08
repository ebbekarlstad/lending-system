package model;

import java.util.ArrayList;

/**
 * Represents an item that can be borrowed or rented in the lending system.
 * An item has attributes such as name, description, owner, availability,
 * cost, contracts, creation date, and type.
 */
public class Item {
  private String name;
  private String description;
  private Member owner;
  private boolean isAvailable;
  private int cost;
  private ArrayList<Contract> contracts;
  private Time createDate;
  private Type type;
  
  /**
   * Enum representing the various types of items that can be lent or rented 
   * in the lending system. Each type categorizes the item to facilitate 
   * organization and retrieval of items based on their categories.
   */
  public enum Type {
    BOOK,
    TOOL,
    ELCTRONICS,
    OTHER
  }
  
  /**
   * Constructs an Item with the specified attributes.
   *
   * @param name        the name of the item
   * @param description a brief description of the item
   * @param owner       the owner of the item
   * @param type        the type of the item
   * @param cost        the cost of renting the item
   */
  public Item(String name, String description, Member owner, Type type, int cost, Time createDate) {
    this.name = name;
    this.description = description;
    this.owner = new Member(owner);
    this.isAvailable = true;
    this.cost = cost;
    this.contracts = new ArrayList<Contract>();
    this.createDate = new Time(createDate);
    this.type = type;
  }

  /**
   * Copy constructor for creating a new Item from an existing one.
   *
   * @param i the Item to copy
   */
  public Item(Item i) {
    this.name = i.getName();
    this.description = i.getDescription();
    this.owner = new Member(i.getOwner());
    this.isAvailable = i.isAvailable;
    this.cost = i.getCost();
    this.contracts = new ArrayList<>();
    this.createDate = new Time(i.getStartDate());
    this.type = i.getType(); 
  }
  
  // Getters & Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Member getOwner() {
    return new Member(owner);
  }

  public void setOwner(Member owner) {
    this.owner = new Member(owner);
  }

  public boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public Time getStartDate() {
    return new Time(createDate);
  }

  /**
   * Getting the contracts.
   *
   */
  public ArrayList<Contract> getContracts() {
    ArrayList<Contract> contractCopies = new ArrayList<>();
    for (Contract contract : contracts) {
      contractCopies.add(new Contract(contract)); // Return copies of contracts
    }
    return contractCopies; // Return the copy of the contracts
  }

  /**
   * Setting the contracts.
   *
   */
  public void setContracts(ArrayList<Contract> contracts) {
    this.contracts = new ArrayList<>(); // Create a new list to avoid reference issues
    for (Contract contract : contracts) {
      this.contracts.add(new Contract(contract)); // Store copies of contracts
    }
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public void addContract(Contract contract) {
    this.contracts.add(contract);
  }
}
