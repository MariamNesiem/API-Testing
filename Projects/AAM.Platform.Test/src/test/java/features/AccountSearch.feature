Feature: Account Search Test

#    Background: Get Access Token
#        When User login with valid credentials 
#        Then Access Token is generated successfully

     
Scenario Outline: Verify that Account Data is returned Succesfully
	Given Search Criteria with "<accountName>"
	When User calls Account Search API with "POST" http request
	Then The API call got success with status code 200
Examples:
|accountName|
|acc|
