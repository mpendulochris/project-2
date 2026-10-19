package com.mycompany.poeparttwo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit tests for Message class
 * Tests logic-based methods only
 */
public class MessageTest {

    Message message = new Message();

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    // ===============================
    // MESSAGE LENGTH TESTS (250 chars)
    // ===============================
    @Test
    void testMessageLengthValid() {
        String msg = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(message.checkMessageLength(msg));
    }

    @Test
    void testMessageLengthInvalid() {
        String msg = "A".repeat(300); // longer than 250 chars
        assertFalse(message.checkMessageLength(msg));
    }

    // ===============================
    // CELL NUMBER VALIDATION TESTS
    // ===============================
    @Test
    void testValidCellNumber() {
        String result = message.checkCellNumber("+27718693002");
        assertEquals("Valid", result);
    }

    @Test
    void testInvalidCellNumberMissingCode() {
        String result = message.checkCellNumber("08575975889");
        assertEquals("Invalid", result);
    }

    @Test
    void testInvalidCellNumberWithLetters() {
        String result = message.checkCellNumber("+27ABC12345");
        assertEquals("Invalid", result);
    }

    // ===============================
    // MESSAGE HASH TESTS
    // ===============================
    @Test
    void testMessageHashGeneration() {
        String messageId = "0012345678";
        String messageText = "Hi Mike tonight";

        String hash = message.generateMessageHash(messageId, 0, messageText);

        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    void testMultipleMessageHashesNotNull() {
        String messageId = "0012345678";

        String[] messages = {
                "Hi Mike tonight",
                "Hi Keegan payment received"
        };

        for (int i = 0; i < messages.length; i++) {
            String hash = message.generateMessageHash(messageId, i, messages[i]);
            assertNotNull(hash);
            assertTrue(hash.contains(":"));
        }
    }

    // ===============================
    // MESSAGE ID TEST
    // ===============================
    @Test
    void testGenerateMessageIdLength() {
        String id = message.generateMessageId();
        assertEquals(10, id.length());
    }

    @Test
    void testGenerateMessageIdNumeric() {
        String id = message.generateMessageId();
        assertTrue(id.matches("\\d+"));
    }

    // ===============================
    // STORE MESSAGE (NO EXCEPTION)
    // ===============================
    @Test
    void testStoreMessageDoesNotThrow() {
        assertDoesNotThrow(() -> {
            message.saveMessage(
                    "0012345678",
                    "Hi Mike",
                    "00:0:HIMIKE",
                    "+27718693002"
            );
        });
    }

    // ===============================
    // TOTAL MESSAGE COUNT
    // ===============================
    @Test
    void testCalculateTotalMessages() {
        int total = message.calculateTotalMessages(2);
        assertEquals(2, total);
    }

    /**
     * Test of selectMessageAction method, of class Message.
     */
    @Test
    public void testSelectMessageAction() {
        System.out.println("selectMessageAction");
        String msgId = "";
        String msgContent = "";
        String msgHash = "";
        String receiver = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.selectMessageAction(msgId, msgContent, msgHash, receiver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMessageId method, of class Message.
     */
    @Test
    public void testGenerateMessageId() {
        System.out.println("generateMessageId");
        Message instance = new Message();
        String expResult = "";
        String result = instance.generateMessageId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMessageHash method, of class Message.
     */
    @Test
    public void testGenerateMessageHash() {
        System.out.println("generateMessageHash");
        String msgId = "";
        int index = 0;
        String text = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.generateMessageHash(msgId, index, text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageLength method, of class Message.
     */
    @Test
    public void testCheckMessageLength() {
        System.out.println("checkMessageLength");
        String text = "";
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageLength(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayOnlySentMessages method, of class Message.
     */
    @Test
    public void testDisplayOnlySentMessages() {
        System.out.println("displayOnlySentMessages");
        String id = "";
        String text = "";
        String hash = "";
        String recipient = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.displayOnlySentMessages(id, text, hash, recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellNumber method, of class Message.
     */
    @Test
    public void testCheckCellNumber() {
        System.out.println("checkCellNumber");
        String number = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.checkCellNumber(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMessage method, of class Message.
     */
    @Test
    public void testSaveMessage() {
        System.out.println("saveMessage");
        String id = "";
        String text = "";
        String hash = "";
        String recipient = "";
        Message instance = new Message();
        instance.saveMessage(id, text, hash, recipient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}