package org.example.SerenityWithRestAssuredDemo.tests;

import net.thucydides.core.annotations.WithTag;
import org.example.SerenityWithRestAssuredDemo.steps.EmployeeSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@WithTag("TestAutomation")
public class EmployeesTest {

	@Steps
	EmployeeSteps employeeSteps;

	@Test
	@Title("Get User")
	public void verifyValidUser() {
		employeeSteps.sendUser(1);
		employeeSteps.verifyStatusCode(200);
		employeeSteps.verifyId(1);
		employeeSteps.verifyName("Tiger Nixon");
		employeeSteps.verifyAge(61);
		employeeSteps.verifySalary(320800);
		employeeSteps.verifyMessage("Successfully! Record has been fetched.");

	}

	@Test
	@Title("Create User")
	public void createValidUser() {

		employeeSteps.createUser("prajjawal","test.png", 21,2099);
		employeeSteps.verifyStatusCode(200);
		employeeSteps.verifyName("Shawn Test");
		employeeSteps.verifyAge(30);
		employeeSteps.verifySalary(11111);
		employeeSteps.verifyMessage("Successfully! Record has been added.");

	}
}
