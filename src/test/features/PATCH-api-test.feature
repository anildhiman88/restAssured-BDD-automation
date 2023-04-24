@firstTest
Feature: API testing with Rest Assured and BDD [PATCH METHOD]

  Scenario: Update an existing post partially
    Given I have a request body for a partial update of a post
    When I send a PATCH request to "/posts/1"
    Then the response status code should be 200