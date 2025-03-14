Feature: Purchase the order from ecommerce

Background: Buyer landed to website
    Given Buyer landing to ecommerce

Scenario: Loged Out User
    When Buyer logged to website user locked_out_user and password secret_sauce
    Then Buyer will see notification Epic sadface: Sorry, this user has been locked out.

Scenario Outline: Create Order Positive Case
    Given Buyer logged to website username <username> and password <password>
    When Buyer add product <product_name> to Cart
    And Buyer checkout product <product_name>
    And Buyer input first name <first_name>, last name <last_name>, and postal code <postal_code>
    And Buyer finish order product <product_name>
    Then Buyer will see message is displayed on confirmation page Thank you for your order!

    Examples:
    |username        | password     | product_name                 | first_name | last_name  | postal_code |
    |standard_user   | secret_sauce | Sauce Labs Bolt T-Shirt      | Novel      | Rehandhika | 63411       |
    |standard_user   | secret_sauce | Sauce Labs Onesie            | Novel      | Rehandhika | 63412       |
    |standard_user   | secret_sauce | Sauce Labs Bike Light        | Novel      | Rehandhika | 63413       |
    |standard_user   | secret_sauce | Sauce Labs Fleece Jacket     | Novel      | Rehandhika | 63414       |
    

