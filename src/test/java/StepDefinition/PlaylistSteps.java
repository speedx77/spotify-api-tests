package StepDefinition;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import context.ScenarioContext;
import io.restassured.specification.ResponseSpecification;
import resources.utils;

public class PlaylistSteps extends utils {
	
	ScenarioContext scenarioContext;
	ResponseSpecification resSpec;
	
	public PlaylistSteps(ScenarioContext context) {
		scenarioContext = context;
	}
	
	@When("user calls get playlist endpoint for playlist {string}")
	public void user_calls_get_playlist(String playlistId) {
		scenarioContext.response = given().spec(requestSpecification())
				.when().get("v1/playlists/" + playlistId)
				.then().spec(successResSpec()).extract().response();
				
	}
	
	@Then("the response matches the JSON get playlist schema")
	public void response_matches_json_search_schema() {
		scenarioContext.response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/get_playlists_schema.json"));
	}
	
}