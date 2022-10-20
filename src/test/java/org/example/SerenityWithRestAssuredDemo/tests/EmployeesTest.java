package org.example.SerenityWithRestAssuredDemo.tests;

import net.thucydides.core.annotations.WithTag;
import org.example.SerenityWithRestAssuredDemo.ProducerAndConsumer.Producer;
import org.example.SerenityWithRestAssuredDemo.steps.EmployeeSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import java.io.IOException;

@RunWith(SerenityRunner.class)
@WithTag("TestAutomation")
public class EmployeesTest {

	@Steps
	EmployeeSteps employeeSteps;

	@Test
	@Title("Get User")
	public void verifyValidUser() {
		employeeSteps.sendUser(2);
		employeeSteps.verifyStatusCode(200);
		employeeSteps.verifyId(2);

	}

	@Test
	@Title("Create User")
	public void createValidUser() throws IOException, InterruptedException {

		employeeSteps.createUser("test.json");
		employeeSteps.verifyStatusCode(201);
		employeeSteps.verifyName(employeeSteps.jsonMap.get("name").toString());
		employeeSteps.verifyJob(employeeSteps.jsonMap.get("job").toString());
		Producer.ProduceEvent("tradeEvent.json");



	}

}
