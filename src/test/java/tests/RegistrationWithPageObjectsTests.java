package tests;

import data.TestDataWithFaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    BasePage basePage = new BasePage();
    TestDataWithFaker testData = new TestDataWithFaker();

    @Test
    @Tag("regress")
    @DisplayName("Successful registration with all fields filled")
    void successfulAllFieldsRegistrationTest() {
        step("Open registration form", () -> {
                    registrationPage.openPage();
                });
        step("Close advertising banners", () -> {
                    basePage.removeBanner();
                });
        step("Fill out all form fields and submit the form", () -> {
                    registrationPage.setFirstName(testData.firstName)
                            .setLastName(testData.lastName)
                            .setEmail(testData.userEmail)
                            .setGender(testData.gender)
                            .setUserNumber(testData.userNumber)
                            .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                            .setSubject(testData.subject)
                            .setHobby(testData.hobby)
                            .uploadPicture(testData.picture)
                            .setUserAddress(testData.userAddress)
                            .setUserState(testData.userState)
                            .setUserCity(testData.userCity)
                            .submitForm();
                });
        step("Check result data is correct", () -> {
            registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.userEmail)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.userNumber)
                    .checkResult("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + ","
                            + testData.yearOfBirth)
                    .checkResult("Subjects", testData.subject)
                    .checkResult("Hobbies", testData.hobby)
                    .checkResult("Picture", testData.picture)
                    .checkResult("Address", testData.userAddress)
                    .checkResult("State and City", testData.userState + " " + testData.userCity);
            });
    }

    @Test
    @Tag("regress")
    @Tag("smoke")
    @DisplayName("Successful registration with required fields filled")
    void successfulRequiredFieldsRegistrationTest() {
        step("Open registration form", () -> {
                    registrationPage.openPage();
                });
        step("Close advertising banners", () -> {
                    basePage.removeBanner();
                });
        step("Fill out required form fields and submit the form", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.gender)
                    .setUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                    .submitForm();
                });
        step("Check result data is correct", () -> {
            registrationPage.checkResultFormVisible()
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.userNumber)
                    .checkResult("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + ","
                            + testData.yearOfBirth);
                });
    }

    @Test
    @Tag("regress")
    @DisplayName("Unsuccessful registration with incorrect email address")
    void incorrectEmailRegistrationTest() {
        step("Open registration form", () -> {
                    registrationPage.openPage();
                });
        step("Close advertising banners", () -> {
                    basePage.removeBanner();
                });
        step("Fill out form fields with incorrect email address and submit the form", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.incorrectUserEmail)
                    .setGender(testData.gender)
                    .setUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                    .submitForm();
        });
        step("Check result form is invisible", () -> {
            registrationPage.checkResultFormInvisible();
        });
    }
}