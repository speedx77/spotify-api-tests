package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import io.restassured.path.json.JsonPath;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import resources.utils;
import context.ScenarioContext;


public class userSteps extends utils {
	
	ScenarioContext scenarioContext;
	
	public userSteps(ScenarioContext context) {
		scenarioContext = context;
	}	
	
	
	@Given("i am an authorized user with a token")
	public void i_am_an_authorized_user_with_a_token(){
		//fetch token from .env file in the root directory, replace with updated token when running test suite
		scenarioContext.token = dotenv.get("TOKEN");
	}
	
	
	@When("user calls me endpoint")
	public void user_calls_me_endpoint() {
		//When user calls /v1/me endpoint
		scenarioContext.response = given().baseUri("https://api.spotify.com").header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me")
				.then().extract().response();
		
	}
	
	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer status) {
		//assert that call was successful
		Assert.assertEquals( (int) status, scenarioContext.response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue){
		//print out response
		//String responseString = response.asPrettyString();
		///System.out.println(responseString);
		
		//If call was successful then check display name, change desired display name in examples in the userValidations feature file
		Assert.assertEquals(expectedValue, getJsonPath(scenarioContext.response, keyValue));
	}
	
	
	
	@When("user calls top {string} endpoint with {string} {int} {int}")
	public void call_top_item_endpoint(String itemType, String timeRange, Integer limit, Integer offset) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("time_range", timeRange)
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/top/" + itemType)
				.then().extract().response();
	

		
		
		//String responseString = response.asPrettyString();
		//System.out.println(responseString);
	}

	
	@Then("{string} array has a length of {int} and begins from {int}")
	public void array_exists(String expectedArrayName, int limit, int offset) {
		
		//System.out.println(response.jsonPath().getList(expectedArrayName).size());
		Assert.assertEquals(limit, scenarioContext.response.jsonPath().getList(expectedArrayName).size());
		Assert.assertEquals(offset, scenarioContext.response.jsonPath().getInt("offset"));
		//Assert.assertEquals(getJsonPath(response, expectedArrayName), expectedArrayName);
		
		
	}
	
	
	@When("user calls user profile endpoint for user {string}")
	public void user_calls_user_profile_endpoint_for_user(String userId) {
		
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/users/" + userId)
				.then().extract().response();
		

	}
	
	@When("user calls the follow playlist endpoint for {string}")
	public void user_calls_the_follow_playlist_endpoint(String playlistId) {
		scenarioContext.playlistId = playlistId;
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().put("v1/playlists/" + playlistId + "/followers")
				.then().extract().response();
		

	}
	
	@Then("check if user is following the playlist id: {string}")
	public void check_if_user_is_following_the_playlist_id(String playlistId) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/playlists/" + playlistId + "/followers/contains")
				.then().extract().response();
		

	}
	
	@Then("the response body returns {string}")
	public void the_response_body_returns(String expectedValue) {
		Assert.assertEquals(expectedValue, getResponseBodyAsString(scenarioContext.response));
	}
	
	
	@Then("unfollow playlist id: {string}")
	public void unfollow_playlist_id(String playlistId) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().delete("v1/playlists/" + playlistId + "/followers")
				.then().extract().response();
		

	}
	
	@When("user calls following endpoint with a limit of {int}")
	public void user_calls_following_endpoint_with_a_limit(int limit) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("type", "artist")
				.queryParam("limit", limit)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/following")
				.then().extract().response();
		
		scenarioContext.lastArtistId = scenarioContext.response.jsonPath().get("cursors.after");
	}
	
	@Then("{string} array has a length of {int}")
	public void array_has_a_length_of(String expectedArrayName, int limit){
		//Assert.assertEquals(expectedArrayName, getJsonPath(response, "artists.items"));
		Assert.assertEquals(limit, scenarioContext.response.jsonPath().getList("artists.items").size());
	}
	
	@Then("the user calls the following endpoint again with the after param set to the last artist with {int}")
	public void user_calls_following_endpoint_again__with_the_after_param(int limit) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("type", "artist")
				.queryParam("limit", limit)
				.queryParam("after", scenarioContext.lastArtistId)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/following")
				.then().extract().response();
		

	}
	
	
	@When("user calls the follow endpoint with method {string} item type {string} and a body of {string} {string} {string}")
	public void user_calls_the_follow_endpoint_with_item_with_body(String method, String itemType, String id1, String id2, String id3) {
		
		List<String> ids = Arrays.asList(id1 , id2, id3);
		Map<String, Object> requestBody = new HashMap<>();
		
		requestBody.put("ids", ids);
		
		//String[] ids = {id1, id2, id3};
		//System.out.println(ids);
		if(method.equals("PUT")) {
			scenarioContext.response = given().baseUri("https://api.spotify.com")
					.queryParam("type", itemType)
					.header("Authorization", String.format("Bearer %s", scenarioContext.token))
					.contentType(JSON)
					.body(requestBody)
					//.log().body()
					.when().put("v1/me/following")
					.then().extract().response();	


		} else if(method.equals("DELETE")) {
			scenarioContext.response = given().baseUri("https://api.spotify.com")
					.queryParam("type", itemType)
					.header("Authorization", String.format("Bearer %s", scenarioContext.token))
					.contentType(JSON)
					.body(requestBody)
					//.log().body()
					.when().delete("v1/me/following")
					.then().extract().response();	
			
		}
		
	}
	
	@Then("the user calls the check following endpoint with item type {string} with ids {string} {string} {string}")
	public void user_calls_the_check_following_endpoint(String itemType, String id1, String id2, String id3 ) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("type", itemType)
				.queryParam("ids", id1 + "," + id2 + "," + id3 + ",")
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/following/contains")
				.then().extract().response();	
		

	}
	
	
}







































