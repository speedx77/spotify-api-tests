package StepDefinition;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.ResponseSpecification;

import resources.utils;

import pojo.Artists;
import pojo.Followers;
import pojo.Image;
import pojo.Items;
import pojo.SearchResponse;

// TODO Make POJO more dynamic to fit other search terms

public class SearchSteps extends utils {
	
	ScenarioContext scenarioContext;
	ResponseSpecification resSpec;
	
	public SearchSteps(ScenarioContext context) {
		scenarioContext = context;
	}
	
	
	@When("user calls search endpoint for {string} of type {string} with {int} and {int}")
	public void user_calls_search_endpoint(String searchTerm, String type, Integer limit, Integer offset) {
		scenarioContext.response = given().spec(requestSpecification())
				.queryParam("q", searchTerm)
				.queryParam("type", type)
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.when().get("v1/search")
				.then().spec(successResSpec()).extract().response();
		
		
		
	}
	

	@Then("{string} is found in {string} response body with an {string}")
	public void item_in_response_body_is_found(String artistName, String type, String id) throws JsonMappingException, JsonProcessingException{
		Boolean artistFound = false;
		Response resp = scenarioContext.response;
		
		ObjectMapper mapper = new ObjectMapper();
		SearchResponse searchResponse = mapper.readValue(resp.asString(), SearchResponse.class);
		
		List<Items> artists = searchResponse.getArtists().getItems();
		for (Items artist : artists) {

		    if(artist.getName().equalsIgnoreCase(artistName)) {
		    	if(artist.getId().equalsIgnoreCase(id)) {
		    		artistFound = true;
		    	};
		    }
		}
		
		Assert.assertTrue(String.format("%s has been found in search", artistName), artistFound);
		
	}
	
	@Then("{string} array has a length of {int} and {string} begins from {int}")
	public void item_array_has_length_and_offset(String listName, int limit, String listToOffset, int offset) {
		Assert.assertEquals(limit, scenarioContext.response.jsonPath().getList(listName).size());
		Assert.assertEquals(offset, scenarioContext.response.jsonPath().getInt(listToOffset));
	}
	
	
}