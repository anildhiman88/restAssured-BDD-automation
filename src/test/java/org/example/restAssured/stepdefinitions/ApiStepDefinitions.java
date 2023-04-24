package org.example.restAssured.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class ApiStepDefinitions {

    private static final Logger logger = LogManager.getLogger(ApiStepDefinitions.class);
    private RequestSpecification request;
    private Response response;
    private String requestBody;
    @Given("I have the base API URL")
    public void i_have_the_base_API_URL() {
        logger.info("Setting base API URL");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        request = RestAssured.given();
    }

    @When("I send a GET request to the {string} endpoint")
    public void i_send_a_GET_request_to_the_endpoint(String endpoint) {
        logger.info("Sending GET request to endpoint: {}", endpoint);
        response = request.get(endpoint);
        logger.info("Request URL: " + RestAssured.baseURI + endpoint);
        logger.info("Response: " + response.asString());
    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        logger.info("Checking response status code");
        int actualStatusCode = response.getStatusCode();
        assertEquals(statusCode, actualStatusCode);
    }

    @Given("^I have a request body for a new post$")
    public void iHaveRequestBodyForNewPost() {
        requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}";

    }

    @Given("^I have a request body for an updated post$")
    public void iHaveRequestBodyForUpdatedPost() {
        requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"updated foo\",\n" +
                "  \"body\": \"updated bar\",\n" +
                "  \"userId\": 1\n" +
                "}";
    }

    @Given("^I have a request body for a partial update of a post$")
    public void iHaveRequestBodyForPartialUpdateOfPost() {
        requestBody = "{\n" +
                "  \"title\": \"updated foo\"\n" +
                "}";
    }

    @When("^I send a POST request to \"(.*)\"$")
    public void iSendPOSTRequest(String endpoint) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);
        logger.info("Request URL: " + RestAssured.baseURI + endpoint);
        logger.info("Response: " + response.asString());
    }

    @When("^I send a PUT request to \"(.*)\"$")
    public void iSendPUTRequest(String endpoint) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put(endpoint);
    }

    @When("^I send a PATCH request to \"(.*)\"$")
    public void iSendPATCHRequest(String endpoint) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .patch(endpoint);
        logger.info("Request URL: " + RestAssured.baseURI + endpoint);
        logger.info("Response: " + response.asString());
    }

    @When("^I send a DELETE request to \"(.*)\"$")
    public void iSendDELETERequest(String endpoint) {
        response = RestAssured.given()
                .delete(endpoint);
        logger.info("Request URL: " + RestAssured.baseURI + endpoint);
        logger.info("Response: " + response.asString());
    }

    @Then("^the response status code should be (\\d+)$")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        System.out.println("Response status code: " + actualStatusCode);
    }

}
