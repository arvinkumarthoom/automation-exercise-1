@all
Feature: Web Form Testing

  Scenario: Submit form with valid inputs - This scenario includes interactions with all elements except the link _Return to index_ and _Example range_
    Given I open the "Web form" page
    When I fill out the form with valid data
      | TextInput  | Password  | Textarea  | Dropdown_Number | Dropdown_City | FileName       | Checkbox1 | Checkbox2 | Radiobutton1 | Radiobutton2 | ColorPicker | DatePicker |
      | TextInput1 | Password1 | TextArea1 | Two             | Los Angeles   | sampleFile.txt | Select    |           | Select       |              | #3D557B     | 03/29/2025 |
    And I submit the form
    Then I should be navigated to the "Form submitted" page
    And the message reads "Received!"

  Scenario: Submit form with valid inputs (multiple check boxes) - This scenario includes interactions with all elements except the link _Return to index_ and _Example range_
    Given I open the "Web form" page
    When I fill out the form with valid data
      | TextInput  | Password  | Textarea  | Dropdown_Number | Dropdown_City | FileName       | Checkbox1 | Checkbox2 | Radiobutton1 | Radiobutton2 | ColorPicker | DatePicker |
      | TextInput2 | Password2 | TextArea2 | One             | New York      | sampleFile.txt | Select    | Select    | Select       |              | #1B5F26     | 12/10/2024 |
    And I submit the form
    Then I should be navigated to the "Form submitted" page
    And the message reads "Received!"

  Scenario: Disabled input - Assert _Disabled input_ element has been disabled
    Given I open the "Web form" page
    Then the Disabled input element is in state disabled

  Scenario: Readonly input - Assert _Readonly input_ element has been set to ReadOnly
    Given I open the "Web form" page
    Then the Readonly input element is in state ReadOnly

  Scenario: Return to index - Navigate to index
    Given I open the "Web form" page
    When I click the Return to index link
    Then I should be navigated to the index page with url containing "index.html"

  Scenario: Date picker - Date selection via interaction
    Given I open the "Web form" page
    When I select date "03/10/2026" using mouse interaction
    And I submit the form
    Then I should be navigated to the "Form submitted" page
    And the message reads "Received!"

  @all
  Scenario: Example range - Range selection via interaction
    Given I open the "Web form" page
    When I select range 10 using mouse interaction
    And I submit the form
    Then I should be navigated to the "Form submitted" page
    And the message reads "Received!"



