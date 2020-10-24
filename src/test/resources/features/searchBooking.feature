@BookingTest
Feature: Booking.com Search Feature
  Scenario: Enter Destination and search for results
  Given A Consumer is on the Booking.com landing page
    When The Consumer enter checkin dates for "New York" with "3" for work "yes"
    Then Consumer should see results for the destination of "New York"
