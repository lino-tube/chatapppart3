/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author Linothando
 */
import java.util.Random;
import org.json.JSONObject;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {
    
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    
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
        System.out.println("\nWhat would yoou like to do with your message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        
        int option = input.nextInt();
        
        switch(option) {
            //Messages gets sent successfully 
            case 1:
                totalMessages++;
                return"Message successfully sent";
                
            //message gets disregared    
            case 2:
                return"Press 0 to delete the message";
                
            //message gets stored in a JSON file
            case 3:
                storeMessage();
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

}


