/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author Student
 */
public class Login {
    //this is where the user will store their name
    String firstName;
    //this is where the user will store their surname
    String lastName;
    //this is where the user will store their user name
    String username;
    //this is where the user will store their password
    String password;
    //this is where the user will store their number
    String phoneNumber;
    //check whether the username is correct or not
    public boolean checkUserName(String username){
    //username must contain an underscore(_)
    //username must not be longer than 5 characters
    
    //the username is now being returned
    return username.contains("_") && username.length() <=5;
    }
    
    //checks whether the password is valid or invalid
    public boolean checkPasswordComplexity(String password){
    //password must at least be 8 characters long
    //password must contain at least 1 capital letter
    //paasword must have at least 1 number
    //password must have at least 1 speacial letter
    boolean hasCapital = false;
    boolean hasNumber = false;
    boolean hasSpecial = false;
    
    for (int i = 0; i < password.length(); i++) {
    char c = password.charAt(i);
    //checks whether the password contains a capital letter
    if (Character.isUpperCase(c)){
       hasCapital = true;
    }
    //checks whether the password contains a number
    else if (Character.isDigit(c)){
       hasNumber = true;
    }
    //checks whether the password contains a special character
    else if (Character.isLetterOrDigit(c)){
       hasSpecial = true;
    }
   }
   return password.length() >=8 && hasCapital && hasNumber && hasSpecial;
  }
 
   //checks whether the number is entered correctly or not
   public boolean checkCellPhoneNumber(String phoneNumber) {
       /*
        *the number must have a South African international code(+27)
        *that follows with 9 digits after
        */
       return phoneNumber.startsWith("+27") && phoneNumber.length() <=12;
   } 
   
   //checks whether the user details have been successfully registered
   public String registerUser(String username, String password, String phoneNumber) {
   
       /*
        *Confirms whether the user detials are correct,
        *stores the user detials if everything is correct,
        *and returns specific messages to the user
        */
       if(!checkUserName(username)) {
         return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
       }
       
       if(!checkPasswordComplexity(password)) {
         return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
       }
       
       if(!checkCellPhoneNumber(phoneNumber)) {
         return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
       }
       
       this.username = username;
       this.password = password;
       this.phoneNumber = phoneNumber;
       
       return "User registered successfully";
   }
   //allows the user to login using the same details as the registration details
   public boolean loginUser(String username, String password){
     return this.username.equals(username) && this.password.equals(password);
   }
   
   //returns whether the usename and password are correct
   public String returnLoginStatus(boolean success) {
     if(success) {
       return "Welcome " + username + " it is great to see you again.";
     }
     else {
       return "Username or password is incorrect, please try again";
     }
   }
}    

