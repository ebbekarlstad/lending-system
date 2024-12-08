package controller;

import model.LendingSystemEntry;
import view.ConsoleView;

/**
 * Controller class to manage actions between the model (LendingSystemEntry) and
 * UI (ConsoleView).
 */
public class Controller {
  private LendingSystemEntry lse;
  private ConsoleView ui;

  /**
   * Constructor for the Controller.
   *
   * @param lse the LendingSystemEntry instance to manage data
   * @param ui the ConsoleView instance for user interaction
   */
  public Controller(LendingSystemEntry lse, ConsoleView ui) {
    this.lse = new LendingSystemEntry(lse);
    this.ui = ui;
  }

  /**
   * Starts the main menu loop and handles user choices.
   *
   * @return true if the application should continue running, false to exit.
   */
  public boolean start() {
    String memberId;
    ConsoleView.MainMenuOption choice = ui.displayMainMenu();
    switch (choice) {
      case EDIT_ADD:
        handleEditAdd();
        break;
      case NEW_CONTRACT:
        handleNewContract();
        break;
      case VIEW_MEMBER_BRIEF:
        memberId = ui.getMemberId();
        handleViewMemberBrief(memberId);
        break;
      case VIEW_MEMBER_VERBOSE:
        memberId = ui.getMemberId();
        handleViewMemberVerbose(memberId);
        break;
      case VIEW_CATALOG:
        handleViewCatalog();
        break;
      case INCREMENT_DAY:
        handleIncrementDay();
        break;
      case QUIT:
        return false; // Exit the application
      default:
        ui.displayInvalidInputMessage();
    }
    return true;
  }

  /**
   * Handles the edit and add menu options.
   */
  private void handleEditAdd() {
    ConsoleView.EditAddMenuOption choice = ui.displayEditAddMenu();
    switch (choice) {
      case ADD:
        handleAdd();
        break;
      case EDIT:
        handleEdit();
        break;
      default:
        ui.displayInvalidInputMessage();
    }
  }

  private void handleDeleteMember(String memberId) {
    lse.deleteMember(memberId);
    ui.displaySuccessMessage();
  }

  private void handleDeleteItem(String memberId, String itemName) {
    lse.deleteItem(memberId, itemName);
    ui.displaySuccessMessage();
  }

  /**
   * Handles adding a member or an item based on user input.
   */
  private void handleAdd() {
    ConsoleView.AddMenuOption choice = ui.displayAddMenu();
    switch (choice) {
      case MEMBER:
        addMember();
        break;
      case ITEM:
        addItem();
        break;
      default:
        ui.displayInvalidInputMessage();
    }
  }

  /**
   * Adds new member to the lending system.
   */
  private void addMember() {
    String name = ui.getMemberName();
    String email = ui.getMemberEmail();
    String mobile = ui.getMemberMobile();
    try {
      String memberId = lse.addMember(name, email, mobile);
      handleViewMemberBrief(memberId);
      ui.displaySuccessMessage();
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Adds a new item to the lending system.
   */
  private void addItem() {
    String ownerId = ui.getOwnerId();
    String name = ui.getItemName();
    String description = ui.getItemDescription();
    String typeString = ui.getItemType();
    int cost = ui.getItemCost();
    try {
      lse.addItem(ownerId, name, description, typeString, cost);
      ui.displaySuccessMessage();
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Handles the editing of member or item details based on user input.
   */
  private void handleEdit() {
    ConsoleView.EditMenuOption choice = ui.displayEditMenu();
    switch (choice) {
      case MEMBER:
        editMember();
        break;
      case ITEM:
        editItem();
        break;
      default:
        ui.displayInvalidInputMessage();
    }
  }

  /**
   * Edits the details of a member.
   */
  private void editMember() {
    ConsoleView.EditMemberOption choice = ui.displayEditMember();
    String memberId = ui.getMemberId();
    switch (choice) {
      case NAME:
        String name = ui.getMemberName();
        lse.findMemberById(memberId).setName(name);
        break;
      case EMAIL:
        String email = ui.getMemberEmail();
        lse.findMemberById(memberId).setEmail(email);
        break;
      case MOBILE:
        String mobile = ui.getMemberMobile();
        lse.findMemberById(memberId).setMobile(mobile);
        break;
      case DELETE:
        handleDeleteMember(memberId);
        break;
      default:
        ui.displayInvalidInputMessage();
        break;
    }
  }

  /**
   * Edits the details of an item.
   */
  private void editItem() {
    ConsoleView.EditItemOption choice = ui.displayEditItem();
    String itemName = ui.getItemName();
    switch (choice) {
      case NAME:
        String name = ui.getItemName();
        lse.findItemByName(itemName).setName(name);
        break;
      case DESCRIPTION:
        String description = ui.getItemDescription();
        lse.findItemByName(itemName).setDescription(description);
        break;
      case COST:
        int cost = ui.getItemCost();
        lse.findItemByName(itemName).setCost(cost);
        break;
      case DELETE:
        String memberId = ui.getMemberId();
        handleDeleteItem(memberId, itemName);
        break;
      default:
        ui.displayInvalidInputMessage();
        break;
    }
  }

  /**
   * Handles the creation of a new contract for borrowing an item.
   */
  private void handleNewContract() {
    String borrowerId = ui.getMemberId();
    String itemName = ui.getItemName();
    int duration = ui.getDuration();

    try {
      lse.createContract(lse.findItemByName(itemName), borrowerId, duration);
      ui.displaySuccessMessage();
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Handles viewing brief information about a member.
   *
   * @param memberId the ID of the member to view
   */
  private void handleViewMemberBrief(String memberId) {
    try {
      String memberInfo = lse.formatMemberInfoBrief(lse.getMember(memberId));
      ui.displayMemberInfo(memberInfo);
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Handles viewing detailed information about a member.
   *
   * @param memberId the ID of the member to view
   */
  private void handleViewMemberVerbose(String memberId) {
    try {
      String memberInfo = lse.formatMemberInfoVerbose(lse.getMember(memberId));
      ui.displayMemberInfo(memberInfo);
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Handles viewing the catalog of items.
   */
  private void handleViewCatalog() {
    try {
      String itemInfo = lse.getItemInfo();
      ui.displayItemInfo(itemInfo);
    } catch (Exception e) {
      ui.displayErrorMessage(e.getMessage());
    }
  }

  /**
   * Increments the day in the lending system and displays a success message.
   */
  private void handleIncrementDay() {
    lse.incrementDay();
    ui.displaySuccessMessage();
  }

}