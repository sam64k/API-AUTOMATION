Feature: Inventory Summary - API Automation feature

  @TestCase10 @InventorySummary @REGRESSION
  Scenario: TestCase001 - InventorySummary Test response code is 200
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/ivs/v1/pipe3/inventory"
    And User include header params "Content-Type" as "application/xml"
    And get secret "InventorySummary.user" and "InventorySummary.password"
    Then read payload from file "InventorySummary"
    And User set "facility_code" in request body with value "003"
    And User set "item_alternate_code" in request body with value "TEMP"
    And User set "item_part_a" in request body with value "TEMP"
    And User build request payload
    Then user performs HTTP "POST" request on InventorySummey API
    Then validate response status code is "200"

  @TestCase11 @InventorySummary @REGRESSION
  Scenario: TestCase002 - InventorySummary Test response code is 403 for invalid credentials
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/ivs/v1/pipe3/inventory"
    And User include header params "Content-Type" as "application/xml"
    And get secret "invalid.username" and "invalid.password"
    Then read payload from file "InventorySummary"
    And User set "facility_code" in request body with value "003"
    And User set "item_alternate_code" in request body with value "TEMP"
    And User set "item_part_a" in request body with value "TEMP"
    And User build request payload
    Then user performs HTTP "POST" request on InventorySummey API
    Then validate response status code is "403"
