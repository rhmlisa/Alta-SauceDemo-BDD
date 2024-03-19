@Purchase
Feature: Purchase
  As a user, i want to be able to login, add items to cart, manage cart, and checkout

  Scenario: User login, add items to cart, remove last item, and checkout
    Given User open the web sauce demo
    When  User input "standard_user" as userName and "secret_sauce" as password and click login
    Then User should be directed to the dashboard page
    When User sort product by "Price (low to high)"
    And User click add to cart button "Sauce Labs Onesie"
    And User click add to cart button "Sauce Labs Bike Light"
    And User click cart icon to see the selected items
    Then User should be directed to the shopping cart page
    When User Remove "Sauce Labs Bike Light" as the last added item
    And User click button checkout
    Then User should be directed to the checkout information page
    When User input "Taylor" as firstName and "Swift" as lastName and "3487" as postalCode
    And User click button continue
    Then User should be directed to the checkout overview
    When User click finish
    Then User should be directed to the checkout complete page

