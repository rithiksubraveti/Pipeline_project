Feature: Create dealer

 Background:
    * url baseUrl
    * def addDealer = '/addDealers'
    * def fetchDealer = '/dealers/'

 Scenario: Create a dealer
 
    Given path addDealer
    And request { id:4,dealer_address: 'Co.Clare', dealer_email: 'sales@jjmotors.com',dealer_name: 'JJ Motor' }
    And header Accept = 'application/json'
    When method post
    Then status 201
