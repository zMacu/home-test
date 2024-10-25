Feature: Checkout functionality

  Scenario: Checkout Form Order Success
    Given I navigate to the checkout page
    When I fill in the checkout form with valid details
    And I check the "Shipping address same as billing" checkbox
    And I submit the checkout form
    Then the order confirmation number should not be empty

  Scenario: Checkout Form Alert
    Given I navigate to the checkout page
    When I fill in the checkout form with valid details
    And I uncheck the "Shipping address same as billing" checkbox
    And I try to submit the checkout form
    Then I should see an alert message
    And the alert message should be gone after confirmation


  Scenario: Cart Total Test
    Given I navigate to the checkout page
    Then the cart total should match the item prices added
