Feature: Issue Form submitting
  Scenario: Scenario_test_add_issue
    Given Open Mantis Site
      #And Login as "admin"
      And Login with data below
        | login    | administrator |
        | password | 21750         |
      And Open Report Issue Page
    When Fill issue Form with "issuetest"
      And Submit Issue Form
      And Logout
      And Login as "user"
      And Open View Issue Page
    Then Result contains "issuetest"

