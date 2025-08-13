Feature: Validating Search Endpoint
	Description: Testing out Spotify Web API Search Endpoints
	
	
Background: User is logged in and has a token for auth
	Given i am an authorized user with a token

Scenario: Verify JSON schema for search endpoint
	When user calls search endpoint for "<artist>" of type "<type>" with <limit> and <offset>
	Then the API call is successful with status code 200
	And the response matches the JSON search schema
	
Examples:
	|artist             |type         |limit        |offset      |
	|Phoebe Bridgers    |artist       |10           |0           |
	
	
Scenario: User searches for an artist, album, track
	When user calls search endpoint for "<searchTerm>" of type "<type>" with <limit> and <offset>
	Then the API call is successful with status code 200
	And "<searchTerm>" is found in "<type>" response body with an "<id>"
	And "<type>" array has a length of <limit> and "<type>" begins from <offset>
	
Examples:
	|searchTerm                                           |type         |id                       |limit        |offset      |
	|Nirvana                                              |artist       |7dIxU1XgxBIa3KJAWzaFAC   |10           |0           |	
	|Drake                                                |artist       |3TVXtAsR1Inumwj472S9r4   |1            |0           |	
	|Nothing Was The Same (Deluxe)                        |album        |5mz0mJxb80gqJIcRf9LGHJ   |5            |0           |	
	|rage, rage against the dying of the light            |playlist     |2jVocjSdaKiJ96Z3MZB2Uv   |10           |0           |
	|I Can and I Will                                     |track        |3veBdtlaLO4NH8ZkGyriU4   |5            |0           |	


