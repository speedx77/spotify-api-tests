package StepDefinition;

import resources.utils;
import context.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class TrackHooks extends utils {
	
	ScenarioContext scenarioContext;
	TrackSteps trackStep;
	userSteps userStep;
	
	//Constructor initalizes instance of ScenarioContext and UserStep that takes ScenarioContext as a param
	public TrackHooks(ScenarioContext context) {
		scenarioContext = context;
		this.trackStep = new TrackSteps(this.scenarioContext);
		this.userStep = new userSteps(this.scenarioContext);
	}
	
	@After("@SaveTracks")
	public void afterSaveTracks(){
		scenarioContext.token = dotenv.get("TOKEN");
		
		trackStep.user_calls_save_track_endpoint_with("DELETE", "7FEJ7OIuMbcjG5hcyg3sh3", "5Ve1Y2fzaoer0zG0FIZcwL", "5RvED3NaFaXNDEhoJh5Cv2");
		trackStep.user_calls_check_saved_tracks_endpoint("7FEJ7OIuMbcjG5hcyg3sh3", "5Ve1Y2fzaoer0zG0FIZcwL", "5RvED3NaFaXNDEhoJh5Cv2");
		userStep.the_response_body_returns("[false,false,false]");
	}
	
	@Before("@DeleteTracks")
	public void beforeDeleteTracks() {
		scenarioContext.token = dotenv.get("TOKEN");
		
		trackStep.user_calls_save_track_endpoint_with("PUT", "7FEJ7OIuMbcjG5hcyg3sh3", "5Ve1Y2fzaoer0zG0FIZcwL", "5RvED3NaFaXNDEhoJh5Cv2");
		trackStep.user_calls_check_saved_tracks_endpoint("7FEJ7OIuMbcjG5hcyg3sh3", "5Ve1Y2fzaoer0zG0FIZcwL", "5RvED3NaFaXNDEhoJh5Cv2");
		userStep.the_response_body_returns("[true,true,true]");
	}
	
	
	
}