#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template      
Feature: Validating User Profile Endpoints
Description: Testing out Spotify Web API User Endpoints: https://developer.spotify.com/documentation/web-api

Background: User is logged in and has a token for auth
	Given i am an authorized user with a token

Scenario: GET user current profile information
	   When user calls me endpoint
	   Then the API call is successful with status code 200
	   And "display_name" in response body is "<displayName>"
	   
Examples:
	|displayName  |
	|speedx77     |
	
@SpecificUser
Scenario: Get user profile of specific user
	When user calls user profile endpoint for user "<userId>"
	Then the API call is successful with status code 200
	And "display_name" in response body is "<displayName>"
	And "type" in response body is "<userType>"
	And "uri" in response body is "<uri>"
	And "id" in response body is "<id>"
	And "external_urls.spotify" in response body is "<externalUrl>"
	
Examples:
	|userId                     |displayName   |userType    |uri                                      |id                             |externalUrl                                               |
	|4bbflibvj0k3xne6p7cqc6h3d  |Jean          |user        |spotify:user:4bbflibvj0k3xne6p7cqc6h3d   |4bbflibvj0k3xne6p7cqc6h3d      |https://open.spotify.com/user/4bbflibvj0k3xne6p7cqc6h3d   |

@Items
Scenario: Get users top items
	When user calls top "<item>" endpoint with "<timeRange>" <limit> <offset>
	Then the API call is successful with status code 200
	And "items" array has a length of <limit> and begins from <offset>
	
Examples:
	|item    |timeRange   |limit  |offset  |
	|tracks  |short_term  |10     |0       |
	|tracks  |medium_term |20     |5       |
	|tracks  |long_term   |30     |10      |
	|artists |short_term  |10     |0       |
	|artists |medium_term |20     |5       |
	|artists |long_term   |30     |10      |
	
@FollowedArtists
Scenario: Get users followed artists
	When user calls following endpoint with a limit of <limit>
	Then the API call is successful with status code 200
	And "items" array has a length of <limit>
	And the user calls the following endpoint again with the after param set to the last artist with <limit>
	And the API call is successful with status code 200
	
Examples:
	|limit   |
	|10      |
	
		
#hooks to make sure playlist is unfollowed before/after?
@FollowPlaylist
Scenario: Follow a playlist and check if user is following the playlist
	When user calls the follow playlist endpoint for "<playlistId>"
	Then the API call is successful with status code 200
	And check if user is following the playlist id: "<playlistId>"
	And the API call is successful with status code 200
	And the response body returns "[true]"
	
Examples:
	|playlistId              |
	|68dJSKcRlsUB4i3acWCXEk  |
	
	
@FollowArtistOrUser
Scenario: Follow/Unfollow an Artists or User and check if that user is following them
	When user calls the follow endpoint with method "PUT" item type "<itemType>" and a body of "<id1>" "<id2>" "<id3>"
	Then the API call is successful with status code 204
	And the user calls the check following endpoint with item type "<itemType>" with ids "<id1>" "<id2>" "<id3>"
	And the API call is successful with status code 200
	And the response body returns "[true,true,true]"
	And user calls the follow endpoint with method "DELETE" item type "<itemType>" and a body of "<id1>" "<id2>" "<id3>"
	And the API call is successful with status code 204
	And the user calls the check following endpoint with item type "<itemType>" with ids "<id1>" "<id2>" "<id3>"
	And the API call is successful with status code 200
	And the response body returns "[false,false,false]"
	
	
	#unfollow done with hooks
	
Examples:
	|itemType     |id1                      |id2                       |id3                        |
	|user         |walkingdeavd             |12130614075               |65efe5oef0xnvwobso42pdwes  |
	|artist       |55Aa2cqylxrFIXC767Z865   |0fA0VVWsXO9YnASrzqfmYu    |5f7VJjfbwm532GiveGC0ZK     |