package context;

import io.restassured.response.Response;

public class ScenarioContext{
	
	public String token;
	public String playlistId;
	
	//Needed for Arist Following endpoint to test the After param
	public String lastArtistId;
	
	public Response response;
	

}