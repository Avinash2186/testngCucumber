@DataDrivenTesting
Feature: Amazon Data Driven validation
Scenario Outline: Different Value validations
	Given User is on Home Page
    When User searches for "<SearchString>"
    Then products related to search must appear
    
Examples:
	|SearchString|
	|Ferrari|
	|Razor|