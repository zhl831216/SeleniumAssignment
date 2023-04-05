Feature: Test SVTPlay


  Scenario: Verify the title of the homepage SVT-Play, SVT logo and names of main menus.
    Given SVT Play is available
    When user visit the homepage
    Then the title should be "SVT Play"
    And the logo of the website is visible
    And the names of main menu should be "START", "PROGRAM" and "KANALER"

  Scenario: Verify the link “Tillgänglighet i SVT Play” is visible and have the right text on the link plus the title of the page
    Then the link “Tillgänglighet i SVT Play” should be visible
    And the right text on the link should be "Tillgänglighet i SVT Play"
    When user clicks the link Tillgänglighet i SVT Play
    Then the title of the page Tillgänglighet i SVT Play should be "Så arbetar SVT med tillgänglighet"

  Scenario: Verify the total number of categories in the page Program
    When user visit the homepage
    And user clicks the link Program in the main menu
    Then the total number of categories in the page Program should be 17

  Scenario: Verify the total number of series in the page Vårens Serier
    When user visit the homepage
    And user clicks the link Vårens Serier
    Then the total number of series in the page should be 24

  Scenario: Verify the page Djur&Natur
    When user visit the homepage
    And user clicks the link Djur&Natur
    And clicks the tab Program A-Ö in the page Djur&Natur
    And clicks the checkbox Kan ses utomlands in the tab Program A-Ö
    Then the total number of programs which can be seen from abroad should be 22
    And the programs are sorted in the alphabetic sequence

  Scenario: Verify the device names in SVT-Play
    When user visit the homepage
    Then The device names which displayed in the page should be "Dator","Mobil & platta" and "TV"

  Scenario: Verify the text under the title in the page Barn
    When user visit the homepage
    And user clicks the link Barn in the home page
    Then the text which is under the title should be "Se populära barnprogram och barnserier, tecknade program och klassiska barnprogram som Pippi Långstrump, Bolibompa, Labyrint och Greta gris."

  Scenario:Verify the title in the Kanaler page
    When user visit the homepage
    And User clicks Kanaler in the main menu
    Then The title of page Kanaler should be "På SVT just nu"

  Scenario: Verify the result of search function by giving "Agenda"
    When user visit the homepage
    And user input the word Agenda
    Then the program which is visible on the top of list should be "Agenda"

  Scenario: Verify the result of search function by giving "Pistvakt"
    When user visit the homepage
    And user input the word Pistvakt
    Then the program name of the search result is "Pistvakt"
    When user visit the page Pistvakt
    And user clicks season2 of Pistvakt
    Then the total number of the series should be 6
    And the name of the episode5 in season2 should be "5. Personalfestan"



