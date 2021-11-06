 class Menu {

     static void displayMenu() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                      1. Phonebook main menu                        |");
        System.out.println("| Input the number for your chosen option, followed by the ENTER key |");
        System.out.println("| 1. Main menu                                                       |");
        System.out.println("| 2. Display all contacts                                            |");
        System.out.println("| 3. Add a contact                                                   |");
        System.out.println("| 4. Update a contact                                                |");
        System.out.println("| 5. Delete a contact                                                |");
        System.out.println("| 6. Search contacts                                                 |");
        System.out.println("| 7. Quit                                                            |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("> ");
    }

     static void displayContacts() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                    2. Display all contacts                         |");
        System.out.println("+--------------------------------------------------------------------+");
    }

     static void addContact() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                         3. Add a contact                           |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("Enter contact's name: ");
    }

     static void updateContact() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                        4. Update a contact                         |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("Enter the name of the contact that you would like to update (case-sensitive): ");
    }

     static void deleteContact() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                        5. Delete a contact                         |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("Enter the name of the contact that you would like to delete (case-sensitive): ");
    }

     static void searchContacts() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                       6. Search contacts                           |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("Enter the name of the contact that you are searching for (case-sensitive): ");
    }

     static void quit() {
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                           7. Goodbye!                              |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.print("Thanks for using the phonebook :)");
    }

     static void chooseOption() {
        System.out.print("Please choose an option (enter '1' for the main menu): ");
    }
}
