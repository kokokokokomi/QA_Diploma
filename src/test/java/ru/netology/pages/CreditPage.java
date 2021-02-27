package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class CreditPage {

    private SelenideElement creditLabel = $(byText("Кредит по данным карты"));

    public FormCardPage formCard() {
        creditLabel.should(visible);
        return new FormCardPage();
    }
}
