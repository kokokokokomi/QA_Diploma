package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;
import ru.netology.utils.ui.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormCardPage {
    private SelenideElement cardNumberField = $$("[class='form-field form-field_size_m form-field_theme_alfa-on-white']").find(exactText("Номер карты"));
    private SelenideElement monthField = $$("[class='input-group__input-case']").find(exactText("Месяц"));
    private SelenideElement yearField = $$("[class='input-group__input-case']").find(exactText("Год"));
    private SelenideElement usernameField = $$("[class='input-group__input-case']").find(exactText("Владелец"));
    private SelenideElement cvcField = $$("[class='input-group__input-case']").find(exactText("CVC/CVV"));
    private SelenideElement processButton = $$("button").find(exactText("Продолжить"));

    public void formFilled(CardInfo card) {
        cardNumberField.$("input").setValue(card.getNumber());
        monthField.$("input").setValue(card.getMonth());
        yearField.$("input").setValue(card.getYear());
        usernameField.$("input").setValue(card.getUser());
        cvcField.$("input").setValue(Integer.toString(card.getCode()));
        processButton.click();
    }

    public void successOperation() {
        $(withText("Успешно")).waitUntil(visible, 15000);
        $(withText("Ошибка! Банк отказал в проведении операции.")).waitUntil(hidden, 15000);
    }

    public void rejectOperation() {
        $(withText("Ошибка! Банк отказал в проведении операции.")).waitUntil(visible, 15000);
        $(withText("Успешно")).waitUntil(hidden, 15000);
    }

    public void errorCardNumberOperation(String errorMessage) {
        SelenideElement error = $("[class='form-field form-field_size_m form-field_theme_alfa-on-white']").shouldHave(text("Номер карты"));
        error.$("[class='input__sub']").shouldHave(exactText(errorMessage));
    }

    public void errorFieldOperation(String field, String errorMessage) {
        SelenideElement error = $("[class='input-group__input-case input-group__input-case_invalid']").shouldHave(text(field));
        error.$("[class='input__sub']").shouldHave(exactText(errorMessage));
    }

    public void errorCodeNumberOperation(String errorMessage) {
        SelenideElement error = $("[class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white']").shouldHave(text("CVC/CVV"));
        error.$("[class='input__sub']").shouldHave(exactText(errorMessage));
    }

    public void codeFieldIsEmptyOperation() {
        CardInfo card = DataHelper.getValidCardInfoApproved();
        cardNumberField.$("input").setValue(card.getNumber());
        monthField.$("input").setValue(card.getMonth());
        yearField.$("input").setValue(card.getYear());
        usernameField.$("input").setValue(card.getUser());
        processButton.click();
    }

    public void codeWithSpecialSymbolsOperation() {
        CardInfo card = DataHelper.getValidCardInfoApproved();
        cardNumberField.$("input").setValue(card.getNumber());
        monthField.$("input").setValue(card.getMonth());
        yearField.$("input").setValue(card.getYear());
        usernameField.$("input").setValue(card.getUser());
        cvcField.$("input").setValue(DataHelper.getSpecialSymbols(3));
        processButton.click();
    }
}
