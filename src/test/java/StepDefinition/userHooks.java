package StepDefinition;

import resources.utils;
import io.cucumber.java.After;

public class userHooks extends utils {
	
	userSteps userStep = new userSteps();
	
	@After("@FollowPlaylist")
	public void afterFollowPlaylistScenario() {
		String playlistId = userStep.storedPlaylistId;
		
		userStep.unfollow_playlist_id(playlistId);
		userStep.check_if_user_is_following_the_playlist_id(playlistId);
		userStep.the_response_body_returns("[False]");
	}
	
}