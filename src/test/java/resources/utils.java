package resources;


import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.github.cdimascio.dotenv.Dotenv;


public class utils{
	
	
	public static Dotenv dotenv = Dotenv.load();
	public static RequestSpecification req;
	public static ResponseSpecification res;
	
	///general request spec
	public RequestSpecification requestSpecification() {
		req = new RequestSpecBuilder()
				.setContentType("application/json")
				.setBaseUri(dotenv.get("baseUrl"))
				.addHeader("Authorization", String.format("Bearer %s", dotenv.get("TOKEN")))
				.build();
		
		return req;

	}
	//reqSpec specific for when content type of JSON is needed 
	// (could make this an param if needed more often
	public RequestSpecification jsonRequestSpecification() {
		req = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(dotenv.get("baseUrl"))
				.addHeader("Authorization", String.format("Bearer %s", dotenv.get("TOKEN")))
				.build();
		
		return req;
	}
	
	//For Status Code 200 HTTP calls
	public ResponseSpecification successResSpec(){
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		return res;
	}
	
	//For Status Code 204 HTTP calls
	public ResponseSpecification notFoundResSpec() {
		res = new ResponseSpecBuilder()
				.expectStatusCode(204)
				.expectContentType(ContentType.JSON)
				.build();
		
		return res;
	}
	
	public ResponseSpecification noContentNotFoundResSpec() {
		res = new ResponseSpecBuilder()
				.expectStatusCode(204)
				.build();
		
		return res;
	}
	
	public ResponseSpecification noContentResSpec() {
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		return res;
	}

	//general method to convert response to JSON
	public static String getJsonPath(Response response, String key) {
		
		String responseString = response.asString();

		JsonPath js = new JsonPath(responseString); 
		
		return js.get(key);
		
	}
	
	//general method to convert response to String
	public static String getResponseBodyAsString(Response response) {
		return response.getBody().asString();
	}
}
