package guru.qu.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoqaTests {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillformTest() {
        open("/automation-practice-form");
        // Removing AD because it is overlapping Submit button
        Selenide.executeJavaScript("document.getElementById('fixedban').remove()");
        // Removing Footer because it is overlapping Submit button
        Selenide.executeJavaScript("document.getElementsByTagName('footer')[0].remove()");
        $("[id=firstName]").setValue("Vera");
        $("[id=lastName]").setValue("Padgok");
        $("[id=userEmail]").setValue("testForm@mailinator.com");
        $("[for=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234543456");
        $("[id=dateOfBirthInput]").click();
        $("[class=react-datepicker__month-select]").selectOption("August");
        $("[class=react-datepicker__year-select]").selectOption("1984");
        $("[aria-label='Choose Friday, August 31st, 1984']").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("#uploadPicture").uploadFromClasspath("text.txt");
        $("#currentAddress").setValue("Minsk, 220117");
        $("#state").scrollTo().click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Vera Padgok"),
                text("testForm@mailinator.com"),
                text("Female"),
                text("1234543456"),
                text("31 August,1984"),
                text("Math"),
                text("Sports"),
                text("text.txt"),
                text("Minsk, 220117"),
                text("NCR Delhi"));
        $("#closeLargeModal").click();
    }
}

