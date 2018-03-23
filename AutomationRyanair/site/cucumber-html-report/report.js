$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/feature/DeclinedPaymentTest.feature");
formatter.feature({
  "name": "Error message on declined payment",
  "description": "  Description: This feature is to test error message of declined payment error after submiting the order",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "",
  "description": "    Making booking and receiving error message when card is declined",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I make a booking of a ticket for adults and child",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I pay for booking with card",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I should get payment declined message",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});