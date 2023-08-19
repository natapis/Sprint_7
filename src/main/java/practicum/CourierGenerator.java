package practicum;

import practicum.Courier;

import static practicum.RandomString.randomString;

public class CourierGenerator {
    public static Courier randomCourier(){
        return new Courier()
                .withLogin(randomString(8))
                .withPassword(randomString(12))
                .withFirstName(randomString(10));
    }
}
