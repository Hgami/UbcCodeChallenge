package com.ubc.qa.automation.runner.stepdefs;

import java.io.IOException;

import com.ubc.qa.automation.GitHubGistRest;

import cucumber.api.java.en.And;

public class GitHubGistStepDefs {
	
	GitHubGistRest gitHubGistRest = new GitHubGistRest();
	
	
	@And("Validate File Parameter")
	public void Validate_File_Parameter() throws IOException {
		gitHubGistRest.validateFileParameter();
	}
	
	@And("Validate User Agent Header")
	public void Validate_User_Agent_Header() throws IOException {
		gitHubGistRest.validateUserAgentHeader();
	}

	@And("Create new Gist")
	public void Create_new_Gist() {
		gitHubGistRest.createNewGist();
	}
	
	@And("Get newly created Gist by GistId")
	public void Get_newly_created_Gist_by_GistId() {
		gitHubGistRest.gistByGistId();
	}
	
}

