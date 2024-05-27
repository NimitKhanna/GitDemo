Feature: Purchase the order from E Commerce Website
I want to use this template for my feature file

Background: 
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive test of submitting the order
Given Logged in with username <name> and password <password>
When I add product <productname> to the cart
And Checkout <productname> and submit the order
Then "THANKYOU FOR THE ORDER." message displays on ConfirmationPage

Examples:
    |     name              | password     | productname     |
    |nimitkhanna8@gmail.com | Selenium@123 | ZARA COAT 3     |
