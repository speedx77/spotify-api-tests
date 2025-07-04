Feature: Validating Track Endpoints
	Description: Testing out Spotify Web API Track Related Endpoints
	
Background: User is logged in and has a token for auth
	Given i am an authorized user with a token
	
	
Scenario: GET track information
	When user calls tracks endpoint for "<id>"
	Then the API call is successful with status code 200
	And "id" in response body is "<id>"
	And "name" in response body is "<name>"

Examples:
|id                      |name         |
|7KlhGxWSB9ljGK6gEKIGhi  |VULTURES     |
|47k1Ox90yk7EicCAd3QDhc  |NICE OUT     |