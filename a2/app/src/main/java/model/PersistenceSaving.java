package model;

import java.io.IOException;
import java.util.List;

/**
 * Simple blueprint showing how data could be saved/loaded to/from text files.
 */
public class PersistenceSaving {
  @SuppressWarnings("unused")
  private static final String MEMBERS_FILE = "data/members.txt";
  @SuppressWarnings("unused")
  private static final String ITEMS_FILE = "data/items.txt";
  @SuppressWarnings("unused")
  private static final String CONTRACTS_FILE = "data/contracts.txt";

  /**
   * Example save method - shows basic pattern for saving data.
   */
  public void saveMembers(List<Member> members) throws IOException {
    // Open file for writing
    // For each member, write this to file:
    // memberId, name, email, phone, date, credits
  }

  public void loadMembers() throws IOException {
    // Open file for reading
    // Create new Member object with data
    // Add to list
  }

  /**
  * Main saving system.
  */
  public void saveSystem(LendingSystemEntry system) throws IOException {
    saveMembers(system.getMemberCopy());
    // Similar for items and contracts
  }

  public void loadSystem() throws IOException {
    loadMembers();
    // Similar for items and contracts
  }
}