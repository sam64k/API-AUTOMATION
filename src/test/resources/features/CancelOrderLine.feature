Feature: Cancel Order Line - api

  @FT-3871
  Scenario: Validating Cancel Order API - tO url encode the payload
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/iht/v1/pipe3/cancelOrderLine"
    And get secret "CancelOrderLine.user" and "CancelOrderLine.password"
    Then read payload from file "CancelOrderLine"
    And User include header params "Content-Type" as "application/xml"
    And set current "TimeStamp" "yyyy-MM-dd'T'hh:mm:ss"
    And User set "activity_code" in request body with value "27"
    And User set "item_code" in request body with value "29NBA"
    And User set "item_description" in request body with value "Stella Round Marble Coffee &amp;Table"
    And User build request payload
    Then user performs HTTP POST request with basic auth
    Then validate response status code is "200"

  @FT-3871
  Scenario: Validating Cancel Order API - tO url encode the payload
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/iht/v1/pipe3/cancelOrderLine"
    And get secret "CancelOrderLine.user" and "CancelOrderLine.password"
    Then read payload from file "CancelOrderLine"
    And User include header params "Content-Type" as "application/xml"
    And set current "TimeStamp" "yyyy-MM-dd'T'hh:mm:ss"
    And User set "activity_code" in request body with value "27"
    And User set "item_code" in request body with value "29NBA"
    And User set "item_description" in request body with value "Beckham & Posh © é"
    And User build request payload
    Then user performs HTTP POST request with basic auth
    Then validate response status code is "200"

  @FT-3871
  Scenario: Validating Cancel Order API - tO url encode the payload
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/iht/v1/pipe3/cancelOrderLine"
    And get secret "CancelOrderLine.user" and "CancelOrderLine.password"
    Then read payload from file "CancelOrderLine"
    And User include header params "Content-Type" as "application/xml"
    And set current "TimeStamp" "yyyy-MM-dd'T'hh:mm:ss"
    And User set "activity_code" in request body with value "27"
    And User set "item_code" in request body with value "6DLW9"
    And User set "item_description" in request body with value "Little Acòrns @ ® æ"
    And User build request payload
    Then user performs HTTP POST request with basic auth
    Then validate response status code is "200"