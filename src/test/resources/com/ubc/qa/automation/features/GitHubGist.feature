@ubc.github.gist.released

Feature: GitHub Api calls for UBC Code challenge
  
  Scenario: VALIDATE , POST  and GET data for newly created Gist
  	And Validate File Parameter
  	And Validate User Agent Header
    And Create new Gist
   	And Get newly created Gist by GistId 
  	