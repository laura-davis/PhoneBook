import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Main {

    static final Scanner keyboard = new Scanner(System.in);
    static final PhonebookHashMap phonebookHashMap = new PhonebookHashMap();
    static final PhonebookTreeMap phonebookTreeMap = new PhonebookTreeMap();
    static final PhonebookArrayList phonebookArrayList = new PhonebookArrayList();
    static final PhonebookLinkedList phonebookLinkedList = new PhonebookLinkedList();

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        File contactsFile = new File("./resources/100.json"); // Change file name to add 100, 1,000 or 10,000 contacts to each phonebook.

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

        Thread.sleep(3000);

        long treeMapStart = System.nanoTime();
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            phonebookTreeMap.addContact(name, phone);
        }
        long treeMapEnd = System.nanoTime();
        double treeMapMs = 1.0 * (treeMapEnd - treeMapStart) / 1000000;

        Thread.sleep(3000);

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

        Thread.sleep(3000);

        long linkedListStart = System.nanoTime();
        for (Object o : json) {
            JSONObject contact = (JSONObject) o;
            String name = (String) contact.get("name");
            String phone = (String) contact.get("phone");
            Contact newContact = Contact.newContact(name, phone);
            phonebookLinkedList.addContact(newContact);
        }
        long linkedListEnd = System.nanoTime();
        double linkedListMs = 1.0 * (linkedListEnd - linkedListStart) / 1000000;

        System.out.println("Adding " + contactsFile.getName() + " took " + hashMapMs + "ms for the hashmap and " + treeMapMs + "ms for the treemap.");
        System.out.println("Adding " + contactsFile.getName() + " took " + arrayListMs + "ms for the arraylist and " + linkedListMs + "ms for the linkedlist.");

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
                    long hashMapStart2 = System.nanoTime();
                    phonebookHashMap.displayContacts();
                    long hashMapEnd2 = System.nanoTime();
                    double hashMapMs2 = 1.0 * (hashMapEnd2 - hashMapStart2) / 1000000;
                    Thread.sleep(1000);
                    long treeMapStart2 = System.nanoTime();
                    phonebookTreeMap.displayContacts();
                    long treemapEnd2 = System.nanoTime();
                    double treeMapMs2 = 1.0 * (treemapEnd2 - treeMapStart2) / 1000000;
                    Thread.sleep(1000);
                    long arrayListStart2 = System.nanoTime();
                    phonebookArrayList.displayContacts();
                    long arrayListEnd2 = System.nanoTime();
                    double arrayListMs2 = 1.0 * (arrayListEnd2 - arrayListStart2) / 1000000;
                    Thread.sleep(1000);
                    long linkedListStart2 = System.nanoTime();
                    phonebookLinkedList.displayContacts();
                    long linkedListEnd2 = System.nanoTime();
                    double linkedListMs2 = 1.0 * (linkedListEnd2 - linkedListStart2) / 1000000;
                    System.out.println("Retrieving contacts from " + contactsFile.getName() + " took " + hashMapMs2 + "ms for the hashmap and " + treeMapMs2 + "ms for the treemap.");
                    System.out.println("Retrieving contacts from " + contactsFile.getName() + " took " + arrayListMs2 + "ms for the arraylist and " + linkedListMs2 + "ms for the linkedlist.");
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

    static void addContact() {
        Menu.addContact();
        String contactName = keyboard.nextLine();
        System.out.print("Enter contact's phone number: ");
        String contactPhone = keyboard.nextLine();
        long hashMapStart = System.nanoTime();
        phonebookHashMap.addContact(contactName, contactPhone);
        long hashMapEnd = System.nanoTime();
        double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
        long treeMapStart = System.nanoTime();
        phonebookTreeMap.addContact(contactName, contactPhone);
        long treeMapEnd = System.nanoTime();
        double treeMapMs = 1.0 * (treeMapEnd - treeMapStart) / 1000000;
        Contact newContact = Contact.newContact(contactName, contactPhone);
        long arrayListStart = System.nanoTime();
        phonebookArrayList.addContact(newContact);
        long arrayListEnd = System.nanoTime();
        double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
        long linkedListStart = System.nanoTime();
        phonebookLinkedList.addContact(newContact);
        long linkedListEnd = System.nanoTime();
        double linkedListMs = 1.0 * (linkedListEnd - linkedListStart) / 1000000;
        System.out.println("This insertion took " + hashMapMs + "ms for the hashmap and " + treeMapMs + "ms for the treemap.");
        System.out.println("This insertion took " + arrayListMs + "ms for the arraylist and " + linkedListMs + "ms for the linkedlist.");
        Menu.chooseOption();
    }

    static void updateContact() {
        Menu.updateContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        Contact existingContact2 = phonebookLinkedList.findContact(existingContactName);

        if (existingContact != null && existingContact2 != null && phonebookHashMap.findContact(existingContactName) && phonebookTreeMap.findContact(existingContactName)) {
            System.out.print("Enter a new name for " + existingContactName + ": ");
            String newName = keyboard.nextLine();
            System.out.print("Enter a new phone number for " + newName + ": ");
            String newPhone = keyboard.nextLine();
            Contact newContact = Contact.newContact(newName, newPhone);
            long hashMapStart = System.nanoTime();
            phonebookHashMap.updateContact(existingContactName, newName, newPhone);
            long hashMapEnd = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            long treeMapStart = System.nanoTime();
            phonebookTreeMap.updateContact(existingContactName, newName, newPhone);
            long treeMapEnd = System.nanoTime();
            double treeMapMs = 1.0 * (treeMapEnd - treeMapStart) / 1000000;
            long arrayListStart = System.nanoTime();
            phonebookArrayList.updateContact(existingContact, newContact);
            long arrayListEnd = System.nanoTime();
            double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
            long linkedListStart = System.nanoTime();
            phonebookLinkedList.updateContact(existingContact2, newContact);
            long linkedListEnd = System.nanoTime();
            double linkedListMs = 1.0 * (linkedListEnd - linkedListStart) / 1000000;
            System.out.println("The update took " + hashMapMs + "ms for the hashmap and " + treeMapMs + "ms for the treemap.");
            System.out.println("The update took " + arrayListMs + "ms for the arraylist and " + linkedListMs + "ms for the linkedlist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    static void deleteContact() {
        Menu.deleteContact();
        String existingContactName = keyboard.nextLine();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        Contact existingContact2 = phonebookLinkedList.findContact(existingContactName);
        if (phonebookHashMap.findContact(existingContactName) && phonebookTreeMap.findContact(existingContactName) && existingContact != null && existingContact2 != null) {
            long hashMapStart = System.nanoTime();
            phonebookHashMap.deleteContact(existingContactName);
            long hashMapEnd = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            long treeMapStart = System.nanoTime();
            phonebookTreeMap.deleteContact(existingContactName);
            long treeMapEnd = System.nanoTime();
            double treeMapMs = 1.0 * (treeMapEnd - treeMapStart) / 1000000;
            long arrayListStart = System.nanoTime();
            phonebookArrayList.deleteContact(existingContact);
            long arrayListEnd = System.nanoTime();
            double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
            long linkedListStart = System.nanoTime();
            phonebookLinkedList.deleteContact(existingContact2);
            long linkedListEnd = System.nanoTime();
            double linkedListMs = 1.0 * (linkedListEnd - linkedListStart) / 1000000;
            System.out.println("The deletion took " + hashMapMs + "ms for the hashmap and " + treeMapMs + "for the treemap.");
            System.out.println("The deletion took " + arrayListMs + "ms for the arraylist and " + linkedListMs + "for the linkedlist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }

    static void searchContacts() {
        Menu.searchContacts();
        String existingContactName = keyboard.nextLine();
        long arrayListStart = System.nanoTime();
        Contact existingContact = phonebookArrayList.findContact(existingContactName);
        long arrayListEnd = System.nanoTime();
        double arrayListMs = 1.0 * (arrayListEnd - arrayListStart) / 1000000;
        long linkedListStart = System.nanoTime();
        Contact existingContact2 = phonebookLinkedList.findContact(existingContactName);
        long linkedListEnd = System.nanoTime();
        double linkedListMs = 1.0 * (linkedListEnd - linkedListStart) / 1000000;
        if (phonebookHashMap.findContact(existingContactName) && phonebookTreeMap.findContact(existingContactName) && existingContact != null && existingContact2 != null) {
            long hashMapStart = System.nanoTime();
            String phoneNumber = phonebookHashMap.searchContact(existingContactName);
            long hashMapEnd = System.nanoTime();
            double hashMapMs = 1.0 * (hashMapEnd - hashMapStart) / 1000000;
            long treeMapStart = System.nanoTime();
            String phoneNumber2 = phonebookTreeMap.searchContact(existingContactName);
            long treeMapEnd = System.nanoTime();
            double treeMapMs = 1.0 * (treeMapEnd - treeMapStart) / 1000000;
            System.out.println(existingContactName + "'s phone number is " + phoneNumber + " in the hashmap.");
            System.out.println(existingContactName + "'s phone number is " + phoneNumber2 + " in the treemap.");
            System.out.println(existingContactName + "'s phone number is " + existingContact.getPhoneNumber() + " in the arraylist.");
            System.out.println(existingContactName + "'s phone number is " + existingContact2.getPhoneNumber() + " in the linkedlist.");
            System.out.println("The search took " + hashMapMs + "ms for the hashmap and " + treeMapMs + "ms for the treemap.");
            System.out.println("The search took " + arrayListMs + "ms for the arraylist and " + linkedListMs + "ms for the linkedlist.");
        } else {
            System.out.println(existingContactName + " is not listed in the phonebook.");
        }
        Menu.chooseOption();
    }
}
