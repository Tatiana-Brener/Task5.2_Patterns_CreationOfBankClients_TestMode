package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataForRegistration;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class CreationOfNewClientsTests {

    @Test
    void shouldCreateNewValidClient() {
        open("http://localhost:9999");
        DataForRegistration validClient = DataGenerator.generateValidActiveClient();
        $("[name=login]").setValue(validClient.getLogin());
        $("[name=password]").setValue(validClient.getPassword());
        $(".button__text").click();


    }

}
