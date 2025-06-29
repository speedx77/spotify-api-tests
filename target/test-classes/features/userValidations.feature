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
	
Scenario: Get users top items
	When user calls top "<item>" endpoint with "<timeRange>" <limit> <offset>
	Then the API call is successful with status code 200
	#And "item" array exists
	#And "item" array is of length "<limit>" and begins from "<offset>"
	
Examples:
	|item   |timeRange   |limit  |offset  |
	|tracks |short_term  |10     |0       |
	|tracks |medium_term |20     |5       |
	|tracks |long_term   |30     |10      |