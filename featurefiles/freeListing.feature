Feature: Getting the error message for subitting wrong phone number

	Scenario: Getting error message
		Given User is on the free listing page
		When User enters a wrong phone number
		Then User gets an error message