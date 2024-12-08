package controller;

import model.LendingSystemEntry;
import view.ConsoleView;

/**
 * Entry point for the Stuff Lending System.
 */
public class App {
  /**
   * Main method.
   */
  public static void main(String[] args) {
    LendingSystemEntry lendingSystemEntry = new LendingSystemEntry();
    ConsoleView view = new ConsoleView();
    Controller controller = new Controller(lendingSystemEntry, view);
    // Start the application
    while (controller.start()) {
    }
  }
}