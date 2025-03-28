package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormSubmittedPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(FormSubmittedPage.class);

    @FindBy(css = "h1.display-6")
    WebElementFacade headerFormSubmitted;

    @FindBy(id = "message")
    WebElementFacade message;

    public void assertPageHeader(String expHeaderText) {
        assertThat(headerFormSubmitted.getText(), equalTo(expHeaderText));
        log.info("Form Submitted page - Page header assert has been successful");
    }

    public void assertMessageText(String expMessageText) {
        assertThat(message.getText(), equalTo(expMessageText));
        log.info("Form Submitted page - Message text assert has been successful");
    }
}