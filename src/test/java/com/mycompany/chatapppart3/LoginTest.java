/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author Linothando
 */
//this import allows us to test our methods using @Test
import org.junit.Test;

//allows us to use all our assertions
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    //testing the username
    @Test
    public void testUsernameInvalidation() {
        /*
         *Entering an incorret username "kyle!!!!!!!"
         *To check if the registerUser() will return the correct error message
         */
        
        //We are creating a login object to test its method
        Login login = new Login();
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        
        //Checking if the actual result is the exact same as the expected result
        assertEquals(
           "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
            result    
        );
    }
    
    //Testing if the username is true
    @Test
    public void testUsernameValidationBoolean(){
        //Username("kyl_1") is true, it conatains an uderscore and is no more than five characters long.
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    
    }
    
    //Testing if the username is false
    @Test
    public void testUsernameInvalidationBoolean(){
        //Username("kyle!!!!!!!") is invalid, which makes it false.
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    //Testing the password
    @Test
    public void testPasswordInvalidation() {
         /*
         *Entering an incorret password "password"
         *To check if the registerUser() will return the correct error message
         */
         Login login = new Login();
         String result = login.registerUser("kyl_1", "password", "+27838968976");
         
         assertEquals(
                 "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                 result
         );
    
    }
    
    //Testing if the password is true
    @Test
    public void testPasswordValidationBoolean() {
        //Password("Ch&&sec@ke99!") is true, it contains a capital letter, a number, a special character and is at least eight characters in long.
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    //Testing if the password is false
    @Test
    public void testPasswordInvalidationBoolean() {
        //Password("password") is false, it does not meet the complexity rules
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    //Testing the cell phone number
    @Test
    public void testPhoneNumberInvalidation() {
        /*
         *Entering an incorret phone number "0838968976"
         *To check if the registerUser() will return the correct error message
         */
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "0838968976");
        
        assertEquals(
                "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                result
        );
    }
    
    //Testing if the phone number is true
    @Test
    public void testPhoneNumberValidationBoolean() {
        //Phone Number("+27838968976") is true, it conatains a South African international code.
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    //Testing if the phone number is false
    @Test
    public void testPhoneNumberInvalidationBoolean() {
        //Phone Number("0838968976") is false, it does not contain a South African international code 
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }
    
    //Testing if registration will be successful
    @Test
    public void testRegistrationSuccess() {
         /*
         *All inputted details should be correct.
         *To check if the registerUser() will return the correct success message
         */
         Login login = new Login();
         String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
         assertEquals(
                 "User registered successfully.",
                 result
         );
    }
    
    //Testing if the login process will be true
    @Test
    public void testLoginValidation() {
       /*
         *Login with the same details as the registration details.
         *To check if the registerUser() will return the correct login success message.
         */ 
       Login login = new Login();
       String registerUser = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
       
       assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    //Testing if the login process will be false
    @Test
    public void testLoginInvalidation() {
        /*
         *Login with the same details as the registration details, but use the wrong password.
         *To check if the registerUser() will return the correct login error message.
         */
        Login login = new Login();
         String registerUser = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        assertFalse(login.loginUser("kyl_1", "password"));
    }
 
}