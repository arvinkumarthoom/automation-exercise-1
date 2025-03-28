package pages;

import net.serenitybdd.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexPage extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(IndexPage.class);

    public void assertURL(String indexURL) {
        String currentUrl = getDriver().getCurrentUrl();

        if (currentUrl.contains(indexURL)) {
            log.info("URL contains: " + indexURL);
        } else {
            throw new RuntimeException("URL does not contain " + indexURL + "\nActual URL: " + currentUrl);
        }
    }
}