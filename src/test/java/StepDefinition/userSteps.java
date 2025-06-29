package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.path.json.JsonPath;
import static org.junit.Assert.*;

import resources.utils;


public class userSteps extends utils {
	
	Dotenv dotenv = Dotenv.load();
	String token;
	Response response;
	
	@Given("i am an authorized user with a token")
	public void i_am_an_authorized_user_with_a_token(){
		//fetch token from .env file in the root directory, replace with updated token when running test suite
		token = dotenv.get("TOKEN");
	}
	
	
	@When("user calls me endpoint")
	public void user_calls_me_endpoint() {
		//When user calls /v1/me endpoint
		response = given().baseUri("https://api.spotify.com").header("Authorization", String.format("Bearer %s", token) ).when().get("v1/me")
				.then().extract().response();	
	}
	
	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer status) {
		//assert that call was successful
		Assert.assertEquals(response.getStatusCode(), (int) status);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue){
		//print out response
		//String responseString = response.asPrettyString();
		///System.out.println(responseString);
		
		//If call was successful then check display name, change desired display name in examples in the userValidations feature file
		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	
	
	@When("user calls top {string} endpoint with {string} {int} {int}")
	public void call_top_item_endpoint(String itemType, String timeRange, Integer limit, Integer offset) {
		response = given().baseUri("https://api.spotify.com")
				.queryParam("time_range", timeRange)
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.header("Authorization", String.format("Bearer %s", token) ).when().get("v1/me/top/" + itemType)
				.then().extract().response();
		
		
		//String responseString = response.asPrettyString();
		//System.out.println(responseString);
	}

	
}