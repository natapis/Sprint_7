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

    public static Courier doubleCourier(String login){
        return new Courier()
                .withLogin(login)
                .withPassword(randomString(12))
                .withFirstName(randomString(10));
    }

    public static Courier requiredFields(){
        return new Courier()
                .withLogin(randomString(8))
                .withPassword(randomString(10));
    }

    public static Courier withoutPassword(){
        return new Courier()
                .withLogin(randomString(8))
                .withFirstName(randomString(12));
    }
    public static Courier withoutLogin(){
        return new Courier()
                .withPassword(randomString(10))
                .withFirstName(randomString(12));
    }

}
