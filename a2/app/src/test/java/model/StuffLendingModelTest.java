package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StuffLendingModelTest {

    private LendingSystemEntry sut;
    private Member borrower;
    private Member lender;
    private Item item;
    private Time currentTime;

    @BeforeEach
    public void setUp() {
        sut = new LendingSystemEntry();
        
        // Initialize the current time
        currentTime = new Time(2024, 10, 21); // Assuming Time has a default constructor for current date

        // Set up members
        borrower = new Member("borrowerId", "John Doe", "john.doe@example.com", "1234567890", currentTime);
        lender = new Member("lenderId", "Jane Smith", "jane.smith@example.com", "0987654321", currentTime);
        
        // Set up item
        item = new Item("itemId", "A nice item", lender, Item.Type.OTHER, 10, currentTime);
        
        // Add members and item to the system
        sut.addMember(borrower);
        sut.addMember(lender);
        sut.addItem(item);
    }

    @Test
    public void testModelInitialization() {
        // Create an instance of the StuffLendingModel
        LendingSystemEntry sut = new LendingSystemEntry();

        // Simple assertion to check if the model object is not null
        assertTrue(sut != null, "StuffLendingModel should be initialized");
    }

    @Test
    public void testCreateContract_BorrowerNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createContract(item, "nonExistentId", 5);
        });
    }

    @Test
    public void testCreateContract_ItemNotFound() {
        Item nonExistentItem = new Item("nonExistentItem", "Not in the system", lender, Item.Type.OTHER, 10, currentTime);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createContract(nonExistentItem, borrower.getMemberId(), 5);
        });
    }

    @Test
    public void testCreateContract_ItemNotAvailable() {
        item.setIsAvailable(false);
        assertThrows(IllegalStateException.class, () -> {
            sut.createContract(item, borrower.getMemberId(), 5);
        });
    }

    @Test
    public void testIncrementDay_ContractExpires() {
        sut.createContract(item, borrower.getMemberId(), 1);
        sut.incrementDay(); // Should update contract status and make item available
        assertEquals(Contract.Status.COMPLETED, borrower.getBorrowedContracts().get(0).getStatus());
    }

    @Test
    public void testIncrementDay_NoExpiredContracts() {
        sut.createContract(item, borrower.getMemberId(), 5);
        sut.incrementDay(); // Should not change anything, as contract isn't expired
        assertEquals(Contract.Status.ACTIVE, borrower.getBorrowedContracts().get(0).getStatus());
        assertFalse(item.getIsAvailable());
    }
}
