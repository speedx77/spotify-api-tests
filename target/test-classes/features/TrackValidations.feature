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

Scenario: GET multiple track information
	When user calls tracks endpoint for "<id1>" "<id2>" "<id3>"
	Then the API call is successful with status code 200
	And "tracks[0].id" in response body is "<id1>"
	And "tracks[1].id" in response body is "<id2>"
	And "tracks[2].id" in response body is "<id3>"
	And "tracks[0].name" in response body is "<name1>"
	And "tracks[1].name" in response body is "<name2>"
	And "tracks[2].name" in response body is "<name3>"
	
Examples:
|id1                     |id2                        |id3                          |name1             |name2              |name3            |
|7KlhGxWSB9ljGK6gEKIGhi  |47k1Ox90yk7EicCAd3QDhc     |3niLgyM9yJTaZMpeXjjSSk       |VULTURES          |NICE OUT           |Given Up On Me   |       

