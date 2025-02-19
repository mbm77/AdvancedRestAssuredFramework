package reporting;

import java.util.List;
import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.http.Header;

public class ExtentLogger {

	private ExtentLogger() {

	}

	public static void logPassDetails(String log) {
		ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logFailureDetails(String log) {
		ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logExceptionDetails(String log) {
		ExtentManager.getExtentTest().fail(log);
	}

	public static void logInfoDetails(String log) {
		ExtentManager.getExtentTest().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}

	public static void logWarningDetails(String log) {
		ExtentManager.getExtentTest().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}

	public static void logJson(String Json) {
		ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(Json, CodeLanguage.JSON));
	}

	public static void logHeaders(List<Header> headerList) {
		String[][] headersArray = headerList.stream()
				.map(header -> new String[] { header.getName(), header.getValue() }).toArray(String[][]::new);
		ExtentManager.getExtentTest().info(MarkupHelper.createTable(headersArray));
	}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

}
