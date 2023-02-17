package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import testng_course.BaseClass;

public class ListenersDemo implements ITestListener {

//	https://www.browserstack.com/guide/testng-listeners#:~:text=Listeners%20are%20TestNG%20annotations%20that,as%20interfaces%20in%20the%20code.
	/**
	 * it's an interface. Listeners are components in TESTNG that listens(keep a
	 * track) of test execution and helps to perform actions at mutliple stages of
	 * test execution
	 */

	/**
	 * IAnnotationTransformer => work with any annotation e.g - Test, BeforeTest,
	 * etc IAnnotationTransformer2 => work with DataProvider annotation
	 * IInvokedMethodListener IMethodInterceptor => if the method is already invoked
	 * but you want to perform any action IReporter => end of the testing if you
	 * want to generate report
	 */

	// write takescreenshot method in case of failure of a test

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("************* Test '" + result.getName() + "' started *********");
		BaseClass.createTest(result.getName().toString());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("************* Test '" + result.getName() + "' Successful *********");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("************* Test '" + result.getName() + "' failed *********");
		BaseClass.test.fail(result.getThrowable().getMessage(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(BaseClass.takeScreenShot(result.getName().toString() + ".png")).build());
		BaseClass.test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		BaseClass.test.skip(MarkupHelper.createLabel(result.getName() + " Test case Skipped", ExtentColor.YELLOW));
		BaseClass.test.skip(result.getSkipCausedBy().toString());
	}
}
