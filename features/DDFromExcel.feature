@searchItemsFromExcel
Feature: VErify Search Feature Using Excel File

  Scenario: Search Validation from Excel File
    Given User is on Home Page
    When User searches for Data mentioned in an Excel sheet
    Then products related to search must appear
   
