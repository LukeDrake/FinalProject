package util;

import com.github.javafaker.Faker;

public class UserHelper {
    public static final FakeUser REGISTERED_USER = new FakeUser("Сидр", "dsa@df.ru", "12341231");
    public static final FakeUser ERROR_USER = new FakeUser("Error", "error@er.er", "123");
    public static class FakeUser{
        String name;
        String email;
        String password;

        public FakeUser(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
    public static FakeUser getFakeUser(){
        Faker FAKER = new Faker();
        return new FakeUser(FAKER.name().username(), FAKER.internet().emailAddress(), FAKER.internet().password(8,16));
    }
}
