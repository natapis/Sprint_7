package practicum.order;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public Order() {

    }

    public Order withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Order withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Order withAddress(String address) {
        this.address = address;
        return this;
    }

    public Order withMetroStation(int metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public Order withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Order withRentTime(int rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public Order withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public Order withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Order withColor(String[] color) {
        this.color = color;
        return this;
    }

}
