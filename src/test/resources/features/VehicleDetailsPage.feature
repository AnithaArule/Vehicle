@Functional
Feature: Assert the vehicle details
  As a user
  for a given valid registration number
  I should be able to verify DVLA data against given vehicle details

  @make
  Scenario: Assert the Vehicle model for given vehicle registration number
    Given a user is on gov home page
    And user is on vehicle input page
    And user enters valid registration number from excel with row 1 and column 2
    When user selects continue to confirm
    Then user should be able to verify the vehicle model as in row 1 column 0

  @colour
  Scenario: Assert the vehicle colour for given registration number
    Given a user is on gov home page
    And user is on vehicle input page
    And user enters valid registration number from excel with row 1 and column 2
    When user selects continue to confirm
    Then assert the vehicle colour to be as in row 1 coloumn 1

  @mot
  Scenario: Assert the Vehicle has valid MOT
    Given a user is on gov home page
    And user is on vehicle input page
    And user has valid registration number as "SD12 XTH"
    When user confirmed vehicle registration successfully
    Then vehicle had valid MOT
    