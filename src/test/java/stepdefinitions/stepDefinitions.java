package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.FormSubmittedPage;
import pages.IndexPage;
import pages.WebFormPage;
import io.cucumber.datatable.DataTable;

import java.util.Map;


public class stepDefinitions {

    @Steps
    WebFormPage webFormPage;

    @Steps
    FormSubmittedPage formSubmittedPage;

    @Steps
    IndexPage indexPage;

    @Given("I open the {string} page")
    public void iOpenThePage(String expHeaderText) {
        webFormPage.open();
        webFormPage.assertPageHeader(expHeaderText);
    }

    @When("I fill out the form with valid data")
    public void iFillOutTheFormWithValidData(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);

        webFormPage.enterText(data.get("TextInput"));
        webFormPage.enterPassword(data.get("Password"));
        webFormPage.enterTextarea(data.get("Textarea"));
        webFormPage.selectOption(data.get("Dropdown_Number"));
        webFormPage.enterDatalist(data.get("Dropdown_City"));
        webFormPage.uploadFile(data.get("FileName"));

        //If "select" is listed in the datatable, select Checkbox1
        if ("Select".equalsIgnoreCase(data.get("Checkbox1"))) {
            webFormPage.checkFirstCheckbox();
        }

        //If "select" is listed in the datatable, select Checkbox2
        if ("Select".equalsIgnoreCase(data.get("Checkbox2"))) {
            webFormPage.checkSecondCheckbox();
        }

        //If "select" is listed in the datatable, select Checkbox1
        if ("Select".equalsIgnoreCase(data.get("Radiobutton1"))) {
            webFormPage.selectFirstRadioButton();
        }
        if ("Select".equalsIgnoreCase(data.get("Radiobutton2"))) {
            webFormPage.selectSecondRadioButton();
        }
        webFormPage.pickColor(data.get("ColorPicker"));
        webFormPage.pickDate(data.get("DatePicker"));
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        webFormPage.submitForm();
    }

    @Then("I should be navigated to the {string} page")
    public void iShouldBeNavigatedToThePage(String expHeaderText) {
        formSubmittedPage.assertPageHeader(expHeaderText);
    }

    @And("the message reads {string}")
    public void theMessageReads(String expMessageText) {
        formSubmittedPage.assertMessageText(expMessageText);
    }


    @When("I select date {string} using mouse interaction")
    public void iSelectDateUsingMouseInteraction(String date) {
        webFormPage.pickDateUsingMouseClick(date);
    }

    @When("I click the Return to index link")
    public void iClickTheReturnToIndexLink() {
        webFormPage.clickLinkReturnToIndex();
    }

    @Then("I should be navigated to the index page with url containing {string}")
    public void iShouldBeNavigatedToTheIndexPageWithUrlContaining(String indexURL) {
        indexPage.assertURL(indexURL);
    }

    @Then("the Disabled input element is in state disabled")
    public void theDisabledInputElementIsInStateDisabled() {
        webFormPage.assertDisabledInputElement();
    }

    @Then("the Readonly input element is in state ReadOnly")
    public void theReadonlyInputElementIsInStateReadOnly() {
        webFormPage.assertReadOnlyElement();
    }

    @When("I select range {int} using mouse interaction")
    public void iSelectRangeUsingMouseInteraction(int range) {
        webFormPage.setRange(range);
    }
}