@searchItems
Feature: Search Feature on Amazon.com

  Scenario: Search Validation
    Given User is on Home Page
    When User searches for "LEGO"
    Then products related to search must appear

    
    Scenario: Search Validation with Pogo
    Given User is on Home Page
    When User searches for "Pogo"
    Then products related to search must appear
    
    Scenario: Search Validation with Pogo
    Given User is on Home Page
    When User searches for "CAPering"
    Then products related to search must appear