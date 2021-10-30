//import com.fasterxml.jackson.annotation.JsonProperty;

public record Contact(String name, String phoneNumber) {

    //    @JsonCreator
    //    public Contact(@JsonProperty("name") String name, @JsonProperty("number") String phoneNumber) {

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public static Contact newContact(String name, String phone) {
        return new Contact(name, phone);
    }
}
