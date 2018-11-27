Feature: Add Item
  # Adding a new item to the inventory

  Scenario: add normal item
    Given: I have a new inventory
    When: I add a new item
    Then: the Item was added to the inventory