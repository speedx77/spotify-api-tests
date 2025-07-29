Feature: Validating Search Endpoint
	Description: Testing out Spotify Web API Search Endpoints
	
	
Background: User is logged in and has a token for auth
	Given i am an authorized user with a token

Scenario: User searches for an artist
	When user calls search endpoint for "<artist>" of type "<type>" with <limit> and <offset>
	Then the API call is successful with status code 200
	And "<artist>" is found in "artists" response body with an "<id>"
	And "artists.items" array has a length of <limit> and "artists.offset" begins from <offset>
	
Examples:
	|artist             |type      |id                       |limit        |offset      |
	|Nirvana            |artist    |7dIxU1XgxBIa3KJAWzaFAC   |10           |0           |
	|Drake              |artist    |3TVXtAsR1Inumwj472S9r4   |1            |0           |
	
	
Scenario: User searchs for an album
	When user calls search endpoint for "<album>" of type "<type>" with <limit> and <offset>
	Then the API call is successful with status code 200
	And "<album>" is found in "albums" response body with an "<id>"
	And "albums.items" array has a length of <limit> and "albums.offset" begins from <offset>
	
	
Examples:
	|album                               |type      |id                       |limit        |offset      |
	|Nothing Was The Same (Deluxe)       |album     |5mz0mJxb80gqJIcRf9LGHJ   |10           |0           |
