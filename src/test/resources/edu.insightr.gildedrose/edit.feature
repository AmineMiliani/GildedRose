Feature: Edit Item
  # editing name, sellin and quality of an item

  Scenario: edit item
    Given: I have a new inventory
    When: I edit the name of the item with the identifier 1 to be mao
    When: I edit the sell in value of the item with identifier 1 to be 5
    When: I edit the quality of the item with identifier 1 to be 5
    Then: the item 1 has name: mao, sellin 1, quality 1