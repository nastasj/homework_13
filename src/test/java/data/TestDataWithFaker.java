package data;

import com.github.javafaker.Faker;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class TestDataWithFaker {

    Faker faker = new Faker(new Locale("en-US"));
    public int dayOfBirth = faker.number().numberBetween(1,28);
    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            incorrectUserEmail = faker.internet().macAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            monthOfBirth = Month.of(faker.number().numberBetween(1,12)).getDisplayName(TextStyle.FULL, Locale.US),
            yearOfBirth = String.format("%s",faker.number().numberBetween(1900,2006)),
            subject = faker.options().option("English", "Maths", "Arts", "Economics",
                "Computer Science", "Biology", "Physics", "Commerce", "Chemistry", "History"),
            hobby = faker.options().option("Sports", "Reading", "Music"),
            picture = faker.options().option("Picture.webp", "Picture_2.webp", "Picture_3.jpeg"),
            userAddress = faker.address().fullAddress(),
            userState = faker.options().option("NCR", "Uttar Pradesh","Haryana", "Rajasthan"),
            userCity = getRandomCity(userState);

    public String getRandomCity(String value) {
        if (userState.equals( "NCR"))
            userCity = faker.options().option("Delhi","Gurgaon","Noida");
        else if (userState.equals("Uttar Pradesh"))
            userCity = faker.options().option("Agra","Lucknow","Merrut");
        else if (userState.equals("Haryana"))
            userCity = faker.options().option("Karnal","Panipat");
        else if (userState.equals("Rajasthan"))
            userCity = faker.options().option("Jaipur","Jaiselmer");
        return userCity;
    }

}
