import java.util.LinkedList;

public class PhonebookLinkedList {

    private final LinkedList<Contact> contact = new LinkedList<>();

    void displayContacts() {
        if (contact.size() == 0) {
            System.out.println("The phonebook is currently empty.");
        } else {
            System.out.println("The phonebook contains the following contact details: ");
            for (int i = 0; i < contact.size(); i++) {
                System.out.println("" + (i + 1) + ")\t\t" + "Name : " + contact.get(i).getName() + "\t\t" +
                        "Phone number: " + contact.get(i).getPhoneNumber());
            }
        }
    }

    void addContact(Contact newContact) {
        if (searchContact(newContact.getName()) >= 0) {
            System.out.println(newContact.getName() + "'s details are already in the phonebook.");
            return;
        }
        contact.add(newContact);
        System.out.println(newContact.getName() + "'s details have been added to the phonebook.");
    }

    void updateContact(Contact oldContact, Contact newContact) {
        int contactPosition = searchContact(oldContact);

        if (contactPosition < 0) {
            System.out.println(oldContact.getName() + " is not in the phonebook.");
            return;
        }
        contact.set(contactPosition, newContact);
        System.out.println(newContact.getName() + "'s details were updated in the phonebook.");
    }

    void deleteContact(Contact contact) {
        int position = searchContact(contact);
        if (position >= 0) {
            this.contact.remove(position);
        }
    }

    int searchContact(Contact contact) {
        return this.contact.indexOf(contact);
    }

    int searchContact(String searchContact) {
        for (int i = 0; i < contact.size(); i++) {
            Contact contact = this.contact.get(i);

            if (contact.getName().equalsIgnoreCase(searchContact)) {
                return i;
            }
        }
        return -1;
    }

    Contact findContact(String name) {
        int position = searchContact(name);
        if (position >= 0) {
            return this.contact.get(position);
        }
        return null;
    }
}
