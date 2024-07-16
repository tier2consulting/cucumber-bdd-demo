Feature: This is a feature file

  Scenario: Something happens in this scenario and we must test it

    Given I insert a User record with the following fields
      | username | usrnam |
      | password | psswrd |

    # TODO
    When I send a GET request to "http://localhost:8080/user/1"

