Feature: Login functionality

  Scenario: Successful login
    Given I am on the login page
    When I login with username "johndoe19" and password "supersecret"
    Then I should see the welcome message with username "johndoe19"

  Scenario: Login failure with incorrect credentials
    Given I am on the login page
    When I login with username "wronguser" and password "wrongpassword"
    Then I should see an error message "Wrong credentials"

  Scenario: Login failure with blank credentials
    Given I am on the login page
    When I click on login button
    Then I should see an error message "Fields can not be empty"
