package model;

/**
 * Represents a contract for borrowing an item, including details about the borrower,
 * item, duration, status, and total cost.
 */
public class Contract {
  private Member borrower;
  private Item item;
  private Time startDate;
  private Time endDate;
  private Status status;
  private int totalCost;


  /**
   * Enum representing the status of the contract.
   */
  public enum Status {
    ACTIVE, COMPLETED
  }

  /**
   * Constructs a Contract with the specified borrower, item, start date, end date, and total cost.
   *
   * @param borrower   the member borrowing the item
   * @param item       the item being borrowed
   * @param startDate  the start date of the contract
   * @param endDate    the end date of the contract
   * @param totalCost  the total cost of borrowing the item
   */
  public Contract(Member borrower, Item item, Time startDate, Time endDate, int totalCost) {
    this.borrower = new Member(borrower);
    this.item = new Item(item);
    this.startDate = new Time(startDate);
    this.endDate = new Time(endDate);
    this.status = Status.ACTIVE;
    this.totalCost = totalCost;
  }

  /**
   * Copy constructor for creating a new Contract from an existing one.
   *
   * @param contract the Contract to copy
   */
  public Contract(Contract contract) {
    this.borrower = new Member(contract.getBorrower()); // Create a new Member object
    this.item = new Item(contract.getItem()); // Create a new Item object
    this.startDate = new Time(contract.getStartDate()); // Create a new Time object
    this.endDate = new Time(contract.getEndDate()); // Create a new Time object
    this.status = contract.getStatus(); // Copy status
    this.totalCost = contract.getTotalCost(); // Copy total cost
  }

  // Getters
  public Member getBorrower() {
    return new Member(borrower);
  }
  
  public Item getItem() {
    return new Item(item); 
  }
  
  public Time getStartDate() {
    return new Time(startDate); 
  }

  public Time getEndDate() {
    return new Time(endDate); 
  }

  public Status getStatus() {
    return status; 
  }

  public int getTotalCost() {
    return totalCost; 
  }

  // Setters
  public void setBorrower(Member borrower) {
    this.borrower = new Member(borrower);
  }

  public void setItem(Item item) {
    this.item = new Item(item);
  }

  public void setStartDate(Time startDate) {
    this.startDate = new Time(startDate);
  }

  public void setEndDate(Time endDate) {
    this.endDate = new Time(endDate);
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

  public void setStatus(Status status) {
    this.status = status; 
  }

}
