Feature: OBLPN Shipping Info (Get Label)

  @get-label @FT-2782 @lf-api-cuke-test
  Scenario: OBLPN Shipping Info Get Label returns 200 success
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/osi/v1/pipe3/oblpn-get-label"
    And get secret "OBLPNGetlabel.user" and "OBLPNGetlabel.password"
    Then read payload from file "OBLPNShippingInfo"
    And User include header params "Content-Type" as "application/xml"
    And User set "facility_code" in request body with value "003"
    And User set "oblpn" in request body with value "OBLPN0000008001"
    And User set "order_nbr" in request body with value "2019011710"
    And User set "order_seq_nbr" in request body with value "2019011710"
    And User set "order_dtl_cust_short_text_2" in request body with value "1000005"
    And User build request payload
    Then user performs HTTP POST request on oblpn-getLabel API
    Then validate response status code is "200"


  @get-label @FT-2782
  Scenario: OBLPN Shipping Info Get Label returns 403 for invalid credentials
    Given The API endpoint is "https://nonprd-api.landf.theverygroup.com/fsl/osi/v1/pipe3/oblpn-get-label"
    And get secret "invalid.username" and "invalid.password"
    Then read payload from file "OBLPNShippingInfo"
    And User include header params "Content-Type" as "application/xml"
    And User set "facility_code" in request body with value "003"
    And User set "oblpn" in request body with value "OBLPN0000008001"
    And User set "order_nbr" in request body with value "2019011710"
    And User set "order_seq_nbr" in request body with value "2019011710"
    And User set "order_dtl_cust_short_text_2" in request body with value "1000005"
    And User build request payload
    Then user performs HTTP POST request on oblpn-getLabel API
    Then validate response status code is "403"