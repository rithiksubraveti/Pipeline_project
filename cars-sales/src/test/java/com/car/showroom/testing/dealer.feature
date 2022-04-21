Feature: Return Dealers

  Background:
    Given url baseUrl
    Given path '/dealers'

  Scenario: get list of all dealers
 
    When method GET
    Then status 302
 
