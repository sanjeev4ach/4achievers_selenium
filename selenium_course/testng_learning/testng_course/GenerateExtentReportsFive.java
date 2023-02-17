package testng_course;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateExtentReportsFive {

	// https://www.extentreports.com/docs/versions/5/java/spark-reporter.html
	// https://github.com/extent-framework/extentreports-java/tree/master/config

	/**
	 * Default TestNG reports are not advanced They contain less info
	 * 
	 * 1. Change Viewing order 2. Remove Some menu 3. HighLight a particular log
	 * line 4. List Of String --> How can i log in the report 5. Map<String,String>
	 * --> log in the report
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void setUp() throws InterruptedException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
		extent.attachReporter(spark);

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("4achievers Automation Learning");
		spark.config().setReportName("Extent Report 5 Demo");
		spark.config().setTimelineEnabled(true);

		ExtentTest test = extent.createTest("First Test").assignAuthor("Rohit").assignCategory("Regression")
				.assignCategory("Smoke").assignDevice("chrome"); // create a test node in the report
		test.pass(MarkupHelper.createLabel("First Test in Extent Report", ExtentColor.GREEN));
		test.info("URL is loaded");
		test.info("Value Entered");

		String jsoncode = "{\r\n" + "  \"jobAssignment\" : {\r\n" + "    \"baseWageRates\" : [ {\r\n"
				+ "      \"hourlyRate\" : \"8\",\r\n" + "      \"effectiveDate\" : \"2022-04-13\"\r\n" + "    } ],\r\n"
				+ "    \"primaryLaborAccounts\" : [ {\r\n"
				+ "      \"organizationPath\" : \"LocationAPIOrg_20220712044217/India/Metropolitan Plant/Assembly/Assembler I\",\r\n"
				+ "      \"effectiveDate\" : \"2022-04-13\"\r\n" + "    } ],\r\n"
				+ "    \"jobAssignmentDetails\" : {\r\n" + "      \"payRuleEffectiveDate\" : \"2018-01-01\",\r\n"
				+ "      \"payRuleName\" : \"Agency Bi-Weekly\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"gdapAssignments\" : [ {\r\n" + "    \"role\" : \"Manager_role\",\r\n"
				+ "    \"defaultSwitch\" : true,\r\n" + "    \"originalEffectiveDate\" : \"2017-01-01\",\r\n"
				+ "    \"originalExpirationDate\" : \"2099-01-01\",\r\n" + "    \"gdapName\" : \"All Access\",\r\n"
				+ "    \"effectiveDate\" : \"2017-01-01\",\r\n" + "    \"expirationDate\" : \"2099-01-01\"\r\n"
				+ "  } ],\r\n" + "  \"user\" : {\r\n" + "    \"userAccount\" : {\r\n"
				+ "      \"userPassword\" : \"Pr0mensi0ns@UKG\",\r\n" + "      \"passwordUpdateflag\" : false,\r\n"
				+ "      \"logonProfileName\" : \"Default\",\r\n" + "      \"userName\" : \"UN385714\"\r\n"
				+ "    }\r\n" + "  },\r\n" + "  \"personInformation\" : {\r\n" + "    \"expectedHoursList\" : [ {\r\n"
				+ "      \"quantity\" : 80,\r\n" + "      \"timePeriodTypeName\" : \"Pay Period\"\r\n" + "    } ],\r\n"
				+ "    \"emailAddresses\" : [ {\r\n" + "      \"address\" : \"person.import.licensing@kronos.com\",\r\n"
				+ "      \"contactTypeName\" : \"work\"\r\n" + "    } ],\r\n" + "    \"person\" : {\r\n"
				+ "      \"firstName\" : \"Akansh\",\r\n" + "      \"lastName\" : \"Garg385714\",\r\n"
				+ "      \"hireDate\" : \"2017-01-01\",\r\n" + "      \"middleInitial\" : \"K\",\r\n"
				+ "      \"romanizedFullName\" : \"Akansh Garg385714\",\r\n"
				+ "      \"personNumber\" : \"PN385714\",\r\n"
				+ "      \"phoneticFullName\" : \"Akansh Garg385714\",\r\n" + "      \"shortName\" : \"PN\",\r\n"
				+ "      \"birthDate\" : \"2017-01-01\"\r\n" + "    },\r\n"
				+ "    \"personAuthenticationTypes\" : [ {\r\n" + "      \"authenticationTypeName\" : \"Basic\",\r\n"
				+ "      \"activeFlag\" : true\r\n" + "    } ],\r\n" + "    \"personLicenseTypes\" : [ {\r\n"
				+ "      \"licenseTypeName\" : \"Manager\",\r\n" + "      \"activeFlag\" : true\r\n" + "    }, {\r\n"
				+ "      \"licenseTypeName\" : \"Hourly Timekeeping\",\r\n" + "      \"activeFlag\" : true\r\n"
				+ "    } ],\r\n" + "    \"badgeAssignments\" : [ {\r\n" + "      \"badgeNumber\" : \"1385714\",\r\n"
				+ "      \"expirationDate\" : \"2099-12-31T10:15:30\"\r\n" + "    } ],\r\n"
				+ "    \"userAccountStatusList\" : [ {\r\n" + "      \"userAccountStatusName\" : \"Active\",\r\n"
				+ "      \"effectiveDate\" : \"2017-01-01\"\r\n" + "    } ],\r\n" + "    \"accessAssignment\" : {\r\n"
				+ "      \"managerPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"transferEmployeeFlag\" : false,\r\n"
				+ "      \"professionalPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"managerWorkRuleName\" : \"All Work Rules\",\r\n"
				+ "      \"approveovertimeflag\" : false,\r\n"
				+ "      \"professionalWorkRuleName\" : \"All Work Rules\",\r\n"
				+ "      \"shiftCodeName\" : \"All Shift Templates\",\r\n"
				+ "      \"managerViewPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"preferenceProfileName\" : \"Default\",\r\n"
				+ "      \"accessProfileName\" : \"HR Admin\",\r\n"
				+ "      \"delegateProfileName\" : \"Senior Managers Delegate Profile\",\r\n"
				+ "      \"notificationProfileName\" : \"All\",\r\n"
				+ "      \"schedulePatternName\" : \"All Pattern Templates\",\r\n"
				+ "      \"timeEntryTypeName\" : \"Hourly View\"\r\n" + "    },\r\n"
				+ "    \"postalAddresses\" : [ {\r\n" + "      \"country\" : \"IND\",\r\n"
				+ "      \"city\" : \"Noida\",\r\n" + "      \"street\" : \"Sector 121\",\r\n"
				+ "      \"postalCode\" : \"201301\",\r\n" + "      \"state\" : \"Uttar Pradesh\",\r\n"
				+ "      \"contactTypeName\" : \"Home\"\r\n" + "    } ],\r\n" + "    \"employmentStatusList\" : [ {\r\n"
				+ "      \"employmentStatusName\" : \"Active\",\r\n" + "      \"effectiveDate\" : \"2017-01-01\"\r\n"
				+ "    } ]\r\n" + "  }\r\n" + "}";
		test.info(jsoncode);
		test.info("<pre>" + jsoncode.replace("\n", "<br>") + "</pre>");
		test.info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON));

		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("Test", "Rohit");
		tempMap.put("Test1", "Sanjeev");

		test.pass(MarkupHelper.createOrderedList(tempMap).getMarkup());
		test.fail(MarkupHelper.createLabel("failed to locate element", ExtentColor.RED));
		Thread.sleep(5000);

		ExtentTest test1 = extent.createTest("Second Test").assignAuthor("Sanjeev").assignAuthor("Rohit")
				.assignCategory("Smoke"); // create a test node in the report
		Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }).forEach(test1::pass);
		
		test1.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }))
				.getMarkup());
		test1.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }))
				.getMarkup());

		test1.pass(MarkupHelper.createLabel("First Test in Extent Report", ExtentColor.GREEN));
		test1.info("URL is loaded");
		test1.info("Value Entered");

		String xmlcode = "<endDate>2022-05-16</endDate>" + "<ComputeFunctions><shortname>Actual_HRS</shortname>"
				+ "<FunctionType>Metric</FunctionType></ComputeFunctions>";
		test1.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));

		extent.flush(); // unless you call this method, your report will not be written with logs
		try {
			Desktop.getDesktop().browse(new File("index.html").toURI());
		} catch (IOException e) {}

	}

	@Test(enabled = false)
	public void extentTest() throws InterruptedException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
		// ExtentSparkReporter spark = new
		// ExtentSparkReporter("index.html").viewConfigurer().viewOrder().as(new
		// ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
		ExtentSparkReporter failedspark = new ExtentSparkReporter("failed-tests-index.html").filter().statusFilter()
				.as(new Status[] { Status.FAIL }).apply();
		extent.attachReporter(spark, failedspark);

		// report config changes with Code

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("4achievers Automation Learning");
		spark.config().setReportName("Extent Report 5 Demo");
		spark.config().setTimelineEnabled(true);

		// report config changes with extent config xml file

		/*
		 * File configxml = new File("extent-config.xml"); try {
		 * spark.loadXMLConfig(configxml); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		// report config changes with extent config json file
		/*
		 * File configjson = new File("extent-config.json"); try {
		 * spark.loadJSONConfig(configjson); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		ExtentTest test = extent.createTest("First Test").assignAuthor("Rohit").assignCategory("Regression")
				.assignCategory("Smoke").assignDevice("chrome"); // create a test node in the report
		test.pass("First Test in Extent Report");
		test.info("URL is loaded");
		test.info("Value Entered");

		test.fail(MarkupHelper.createLabel("failed to locate element", ExtentColor.RED));

		String jsoncode = "{\r\n" + "  \"jobAssignment\" : {\r\n" + "    \"baseWageRates\" : [ {\r\n"
				+ "      \"hourlyRate\" : \"8\",\r\n" + "      \"effectiveDate\" : \"2022-04-13\"\r\n" + "    } ],\r\n"
				+ "    \"primaryLaborAccounts\" : [ {\r\n"
				+ "      \"organizationPath\" : \"LocationAPIOrg_20220712044217/India/Metropolitan Plant/Assembly/Assembler I\",\r\n"
				+ "      \"effectiveDate\" : \"2022-04-13\"\r\n" + "    } ],\r\n"
				+ "    \"jobAssignmentDetails\" : {\r\n" + "      \"payRuleEffectiveDate\" : \"2018-01-01\",\r\n"
				+ "      \"payRuleName\" : \"Agency Bi-Weekly\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"gdapAssignments\" : [ {\r\n" + "    \"role\" : \"Manager_role\",\r\n"
				+ "    \"defaultSwitch\" : true,\r\n" + "    \"originalEffectiveDate\" : \"2017-01-01\",\r\n"
				+ "    \"originalExpirationDate\" : \"2099-01-01\",\r\n" + "    \"gdapName\" : \"All Access\",\r\n"
				+ "    \"effectiveDate\" : \"2017-01-01\",\r\n" + "    \"expirationDate\" : \"2099-01-01\"\r\n"
				+ "  } ],\r\n" + "  \"user\" : {\r\n" + "    \"userAccount\" : {\r\n"
				+ "      \"userPassword\" : \"Pr0mensi0ns@UKG\",\r\n" + "      \"passwordUpdateflag\" : false,\r\n"
				+ "      \"logonProfileName\" : \"Default\",\r\n" + "      \"userName\" : \"UN385714\"\r\n"
				+ "    }\r\n" + "  },\r\n" + "  \"personInformation\" : {\r\n" + "    \"expectedHoursList\" : [ {\r\n"
				+ "      \"quantity\" : 80,\r\n" + "      \"timePeriodTypeName\" : \"Pay Period\"\r\n" + "    } ],\r\n"
				+ "    \"emailAddresses\" : [ {\r\n" + "      \"address\" : \"person.import.licensing@kronos.com\",\r\n"
				+ "      \"contactTypeName\" : \"work\"\r\n" + "    } ],\r\n" + "    \"person\" : {\r\n"
				+ "      \"firstName\" : \"Akansh\",\r\n" + "      \"lastName\" : \"Garg385714\",\r\n"
				+ "      \"hireDate\" : \"2017-01-01\",\r\n" + "      \"middleInitial\" : \"K\",\r\n"
				+ "      \"romanizedFullName\" : \"Akansh Garg385714\",\r\n"
				+ "      \"personNumber\" : \"PN385714\",\r\n"
				+ "      \"phoneticFullName\" : \"Akansh Garg385714\",\r\n" + "      \"shortName\" : \"PN\",\r\n"
				+ "      \"birthDate\" : \"2017-01-01\"\r\n" + "    },\r\n"
				+ "    \"personAuthenticationTypes\" : [ {\r\n" + "      \"authenticationTypeName\" : \"Basic\",\r\n"
				+ "      \"activeFlag\" : true\r\n" + "    } ],\r\n" + "    \"personLicenseTypes\" : [ {\r\n"
				+ "      \"licenseTypeName\" : \"Manager\",\r\n" + "      \"activeFlag\" : true\r\n" + "    }, {\r\n"
				+ "      \"licenseTypeName\" : \"Hourly Timekeeping\",\r\n" + "      \"activeFlag\" : true\r\n"
				+ "    } ],\r\n" + "    \"badgeAssignments\" : [ {\r\n" + "      \"badgeNumber\" : \"1385714\",\r\n"
				+ "      \"expirationDate\" : \"2099-12-31T10:15:30\"\r\n" + "    } ],\r\n"
				+ "    \"userAccountStatusList\" : [ {\r\n" + "      \"userAccountStatusName\" : \"Active\",\r\n"
				+ "      \"effectiveDate\" : \"2017-01-01\"\r\n" + "    } ],\r\n" + "    \"accessAssignment\" : {\r\n"
				+ "      \"managerPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"transferEmployeeFlag\" : false,\r\n"
				+ "      \"professionalPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"managerWorkRuleName\" : \"All Work Rules\",\r\n"
				+ "      \"approveovertimeflag\" : false,\r\n"
				+ "      \"professionalWorkRuleName\" : \"All Work Rules\",\r\n"
				+ "      \"shiftCodeName\" : \"All Shift Templates\",\r\n"
				+ "      \"managerViewPayCodeName\" : \"All Pay Codes\",\r\n"
				+ "      \"preferenceProfileName\" : \"Default\",\r\n"
				+ "      \"accessProfileName\" : \"HR Admin\",\r\n"
				+ "      \"delegateProfileName\" : \"Senior Managers Delegate Profile\",\r\n"
				+ "      \"notificationProfileName\" : \"All\",\r\n"
				+ "      \"schedulePatternName\" : \"All Pattern Templates\",\r\n"
				+ "      \"timeEntryTypeName\" : \"Hourly View\"\r\n" + "    },\r\n"
				+ "    \"postalAddresses\" : [ {\r\n" + "      \"country\" : \"IND\",\r\n"
				+ "      \"city\" : \"Noida\",\r\n" + "      \"street\" : \"Sector 121\",\r\n"
				+ "      \"postalCode\" : \"201301\",\r\n" + "      \"state\" : \"Uttar Pradesh\",\r\n"
				+ "      \"contactTypeName\" : \"Home\"\r\n" + "    } ],\r\n" + "    \"employmentStatusList\" : [ {\r\n"
				+ "      \"employmentStatusName\" : \"Active\",\r\n" + "      \"effectiveDate\" : \"2017-01-01\"\r\n"
				+ "    } ]\r\n" + "  }\r\n" + "}";
		test.info(jsoncode);
		test.info("<pre>" + jsoncode.replace("\n", "<br>") + "</pre>");
		test.info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON));
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("Test", "Rohit");
		tempMap.put("Test1", "Sanjeev");

		test.pass(MarkupHelper.createOrderedList(tempMap).getMarkup());

		Thread.sleep(5000);

		// removed debug, fatal, error log levels

		ExtentTest test1 = extent.createTest("Second Test").assignAuthor("Sanjeev").assignAuthor("Rohit")
				.assignCategory("Smoke"); // create a test node in the report
		Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }).forEach(test1::pass);
		test1.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }))
				.getMarkup());
		test1.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[] { "Rohit", "Sanjeev", "4Achievers" }))
				.getMarkup());

		test1.pass(MarkupHelper.createLabel("First Test in Extent Report", ExtentColor.GREEN));
		test1.info("URL is loaded");
		test1.info("Value Entered");

		String xmlcode = "<endDate>2022-05-16</endDate>" + "<ComputeFunctions><shortname>Actual_HRS</shortname>"
				+ "<FunctionType>Metric</FunctionType></ComputeFunctions>";
		test1.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));

		extent.flush(); // unless you call this method, your report will not be written with logs
		try {
			Desktop.getDesktop().browse(new File("index.html").toURI());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
