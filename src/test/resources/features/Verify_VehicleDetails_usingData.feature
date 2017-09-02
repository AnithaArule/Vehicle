@ignore
Feature: Assert the vehicle details
  As a user
  for a given valid registration number
  I should be able to verify DVLA data against given vehicle details


  @vehicleDetails
Scenario Outline: Assert the Vehicle model and vehicle colour using data from feature table
    Given a user is on gov home page
    And user is on vehicle input page
    And user has valid registration number as "<registrationNumber>"
    When user selects continue
    Then user should be navigated to confirm vehicle page with registration number as "<registrationNumber>"
    Then user should be able to verify the vehicle colour to be "<colour>"
    Then user should be able to verify the vehicle model as "<make>"
    Then user should be navigated to vehicle details page

    Examples:
      | registrationNumber | colour | make     |
      | SD12 XTH           | BLACK  | VAUXHALL |