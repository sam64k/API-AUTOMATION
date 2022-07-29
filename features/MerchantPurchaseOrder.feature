Feature: MPO - API Automation feature

  @TestCase1 @FT2039 @OCWMS @REGRESSION @MPO
  Scenario: TestCase1 : MPO - Generating a valid OAuth2 Token
    Given The API endpoint is "https://redft-pipe3-user-pool.auth.eu-west-1.amazoncognito.com/oauth2/token"
    And get secret "mpo.username" and "mpo.pssword"
    When User generates a valid OAuth2 cognito token
    Then validate response status code is "200"
    And User captures "access_token" from response as "mpo_token"

  @TestCase2 @FT2039 @OCWMS @REGRESSION @MPO
  Scenario: TestCase2 : MPO - API returns 200 for valid request
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/mpo/v1/pipe3/merchandise-purchase-order"
    And User includes "mpo_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "merchantPurchaseOrder"
    And I generate a valid contractNumber sarting with "50"
    And User set "callNumber" in request body with value "2"
    And User set "supplierCode" in request body with value "A054"
    And User set "facilityCode" in request body with value "3"
    And User set "deliveryDate" in request body with value "2023-05-29"
    And User set "sku" in request body with value "N7MAX"
    And User set "quantity" in request body with value "10"
    And I preserve "contractNumber" for ASN request
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "200"
    And I preserve "contractNumber" from request
    And validate response body "SendMessageResponse.SendMessageResult.MD5OfMessageAttributes" is "null"
    #Then I navigates to "https://tc3.wms.ocs.oraclecloud.com/theverygroup_test/index/"
    #And wait for "2" sec
    #And I login into ocwms portal
    #And I navigate to "Purchase Orders" TAB
    #And I search for purchase order number
    #Then I click search button
    #And I validate Po nbr
    #Then capture screen
    #And I validate "Status" is "Created"
    #And I validate "Ordered qty" is "10"
    #And I validate "Delivery Date" is "29/05/2023"

  @TestCase3 @FT2039 @OCWMS @REGRESSION @MPO
  Scenario: TestCase3 : MPO - API returns 200 for valid request
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/mpo/v1/pipe3/merchandise-purchase-order"
    And User includes "mpo_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "merchantPurchaseOrder"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "200"

  @TestCase4 @FT2039 @OCWMS @REGRESSION @MPO
  Scenario: TestCase4 : MPO - API request without token to get 401 Unauthorized
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/mpo/v1/pipe3/merchandise-purchase-order"
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "merchantPurchaseOrder"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "401"

  @TestCase5 @FT2039 @OCWMS @REGRESSION @MPO
  Scenario: TestCase5 : MPO - Verify status code 400 when supplierCode is more than 4 chars
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/mpo/v1/pipe3/merchandise-purchase-order"
    And User includes "mpo_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "merchantPurchaseOrder"
    Then User set "supplierCode" in request body with value "VERYY"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "400"

    #@TestCase06 @DBvalidations @REGRESSION
  #Scenario: TestCase11 : DB -  FIM db validations test
    #Given user connects to FIM DB
    #And I query db "select return_item_event_id,return_item_id from returns.return_item_event rie where return_item_id ='4995611';"
    #Then I perform database validations
      #| return_item_event_id | 8176315 |
      #| return_item_id       | 4995611 |
