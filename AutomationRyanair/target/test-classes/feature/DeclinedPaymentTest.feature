Feature: Error message on declined payment
  Description: This feature is to test error message of declined payment error after submiting the order

  Scenario: 
    Making booking and receiving error message when card is declined

    Given I make a booking of a ticket for adults and child
    When I pay for booking with card
    Then I should get payment declined message
