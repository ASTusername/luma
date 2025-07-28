package data;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String password = faker.internet().password(8, 16, true, true, true);
    public String emailPassRecovery = "2222222@mail.ru";
}
