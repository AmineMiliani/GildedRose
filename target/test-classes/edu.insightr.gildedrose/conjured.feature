Feature: Conjured Item
  # a conjured item quality decreases twice the normal rate

  Scenario: conjured item update
    Given I have a new inventory with a conjured item
    Then the quality of the conjured item is 16
    When I update the inventory
    Then the quality of the conjured item is 14
    When I update the inventory
    Then the quality of the conjured item is 10
