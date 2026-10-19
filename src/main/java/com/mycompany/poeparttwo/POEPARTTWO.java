/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeparttwo;

import java.util.Scanner;

public class POEPARTTWO {

     public static void main(String[] args) {
        Login key = new Login();
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username ");
        String name = scan.nextLine();
        if(key.checkUserName(name)){
            System.out.println("Correct");
        }else{
            System.out.println("Incorrect");
        }
        System.out.println("Enter password ");
        String password = scan.nextLine();
        if(key.checkPasswordComplexity(password)){
            System.out.println("Correct");
        }else{
            System.out.println("Incorrect");
        }
        
        System.out.println("Enter cellNumber ");
        String cellnumber = scan.nextLine();
        if(key.checkCellPhoneNumber(cellnumber)){
            System.out.println("Correct");
        }else{
            System.out.println("Incorrect");
        }
            
        
        //checking whether the registration is successful or not
        
        String registractionMessage = key.registerUser(name, password, cellnumber);
        
        System.out.println(registractionMessage);
        if(registractionMessage=="User registered successfully."){
         
            
            //asking the user to login
            
            System.out.println("-----------------------");
            System.out.println("LOGIN");
             System.out.println("-----------------------");
             
             System.out.println("ENTER YOUR USERNAME: ");
             String loginUsername =scan.nextLine();
             
             System.out.println("ENTER YOUR PASSWORD: ");
             String loginPassword =scan.nextLine();
             
             boolean login = key.loginUser(loginUsername, loginPassword);
             
             String LoginInStatus = key.returnLoginStatus(login);
             
             System.out.println(LoginInStatus);
             
             
        int userChoice = 0;
        Scanner inputReader = new Scanner(System.in);

         System.out.println("Welcome to EasyKanban");
        while (userChoice != 3) {

            System.out.println(
                    "\nChoose an option:\n"
                    + "1. Send Messages\n"
                    + "2. View Sent Messages\n"
                    + "3. Exit"
            );

            System.out.print("User: ");
            userChoice = inputReader.nextInt();
            inputReader.nextLine(); // clear input buffer

            switch (userChoice) {
                case 1:
                    processMessages();
                    break;

                case 2:
                    System.out.println("This feature will be added later.");
                    break;

                case 3:
                    System.out.println("Application closed. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
        }
     }

    /**
     * Handles capturing and processing messages
     */
    public static void processMessages() {

        Scanner reader = new Scanner(System.in);
        Message messageHandler = new Message();

        System.out.print("Enter number of messages to process: ");
        int totalMessages = reader.nextInt();
        reader.nextLine();

        // Arrays for storing message details
        String[] contactNumbers = new String[totalMessages];
        String[] generatedIds = new String[totalMessages];
        String[] messageTexts = new String[totalMessages];
        String[] generatedHashes = new String[totalMessages];
        String[] actionResults = new String[totalMessages];

        int sentCounter = 0;

        for (int index = 0; index < totalMessages; index++) {

            // Validate recipient
            do {
                System.out.print("Enter recipient number for message " + (index + 1) + ": ");
                contactNumbers[index] = reader.nextLine();
            } while (!messageHandler.checkCellNumber(contactNumbers[index]).equals("Valid"));

            // Validate message
            do {
                System.out.print("Enter message text: ");
                messageTexts[index] = reader.nextLine();
            } while (!messageHandler.checkMessageLength(messageTexts[index]));

            // Generate ID and Hash
            generatedIds[index] = messageHandler.generateMessageId();
            System.out.println("Message ID: " + generatedIds[index]);

            generatedHashes[index] = messageHandler.generateMessageHash(
                    generatedIds[index], index, messageTexts[index]
            );
            System.out.println("Message Hash: " + generatedHashes[index]);

            // Message action
            actionResults[index] = messageHandler.selectMessageAction(
                    generatedIds[index],
                    messageTexts[index],
                    generatedHashes[index],
                    contactNumbers[index]
            );

            sentCounter = messageHandler.calculateTotalMessages(sentCounter + 1);
        }

        System.out.println("\nTotal messages processed: " + sentCounter);
    }
}
