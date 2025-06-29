package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class utils{
	
public static String getJsonPath(Response response, String key) {
		
		String responseString = response.asString();

		JsonPath js = new JsonPath(responseString); 
		
		return js.get(key);
		
	}
}