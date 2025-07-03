package resources;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import io.github.cdimascio.dotenv.Dotenv;


public class utils{
	
	
	public static Dotenv dotenv = Dotenv.load();

	
	public static String getJsonPath(Response response, String key) {
		
		String responseString = response.asString();

		JsonPath js = new JsonPath(responseString); 
		
		return js.get(key);
		
	}
	
	public static String getResponseBodyAsString(Response response) {
		return response.getBody().asString();
	}
}
