Feature: This is a feature file

  Background:
    Given I clear the database

  @DirtiesContext
  Scenario Outline: Something happens in this scenario and we must test it (Check get request)

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

  @DirtiesContext
  Scenario Outline: Something happens in this scenario and we must test it (Check post request)

    When I send a POST request to "http://localhost:8080/user" with the following data
      | username | <username> |
      | password | <password> |

    Then I assert that the response body contains these values
      | username | <username> |
      | password | <password> |
      | id       | <id>       |

    Examples:
      | id | username | password    |
      | 1  | user1234 | password123 |
