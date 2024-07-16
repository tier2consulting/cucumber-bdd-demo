Feature: This is a feature file

  Scenario Outline: Something happens in this scenario and we must test it

    Given I insert a User record with the following fields
      | username | <username> |
      | password | <password> |

    When I send a GET request to "http://localhost:8080/user/<id>"

    Then I assert that the response body contains these values
      | username | <username> |
      | password | <password> |
      | id       | <id>       |

  Examples:
    | id | username | password |
    | 1  | someUser | somePass |
