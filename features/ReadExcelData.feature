@readExcelData
Feature: Read DAta

  Scenario: Read DAta From Excel
    
    When User Read DAta From Excel Based on Run as Yes and Test Id
    Then Read Data from Excel for other Search Terms

    
    Scenario: Search Validation with Cricket
    Given User is on Home Page
    When User searches for "Cricket"
    Then products related to search must appear
    
    Scenario: Search Validation with Plastic
    Given User is on Home Page
    When User searches for "Plastic"
    Then products related to search must appear