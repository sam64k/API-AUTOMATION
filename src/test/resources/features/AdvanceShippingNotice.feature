Feature: ASN - API Automation feature

  @TestCase6 @FT2048 @FT-3871 @REGRESSION
  Scenario: TestCase6 : ASN -  Generating a valid OAuth2 Token
    Given The API endpoint is "https://redft-pipe3-user-pool.auth.eu-west-1.amazoncognito.com/oauth2/token"
    And get secret "asn.username" and "asn.password"
    When User generates a valid OAuth2 cognito token
    Then validate response status code is "200"
    And User captures "access_token" from response as "asn_token"

  @TestCase7 @FT2048 @OCWMS @REGRESSION @ASN
  Scenario: TestCase7 : ASN - API returns 200 for valid request
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/asn/v1/pipe3/advanced-shipment-notice"
    And User includes "asn_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "AdvanceShippingNotice"
    And I set the request body params
      | Key              | Value      |
      | bookingReference |   03056101 |
      | supplierCode     | A054       |
      | facilityCode     |          3 |
      | typeCode         | DEL        |
      | deliveryDate     | 2022-09-29 |
      | quantity         |          5 |
      | sku              | N7MAX      |
    #And I set purchaseOrderId same as "contractNumber"
    And fetch "contractNumber" from previous request and set as "purchaseOrderId"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "200"
    And validate response body "SendMessageResponse.SendMessageResult.MD5OfMessageAttributes" is "null"

  @TestCase8 @FT2048 @OCWMS @REGRESSION @ASN
  Scenario: TestCase8 : ASN -  Send PUT request without token to get 401 Unauthorized
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/asn/v1/pipe3/advanced-shipment-notice"
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "AdvanceShippingNotice"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "401"

  @TestCase9 @FT2048 @OCWMS @REGRESSION @ASN
  Scenario: TestCase9 : ASN - Verify status code 400 when supplierCode is more than 4 chars
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/asn/v1/pipe3/advanced-shipment-notice"
    And User includes "asn_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "AdvanceShippingNotice"
    Then User set "typeCode" in request body with value "VERYy"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "400"
  #@ignore @TestCase10 @FT2048 @ASN @OCWMSportal @REGRESSION 
  #Scenario: TestCase10 : ASN - Verify ASN is initiated on PO
    #Given I navigates to "https://tc3.wms.ocs.oraclecloud.com/theverygroup_test/index/"
    #And I login into ocwms portal
    #And I navigate to "ASN" TAB
    #And I search for purchase order number on ASN screen
    #Then capture screen
    #Then I click search button
    #And I validate "Status" is "In Transit"
    #Then capture screen
    #And I validate "Shipped Qty" is "5"
    #And I close browser session

  @TestCase10 @FT-3871
  Scenario: TestCase10 : ASN - Update ASN api to url-encode the payload
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/asn/v1/pipe3/advanced-shipment-notice"
    And User includes "asn_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "AdvanceShippingNotice"
    And User set "bookingReference" in request body with value "03056102"
    And User set "supplierCode" in request body with value "A054"
    And User set "facility_code" in request body with value "3"
    And User set "typeCode" in request body with value "DEL"
    And User set "deliveryDate" in request body with value "2022-09-29"
    And User set "quantity" in request body with value "6"
    And User set "sku" in request body with value "N7MAX &amp;"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "200"

  @TestCase11 @FT-3871
  Scenario: TestCase11 : ASN - Update ASN api to url-encode the payload
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/asn/v1/pipe3/advanced-shipment-notice"
    And User includes "asn_token" in request header
    And User include header params "Content-Type" as "application/json"
    Then read payload from file "AdvanceShippingNotice"
    And User set "bookingReference" in request body with value "03056102"
    And User set "supplierCode" in request body with value "A054"
    And User set "facility_code" in request body with value "3"
    And User set "typeCode" in request body with value "DEL"
    And User set "deliveryDate" in request body with value "2022-09-29"
    And User set "quantity" in request body with value "6"
    And User set "sku" in request body with value "Beckham & Posh ©@ ® æ"
    Then User build request payload
    When user performs HTTP "PUT" request
    Then validate response status code is "200"