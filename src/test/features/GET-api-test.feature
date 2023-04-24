@firstTest
Feature: API testing with Rest Assured and BDD [GET METHOD]

  Scenario: Verify response status code
    Given I have the base API URL
    When I send a GET request to the "/users" endpoint
    Then The response status code should be 200
