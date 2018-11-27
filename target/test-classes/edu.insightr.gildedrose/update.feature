Feature: Normal Item
  # a normal item quality decreases at normal rate

  Scenario: normal item update
    Given I have a new inventory with a normal item
    Then the quality of the normal item is 20
    When I update the inventory
    Then the quality of the normal item is 19