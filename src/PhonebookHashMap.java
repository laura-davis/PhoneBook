import java.util.HashMap;

public class PhonebookHashMap {

    private final HashMap<String, String> phonebookHashMap = new HashMap<>();

    public void displayContacts() {
        if (phonebookHashMap.isEmpty()) {
            System.out.println("The hashmap is currently empty.");
        } else {
            int count = 1;
            System.out.println("The hashmap contains the following contact details: ");
            for (HashMap.Entry<String, String> entry : phonebookHashMap.entrySet()) {
                System.out.println(count + ")\t\t" + "Name : " + entry.getKey() + "\t\t" +
                        "Phone number: " + entry.getValue());
                count++;
            }
        }
    }

    public void addContact(String contactName, String contactPhone) {
        if (phonebookHashMap.containsKey(contactName)) {
//            System.out.println(contactName + "'s details are already in the hashmap.");
        } else {
            phonebookHashMap.put(contactName, contactPhone);
//            System.out.println(contactName + "'s details have been added to the hashmap.");
        }
    }

    public void updateContact(String oldContact, String newName, String newPhone) {
        if (phonebookHashMap.containsKey(oldContact)) {
            phonebookHashMap.remove(oldContact);
            phonebookHashMap.put(newName, newPhone);
            System.out.println(newName + "'s details were updated in the hashmap.");
        } else {
            System.out.println(oldContact + " is not listed in the hashmap.");
        }
    }

    public void deleteContact(String existingContactName) {
        phonebookHashMap.remove(existingContactName);
        System.out.println(existingContactName + "'s details were deleted from the hashmap.");
    }

    public String searchContact(String searchContact) {
        return phonebookHashMap.get(searchContact);
    }

    public boolean findContact(String name) {
        return phonebookHashMap.containsKey(name);
    }
}
