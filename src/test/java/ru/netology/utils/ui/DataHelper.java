package ru.netology.utils.ui;

import ru.netology.data.CardInfo;
import ru.netology.data.CardDate;
import ru.netology.data.CardJSON;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class DataHelper {

    private DataHelper() { }

    public static CardJSON getValidCardJsonApproved() {
        return new CardJSON("4444 4444 4444 4441", "APPROVED");
    }

    public static CardJSON getValidCardJsonDeclined() {
        return new CardJSON("4444 4444 4444 4442", "DECLINED");
    }

    public static CardJSON getInvalidCardJsonDeclined() {
        return new CardJSON("4444 4444 4444 4444", "DECLINED");
    }

    public static CardDate getValidDate(String[] month,int monthNumber) {
        LocalDate currentDate = LocalDate.now();
        String currentYear = currentDate.format(DateTimeFormatter.ofPattern("yy", new Locale("ru")));
        String currentMonth = currentDate.format(DateTimeFormatter.ofPattern("MM", new Locale("ru")));
        int intervalYear = 5;
        int randomIndexYear = (int) (Math.random() * intervalYear);
        LocalDate randomValidYear = currentDate.plusYears(randomIndexYear);
        String randomYearStr = randomValidYear.format(DateTimeFormatter.ofPattern("yy", new Locale("ru")));
        int randomIndex = (int) (Math.random() * monthNumber);
        String randomMonth = month[randomIndex];
        if (Integer.parseInt(randomYearStr) == Integer.parseInt(currentYear)) {
            while ((Integer.parseInt(randomMonth) < Integer.parseInt(currentMonth)) {
                randomIndex = (int) (Math.random() * monthNumber);
                randomMonth = month[randomIndex];
            }
        }
        if (Integer.parseInt(randomYearStr) == (Integer.parseInt(currentYear)) + intervalYear) {
            while (Integer.parseInt(randomMonth) > Integer.parseInt(currentMonth)) {
                randomIndex = (int) (Math.random() * monthNumber);
                randomMonth = month[randomIndex];
            }
        }
        return new CardDate(randomMonth, randomYearStr);
    }

    public static CardDate getExpiredDate() {
        LocalDate currentDate = LocalDate.now();
        String currentYear = currentDate.format(DateTimeFormatter.ofPattern("yy", new Locale("ru")));
        String currentMonth = currentDate.format(DateTimeFormatter.ofPattern("MM", new Locale("ru")));
        int intervalYear = Integer.parseInt(currentYear);
        int randomIndexYear = (int) (Math.random() * intervalYear);
        LocalDate randomExpiredYear = currentDate.minusYears(randomIndexYear);
        String randomYearStr = randomExpiredYear.format(DateTimeFormatter.ofPattern("yy", new Locale("ru")));
        int monthNumber = 12;
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int randomIndex = (int) (Math.random() * monthNumber);
        String randomMonth = month[randomIndex];
        if (Integer.parseInt(randomYearStr) == Integer.parseInt(currentYear)) {
            while (Integer.parseInt(randomMonth) > Integer.parseInt(currentMonth)) {
                randomIndex = (int) (Math.random() * monthNumber);
                randomMonth = month[randomIndex];
            }
        }
        return new CardDate(randomMonth, randomYearStr);
    }

    public static CardDate getInvalidDate() {
        Faker faker = new Faker(new Locale("ru"));
        int randomMonth = 12 + Integer.parseInt(faker.regexify("[1-9]{1}"));
        LocalDate currentDate = LocalDate.now();
        LocalDate maxValidYear = currentDate.plusYears(6);
        int invalidIntervalYear = 20;
        int randomIndexYear = (int) (Math.random() * invalidIntervalYear);
        LocalDate randomInvalidYear = currentDate.plusYears(randomIndexYear);
        String randomYearStr = randomInvalidYear.format(DateTimeFormatter.ofPattern("yy", new Locale("ru")));
        return new CardDate(Integer.toString(randomMonth), randomYearStr);
    }

    public static CardDate getValidDateMM() {
        String[] monthMM = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return getValidDate(monthMM, 12);
    }

    public static CardDate getValidDateM() {
        String[] monthM = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        return getValidDate(monthM,9);
    }

    public static CardInfo getValidCardInfoApproved() {
        Faker faker = new Faker(new Locale("en"));
        String number = "4444 4444 4444 4441";
        String status = "APPROVED";
        String year = DataHelper.getValidDateMM().getYear();
        String month = DataHelper.getValidDateMM().getMonth();
        String user = faker.name().firstName() + " " + faker.name().lastName();
        int code = Integer.parseInt(faker.regexify("[1-9]{3}"));
        return new CardInfo(number, status, month, year, code, user);
    }

    public static CardInfo getValidCardInfoDeclined() {
        Faker faker = new Faker(new Locale("en"));
        String number = "4444 4444 4444 4442";
        String status = "DECLINED";
        String year = DataHelper.getValidDateMM().getYear();
        String month = DataHelper.getValidDateMM().getMonth();
        String user = faker.name().firstName() + faker.name().lastName();
        int code = Integer.parseInt(faker.regexify("[0-9]{3}"));
        return new CardInfo(number, status, month, year, code, user);
    }

    public static String getInvalidCardNumber(int lengthString) {
        Faker faker = new Faker(new Locale("ru"));
        int i = 0;
        String result = "";
        while (i < lengthString) {
            result = result + faker.regexify("[0-9]{1}");
            i++;
        }
        return result;
    }

    public static String getSpecialSymbols(int lengthString) {
        String[] specialSymbols = {"!", "@", "#", "$", "%", "^", "&", "(", ")", "'", "_", "+", "?", "<"};
        int numberSpecialSymbols = 14;
        int randomIndexSpecialSymbols = (int) (Math.random() * numberSpecialSymbols);
        int i = 0;
        String result = "";
        while (i < lengthString) {
            result = result + specialSymbols[randomIndexSpecialSymbols];
            randomIndexSpecialSymbols = (int) (Math.random() * numberSpecialSymbols);
            i++;
        }
        return result;
    }

    public static String getUserShortName() {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr = faker.regexify("[A-Z]{1}") + " " + faker.regexify("[A-Z]{1}");
        return nameStr;
    }

    public static String getUserLongName() {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr = faker.regexify("[A-Z]{10}") + " " + faker.regexify("[A-Z]{10}");
        return nameStr;
    }

    public static String getUserVeryLongName() {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr = faker.regexify("[A-Z]{11}") + " " + faker.regexify("[A-Z]{10}");
        return nameStr;
    }

    public static String getUserRuName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getUserDoubleName() {
        Faker faker = new Faker(new Locale("en"));
        String nameStr = faker.name().lastName() + " " + faker.name().firstName() + "-" + faker.name().firstName();
        return nameStr;
    }

    public static String getUserDifferentCaseLettersName() {
        Faker faker = new Faker(new Locale("en"));
        String nameStr = faker.regexify("[A-Z]{6}") + " " + faker.name().firstName();
        return nameStr;
    }

    public static String getCurrentAmount() {
        SelenideElement amountElement = $$("[class='list__item']").findBy(text("руб"));
        String amount = amountElement.getText().replaceAll("[^0-9]", "") + "00";
        return amount;
    }
}
