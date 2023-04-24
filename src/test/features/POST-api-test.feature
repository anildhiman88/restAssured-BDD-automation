@firstTest
Feature: API testing with Rest Assured and BDD [POST METHOD]

  Scenario: Create a new post
    Given I have a request body for a new post
    When I send a POST request to "/posts"
    Then the response status code should be 201