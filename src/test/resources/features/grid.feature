Feature: Grid functionality

  Scenario: Grid Item Test
    Given I navigate to the grid page
    Then the product in position 7 should be "Super Pepperoni"
    And the price should be "$10"

  Scenario: Grid Item Test
    Given I navigate to the grid page
    Then the product in position 7 should be "Super Pepperoni"
    And the price should be "$10"

  Scenario: Grid All Items Test
    Given I navigate to the grid page
    Then all items should have a non-empty title, price, image, and a button
