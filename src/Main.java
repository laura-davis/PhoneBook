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
    private static final PhonebookArrayList phonebookArrayList = new PhonebookArrayList();

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        File contactsFile = new File("./resources/100.json"); // Change file name to add 100, 1,000 or 10,000 contacts to each phonebook.

        JSONArray json = (JSONArray) parser.parse(new FileReader(contactsFile));
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            Contact newContact = Contact.newContact(name, phone);
            phonebookArrayList.addContact(newContact);
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
                    phonebookArrayList.displayContacts();
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
        Contact newContact = Contact.newContact(contactName, contactPhone);
        phonebookArrayList.addContact(newContact);
        Menu.chooseOption();
    }

    private static void updateContact() {
        Menu.updateContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        if (existingContact != null) {
            System.out.print("Enter a new name for " + existingContactName + ": ");
            String newName = keyboard.nextLine();
            System.out.print("Enter a new phone number for " + newName + ": ");
            String newPhone = keyboard.nextLine();
            Contact newContact = Contact.newContact(newName, newPhone);
            phonebookArrayList.updateContact(existingContact, newContact);
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    private static void deleteContact() {
        Menu.deleteContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        if (existingContact != null) {
            phonebookArrayList.deleteContact(existingContact);
            System.out.println(existingContactName + " has been deleted from the phonebook.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    private static void searchContacts() {
        Menu.searchContacts();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        if (existingContact != null) {
            System.out.println(existingContactName + "'s phone number is " + existingContact.getPhoneNumber() + " in the phonebook.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }
}
