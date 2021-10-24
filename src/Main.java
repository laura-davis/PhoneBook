import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Contact> phoneBookHash = new HashMap<>();
        Contact contact1 = new Contact("Dave", "01234 567890");
        Contact contact2 = new Contact("Jane", "01111 222222");
        phoneBookHash.put(contact1.getName(), contact1);
        phoneBookHash.put(contact2.getName(), contact2);

        long s1 = System.nanoTime();
        phoneBookHash.get("Jane");
        long e1 = System.nanoTime();
        double ms1 = 1.0 * (e1 - s1) / 1000000;
        System.out.println("The first search took " + ms1 + " milliseconds.");

        ArrayList<Contact> phoneBookArray = new ArrayList<>();
        Contact contact3 = new Contact("Bob", "01122 334455");
        Contact contact4 = new Contact("Tina", "09876 543210");
        phoneBookArray.add(contact3);
        phoneBookArray.add(contact4);

        long s2 = System.nanoTime();
        Contact match = null;
        for (Contact contact: phoneBookArray) {
            if (contact.getName().equals("Bob")) {
                match = contact;
                break;
            }
        }
        long e2 = System.nanoTime();
        double ms2 = 1.0 * (e2 - s2) / 1000000;

        System.out.println("The second search took " + ms2 + " milliseconds.");

    }
}
