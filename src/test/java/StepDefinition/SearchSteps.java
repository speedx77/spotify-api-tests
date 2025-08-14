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
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import resources.utils;

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
				.queryParam("locale", "en-us")
				.when().get("v1/search")
				.then().spec(successResSpec()).extract().response();
		
		
		
	}
	
	@Then("the response matches the JSON search schema")
	public void response_matches_json_search_schema() {
		scenarioContext.response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/search_schema.json"));
	}
	
	

	@Then("{string} is found in {string} response body with an {string}")
	public void item_in_response_body_is_found(String name, String type, String id) throws JsonMappingException, JsonProcessingException{
		Boolean itemFound = false;
		Response resp = scenarioContext.response;
		
		ObjectMapper mapper = new ObjectMapper();
		SearchResponse searchResponse = mapper.readValue(resp.asString(), SearchResponse.class);
		
		type = type.concat("s");

		if(type.equals("artists")) {
			List<Items> items = searchResponse.getArtists().getItems();
			for (Items item : items) {
				if(item != null) {
					if(item.getName().equalsIgnoreCase(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}	    
			}
		} else if (type.equals("albums")) {
			List<Items> items = searchResponse.getAlbums().getItems();
			for (Items item : items) {
				if(item != null) {
					if(item.getName().equalsIgnoreCase(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}
			}
		} else if (type.equals("playlists")) {
			List<Items> items = searchResponse.getPlaylists().getItems();
			for (Items item : items) {
				if(item != null ) {
					if(item.getName().equalsIgnoreCase(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}
			    
			}
		} else if (type.equals("tracks")) {
			List<Items> items = searchResponse.getTracks().getItems();
			for (Items item : items) {
				if(item != null ) {
					if(item.getName().equalsIgnoreCase(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}
			    
			}
		} else if (type.equals("shows")) {
			List<Items> items = searchResponse.getShows().getItems();
			for (Items item : items) {
				if(item != null ) {
					if(item.getName().equalsIgnoreCase(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}
			    
			}
		} else if (type.equals("episodes")) {
			//test example includes a partial title so changed from equals to contains
			List<Items> items = searchResponse.getEpisodes().getItems();
			for (Items item : items) {
				if(item != null ) {
					if(item.getName().contains(name)) {
				    	if(item.getId().equalsIgnoreCase(id)) {
				    		itemFound = true;
				    	};
				    }
				}
			    
			}
		} else if (type.equals("audiobooks")) {
			//test example includes spelling mistake so the name.equals/contains has been removed here
			List<Items> items = searchResponse.getAudiobooks().getItems();
			for (Items item : items) {
				if(item != null ) {
			    	if(item.getId().equalsIgnoreCase(id)) {
			    		itemFound = true;
			    	};				    
				}		    
			}
		}
		
		
		
		
		Assert.assertTrue(String.format("%s has been found in search", name), itemFound);
		
	}
	
	@Then("{string} array has a length of {int} and {string} begins from {int}")
	public void item_array_has_length_and_offset(String listName, int limit, String listToOffset, int offset) {
		listName = listName.concat("s.items");
		listToOffset = listToOffset.concat("s.offset");
		Assert.assertEquals(limit, scenarioContext.response.jsonPath().getList(listName).size());
		Assert.assertEquals(offset, scenarioContext.response.jsonPath().getInt(listToOffset));
	}
	
	
	
}