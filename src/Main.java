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

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        File contactsFile = new File("./resources/100.json"); // Change file name to add 100, 1,000 or 10,000 contacts to each phonebook.

        JSONArray json = (JSONArray) parser.parse(new FileReader(contactsFile));
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            phonebookHashMap.addContact(name, phone);
        }
        System.out.println("\nContacts from " + contactsFile.getName() + " have been added to the phonebook.\n");

        boolean quit = false;
        int selection;

        Menu.displayMenu();

        while (!quit) {
            selection = keyboard.nextInt();
            keyboard.nextLine();
            switch (selection) {
                case 1 -> Menu.displayMenu();
                case 2 -> {
                    Menu.displayContacts();
                    phonebookHashMap.displayContacts();
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

    private static void addContact() {
        Menu.addContact();
        String contactName = keyboard.nextLine();
        System.out.print("Enter contact's phone number: ");
        String contactPhone = keyboard.nextLine();
        phonebookHashMap.addContact(contactName, contactPhone);
        Menu.chooseOption();
    }

    private static void updateContact() {
        Menu.updateContact();
        String existingContactName = keyboard.nextLine();

        if (phonebookHashMap.findContact(existingContactName)) {
            System.out.print("Enter a new name for " + existingContactName + ": ");
            String newName = keyboard.nextLine();
            System.out.print("Enter a new phone number for " + newName + ": ");
            String newPhone = keyboard.nextLine();
            phonebookHashMap.updateContact(existingContactName, newName, newPhone);
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    private static void deleteContact() {
        Menu.deleteContact();
        String existingContactName = keyboard.nextLine();
        if (phonebookHashMap.findContact(existingContactName)) {
            phonebookHashMap.deleteContact(existingContactName);
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    private static void searchContacts() {
        Menu.searchContacts();
        String existingContactName = keyboard.nextLine();
        if (phonebookHashMap.findContact(existingContactName))
        {
            String phoneNumber = phonebookHashMap.searchContact(existingContactName);
            System.out.println(existingContactName + "'s phone number is " + phoneNumber + " in the phonebook.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }
}
