@contractValidation
Feature: Validate Response Schema

  Scenario: Get User
    Given path '/users'
    When method 'GET'
    Then status 200
    And validate response against schema "schema/GET-response.json"
