package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WebFormPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(WebFormPage.class);

    @FindBy(css = "h1.display-6")
    WebElementFacade headerWebForm;

    @FindBy(name = "my-text")
    WebElementFacade inputText;

    @FindBy(name = "my-password")
    WebElementFacade inputPassword;

    @FindBy(name = "my-textarea")
    WebElementFacade inputTextarea;

    @FindBy(name = "my-disabled")
    WebElementFacade inputDisabled;

    @FindBy(name = "my-readonly")
    WebElementFacade readOnly;

    @FindBy(name = "my-select")
    WebElementFacade selectDropDownNumber;

    @FindBy(name = "my-datalist")
    WebElementFacade selectFromDataListCountry;

    @FindBy(name = "my-file")
    WebElementFacade selectFile;

    @FindBy(id = "my-check-1")
    WebElementFacade checkBoxChecked;

    @FindBy(id = "my-check-2")
    WebElementFacade checkBoxDefault;

    @FindBy(id = "my-radio-1")
    WebElementFacade radioButtonChecked;

    @FindBy(id = "my-radio-2")
    WebElementFacade radioButtonDefault;

    @FindBy(name = "my-colors")
    WebElementFacade pickerColor;

    @FindBy(name = "my-date")
    WebElementFacade pickerDate;

    @FindBy(name = "my-range")
    WebElementFacade sliderRange;

    @FindBy(linkText = "Return to index")
    WebElementFacade linkReturnToIndex;

    @FindBy(css = "button[type='submit']")
    WebElementFacade buttonSubmit;

    // Header that displays current month and year (e.g., "March 2025")
    @FindBy(css = ".datepicker-days .datepicker-switch")
    public WebElementFacade datepickerSwitch;

    // Button to go to previous month
    @FindBy(css = ".datepicker-days .prev")
    public WebElementFacade prevButton;

    // Button to go to next month
    @FindBy(css = ".datepicker-days .next")
    public WebElementFacade nextButton;

    // All selectable day elements in the current month view
    @FindBy(css = ".datepicker-days td.day")
    public List<WebElementFacade> calendarDays;


    public void enterText(String text) {
        if (!inputText.isPresent()) {
            throw new RuntimeException("Element not present in DOM");
        }
        if (!inputText.isEnabled()) {
            throw new RuntimeException("Element is not enabled");
        }
        inputText.clear();
        inputText.type(text);
        log.info("Typed text into input field: " + text);

    }

    public void enterPassword(String password) {
        if (!inputPassword.isPresent()) {
            throw new RuntimeException("Password input not present in DOM");
        }
        if (!inputPassword.isEnabled()) {
            throw new RuntimeException("Password input is not enabled");
        }
        inputPassword.clear();
        inputPassword.type(password);
        log.info("Entered password: "+ password);
    }

    public void enterTextarea(String message) {
        if (!inputTextarea.isPresent()) {
            throw new RuntimeException("Textarea not present in DOM");
        }
        if (!inputTextarea.isEnabled()) {
            throw new RuntimeException("Textarea is not enabled");
        }
        inputTextarea.clear();
        inputTextarea.type(message);
        log.info("Entered message into textarea: "+ message);
    }

    public void selectOption(String option) {
        if (!selectDropDownNumber.isPresent()) {
            throw new RuntimeException("Dropdown not present in DOM");
        }
        if (!selectDropDownNumber.isEnabled()) {
            throw new RuntimeException("Dropdown is not enabled");
        }
        selectDropDownNumber.selectByVisibleText(option);
        log.info("Selected dropdown option: " + option);
    }

    public void enterDatalist(String location) {
        if (!selectFromDataListCountry.isPresent()) {
            throw new RuntimeException("Datalist field not present in DOM");
        }
        if (!selectFromDataListCountry.isEnabled()) {
            throw new RuntimeException("Datalist field is not enabled");
        }
        selectFromDataListCountry.clear();
        selectFromDataListCountry.type(location);
        log.info("Entered datalist location: " + location);
    }

    public void uploadFile(String fileName) {
        String filePath = "src/test/resources/testData/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }
        String absolutePath = file.getAbsolutePath();
        selectFile.sendKeys(absolutePath);
        log.info("Uploaded file from path: " + absolutePath);
    }

    public void checkFirstCheckbox() {
        if (!checkBoxChecked.isSelected()) {
            checkBoxChecked.click();
            log.info("Checked first checkbox");
        }
    }

    public void checkSecondCheckbox() {
        if (!checkBoxDefault.isSelected()) {
            checkBoxDefault.click();
            log.info("Checked second checkbox");
        }
    }

    public void selectFirstRadioButton() {
        if (!radioButtonChecked.isSelected()) {
            radioButtonChecked.click();
            log.info("Selected first radio button");
        }
    }

    public void selectSecondRadioButton() {
        if (!radioButtonDefault.isSelected()) {
            radioButtonDefault.click();
            log.info("Selected second radio button");
        }
    }

    public void pickColor(String hexColor) {
        if (!pickerColor.isPresent()) {
            throw new RuntimeException("Color picker not present in DOM");
        }
        if (!pickerColor.isEnabled()) {
            throw new RuntimeException("Color picker is not enabled");
        }
        pickerColor.clear();
        pickerColor.type(hexColor);
        log.info("Picked color: " + hexColor);
    }

    public void pickDate(String date) {
        if (!pickerDate.isPresent()) {
            throw new RuntimeException("Date picker not present in DOM");
        }
        if (!pickerDate.isEnabled()) {
            throw new RuntimeException("Date picker is not enabled");
        }
        pickerDate.clear();
        pickerDate.type(date);
        log.info("Picked date: " + date);

        // The calendar popup remains open - Click outside.
        WebElementFacade body = $(By.tagName("body"));
        body.click();
    }

    public void setRange(int value) {
        if (!sliderRange.isPresent()) {
            throw new RuntimeException("Range slider not present in DOM");
        }
        if (!sliderRange.isEnabled()) {
            throw new RuntimeException("Range slider is not enabled");
        }
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));";
        js.executeScript(script, sliderRange, value);

        log.info("Set range to: " + value);
    }

    public void clickLinkReturnToIndex() {
        if (!linkReturnToIndex.isPresent()) {
            throw new RuntimeException("Return link not present in DOM");
        }
        linkReturnToIndex.click();
        log.info("Clicked link Return to Index");
    }

    public void submitForm() {
        if (!buttonSubmit.isPresent()) {
            throw new RuntimeException("Submit button not present in DOM");
        }
        buttonSubmit.click();
        log.info("Clicked Submit button");
    }

    public void assertPageHeader(String expHeaderText) {
        assertThat(headerWebForm.getText(), equalTo(expHeaderText));
        log.info("Web form page - Page header assert has been successful");
    }

    public void assertDisabledInputElement() {
        boolean isDisabled = !inputDisabled.isEnabled(); // returns true if input is disabled
        assertThat(isDisabled, equalTo(true));
        log.info("Web form page - Disabled input element is disabled");
    }

    public void assertReadOnlyElement() {
        boolean isReadOnly = readOnly.getAttribute("readonly") != null;
        assertThat(isReadOnly, equalTo(true));
        log.info("Web form page - Readonly input element is ReadOnly");
    }

    public void pickDateUsingMouseClick(String date) {
        // Parse input date
        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String targetMonthYear = targetDate.getMonth().name().substring(0, 1).toUpperCase() +
                targetDate.getMonth().name().substring(1).toLowerCase() +
                " " + targetDate.getYear();

        // Open the date picker
        pickerDate.click();

        // Navigate to correct month/year
        while (!datepickerSwitch.getText().equalsIgnoreCase(targetMonthYear)) {
            LocalDate currentMonth = parseMonthYear(datepickerSwitch.getText());

            if (currentMonth.isBefore(targetDate.withDayOfMonth(1))) {
                nextButton.click();
            } else {
                prevButton.click();
            }
        }

        // Click the day
        for (WebElementFacade day : calendarDays) {
            if (day.getText().equals(String.valueOf(targetDate.getDayOfMonth()))) {
                day.click();
                break;
            }
        }

        // The calendar popup remains open - Click outside.
        WebElementFacade body = $(By.tagName("body"));
        body.click();
    }


    // Helper to parse month and year from calendar header
    private LocalDate parseMonthYear(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return LocalDate.parse("01 " + text, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

}