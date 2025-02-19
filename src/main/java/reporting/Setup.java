package reporting;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Setup implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		String reportFileName = ExtentReportManager.getReportNameWithTimeStamp();
		String fullReportPath = System.getProperty("user.dir") + "/reports/" + reportFileName;
		ExtentReportManager.createInstance(fullReportPath, "Test API Automation Report", "Test Execution Report");
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReportManager.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		//String testName = result.getTestClass().getName() + " - " + result.getMethod().getMethodName();
		//ExtentReportManager.createTest(testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.logFailureDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replace(",", "<br>");
		String formattedTrace = "<details>\r\n" + "<summary>Click Here To See Exception Logs</summary>\r\n" + ""
				+ stackTrace + "\r\n" + "</details>";
		ExtentLogger.logExceptionDetails(formattedTrace);
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
