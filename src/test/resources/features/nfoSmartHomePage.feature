@nfoSmartHomePage
  Feature: Home page interactions
  Scenario: Register
    Given Consumer is on nfoSmart url "localhost"
    When a customer see the home page
    Then he should be able to register with nfoSmart
