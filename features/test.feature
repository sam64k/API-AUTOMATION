
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @jenkins
  Scenario: Title of your scenario
    And I access username from jenkinsfile
 

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |

  @tag1
  Scenario: Generating a valid OAuth2 Token
    Then User set "contractNumber" in request body with value "101010"
    Then User set "supplierCode" in request body with value "SAM1"
    Then User set "facilityCode" in request body with value "3"
    Then User build request payload

  #And validate response body "token" is "value"
  @tag2
  Scenario: Merchant purchase order API returns 200 for valid request
    Then User build request payload

  #Then validate response status code is "200"
  # And validate response body "key" is "value"
  @Sanitycode2
  Scenario: this is sanity test
    And this is step1

  @Sanitycode
  Scenario: this is sanity test
    And User forms request header
      | Key4 | Value4 |

  @parameter
  Scenario: this is sanity test
    And I set the request body params
      | bookingReference | 03051101 |
      | supplierCode     | M354     |
      | facilityCode     |        3 |
      | typeCode         | PRET     |
