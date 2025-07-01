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
	
#hooks to make sure playlist is unfollowed before?
@FollowPlaylist
Scenario: Follow a playlist and check if user is following the playlist
	When user calls the follow playlist endpoint
	Then the API call is successful with status code 200
	And check if "<userId"> is following the playlist id: "<playlistId">