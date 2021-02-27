@nfoSmartHomePage
Feature: Home page interactions

  Scenario: NfoSmartLanding page
    Given Consumer is on nfoSmart url "nfosmart.com"
    Then I should see the following fields are shown:
      | Page Title |
    And I have the following text on the page
      | Field             | Text                                                                                                                                                     |
      | Page Title        | Welcome to NfoSmart!                                                                                                                                     |
      | MarketingDiscover | Discover a world of unlimited opportunities using Advanced QR Code Technologywith Controlled Access for the IoT. The Imagination is the only limitation! |
    And I should see the page contains the following data:
      | Field                | Text                                                                                                                                                     |
      | MarketingImagination | Discover a world of unlimited opportunities using Advanced QR Code Technologywith Controlled Access for the IoT. The Imagination is the only limitation! |
      | Contact Us           | Contact us                                                                                                                                               |
      | Marketing Adventure  | Adventure awaits!                                                                                                                                        |
      | Sign-in              | SIGN-IN                                                                                                                                                  |
