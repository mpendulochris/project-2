/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeparttwo;


/**
 *
 * @author Student
 */
public class Login {
     // my stored details for login check
    public String savedUser;
    public String savedPass;
    public String savedPhone; 

    // check username - must have _ and max 5 characters
     public  boolean checkUserName(String username) {
        
        return username.contains("_") && username.length() <= 5;
    }

    // check cell number - must start with +27 and be 12 chars long
    public  boolean checkCellPhoneNumber(String phone) {
        boolean startsCorrect = phone.startsWith("+27");
        boolean correctLength = phone.length() == 12;
        return startsCorrect && correctLength;
    }

    // check password - 8 chars, 1 capital, 1 number, 1 special char
    public  boolean checkPasswordComplexity(String pwd) {
        boolean lengthOk = pwd.length() >= 8;
        boolean capitalOk = false;
        boolean numberOk = false;
        boolean specialOk = false;

        for (char c : pwd.toCharArray()) {
            if (Character.isUpperCase(c)) {
                capitalOk = true;
            }
            if (Character.isDigit(c)) {
                numberOk = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                specialOk = true;
            }
        }
        return lengthOk && capitalOk && numberOk && specialOk;
    }

    // register user - my own wording
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
        // if all ok, save details
        this.savedUser = username;
        this.savedPass = password;
        this.savedPhone = phoneNumber;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        if (savedUser == null || savedPass == null) {
            return false;
        }
        boolean userMatch = savedUser.equals(username);
        boolean passMatch = savedPass.equals(password);
        return userMatch && passMatch;
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Successful login";
        } else {
            return "Failed login";
        }
    }
}
