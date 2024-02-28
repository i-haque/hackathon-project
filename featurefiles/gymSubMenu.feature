Feature: Get all the sub menus

	Scenario: Get all the sub menus
		Given User is on the home page
		When User selects the location and clicks on the gym icon
		Then User needs to collect all the sub menus
		And User closes the browser
		