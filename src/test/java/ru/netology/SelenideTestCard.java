package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTestCard {

    String planing = generateDate(3);

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void SelenideTestCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Екатеринбург");
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(planing);
        $("[name=\"name\"]").setValue("Скударнов Александр");
        $("[name=\"phone\"]").setValue("+79226099999");
        $("[data-test-id='agreement']").click();
        $("[class=\"button__text\"]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class=\"notification__content\"]")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planing), Duration.ofSeconds(15))
                .shouldBe(visible);

    }

}
