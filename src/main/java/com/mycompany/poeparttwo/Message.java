
package com.mycompany.poeparttwo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.Random;
import java.util.Scanner;

public class Message {

    Scanner userInput = new Scanner(System.in);

    /**
     * Allows user to choose message action
     */
    public String selectMessageAction(String msgId, String msgContent, String msgHash, String receiver) {

        String[] actionLabels = {
            "Sent",
            "Disregarded",
            "Stored"
        };

        System.out.println("\nChoose action:");
        System.out.println("0. Send Message");
        System.out.println("1. Disregard Message");
        System.out.println("2. Store Message");

        System.out.print("User: ");
        int userSelection = userInput.nextInt();
        userInput.nextLine();

        switch (userSelection) {
            case 0:
                System.out.println("Message sent successfully.");
                System.out.println(displayOnlySentMessages(msgId, msgContent, msgHash, receiver));
                break;

            case 1:
                System.out.println("Message ignored.");
                break;

            case 2:
                saveMessage(msgId, msgContent, msgHash, receiver);
                System.out.println("Message saved to file.");
                break;
        }

        return actionLabels[userSelection];
    }

    /**
     * Generates a random 10-digit ID
     */
    public String generateMessageId() {
        Random rand = new Random();
        StringBuilder idBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            idBuilder.append(rand.nextInt(10));
        }

        return idBuilder.toString();
    }

    /**
     * Creates a message hash
     */
    public String generateMessageHash(String msgId, int index, String text) {
        String start = msgId.substring(0, 2);
        String first = text.split(" ")[0];
        String last = text.split(" ")[text.split(" ").length - 1];

        return (start + ":" + index + ":" + first + last).toUpperCase();
    }

    /**
     * Validates message length
     */
    public boolean checkMessageLength(String text) {

        if (text.length() <= 250) {
            System.out.println("Message length valid.");
            return true;
        }

        System.out.println("Message too long (max 250 characters).");
        return false;
    }

    /**
     * Displays sent messages only
     */
    public String displayOnlySentMessages(String id, String text, String hash, String recipient) {

        StringBuilder output = new StringBuilder();

        output.append("\n------------------------\n")
              .append("Message ID: ").append(id).append("\n")
              .append("Message Hash: ").append(hash).append("\n")
              .append("Recipient: ").append(recipient).append("\n")
              .append("Message: ").append(text).append("\n")
              .append("------------------------");

        return output.toString();
    }

    /**
     * Validates SA cell numbers (+27)
     */
    public String checkCellNumber(String number) {

        if (number != null
                && number.startsWith("+27")
                && number.length() >= 12
                && number.matches("\\+27[0-9]+")) {

            System.out.println("Cell number accepted.");
            return "Valid";
        }

        System.out.println("Invalid cell number. Must start with +27.");
        return "Invalid";
    }

    /**
     * Saves message to JSON file
     */
    public void saveMessage(String id, String text, String hash, String recipient) {

        File storage = new File("messages.json");
        Gson gson = new Gson();

        String[] record = { id, text, hash, recipient };

        try {
            if (!storage.exists()) {
                storage.createNewFile();
            }

            try (FileWriter writer = new FileWriter(storage, true)) {
                writer.write(gson.toJson(record));
                writer.write(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Storage error: " + e.getMessage());
        }
    }

    /**
     * Returns updated total count
     */
    public int calculateTotalMessages(int count) {
        return count;
    }
}
