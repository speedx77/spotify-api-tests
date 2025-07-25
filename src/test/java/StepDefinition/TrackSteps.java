package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.utils;
import io.restassured.http.ContentType;

public class TrackSteps extends utils{
	
	ScenarioContext scenarioContext;
	ResponseSpecification resSpec;
	
	public TrackSteps(ScenarioContext context) {
		scenarioContext = context;
	}
	
	
	@When("user calls tracks endpoint for {string}")
	public void user_calls_tracks_endpoint(String trackId)  {
		//scenarioContext.response = given().baseUri("https://api.spotify.com").header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/tracks/" + trackId)
		//		.then().extract().response();
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		scenarioContext.response = given().spec(requestSpecification()).when().get("v1/tracks/" + trackId).then().spec(resSpec).extract().response();

	}
	
	@When("user calls tracks endpoint for {string} {string} {string}")
	public void user_calls_tracks_endpoint(String trackId1, String trackId2, String trackId3) {
		
		System.out.println(trackId1 + "," + trackId2 + "," + trackId3);
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/tracks")
				.then().extract().response();
	}
	
	@When("user calls saved tracks endpoint with {int} and {int}")
	public void user_calls_saved_tracks_endpoint(int limit, int offset) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/tracks")
				.then().extract().response();
	}
	
	@When("user calls save track endpoint with method {string} and {string} {string} {string}")
	public void user_calls_save_track_endpoint_with(String method, String trackId1, String trackId2, String trackId3) {
		
		if(method.equalsIgnoreCase("PUT")) {
			scenarioContext.response = given().baseUri("https://api.spotify.com")
					.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
					.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().put("v1/me/tracks")
					.then().extract().response();
		} else if (method.equalsIgnoreCase("DELETE")) {
			scenarioContext.response = given().baseUri("https://api.spotify.com")
					.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
					.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().delete("v1/me/tracks")
					.then().extract().response();
		}
	}
	
	@Then("user calls check saved tracks endpoint with {string} {string} {string}")
	public void user_calls_check_saved_tracks_endpoint(String trackId1, String trackId2, String trackId3) {
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/me/tracks/contains")
				.then().extract().response();
	}
	

	
	
	
	
	
}
