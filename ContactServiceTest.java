import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
        contact = new Contact("001", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
    }

    @Test
    public void testAddContact() {
        Contact newContact = new Contact("002", "Jane", "Smith", "0987654321", "456 Oak Ave");
        service.addContact(newContact);
        assertEquals("Jane", service.getContact("002").getFirstName());
    }

    @Test
    public void testDeleteContact() {
        service.deleteContact("001");
        assertNull(service.getContact("001"));
    }

    @Test
    public void testUpdateFirstName() {
        service.updateFirstName("001", "Mike");
        assertEquals("Mike", service.getContact("001").getFirstName());
    }

    @Test
    public void testDuplicateIdThrowsException() {
        Contact duplicate = new Contact("001", "Someone", "Else", "1112223333", "789 Elm St");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicate);
        });
    }
}
