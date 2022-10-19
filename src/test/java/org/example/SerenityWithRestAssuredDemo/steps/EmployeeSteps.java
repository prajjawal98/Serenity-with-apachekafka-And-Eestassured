package org.example.SerenityWithRestAssuredDemo.steps;

import static org.hamcrest.Matchers.equalTo;

import net.thucydides.core.annotations.WithTag;
import org.json.JSONObject;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class EmployeeSteps {

	private static final String URL = "http://dummy.restapiexample.com/api/v1";
	public Response response;

	@Step("Search user by id {0}")
	public void sendUser(int id) {
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.when().get(URL + "/employee/" + id);

	}

	@Step("Create a new user")
	public void createUser() {

		JSONObject data = new JSONObject();

		data.put("employee_name", "Shawn Test");
		data.put("profile_image", "test.png");
		data.put("employee_age", 30);
		data.put("employee_salary", 11111);

		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.body(data.toString()).when().post(URL + "/create");

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

		SerenityRest.restAssuredThat(response -> response.body("data.employee_name", equalTo(expectedName)));
	}

	@Step("Verify the user salary {0}")
	public void verifySalary(int expectedSalary) {
		SerenityRest.restAssuredThat(response -> response.body("data.employee_salary", equalTo(expectedSalary)));
	}

	@Step("Verify the user age {0}")
	public void verifyAge(int expectedAge) {
		SerenityRest.restAssuredThat(response -> response.body("data.employee_age", equalTo(expectedAge)));
	}

	@Step("Verify the message {0}")
	public void verifyMessage(String expectedMessage) {
		SerenityRest.restAssuredThat(response -> response.body("message", equalTo(expectedMessage)));
	}

}
