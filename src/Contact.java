record Contact(String name, String phoneNumber) {

    String getName() {
        return this.name;
    }

    String getPhoneNumber() {
        return this.phoneNumber;
    }

    static Contact newContact(String name, String phone) {
        return new Contact(name, phone);
    }
}
