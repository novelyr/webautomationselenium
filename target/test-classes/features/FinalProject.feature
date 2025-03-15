Feature: Purchase the order from ecommerce

Background: Buyer landed to website
    Given Buyer landing to ecommerce

    Scenario Outline: Error User Stuck
    Given Buyer logged to website username <username> and password <password>
    When Buyer add product <product_name> to Cart
    And Buyer checkout product <product_name>
    And Buyer input first name <first_name>, last name <last_name>, and postal code <postal_code>
    And Buyer finish order product <product_name>
    Then Buyer still in the finish page with title Checkout: Overview


    Examples:
    |username        | password     | product_name                 | first_name | last_name  | postal_code |
    |error_user   | secret_sauce | Sauce Labs Backpack      | Novel      | Rehandhika | 63419       |

Scenario Outline: Problem User Order
    Given Buyer logged to website username <username> and password <password>
    When Buyer add product <product_name> to Cart
    And Buyer checkout product <product_name>
    And Buyer input first name <first_name>, last name <last_name>, and postal code <postal_code>
    Then Buyer will see error input notification Error: Last Name is required

    Examples:
    |username        | password     | product_name                 | first_name | last_name  | postal_code |
    |problem_user   | secret_sauce | Sauce Labs Backpack      | Novel      | Rehandhika | 63410       |


Scenario: Loged Out User
    Given Buyer logged to website username <username> and password <password>
    Then Buyer will see notification Epic sadface: Sorry, this user has been locked out.

    Examples:
    |username        | password     | product_name                 | first_name | last_name  | postal_code |
    |locked_out_user   | secret_sauce | Sauce Labs Bolt T-Shirt      | Novel      | Rehandhika | 63411       |

Scenario Outline: Create Order Standard User
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
    

