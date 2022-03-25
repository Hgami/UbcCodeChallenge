package com.ubc.qa.automation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.hamcrest.collection.IsMapContaining;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.io.CharStreams;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GitHubGistRest {

	public static final String gistURL = "https://api.github.com/gists";
	public static final String fileContent = "UBC IRP Student QA!";
	public static String gistId = null;

	private final static Logger log = Logger.getLogger(GitHubGistRest.class.getName());

	RestAssuredRequest restRequest = new RestAssuredRequest();

	
	// public methods which can be accessed by other rest .java-class

	public void validateFileParameter() throws IOException {
		validateFileParametersForCreateGist();
	}

	public void validateUserAgentHeader() {
		validateUserAgentHeaderForCreateGist();
	}

	public void createNewGist() {
		postCreateNewGist();

	}

	public void gistByGistId() {
		getGistByGistId();
	}

	
	
	/* Validate File parameters */
	private void validateFileParametersForCreateGist() throws IOException {

		/* Check file parameters for creating gist */
		// Reading from file
		Reader reader = Files.newBufferedReader(
				Paths.get("src/main/resources/com/ubc/qa/automation/jsonPayload/payload_createGist.json"));
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode parser = objectMapper.readTree(reader);

		/* Validating values from file */

		if (parser.has("files")) {
			log.info("Files parameter is present!");
		} else {
			assertTrue("Files parameter is not present in json Payload", parser.has("files"));
		}

		if (parser.path("files").has("UBC")) {
			log.info("UBC File Name is present!");
		} else {
			assertTrue("UBC File Name should be part of Json Payload", parser.path("files").has("UBC"));
		}
	}

	/* Validate User Agent Header */
	private void validateUserAgentHeaderForCreateGist() {
		Map<String, String> mockHeaderTest = setHeaders();

		/* Test size and Map Entry Values */
		assertThat(mockHeaderTest.size(), is(2));
		assertThat(mockHeaderTest, IsMapContaining.hasEntry("User-Agent", "chrome"));
	}

	/* Create new Gist */
	private void postCreateNewGist() {

		System.out.println("-------> POST Api Call to create gist" + gistURL);
		try {

			/* Reading json file */

			Reader reader = Files.newBufferedReader(
					Paths.get("src/main/resources/com/ubc/qa/automation/jsonPayload/payload_createGist.json"));
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode parser = objectMapper.readTree(reader);
			JsonNode files = parser.findPath("files");
			JsonNode ubc = files.findPath("UBC");

			/* Updating the value of json body */

			if (parser.path("files").get("UBC").get("content").asText().equalsIgnoreCase("content_value")) {
				((ObjectNode) ubc).put("content", fileContent);
			}

			Response response = restRequest.sendJsonRequestWithBody(gistURL, parser.toString(), setHeaders());
			assertEquals("The Status Code should be 200", 201, response.statusCode());

			JsonPath jsonpath = response.jsonPath();
			gistId = jsonpath.getString("id");

			log.info("New Gist is created with gist Id as : " + gistId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* Get Newly Created Gist By Gist Id */
	@SuppressWarnings("unchecked")
	private void getGistByGistId() {
		System.out.println("-------> API call to Get Gist by gistId " + gistURL+"/"+gistId);
		Response response = restRequest.getRequestCall(gistURL+"/"+gistId, setHeaders());
		assertEquals("The Status Code should be 200", 200, response.statusCode());
		
		Map<Object, Object> fileMap =  response.getBody().jsonPath().getMap("files");
		Map<String,String> fileDetails = (Map<String, String>) fileMap.get("UBC");
		assertEquals("File has different contents! ", fileContent, fileDetails.get("content"));
		System.out.println("-------> Content Value inside UBC file is : " +fileDetails.get("content"));
	}

	
	// Below are the common private Methods 
	
	/* GetTokenDetails for Authorization */
	private String getTokenDetails() {
		Reader reader;
		String token = null;
		try {
			reader = Files
					.newBufferedReader(Paths.get("src/main/resources/com/ubc/qa/automation/Authentication/token"));
			token = CharStreams.toString(reader);
			assertNotNull("Token should not be null", token);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return token;
	}

	/* set Headers for API Calls */
	private Map<String, String> setHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + getTokenDetails());
		headers.put("User-Agent", "chrome");
		return headers;
	}

}
