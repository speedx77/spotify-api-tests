Feature: Validating Playlists Endpoint
	Description: Testing out Spotify Web API Playlists Endpoints
	
Scenario: Verify JSON schema for get playlist endpoint
	When user calls get playlist endpoint for playlist "<playlist_id>"
	Then the API call is successful with status code 200
	And the response matches the JSON get playlist schema
	
Examples:
	|playlist_id                 |
	|55KihE2DTArc4GFgfthwEq      |
	|5bCr9O9ktvaoHkgDbcVPGe      |
	
Scenario: Verify get playlist endpoint returns the correct playlist
	When user calls get playlist endpoint for playlist "<playlist_id>"
	Then the API call is successful with status code 200
	And "id" in response body is "<playlist_id>"
	And "name" in response body is "<name>"
	
Examples:
	|playlist_id                 |name                         |
	|6lsqB3LAg0fMR8jlOryFPN      |Neither Out Far Nor In Deep  |
	|6tCUT9QYdNLoRg2p0DYR30      |The Hollow Men V             |
	