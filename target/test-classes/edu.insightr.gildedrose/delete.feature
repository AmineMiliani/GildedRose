Feature: Delete Added Item
  # Deleting a newly added item

  Scenario: delete normal added item
    Given: I have a new inventory
    When: I add a new item
    Then: the item was added to the inventory
    When: I delete an item
    Then: the item was deleted from the inventory