package StepDefinition;

import static io.restassured.RestAssured.given;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.ResponseSpecification;
import resources.utils;

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
		
		scenarioContext.response = given().spec(requestSpecification()).when().get("v1/tracks/" + trackId).then().spec(successResSpec()).extract().response();

	}
	
	@When("user calls tracks endpoint for {string} {string} {string}")
	public void user_calls_tracks_endpoint(String trackId1, String trackId2, String trackId3) {
		scenarioContext.response = given().spec(requestSpecification())
				.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
				.when().get("v1/tracks").then().spec(successResSpec()).extract().response();

	}
	
	@When("user calls saved tracks endpoint with {int} and {int}")
	public void user_calls_saved_tracks_endpoint(int limit, int offset) {	
		scenarioContext.response = given().spec(requestSpecification())
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.when().get("v1/me/tracks")
				.then().spec(successResSpec()).extract().response();
	}
	
	@When("user calls save track endpoint with method {string} and {string} {string} {string}")
	public void user_calls_save_track_endpoint_with(String method, String trackId1, String trackId2, String trackId3) {
		
		if(method.equalsIgnoreCase("PUT")) {
			scenarioContext.response = given().spec(requestSpecification())
					.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
					.when().put("v1/me/tracks")
					.then().spec(noContentResSpec()).extract().response();
			
		} else if (method.equalsIgnoreCase("DELETE")) {
			scenarioContext.response = given().spec(requestSpecification())
					.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
					.when().delete("v1/me/tracks")
					.then().spec(noContentResSpec()).extract().response();
		}
	}
	
	@Then("user calls check saved tracks endpoint with {string} {string} {string}")
	public void user_calls_check_saved_tracks_endpoint(String trackId1, String trackId2, String trackId3) {
		scenarioContext.response = given().spec(requestSpecification())
				.queryParam("ids", trackId1 + "," + trackId2 + "," + trackId3)
				.when().get("v1/me/tracks/contains")
				.then().spec(successResSpec()).extract().response();
	}
	

	
	
	
	
	
}
