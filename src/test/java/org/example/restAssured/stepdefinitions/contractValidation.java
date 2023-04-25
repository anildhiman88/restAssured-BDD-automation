package org.example.restAssured.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class contractValidation {
    private static final Logger logger = LogManager.getLogger(ApiStepDefinitions.class);

    private Response response;

    private RequestSpecification request;


    @Given("path {string}")
    public void path(String path) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        request = RestAssured.given().basePath(path);
    }

    @When("method {string}")
    public void method(String method) {
        switch (method) {
            case "GET":
                response = request.get();
                break;
            case "POST":
                response = request.post();
                break;
            case "PUT":
                response = request.put();
                break;
            case "DELETE":
                response = request.delete();
                break;
            default:
                throw new IllegalArgumentException("Invalid method: " + method);
        }
    }

    @Then("status {int}")
    public void status(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("match contract response contains {string}")
    public void match_contract_response_contains(String jsonPath) {
        response.then().assertThat().body(jsonPath + ".*", hasSize(greaterThan(0)));
    }

    @Then("validate response against schema {string}")
    public void validate_response_against_schema(String schemaPath) throws IOException, ProcessingException {
        logger.info("Validating response against schema: {}", schemaPath);

        File schemaFile = new File("src/test/resources/" + schemaPath);
        logger.debug("Schema file path: {}", schemaFile.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();

        JsonNode schemaNode = mapper.readTree(schemaFile);
        logger.debug("Schema file contents: {}", schemaNode.toString());

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schemaValidator = factory.getJsonSchema(schemaNode);

        JsonNode responseNode = mapper.readTree(response.asString());
        logger.debug("Response contents: {}", responseNode.toString());

        schemaValidator.validate(responseNode);
        logger.info("Schema validation successful");
    }
}
