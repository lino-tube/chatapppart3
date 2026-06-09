# Chat Application Part 3

## Student Information
 Name: Linothando Tube
 Student Number: ST10533499
 Module: PROG5121

---

## Project Information
 The project now contains five classes:
  Login.java,
  Main.java,
  LoginTest.java,
  Message.java, and
  MessageTest.java.

---

## Features
 Username Validation
 We are checking if the username meets the required rules.
 The username must:
  contain an underscore (_) and 
  must not be longer than five characters.
 If the username does not meet the requirements, an error message gets displayed.
 
## Password Validation
 We are checking if the password meets the complexity requirements.
 The password must at least be 8 characters,
   contain a capital letter,
   a number, and a special character.
 If the password is incorrectly entered, an error message gets displayed.

## Cell Phone Number Validation
 We are checking if the cell phone number is correctly formatted.
 The number must:
  start with an international code +27, and 
  contain a total of 12 characters.
 If the number is invalid, an error message gets displayed and the user gets asked to re-enter the phone number.

---

## Message Features
 ### Message ID Generation
   It is a unique 10-digit message ID that gets automatically generated for every message sent.

 ### Message Length Validation
   Checks whether the message contains 250 characters or less.
   If yes, a success message for having the correct lenghth gets displayed.
   If invalid, the system displays how many characters has exceeded the limit.
   
 ### Message Hash Creation
   A message hash is automatically created using:
   the first 2 digits of the message ID,
   the message number, and
   the first and last words of the message
   The hash is displayed in uppercase format.

 ### Send Message Options
   Users can choose from the following options:
    Send Message,
    Disregard Message, and 
    Store Message to send later
   Depending on the user's option, the application displays the correct response message.

 ### Total Messages Sent
   The application keeps track of the total number of successfully sent messages.

### Display Sent Messages
  All sent messages are stored in an ArrayList and displayed back to the user after sending them.

 ### Store Messages in the JSON File
   Selected messages for storage gets saved into the messages.json file using the org.json library.
   Stored information includes:
      Message ID,
      Recipient Cell Number, and 
      Message Text

 ### Stored Messages Menu
   A submenu was added to allow users to work with stored messages. The menu provides options such as display stored messages, search for messages, delete messages, and generate message reports.

 ### Display Stored Messages 
   Stored messages can be loaded from the messages.json file and displayed to the user through the stored messages menu.

 ### Search by Message ID
   Users can enter a message ID to search for whichever message they would like to see. The application will then use that message ID to search for the corresponding message and display its information to the user.

 ### Search by Recipient 
   Users can enter a recipient's cell phone number to search for messages stored under that number. The aaplication will use that recipient's cell phone number to find and display all messages associated with that recipient.

 ### Delete Message by Hash
   Users can delete a message by entering its message hash. Once the hash has been found, the message is removed from the stored collections and a confirmation message is displayed.

 ### Message Report
   The application can generate a report displaying the details of messages, including the message hash, recipient number, and the message content itself.

 ### Load Stored Messages
   Stored messages are loaded from the messages.json file when the application starts, allowing previously stored messages to be accessed through the sebmenu.
   
 ### Message Testing
   The MessageTest.java class was created to test if the message class works properly.
   The following methods were tested:
      checkMessageLength(),
      checkRecipientCell(),
      createMessageHash(),
      checkMessageID(),
      sentMessage(),
      displayLongestMessage(),
      searchByMessageID(),
      searchByRecipient(), and 
      deleteByHash().
