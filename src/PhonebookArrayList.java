import java.util.ArrayList;

class PhonebookArrayList {

    final ArrayList<Contact> contactArrayList = new ArrayList<>();

    void displayContacts() {
        if (contactArrayList.size() == 0) {
            System.out.println("The arraylist is currently empty.");
        } else {
            System.out.println("The arraylist contains the following contact details: ");
            for (int i = 0; i < contactArrayList.size(); i++) {
                System.out.println("" + (i + 1) + ")\t\t" + "Name : " + contactArrayList.get(i).getName() + "\t\t" +
                        "Phone number: " + contactArrayList.get(i).getPhoneNumber());
            }
        }
    }

    void addContact(Contact newContact) {
        if (searchContact(newContact.getName()) >= 0) {
            System.out.println(newContact.getName() + "'s details are already in the arraylist.");
            return;
        }
        contactArrayList.add(newContact);
        System.out.println(newContact.getName() + "'s details have been added to the arraylist.");
    }

    void updateContact(Contact oldContact, Contact newContact) {
        int contactPosition = searchContact(oldContact);

        if (contactPosition < 0) {
            System.out.println(oldContact.getName() + " is not in the arraylist.");
            return;
        }
        contactArrayList.set(contactPosition, newContact);
        System.out.println(newContact.getName() + "'s details were updated in the arraylist.");
    }

    void deleteContact(Contact contact) {
        int position = searchContact(contact);
        if (position >= 0) {
            this.contactArrayList.remove(position);
        }
    }

    int searchContact(Contact contact) {
        return this.contactArrayList.indexOf(contact);
    }

    int searchContact(String searchContact) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            Contact contact = this.contactArrayList.get(i);

            if (contact.getName().equalsIgnoreCase(searchContact)) {
                return i;
            }
        }
        return -1;
    }

    Contact findContact(String name) {
        int position = searchContact(name);
        if (position >= 0) {
            return this.contactArrayList.get(position);
        }
        return null;
    }
}
