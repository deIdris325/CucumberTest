Feature: Test: Hello Cucumber World!

  Scenario Outline: Open cucumber.io website
    Given call the website "http://www.cucumber.io"
    And verify as Text "Docs"
  Examples: Testdata
    | word   |
    | Docs   |
    | Blog   |
    | Ecents |

