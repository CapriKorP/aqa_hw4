package ru.korndev;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryServiceTest {

    int days = 3;

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void happyPathTest() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(days));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79998881234");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content").shouldBe(Condition.text("Встреча успешно забронирована на " + LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void notSetCityTest() {

        open("http://localhost:9999/");
        //$("[data-test-id='city'] input").setValue("Москва");
        //$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(days));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79998881234");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='city' and contains(@class, 'input_invalid')]//span[contains(text(), 'Поле')]").should(Condition.appear);
    }

    @Test
    void notSetNameTest() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(days));
        //$("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79998881234");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='name' and contains(@class, 'input_invalid')]//span[contains(text(), 'Поле')]").should(Condition.appear);
    }

    @Test
    void notSetPhoneTest() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(days));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        //$("[data-test-id='phone'] input").setValue("+79998881234");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='phone' and contains(@class, 'input_invalid')]//span[contains(text(), 'Поле')]").should(Condition.appear);
    }

    @Test
    void notSetCheckBoxAgreement() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(days));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79998881234");
        // $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//label[@data-test-id='agreement'][contains(@class, 'input_invalid')]").should(appear);
    }
}