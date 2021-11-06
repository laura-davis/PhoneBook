# PhoneBook

This project was built using IntelliJ IDEA 2021.2.3 (Ultimate Edition) and uses [openjdk-17 version 17.0.1](https://jdk.java.net/17/) and [json-simple-1.1.1.jar](https://code.google.com/archive/p/json-simple/downloads).

To run:

1. Clone the project
2. Ensure that you have the necessary dependencies installed
3. Open `src/Main.java`
4. Adjust the pathname on line 20 accordingly; i.e.: `File contactsFile = new File("./resources/xxx.json");`whereby `xxx` is replaced with `100`, `1000` or `10000` to load that number of dummy contacts in to each data structure
5. Run `Main.main()` (depending on the number of contacts being loaded from JSON, the program may take a while to start)
6. Follow the on-screen instructions when prompted

To see the code running using a specific data structure , use the `git checkout xxx` command in the terminal to access a different code branch, by replacing `xxx` with `Arraylist`, `main`, `Linkedlist` or `Treemap`.
