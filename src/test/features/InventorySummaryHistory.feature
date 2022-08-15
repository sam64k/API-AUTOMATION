Feature: IHT - API Automation feature

  @TestCase001 @IHT @REGRESSION
  Scenario: TestCase6 : IHT -  Inventory Summary History
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/iht/v1/pipe3/stockMovement"
    And get secret "IHT.user" and "IHT.password"
    And User include header params "Content-Type" as "application/xml"
    Then read payload from file "InventorySummaryHistory"
    And User set "facility_code" in request body with value "003"
    And User set "activity_code" in request body with value "19"
    And User set "shipment_type" in request body with value "STD"
    And User set "lock_code" in request body with value "PM"
    And User set "reason_code" in request body with value "01"
    And User set "po_nbr" in request body with value "123456"
    And User set "shipment_nbr" in request body with value "IDC_567890_1234"
    And User set "orig_qty" in request body with value "10"
    And User set "adj_qty" in request body with value "5"
    And User set "item_code" in request body with value "KR3G9"
    And User set "ref_code_1" in request body with value "OLD"
    And User set "ref_value_1" in request body with value "PK"
    And User set "ref_code_2" in request body with value "NEW"
    And User set "ref_value_2" in request body with value "PM"
    And User set "po_nbr" in request body with value "123456"
    And User build request payload
    Then user performs HTTP POST request with basic auth
    Then validate response status code is "200"