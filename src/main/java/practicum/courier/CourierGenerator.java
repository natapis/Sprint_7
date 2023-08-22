package practicum.courier;


import com.github.javafaker.Faker;


public class CourierGenerator {
    public static Courier randomCourier() {
        Faker faker = new Faker();
        return new Courier()
                .withLogin(faker.funnyName().name())
                .withPassword(faker.gameOfThrones().dragon())
                .withFirstName(faker.name().firstName());
    }

    public static Courier doubleCourier(String login) {
        Faker faker = new Faker();
        return new Courier()
                .withLogin(login)
                .withPassword(faker.gameOfThrones().dragon())
                .withFirstName(faker.name().firstName());
    }

    public static Courier requiredFields() {
        Faker faker = new Faker();
        return new Courier()
                .withLogin(faker.funnyName().name())
                .withPassword(faker.gameOfThrones().dragon());
    }

    public static Courier withoutPassword() {
        Faker faker = new Faker();
        return new Courier()
                .withLogin(faker.funnyName().name())
                .withFirstName(faker.name().firstName());
    }

    public static Courier withoutLogin() {
        Faker faker = new Faker();
        return new Courier()
                .withPassword(faker.gameOfThrones().dragon())
                .withFirstName(faker.name().firstName());
    }

}
