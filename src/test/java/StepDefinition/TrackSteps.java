package StepDefinition;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import resources.utils;

public class TrackSteps extends utils{
	
	ScenarioContext scenarioContext;
	
	public TrackSteps(ScenarioContext context) {
		scenarioContext = context;
	}
	
	
	@When("user calls tracks endpoint for {string}")
	public void user_calls_tracks_endpoint(String trackId) {
		scenarioContext.response = given().baseUri("https://api.spotify.com").header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/tracks/" + trackId)
				.then().extract().response();
		

	}
	
	@When("user calls tracks endpoint for {string} {string} {string}")
	public void user_calls_tracks_endpoint(String trackId1, String trackId2, String trackId3) {
		
		System.out.println(trackId1 + "," + trackId2 + "," + trackId3);
		scenarioContext.response = given().baseUri("https://api.spotify.com")
				.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
				.header("Authorization", String.format("Bearer %s", scenarioContext.token) ).when().get("v1/tracks")
				.then().extract().response();
	}
	
	
}
