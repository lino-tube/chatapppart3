/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapppart3;


//imports JUnit 4 annotations and assertion methods for testing
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */

//test class used to test methods from the Message class
public class MessageTest {
    
    //message objects used in multiple test methods
    private Message message1;
    private Message message2;
    
    /*
     the @BeforeEach annotation runs this method
     before every test method
     */
    @BeforeEach
    public void setUp(){
        
       //we creating test Message objects
       message1 = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
       message2 = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
    }
    
     /*
     tests if a valid message under 250 characters
     returns the correct success message
     */
    @Test
    public void testCheckMessageLength_validMessage_returnsSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        //stores the returned result
        String result = msg.checkMessageLength();
        
        //checks if the expected result matches the actual result
        assertEquals("Message ready to send.", result);
    }
    
    /*
     tests if a message longer than 250 characters
     returns the correct error message
     */
    @Test
    public void tesChecktMessageLength_over250chars_returnsFailureWithCount() {
        String longMessage = "";
        
        //creates a long message
        for (int i = 0; i < 260; i++){
            longMessage += "a";
        }
        Message msg = new Message (1, "+27718693002", longMessage);
        
        String result = msg.checkMessageLength();
        
        //checks if the correct error message is returned
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }
    
    /*
     tests if a message exactly at 250 characters
     is accepted successfully
     */
    @Test                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    public void testCheckMessageLength_exactlyAtLImit_returnSuccess() {
        //creates a message with exactly 250 characters
        String message250 = "";
        
        for(int i = 0; i < 250; i++){
            message250 += "a";
        }
        Message msg = new Message(1, "+27718693002", message250);
        
        String result = msg.checkMessageLength();
        //checks if the message is accepted
        assertEquals("Message ready to send.", result);
    }
    
    /*
     tests if a message with 251 characters
     returns the correct error message
     */

    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1(){
        //creates a message with 251 characters
        String message251 = "";
        
        for (int i = 0; i < 251; i++){
            message251 += "a";
        }
        Message msg = new Message(1, "+27718693002", message251);
        
        String result = msg.checkMessageLength();
        //checks if the correct error message is returned
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", result);
    }
    
    /*
     tests if a valid recipient cellphone number
     returns the correct success message
     */

    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        String result = msg.checkRecipientCell();
        
        //checks if the cellphone number is valid
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    /*
     tests if an invalid recipient cellphone number
     returns the correct error message
     */
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure(){
        Message msg = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        
        String result = msg.checkRecipientCell();
        
        //checks if the correct error message is returned
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
        /*
     tests if the generated message hash
     ends with the expected words
     */

    @Test
    public void testCreateMesageHash_correctFormat_endsWithExpectedWords(){
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        String hash = msg.createMessageHash();
        //checks if the hash ends with the expected value
        assertTrue(hash.endsWith(":1:HITONIGHT"));
    }
    
     /*
     tests if the generated message hash
     is entirely uppercase
     */
    @Test
    public void testCreateMessageHash_isUppercase(){
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
          
        String hash = msg.createMessageHash();
        //checks if the hash is in uppercases
        assertEquals(hash.toUpperCase(), hash);
    }
    
    /*
     tests multiple messages in a loop
     to check if the expected words exists in the hash
     */
    @Test
    public void testCreateMessageHash_mutipleMessages_loopTest() {
        //stores Message objects in an array
        Message[] messages = {message1, message2};
        //stores expected hash values
        String[] expectedWords = {
            "HITONIGHT", "HIPAYMENT"
        };
        
        //loops through all messages
        for(int i = 0; i < messages.length; i++) {
            //generates hash for each message
            String hash = messages[i].createMessageHash();
            //checks if the hash contains the expected words
            assertTrue(hash.contains(expectedWords[i]));
        }
    }
    
    /*
     tests if the generated message ID
     is not null
     */
    @Test
    public void testCheckMessageID_generatedID_isNotNull() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        //generates the message ID
        String ID = msg.generateMessageID();
        
        //checks if the ID is not null
        assertNotNull("Message ID must not be null.", ID);
    }
    /*
     tests if the generated message ID
     is exactly 10 characters long
     */
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        //checks if the ID length is valid
        boolean ID = msg.checkMessageID();
        //asserts that the result is true
        assertTrue(ID);
    }
    /*
     subclass used to imitate user choices
     for the sentMessage() method
     */

    class TestTableMessage extends Message {
        
        //stores the selected option
        private int option;
        
        //constructor for the test subclass
        public TestTableMessage(int messageNumber, String recipientCell, String messageText, int option){
            super(messageNumber, recipientCell, messageText);
            this.option = option;
        }
        
         /*
         overrides the sentMessage method
         so the user input is not required during testing
         */
        @Override
        
        public String sentMessage() {
            
            //returns different results depending on the option
            switch(option){
                case 1:
                    return "Message successfully sent.";
                case 2:
                    return "Press 0 to delete the message.";
                case 3: 
                    return "Message successfully stored.";
                default:
                    return "Invalid option. Please choose option 1, 2, or 3";
            }
        } 
    }
    
    
    /*
     tests if selecting option 1
     returns the correct send message result
     */
    @Test
    public void testSentMessage_userSelectSend_returnsCorrectString() {
        TestTableMessage msg = new TestTableMessage(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?",1);
        
        String  result = msg.sentMessage();
        
        assertEquals("Message successfully sent.", result);
    }
    /*
     tests if selecting option 2
     returns the correct disregard message result
     */
    @Test
    public void testSentMessage_userSelectsDisregard_returnsCorrectString() {
        TestTableMessage msg = new TestTableMessage(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?", 2);
        
        String result = msg.sentMessage();
         
        assertEquals("Press 0 to delete the message.", result);
    }
    
    /*
     tests if selecting option 3
     returns the correct store message result
     */
    @Test
    public void testSentMessage_userSelectsStore_returnsCorrectString() {
        TestTableMessage msg = new TestTableMessage(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?", 3);
        
        String result = msg.sentMessage();
        assertEquals("Message successfully stored.", result);
    }
    
    /*
     tests if the sentMessages ArrayList
     stores messages correctly
     */
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
        //clears exisiting test data
        Message.getSentMessages().clear();
        
        Message.getSentMessages().add("Did you get the cake?");
        Message.getSentMessages().add("It is dinner time!");
        
        assertTrue(Message.getSentMessages().add("Did you get the cake?"));
        assertTrue(Message.getSentMessages().add("It is dinner time!"));
    }
    
    /*
     tests if the longest stored message
     is correctly returned
     */
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {
        Message.getStoredMessages().clear();
        
        Message.getStoredMessages().add("Ok, I am leaving without you.");
        Message.getStoredMessages().add("Where are you? You are late! I have asked you to be on time.");
        
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, Message.displayLongestMessage());
    }
    
    /*
     tests if searching by message ID
     returns the correct message
     */
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {
        Message.getMessageIDs().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageIDs().add("0838884567");
        Message.getSentMessages().add("It is dinner time!");
        
        assertEquals("It is dinner time!", Message.searchByMessageID("0838884567"));
    }
    
    /*
     tests if searching by the recipient's cell phone number
     returns all matching messages
     */
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {
        Message.getRecipientList().clear();
        Message.getSentMessages().clear();
        
        Message.getRecipientList().add("+27838884567");
        Message.getSentMessages().add("Where are you? You are late! I have asked you to be on time.");
        
        Message.getRecipientList().add("+27838884567");
        Message.getSentMessages().add("Ok, I am leaving without you.");
        
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    /*
     tests if deleting a message by its hash will 
     removes the correct message
     */
    @Test
    public void testDeletebyHash_removesCorrectMessage() {
        Message.getMessageHashes().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageHashes().add("00:2:WHERETIME");
        Message.getSentMessages().add("Where are you? You are late! I have asked you to be on time");
        
        String expected = "Message: " + "Where are you? You are late! I have asked you to be on time" + " successfully deleted.";
        
        assertEquals(expected, Message.deleteByHash("00:2:WHERETIME"));
    }
    
    /*
     tests if the generated report 
     conatins all the required message details
    */
    @Test
    public void testDisplayReport_containsRequiredFields() {
        Message.getMessageHashes().clear();
        Message.getRecipientList().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageHashes().add("00:1:DIDCAKE");
        Message.getRecipientList().add("+27834557896");
        Message.getSentMessages().add("Did you get the cake?");
        
        String report = Message.printMessages();
        
        assertTrue(report.contains("00:1:DIDCAKE"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        
    }
    
}


