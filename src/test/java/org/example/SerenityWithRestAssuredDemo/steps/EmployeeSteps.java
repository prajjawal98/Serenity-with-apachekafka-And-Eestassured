package org.example.SerenityWithRestAssuredDemo.steps;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EmployeeSteps {

	private static final String Post_URL = "https://reqres.in/";
	public Map<String, Object> jsonMap;
	 Response response;

	@Step("Search user by id {0}")
	public void sendUser(int id) {
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.when().get(Post_URL + "api/users/" + id);

	}

	@Step("Create a new user")
	public void createUser(String fileName) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		jsonMap = objectMapper.readValue(new File(fileName), new TypeReference<>() {});
		JSONObject json = new JSONObject(jsonMap);
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.body(json.toString()).when().post(Post_URL + "api/users");
	}

	@Step("Verify the status code {0}")
	public void verifyStatusCode(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}

	@Step("Verify the user id {0}")
	public void verifyId(int expectedId) {
		SerenityRest.restAssuredThat(response -> response.body("data.id", equalTo(expectedId)));
	}

	@Step("Verify the user name {0}")
	public void verifyName(String expectedName) {

		SerenityRest.restAssuredThat(response -> response.body("name", equalTo(expectedName)));
	}

	@Step("Verify the user salary {0}")
	public void verifyJob(String expectedJob) {
		SerenityRest.restAssuredThat(response -> response.body("job", equalTo(expectedJob)));
	}

}
