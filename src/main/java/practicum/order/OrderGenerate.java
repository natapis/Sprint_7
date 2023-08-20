package practicum.order;


public class OrderGenerate {
    public OrderGenerate() {

    }

    public static Order generateOrder(String[] color) {
        return new Order()
                .withFirstName("Nataliya")
                .withLastName("Pisareva")
                .withAddress("Tutaev")
                .withMetroStation("Сокол")
                .withPhone("2-17-65")
                .withRentTime(2)
                .withDeliveryDate("21.08.2023")
                .withComment("test")
                .withColor(color);
    }
}
