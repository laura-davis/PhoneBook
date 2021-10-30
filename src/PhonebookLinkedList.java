import java.util.LinkedList;

public class PhonebookLinkedList {

    private final LinkedList<Contact> contactLinkedList = new LinkedList<>();

    public void displayContacts() {
        if (contactLinkedList.size() == 0) {
            System.out.println("The linkedlist is currently empty.");
        } else {
            System.out.println("The linkedlist contains the following contact details: ");
            for (int i = 0; i < contactLinkedList.size(); i++) {
                System.out.println("" + (i + 1) + ")\t\t" + "Name : " + contactLinkedList.get(i).getName() + "\t\t" +
                        "Phone number: " + contactLinkedList.get(i).getPhoneNumber());
            }
        }
    }

    public void addContact(Contact newContact) {
        if (searchContact(newContact.getName()) >= 0) {
//            System.out.println(newContact.getName() + "'s details are already in the linkedlist.");
            return;
        }
        contactLinkedList.add(newContact);
//        System.out.println(newContact.getName() + "'s details have been added to the linkedlist.");
    }

    public void updateContact(Contact oldContact, Contact newContact) {
        int contactPosition = searchContact(oldContact);

        if (contactPosition < 0) {
            System.out.println(oldContact.getName() + " is not in the linkedlist.");
            return;
        }
        contactLinkedList.set(contactPosition, newContact);
        System.out.println(newContact.getName() + "'s details were updated in the linkedlist.");
    }

    public void deleteContact(Contact contact) {
        int position = searchContact(contact);
        if (position >= 0) {
            this.contactLinkedList.remove(position);
        }
    }

    public int searchContact(Contact contact) {
        return this.contactLinkedList.indexOf(contact);
    }

    public int searchContact(String searchContact) {
        for (int i = 0; i < contactLinkedList.size(); i++) {
            Contact contact = this.contactLinkedList.get(i);

            if (contact.getName().equalsIgnoreCase(searchContact)) {
                return i;
            }
        }
        return -1;
    }

    public Contact findContact(String name) {
        int position = searchContact(name);
        if (position >= 0) {
            return this.contactLinkedList.get(position);
        }
        return null;
    }
}
