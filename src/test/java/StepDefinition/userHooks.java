package StepDefinition;

import resources.utils;
import context.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class userHooks extends utils {
	
	ScenarioContext scenarioContext;
	userSteps userStep;
	
	//Constructor initalizes instance of ScenarioContext and UserStep that takes ScenarioContext as a param
	public userHooks(ScenarioContext context) {
		scenarioContext = context;
		this.userStep = new userSteps(this.scenarioContext);
	}
	
	@Before("@FollowPlaylist")
	public void beforeFollowPlaylistScenario() {
		scenarioContext.token = dotenv.get("TOKEN");
		userStep.unfollow_playlist_id(scenarioContext.playlistId);
	}
	
	@After("@FollowPlaylist")
	public void afterFollowPlaylistScenario() {
		userStep.unfollow_playlist_id(scenarioContext.playlistId);
		userStep.check_if_user_is_following_the_playlist_id(scenarioContext.playlistId);
		userStep.the_response_body_returns("[false]");
	}
	
	@Before("@FollowArtistOrUser")
	public void beforeFollowArtistOrUser() {
		scenarioContext.token = dotenv.get("TOKEN");
		
		//you can't directly access examples in feature for beforeHooks, so hard coding these
		userStep.user_calls_the_follow_endpoint_with_item_with_body("DELETE", "user", "walkingdeavd", "12130614075", "65efe5oef0xnvwobso42pdwes");
		userStep.user_calls_the_check_following_endpoint("user", "walkingdeavd", "12130614075", "65efe5oef0xnvwobso42pdwes");
		userStep.the_response_body_returns("[false,false,false]");

		userStep.user_calls_the_follow_endpoint_with_item_with_body("DELETE", "artist", "55Aa2cqylxrFIXC767Z865", "0fA0VVWsXO9YnASrzqfmYu", "5f7VJjfbwm532GiveGC0ZK");
		userStep.user_calls_the_check_following_endpoint("artist", "55Aa2cqylxrFIXC767Z865", "0fA0VVWsXO9YnASrzqfmYu", "5f7VJjfbwm532GiveGC0ZK");
		userStep.the_response_body_returns("[false,false,false]");
	}
	
	
}