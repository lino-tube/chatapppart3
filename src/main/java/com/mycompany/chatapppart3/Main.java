/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author Linothando
 */

//imports the Scanner class so we can take input from the user
import java.util.Scanner;
//imports the ArrayLIst class so we can store multiple sent messages
import java.util.ArrayList;

/**
 *
 * @author Student
 */

//the programm will now be interacting with user
public class Main {
    public static void main(String[] args) {
    
        //the scanner will allow the user to enter their details
        Scanner input = new Scanner(System.in);
        
        /*
         an object of the login class is being created, 
         so we can call its methods
        */
        Login login = new Login();
        
        //welcome message shown when the program starts 
        System.out.println("=============================");
        System.out.println(    "WELCOME TO CHATAPP."    );
        System.out.println("=============================");
        
        //user registration process for the chat app
        System.out.println("\n=== USER REGISTRATION ===");
        
        //prompts the user to enter their first name
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        
        //prompts the user to enter their last name
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        
        //variable used to store a username
        String username;
        
        //looping until the user enters a valid username
        while(true){
            //prompts the user to enter their username
            System.out.println("Enter a username(must contain an underscore(_) and be 5 characters max): ");
            username = input.nextLine();
            
            //checks if the username is valid 
            if(login.checkUserName(username)){
                System.out.println("Username successfully captured.");
                
                //exits the loop once the username has been correctly entered
                break;
            } else {
                //displays an error message for an invalid username
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
        
        //variable used to store a password
        String password;
        
        //looping until the user enters a valid password
        while(true){
            //prompts the user to enter their password
            System.out.println("Enter a password( must contain at least 8 characters, 1 capital letter, 1 number, and 1 special): ");
            password = input.nextLine();
            
            //checks if the password meets the complexity requirements
            if(login.checkPasswordComplexity(password)){
                System.out.println("Password successfully captured.");
                
                //exits the loop once the password has been correctly entered
                break;
            } else {
                //displays an error message for an invalid password
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

       //variable used to store a phoneNumber
        String phoneNumber;
        
        //looping until the user enters a valid phoneNumber
        while(true) {
            //prompts the user to enter their phoneNumber
            System.out.println("Enter your South African phone number (must include an international code e.g. +27875678907): ");
            phoneNumber = input.nextLine();
            
            //checks if the phoneNumber is valid 
            if(login.checkCellPhoneNumber(phoneNumber)){
                System.out.println("Cell phone number successfully captured.");
                
                //exits the loop once the phoneNumber has been correctly entered
                break;
            } else {
                //displays an error message for an invalid phoneNumber
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        }
        //registerUser method gets called and the message it returns gets stored
        login.registerUser(username, password, phoneNumber);
        
        //registration message is being shown
        System.out.println("Registration successful. Welcome, " + firstName + lastName + "!");
        
        //user login process for the chat app 
        System.out.println("\n=== USER LOGIN ===");
        
        //boolean variable used to track login status
        boolean loggedIn = false;
        
        //stores the number of login attempts
        int attempts = 0;
        
        /*
         *login loop
         *user has a maximum of 3 login attempts
         */
        while(!loggedIn && attempts < 3){
            //prompts the user to enter their username
            System.out.println("Enter your username: ");
            String loginUsername = input.nextLine();
        
            //prompts the user to enter their password
            System.out.println("Enter your password: ");
            String loginPassword = input.nextLine();
        
            //loginUser method gets called to check if the details entered matches the stored ones
            loggedIn = login.loginUser(loginUsername, loginPassword);
            
            //stores the login status message
            String loginMessage = login.returnLoginStatus(loggedIn);
            //display the login status message
            System.out.println(loginMessage);
            
            //increase login attempts
            attempts++;
            
            //display the remaining attempts if login failed
            if (!loggedIn && attempts < 3){
                System.out.println("You have " + (3 - attempts) + " attemps(s)" +" remaining.");
            }
        }
        
        //message shown if the user failed all login attempts
        if(!loggedIn) {
            System.out.println("You have reached the maximum number of your login attempts.");
        } else {
            //if the user has logged in successfully, we welcome them to the chatapp
            System.out.println("\nWelcome to ChatApp.");
        }
        
        /*
         only allow access to messaging system
         if login was successful
         */

        if(loggedIn) {
            
            //controls the menu loop
            boolean running = true;
            /*
             ArrayList used to store all sent messages
             so they can be displayed later
             */
            ArrayList<String> sentMessages = new ArrayList<>();
            
            /*
             *main menu loop
             *continues running until the user chooses to quit
             */
            while(running) {
                System.out.println("\n--------------------------------------");
                
                //display the menu choices to the user
                System.out.println("Please choose an option: ");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                
                //prompting the user to enter their option(1, 2 or 3)
                System.out.println("Enter your choice: ");
                //reads the user input 
                int choice = input.nextInt();
                input.nextLine();
                //Message object declaration
                Message msg = null;
                
                //switch statement used for menu options
                switch(choice) {
                    case 1:
                        //we asking the user to enter how mny messages they wish to send.
                        System.out.println("\nHow many messages would you like to send?");
                        int numMessages = input.nextInt();
                        input.nextLine();
                        
                        ////loops according to the number of messages the user will be sending
                        for(int i = 0; i < numMessages; i++){
                            int messageNumber = i + 1;
                        
                            System.out.println("\n=== Message " + messageNumber + " of " + numMessages + " ===");
                            
                             /*
                             temporary Message object created
                             to generate a unique message ID
                             */
                            msg = new Message(messageNumber, "", "");
                            
                            //generate message ID
                            String messageID = msg.generateMessageID();
                            //display generated message ID
                            System.out.println("Message ID: " + messageID);
                            
                            //we are prompting the user to enter the number of the person or people they sending messages to
                            System.out.println("Enter recipient cell phone number(e.g. +27678907890): ");
                            String recipientCell = input.nextLine();
                            
                            //create Message object with recipient number
                            msg = new Message(messageNumber, recipientCell, "");
                            
                            //validates recipient cellphone number
                            if((recipientCell.startsWith("+27") && recipientCell.length() <=12)){
                                System.out.println(msg.checkRecipientCell());
                            }
                            //we are prompting the user to type the message they want to send
                            System.out.println("Enter your message(max 250 characters): ");
                            String messageText = input.nextLine();
                            
                            //creates Message object with full details
                            msg = new Message(messageNumber, recipientCell, messageText);
                            
                            //validates message length
                            if (messageText.length() <= 250){
                                System.out.println(msg.checkMessageLength());
                            }
                            
                            //generates message hash
                            String result = msg.createMessageHash();
                            //displays message hash
                            System.out.println("Message Hash: " + result);
                            
                            //displays message details
                            System.out.println("\n------ MESSAGE DETAILS ------");
                            
                            String Results = msg.printMessage();
                            System.out.println(Results);
                            
                            //Calls sentMessage method
                            String Result = msg.sentMessage();
                            System.out.println(Result);
                                
                            /*
                             *store the message details inside the ArrayList
                             *so all sent messages can be displayed later
                             */
                            sentMessages.add(msg.printMessage());
                        }
                        //displays total messages sent
                        System.out.println("\n=====================================================");
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                        
                        //displays all sent messages
                        System.out.println("\n=== ALL SENT MESSAGES ===");
                        System.out.println("-------------------------------------");
                        
                        //loops through the ArrayList and displays all messages
                        for(String message : sentMessages) {
                            System.out.println(message);
                            System.out.println("-------------------------");
                        }
                        break;
                     case 2: 
                        //displays that this feature is coming soon
                        System.out.println("Comming Soon.");
                        break;
                    case 3:  
                        
                        //the user now quits
                        System.out.println("Thank you for using ChatApp. Goodbye!");
                        
                        //stops the menu loop
                        running = false;
                        break;
                    case 4: 
                        storedMessagesMenu();
                        break;
                    default:
                        
        System.out.println("b) Display longest message");
                        //error message for invalid menu option
                        System.out.println("Inavlid option, please choose either 1, 2, or 3. ");
                }
            }
        }else{
            //If the user's login failed, we tell them to try again
            System.out.println("Login was unsuccessful, please try again.");
        }
    }
    
    public static void storedMessagesMenu() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("a) Display all stored messages");
        System.out.println("b) Display longest message");
        System.out.println("c) Search by message ID");
        System.out.println("d) Search by recipient");
        System.out.println("e) Delete by message hash");
        System.out.println("f) Display full report");
    }
}
