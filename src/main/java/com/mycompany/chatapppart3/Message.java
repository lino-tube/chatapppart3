/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author Linothando
 */
//Reads data from the messages.json file
import java.io.BufferedReader;
//opens and reads message.json file
import java.io.FileReader;
//generates random message IDs
import java.util.Random;
//Creates and reads JSON Objects
import org.json.JSONObject;
//Handles file input and output exceptions
import java.io.IOException;
//Writes message data into the message.json file
import java.io.FileWriter;
//Stores multiple messages flexibly
import java.util.ArrayList;
//creates lists of message information
import java.util.List;
//Accepts user input
import java.util.Scanner;

public class Message {
    
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> recipientList = new ArrayList<>();
    
    //These vaariables will used to store the details of each message
    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    
    //this is used to keep count of the total number of messages sent
    private static int totalMessages = 0;
    
    /*
     * Constructor used for creating a new Message object
     * The message ID and message hash are automatically generated
     */
    public Message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    
    //generates a random 10-digit message ID.
    public String generateMessageID(){
        Random random = new Random ();
        
        String id = "";
        
        //Loops 10 times to create the message id
        for (int i = 0; i < 10; i++){
            
            id += random.nextInt(10);
        }
        return id;
    }
    
    //checks if the message id contains 10 digits or less
    public boolean checkMessageID() {
        return messageID.length() <=10;
    }
    
    //Validates the recipient cell phone number
    public String checkRecipientCell(){
        //The number must start with an international code(+27) and not exceed 12 characters
        if(recipientCell.startsWith("+27") && recipientCell.length() <=12){
            return"Cell phone number successfully captured.";
        }else{
            return"Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
      
    //we are checking if the message length is correct or not and returning the response messages.
    public String checkMessageLength(){
        
        if (messageText.length() <= 250){
            
            return"Message ready to send.";
      
        }else{
            //Calculates how many characters exceed the given limit 
            int over = messageText.length() - 250;
            
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }
    
    //Creating a unique message hash
    public String createMessageHash(){
        //Taking the first two characters of the message ID
        String idPart = messageID.substring(0, 2);
        
        //In order to find the first and last words, we must split the message into words.
        String[] words = messageText.split(" ");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        //combine the idPart, messageNumber, firstWord and lastWord together, to form a message hash
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
        //return everything in capital letters
        return hash.toUpperCase();
    }
    //allows the user to choose what they want to do with their messages either to send, disregard, or store.
    public String sentMessage() {
        
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat would you like to do with your message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        
        int option = input.nextInt();
        
        switch(option) {
            //Messages gets sent successfully 
            case 1:
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipientList.add(recipientCell);
                
                totalMessages++;
                return"Message successfully sent";
                
            //message gets disregared    
            case 2:
                disregardedMessages.add(messageText);
                
                return"Press 0 to delete the message";
                
            //message gets stored in a JSON file
            case 3:
                storeMessage();
                
                storedMessages.add(messageText);
                
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipientList.add(recipientCell);
                
                System.out.println("Mesage saved to messages.json.");
                return"Message successfully stored";
            //Invalid slection option    
            default:
                return"\nInvalid option. Please choose option 1, 2, or 3";    
        }
    }
    
    //returns the total number of messages sent
    public static int returnTotalMessages() {
        return totalMessages;
    }
    
    //displays all the message details 
    public String printMessage() {
        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipientCell +
                "\nMessage: " + messageText;
    }
    
    /*
    *Stores the message information inside a JSON file, using the org.json library
    */
    //JSON library used: org.json
    //Source: https://mvnrepository.com/artifact/org.json/json 
    public void storeMessage() {  
        
        //creating a JSON object
        JSONObject obj = new JSONObject();
            obj.put("messageID", this.messageID); 
            obj.put("recipientCell", this.recipientCell); 
            obj.put("messageText",   this.messageText); 
            
        //FileWriter writes the JSON object into the messages.json file
        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(obj.toString());
            fw.write("\n");
            
            fw.close();
            
        } catch (IOException e){
            
            //Display an error message if storing the message fails
            System.out.println("Error storing message: " + e.getMessage());
        }
    } 
    
    //Finds and returns the longest message stored in the program
    public static String displayLongestMessage(){
        String longest = "";
        
        System.out.println("=== Longest Message ===\n");
        
        for(String msg : storedMessages){
            if(msg.length() > longest.length()){
                longest = msg;
            }
        }
        
        return longest;
    }
    
    //Searches for message using its message ID
    public static String searchByMessageID(String id) {
        for(int i = 0; i < messageIDs.size(); i++){
            if(messageIDs.get(i).equals(id)){
                return sentMessages.get(i);
            }
        }
        return "Message not found.";
    }
    
    //Displays all messages that belongs to a specific recipient
    public static String searchByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for(int i = 0; i < recipientList.size(); i++){
            if (recipientList.get(i).equals(recipient)){
                results.append(sentMessages.get(i)).append("\n");
            }
        }
        return results.toString();
    }
    
    //Deletes message by is message Hash
    public static String deleteByHash(String hash) {
        for(int i = 0; i < messageHashes.size(); i++){
            if(messageHashes.get(i).equals(hash)){
                //Stores messages before removing them so they can be displayed in the confirmation message
                String deleteMessage = sentMessages.get(i);
                //Removes all relted message information from the ArrayList
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipientList.remove(i);
                sentMessages.remove(i);
                
                return "Message: " + deleteMessage + " successfully deleted.";
            }    
         }
        return "Hash not found."; 
    }
    
    //JSON library used: org.json
    //Source: https://mvnrepository.com/artifact/org.json/json 
    public static void loadStoredMessages(){
        try(BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
           String line;
           
           while((line = reader.readLine()) != null) {
               JSONObject obj = new JSONObject(line);
               storedMessages.add(obj.getString("messageText"));
           }
        } catch (IOException e) {
            System.out.println("No stored messages found.");
        }
    }
    
    //Generates a report conataining all message details 
    public static String printMessages() {
        StringBuilder report = new StringBuilder();
        
        report.append("=== Message Report ===\n"); 
        for (int i = 0; i < sentMessages.size(); i++){
            report.append("---------------------------\n");
            report.append("Hash: ").append(messageHashes.get(i)).append("\n");
            report.append("Recipient: ").append(recipientList.get(i)).append("\n");
            report.append("Message: ").append(sentMessages.get(i)).append("\n");
        }
        return report.toString(); 
    }
    
    //Displays all messages that have been stored and viewed later on
    public static String displayStoredMessages(){
        StringBuilder result = new StringBuilder();
        
        result.append("=== Stored Messages ===\n");
        
        for(int i = 0; i < storedMessages.size(); i++) {
            result.append("------------------------------\n");
            result.append("Message ").append(i + 1).append(": ").append(storedMessages.get(i)).append("\n");
        }
        
        result.append("------------------------------\n");
        return result.toString();
    }
    
    //Returns the list of successfully sent messages
    public static List<String> getSentMessages(){
        return sentMessages;
    }
    
    //Returns the list of disregared messages
    public static List<String> getDisregardedMessages() {
        return disregardedMessages;
    } 
    
    //Returns the list of all stored messages
    public static List<String> getStoredMessages(){
        return storedMessages;
    }
    
    //Returns all generated message Hashes
    public static List<String> getMessageHashes() {
        return messageHashes;
    }
    
    //Returns all generated message IDs
    public static List<String> getMessageIDs() {
        return messageIDs;
    }
    
    //Returns all recipient cell phone numbers
    public static List<String> getRecipientList() {
        return recipientList;
    }
}


