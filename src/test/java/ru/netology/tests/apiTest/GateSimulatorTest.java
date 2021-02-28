package ru.netology.tests.apiTest;

import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.CardJSON;
import ru.netology.utils.ui.DataHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.netology.utils.api.JSONGateSimulator.*;

public class GateSimulatorTest {

    @Test
    void checkStatusByPaymentGateWithStatusApproved() {
        CardJSON card = DataHelper.getValidCardJsonApproved();
        String actual = jsonByPayment(card);
        String expected = card.getStatus();
        assertThat(actual, equalTo(expected));
    }

    @Test
    void checkStatusByPaymentGateWithStatusDeclined() {
        CardJSON card = DataHelper.getValidCardJsonDeclined();
        String actual = jsonByPayment(card);
        String expected = card.getStatus();
        assertThat(actual, equalTo(expected));
    }

    @Test
    void checkStatusByCreditGateWithStatusApproved() {
        CardJSON card = DataHelper.getValidCardJsonApproved();
        String actual = jsonByCredit(card);
        String expected = card.getStatus();
        assertThat(actual, equalTo(expected));
    }

    @Test
    void checkStatusByCreditGateWithStatusDeclined() {
        CardJSON card = DataHelper.getValidCardJsonDeclined();
        String actual = jsonByCredit(card);
        String expected = card.getStatus();
        assertThat(actual, equalTo(expected));
    }

    @Test
    void waitStatusCode400ByCreditGateInvalidCard() {
        CardJSON card = DataHelper.getInvalidCardJsonDeclined();
        jsonByCreditInvalidCard(card);
    }

    @Test
    void waitStatusCode400ByPaymentGateInvalidCard() {
        CardJSON card = DataHelper.getInvalidCardJsonDeclined();
        jsonByPaymentInvalidCard(card);
    }
}
