Feature: Login Functionality

  Scenario Outline: Login with different credentials
    Given I am on the login page
    When I enter credential "<credential>"
    And I click the login button
    Then I should see the appropriate response

    Examples:
      | credential |
      | standard   |
      | problem    |
      | performance|
