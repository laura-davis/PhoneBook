import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner keyboard = new Scanner(System.in);
    private static final PhonebookHashMap phonebookHashMap = new PhonebookHashMap();
    private static final PhonebookArrayList phonebookArrayList = new PhonebookArrayList();

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        File contactsFile = new File("./resources/10000.json"); // Change file name to add 100, 1,000 or 10,000 contacts to each phonebook.

        JSONArray json = (JSONArray) parser.parse(new FileReader(contactsFile));
        long hashMapStart = System.nanoTime();
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            phonebookHashMap.addContact(name, phone);
        }
        long hashMapEnd = System.nanoTime();
        double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;

        Thread.sleep(1000);

        long arrayListStart = System.nanoTime();
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            Contact newContact = Contact.newContact(name, phone);
            phonebookArrayList.addContact(newContact);
        }
        long arrayListEnd = System.nanoTime();
        double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;

        System.out.println("Adding " + contactsFile.getName() + " took " + hashMapMs + "ms for the hashmap and " + arrayListMs + "ms for the arraylist.");

        boolean quit = false;
        int selection;

        Menu.displayMenu();

        while (!quit) {
            //TODO - try / catch - exceptions handling if invalid data input
            selection = keyboard.nextInt();
            keyboard.nextLine();
            switch (selection) {
                case 1 -> Menu.displayMenu();
                case 2 -> {
                    Menu.displayContacts();
                    long hashMapStart2 = System.nanoTime();
                    phonebookHashMap.displayContacts();
                    long hashMapEnd2 = System.nanoTime();
                    double hashMapMs2 = 1.0 * (hashMapEnd2 - hashMapStart2) / 1000000;
                    Thread.sleep(1000);
                    long arrayListStart2 = System.nanoTime();
                    phonebookArrayList.displayContacts();
                    long arrayListEnd2 = System.nanoTime();
                    double arrayListMs2 = 1.0 * (arrayListEnd2 - arrayListStart2) / 1000000;
                    System.out.println("Retrieving contacts from " + contactsFile.getName() + " took " + hashMapMs2 + "ms for the hashmap and " + arrayListMs2 + "ms for the arraylist.");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("error");
                    }
                    Menu.chooseOption();
                }
                case 3 -> addContact();
                case 4 -> updateContact();

                case 5 -> deleteContact();

                case 6 -> searchContacts();

                case 7 -> {
                    quit = true;
                    Menu.quit();
                }
                default -> System.out.println("Please enter a valid selection.");
            }
        }
    }

    public static void addContact() {
        Menu.addContact();
        String contactName = keyboard.nextLine();
        System.out.print("Enter contact's phone number: ");
        String contactPhone = keyboard.nextLine();
        long hashMapStart = System.nanoTime();
        phonebookHashMap.addContact(contactName, contactPhone);
        long hashMapEnd = System.nanoTime();
        double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
        Contact newContact = Contact.newContact(contactName, contactPhone);
        long arrayListStart = System.nanoTime();
        phonebookArrayList.addContact(newContact);
        long arrayListEnd = System.nanoTime();
        double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
        System.out.println("This insertion took " + hashMapMs + "ms for the hashmap and " + arrayListMs + "ms for the arraylist.");
        Menu.chooseOption();
    }

    private static void updateContact() {
        Menu.updateContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);

        if (existingContact != null && phonebookHashMap.findContact(existingContactName)) {
            //TODO - add optional step - skip name / number
            System.out.print("Enter a new name for " + existingContactName + ": ");
            String newName = keyboard.nextLine();
            System.out.print("Enter a new phone number for " + newName + ": ");
            String newPhone = keyboard.nextLine();
            Contact newContact = Contact.newContact(newName, newPhone);
            long hashMapStart = System.nanoTime();
            phonebookHashMap.updateContact(existingContactName, newName, newPhone);
            long hashMapEnd = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            long arrayListStart = System.nanoTime();
            phonebookArrayList.updateContact(existingContact, newContact);
            long arrayListEnd = System.nanoTime();
            double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
            System.out.println("The update took " + hashMapMs + "ms for the hashmap and " + arrayListMs + "ms for the arraylist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
            Menu.chooseOption();
        }
        Menu.chooseOption();
    }

    private static void deleteContact() {
        Menu.deleteContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        if (phonebookHashMap.findContact(existingContactName) && existingContact != null) {
            phonebookHashMap.deleteContact(existingContactName);
            long hashMapEnd = System.nanoTime();
            long hashMapStart = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            long arrayListStart = System.nanoTime();
            phonebookArrayList.deleteContact(existingContact);
            long arrayListEnd = System.nanoTime();
            double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
            System.out.println("The deletion took " + hashMapMs + "ms for the hashmap and " + arrayListMs + "for the arraylist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    private static void searchContacts() {
        Menu.searchContacts();
        String existingContactName = keyboard.nextLine();
        long arrayListStart = System.nanoTime();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        long arrayListEnd = System.nanoTime();
        double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
        if (phonebookHashMap.findContact(existingContactName) && existingContact != null) {
            String phoneNumber = phonebookHashMap.searchContact(existingContactName);
            long hashMapEnd = System.nanoTime();
            long hashMapStart = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            System.out.println(existingContactName + "'s phone number is " + phoneNumber + " in the hashmap.");
            System.out.println(existingContactName + "'s phone number is " + existingContact.getPhoneNumber() + " in the arraylist.");
            System.out.println("The search took " + hashMapMs + "ms for the hashmap and " + arrayListMs + "ms for the arraylist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
            Menu.chooseOption();
        }
        Menu.chooseOption();
    }
}
