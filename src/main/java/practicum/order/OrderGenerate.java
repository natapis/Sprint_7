package practicum.order;


import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

public class OrderGenerate {
    public OrderGenerate() {

    }

    public static Order generateOrder(String[] color) {
        Faker faker = new Faker();
        return new Order()
                .withFirstName(faker.name().firstName())
                .withLastName(faker.name().lastName())
                .withAddress(faker.address().streetAddress())
                .withMetroStation(faker.number().numberBetween(1, 215))
                .withPhone(faker.phoneNumber().phoneNumber())
                .withRentTime(faker.number().numberBetween(1, 10))
                .withDeliveryDate(faker.date().future(10, TimeUnit.DAYS).toString())
                .withComment(faker.gameOfThrones().house())
                .withColor(color);
    }
}
