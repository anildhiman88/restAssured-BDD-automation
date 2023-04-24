@firstTest
Feature: API testing with Rest Assured and BDD [PUT & DELETE METHOD]

  Scenario: Update an existing post
    Given I have a request body for an updated post
    When I send a PUT request to "/posts/1"
    Then the response status code should be 200

  Scenario: Delete an existing post
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200
