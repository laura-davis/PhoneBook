import java.util.HashMap;
import java.util.TreeMap;

public class PhonebookTreeMap {

    private final TreeMap<String, String> phonebookTreeMap = new TreeMap<>();

    public void displayContacts() {
        if (phonebookTreeMap.isEmpty()) {
            System.out.println("The phonebook is currently empty.");
        } else {
            int count = 1;
            System.out.println("The phonebook contains the following contact details: ");
            for (HashMap.Entry<String, String> entry : phonebookTreeMap.entrySet()) {
                System.out.println(count + ")\t\t" + "Name : " + entry.getKey() + "\t\t" +
                        "Phone number: " + entry.getValue());
                count++;
            }
        }
    }

    public void addContact(String contactName, String contactPhone) {
        if (phonebookTreeMap.containsKey(contactName)) {
            System.out.println(contactName + "'s details are already in the phonebook.");
        } else {
            phonebookTreeMap.put(contactName, contactPhone);
            System.out.println(contactName + "'s details have been added to the phonebook.");
        }
    }

    public void updateContact(String oldContact, String newName, String newPhone) {
        if (phonebookTreeMap.containsKey(oldContact)) {
            phonebookTreeMap.remove(oldContact);
            phonebookTreeMap.put(newName, newPhone);
            System.out.println(newName + "'s details were updated in the phonebook.");
        } else {
            System.out.println(oldContact + " is not listed in the phonebook.");
        }
    }

    public void deleteContact(String existingContactName) {
        phonebookTreeMap.remove(existingContactName);
        System.out.println(existingContactName + "'s details were deleted from the phonebook.");
    }

    public String searchContact(String searchContact) {
        return phonebookTreeMap.get(searchContact);
    }

    public boolean findContact(String name) {
        return phonebookTreeMap.containsKey(name);
    }
}