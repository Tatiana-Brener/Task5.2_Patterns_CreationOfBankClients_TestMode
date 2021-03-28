package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void requestForm(DataForRegistration dataForRegistration) {
        given()
                .spec(requestSpec)
                .body(dataForRegistration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static DataForRegistration generateValidActiveClient() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "active";
        DataForRegistration newClient = new DataForRegistration(login, password, status);
        requestForm(newClient);
        return newClient;

    }

    public static DataForRegistration generateValidBlockedClient() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "blocked";
        DataForRegistration newClient = new DataForRegistration(login, password, status);
        requestForm(newClient);
        return newClient;
    }

    public static DataForRegistration generateClientWithIncorrectLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = "maya";
        String password = faker.internet().password();
        String status = "active";
        DataForRegistration newClient = new DataForRegistration(login, password, status);
        requestForm(newClient);
        return new DataForRegistration("mayyaa", password, status);
    }

    public static DataForRegistration generateClientWithIncorrectPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = "password";
        String status = "active";
        DataForRegistration newClient = new DataForRegistration(login, password, status);
        requestForm(newClient);
        return new DataForRegistration(login, "incorrectpassword", status);
    }
}



