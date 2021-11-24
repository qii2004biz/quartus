@nfoSmartHomePage
Feature: Home page interactions

  Scenario: NfoSmartLanding page
    Given Consumer is on nfoSmart url "nfosmart.com"
    Then I should see the following fields are shown:
      | Page Title |
    And I have the following text on the page
      | Field             | Text                                                                           |
      | Page Title        | NfoSmart                                                                       |
      | MarketingDiscover | Discover a world of unlimited opportunities using Advanced QR Code Technology. |
    And I should see the page contains the following data:
      | Field                | Text                                                                             |
      | About                | About                                                                            |
      | Contact Us           | Contact Us                                                                       |
      | LogIn                | Log In  Sign Up                                                                   |
      | MarketingDiscover    | Discover a world of unlimited opportunities using Advanced QR Code Technology.   |
      | Contact-less Payment | Contact-less Payment                                                             |
      | QR Code              | QR Code payments for business and individual use.                                |
      | NfoPay               | NfoPay                                                                           |
      | Scan and pay         | Scan and pay.                                                                    |
      | Send videos          | Want to send videos or money in your greeting cards?Now you can using QR Codes! |
      | Try it now           | TRY IT NOW                                                                       |
      | Utilize our QR Code  | Utilize our QR Code Technology for self-serve kiosks and payments!               |
