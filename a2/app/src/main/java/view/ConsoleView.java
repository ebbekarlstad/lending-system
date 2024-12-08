package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * The ConsoleView class provides a user interface for interacting with the application
 * through the console. It includes methods for displaying menus, getting user inputs,
 * and showing information related to members and items. This class uses a Scanner
 * to capture user inputs.
 */
public class ConsoleView {
  private Scanner scanner;

  /**
   * Constructs a ConsoleView object, initializing the scanner for user input.
   * The scanner uses UTF-8 encoding.
   */
  public ConsoleView() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * Enum for main menu options available to the user.
   */
  public enum MainMenuOption {
    EDIT_ADD, NEW_CONTRACT, VIEW_MEMBER_BRIEF, VIEW_MEMBER_VERBOSE, VIEW_CATALOG, INCREMENT_DAY, QUIT
  }

  /**
   * Enum for edit/add menu options.
   */
  public enum EditAddMenuOption {
    ADD, EDIT
  }

  /**
   * Enum for add menu options.
   */
  public enum AddMenuOption {
    MEMBER, ITEM
  }

  /**
   * Enum for edit menu options.
   */
  public enum EditMenuOption {
    MEMBER, ITEM
  }

  /**
   * Enum for editing member details options.
   */
  public enum EditMemberOption {
    NAME, EMAIL, MOBILE, DELETE
  }

  /**
   * Enum for editing item details options.
   */
  public enum EditItemOption {
    NAME, DESCRIPTION, COST, DELETE
  }

  /**
   * Displays the main menu to the user and returns their choice as a MainMenuOption.
   *
   * @return the selected MainMenuOption
   */
  public MainMenuOption displayMainMenu() {
    System.out.println("Main Menu:");
    System.out.println("1) Edit/add");
    System.out.println("2) New contract");
    System.out.println("3) View member brief");
    System.out.println("4) View member verbose");
    System.out.println("5) View catalog");
    System.out.println("6) Increment day");
    System.out.println("7) Quit");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return MainMenuOption.values()[choice - 1];
  }

  /**
   * Displays the Edit/Add menu and returns the selected option.
   *
   * @return the selected EditAddMenuOption
   */
  public EditAddMenuOption displayEditAddMenu() {
    System.out.println("Edit/Add Menu:");
    System.out.println("1) Add");
    System.out.println("2) Edit");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return EditAddMenuOption.values()[choice - 1];
  }

  /**
   * Displays the Add menu for adding a member or an item.
   *
   * @return the selected AddMenuOption
   */
  public AddMenuOption displayAddMenu() {
    System.out.println("Add Member/Item:");
    System.out.println("1) Member");
    System.out.println("2) Item");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return AddMenuOption.values()[choice - 1];
  }

  /**
   * Displays the Edit menu for editing a member or an item.
   *
   * @return the selected EditMenuOption
   */
  public EditMenuOption displayEditMenu() {
    System.out.println("Edit Member/Item:");
    System.out.println("1) Member");
    System.out.println("2) Item");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return EditMenuOption.values()[choice - 1];
  }

  /**
   * Displays the Edit Member menu and returns the selected option.
   *
   * @return the selected EditMemberOption
   */
  public EditMemberOption displayEditMember() {
    System.out.println("Edit Member:");
    System.out.println("1) Name");
    System.out.println("2) Email");
    System.out.println("3) Phone");
    System.out.println("4) Delete Member");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return EditMemberOption.values()[choice - 1];
  }

  /**
   * Displays the Edit Item menu and returns the selected option.
   *
   * @return the selected EditItemOption
   */
  public EditItemOption displayEditItem() {
    System.out.println("Edit Item:");
    System.out.println("1) Name");
    System.out.println("2) Description");
    System.out.println("3) Cost");
    System.out.println("4) Delete Item");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return EditItemOption.values()[choice - 1];
  }

  /**
   * Displays the Delete Member menu and returns the selected option.
   *
   */
  public void displayDeleteMember() {
    System.out.println("Delete Member:");
  }

  /**
   * Displays the Delete Item menu and returns the selected option.
   *
   */
  public void displayDeleteItem() {
    System.out.println("Delete Item:");
  }

  public void displayErrorMessage(String errorMessage) {
    System.out.println("An error occurred: " + errorMessage);
  }

  public void displayInvalidInputMessage() {
    System.out.println("Invalid input. Please try again.");
  }

  public void displaySuccessMessage() {
    System.out.println("Operation completed successfully.");
  }

  /**
   * Prompts the user to enter a member's name and returns the input.
   *
   * @return the entered member name
   */
  public String getMemberName() {
    System.out.println("Enter member name:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter a member's email and returns the input.
   *
   * @return the entered member email
   */
  public String getMemberEmail() {
    System.out.println("Enter member email:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter a member's mobile number and returns the input.
   *
   * @return the entered member mobile number
   */
  public String getMemberMobile() {
    System.out.println("Enter member mobile:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter an item's name and returns the input.
   *
   * @return the entered item name
   */
  public String getItemName() {
    System.out.println("Enter item name:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter an item's description and returns the input.
   *
   * @return the entered item description
   */
  public String getItemDescription() {
    System.out.println("Enter item description:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the type of the item (e.g., BOOK, TOOL) and returns the input.
   *
   * @return the entered item type
   */
  public String getItemType() {
    System.out.println("Enter item type (BOOK, TOOL, ELECTRONICS, OTHER):");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the cost of the item per day and returns the input.
   *
   * @return the entered item cost per day
   */
  public int getItemCost() {
    System.out.println("Enter item cost per day:");
    int cost = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return cost;
  }

  /**
   * Prompts the user to enter a member's ID and returns the input.
   *
   * @return the entered member ID
   */
  public String getMemberId() {
    System.out.println("Enter member ID:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the owner's ID and returns the input.
   *
   * @return the entered owner ID
   */
  public String getOwnerId() {
    System.out.println("Enter owner ID:");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the duration in days for a contract and returns the input.
   *
   * @return the entered duration in days
   */
  public int getDuration() {
    System.out.println("Enter duration in days:");
    int duration = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    return duration;
  }

  /**
   * Displays the member information provided.
   *
   * @param memberInfo the member information to display
   */
  public void displayMemberInfo(String memberInfo) {
    System.out.println("Member Info:");
    System.out.println(memberInfo);
  }

  /**
   * Displays a list of contracts associated with a member.
   *
   * @param contracts the list of contracts to display
   */
  public void displayMemberContracts(List<String> contracts) {
    System.out.println("Member Contracts:");
    for (String contract : contracts) {
      System.out.println(contract);
    }
  }

  /**
   * Displays the item catalog information.
   *
   * @param catalog the catalog information to display
   */
  public void displayItemInfo(String catalog) {
    System.out.println("Catalog:");
    System.out.println(catalog);
  }
}