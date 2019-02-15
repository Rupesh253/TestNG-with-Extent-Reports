package package2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;
import com.gargoylesoftware.htmlunit.javascript.host.event.MediaStreamEvent;

public class CommonClass {
	public String someScreenshotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Windows.PNG";
	String someVideoPath = System.getProperty("user.dir") + "\\src\\test\\resources\\star_trails.3gp";

	private static ExtentReports extentReports;
	ThreadLocal<ExtentTest> parentThread = new ThreadLocal<ExtentTest>();
	public ThreadLocal<ExtentTest> childThread = new ThreadLocal<ExtentTest>();

	@Parameters({ "oEM", "model", "build" })
	@BeforeSuite
	public void beforeSuite_in_CommonClass_in_package2(String oEM, String model, String build)
			throws UnknownHostException {
		System.out.println("beforeSuite_in_CommonClass_in_package2()");
		extentReports = new ExtentManager().getInstance(
				System.getProperty("user.dir") + "\\src\\test\\java\\package2\\extent.html", oEM, model, build);
	}

	@BeforeTest
	public void beforeTest_in_CommonClass_in_package2() {
		System.out.println("\t beforeTest_in_CommonClass_in_package2()");
	}

	@BeforeClass
	public void beforeClass_in_CommonClass_in_package2() {
		System.out.println("\t \t beforeClass_in_CommonClass_in_package2()");
		ExtentTest parentTest = extentReports.createTest(getClass().getName());
		parentThread.set(parentTest);
	}

	@BeforeMethod
	public void beforeMethod_in_CommonClass_in_package2(Method method) {
		System.out.println("\t \t \t beforeMethod_in_CommonClass_in_package2()");
		ExtentTest childTest = parentThread.get().createNode(method.getName());
		childThread.set(childTest);

	}

	@AfterMethod
	public void afterMethod_in_CommonClass_in_package2(ITestResult testResult) throws Throwable {
		System.out.println("\t \t \t afterMethod_in_CommonClass_in_package2()");
		if (testResult.getStatus() == ITestResult.SUCCESS) {
			childThread.get().pass("Test Passed");
		} else if (testResult.getStatus() == ITestResult.FAILURE) {
			childThread.get().fail("Test Failed with " + testResult.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(someScreenshotPath).build());
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			childThread.get().skip("Test Skipped with " + testResult.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(someScreenshotPath).build());
		}
	}

	@AfterClass
	public void afterClass_in_CommonClass_in_package2() {
		System.out.println("\t \t afterClass_in_CommonClass_in_package2()");
	}

	@AfterTest
	public void aftertest_in_CommonClass_in_package2() {
		System.out.println("\t afterTest_in_CommonClass_in_package2()");
	}

	@AfterSuite
	public void afterSuite_in_CommonClass_in_package2() {
		System.out.println("afterSuite_in_CommonClass_in_package2()");
		extentReports.flush();
	}

	public static class ExtentManager {

		private static ExtentReports extentReports;

		public ExtentReports getInstance(String fileName, String oEM, String model, String build)
				throws UnknownHostException {
			if (extentReports == null)
				createInstance(fileName, oEM, model, build);
			return extentReports;
		}

		public ExtentReports createInstance(String fileName, String oEM, String model, String build)
				throws UnknownHostException {
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("TestNG Extent Report");
			htmlReporter.config().setEncoding("UTF-8");
			htmlReporter.config()
					.setReportName("Extent Reports for OEM:Model:Build::" + oEM + ":" + model + ":" + build);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);

			extentReports = new ExtentReports();
			extentReports.setSystemInfo("Host", InetAddress.getLocalHost().toString().split("/")[0]);
			extentReports.setSystemInfo("IP Address", InetAddress.getLocalHost().toString().split("/")[1]);
			extentReports.setSystemInfo("Username", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("Os Arch", System.getProperty("os.arch"));
			extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);

			KlovReporter klovReporter = new KlovReporter();
			klovReporter.setAnalysisStrategy(AnalysisStrategy.TEST);
			klovReporter.initMongoDbConnection("localhost", 27017);
			klovReporter.setProjectName(oEM);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(Calendar.getInstance().getTime());
			klovReporter.setReportName(model + "-Build:" + build);
			klovReporter.setKlovUrl("http://localhost:90/");
			extentReports.attachReporter(klovReporter);
			extentReports.attachReporter(htmlReporter);

			return extentReports;
		}

	}
}
