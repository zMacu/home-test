Feature: Search functionality

  Scenario: Search Success
    Given I navigate to the search page
    When I search for "automation"
    Then I should see "Found one result for automation"

  Scenario: Search Empty
    Given I navigate to the search page
    When I leave the search box empty and submit
    Then I should see "Please provide a search word."
