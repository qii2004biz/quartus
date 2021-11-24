Feature: Context holds global variables
  Scenario Outline: Add multiple variables to context
    Given I add "<value>" to context
    Then I should be able to get the context "<value>"
    Examples:
    |value|
    |value1|
    |value2|

