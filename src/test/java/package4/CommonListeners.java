package package4;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners(package4.CommonListeners.class)
public class CommonListeners implements ITestListener {

	String someScreenshotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Windows.PNG";

	public static ExtentReports extentReports = ExtentManager.getInstance();
	public static ThreadLocal<ExtentTest> parentThread = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> childThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest childTest = parentThread.get().createNode(result.getMethod().getMethodName());
		childThread.set(childTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			childThread.get().pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(someScreenshotPath).build());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			childThread.get().fail("Test failed due to",
					MediaEntityBuilder.createScreenCaptureFromPath(someScreenshotPath).build());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			childThread.get().skip("Test skipped",
					MediaEntityBuilder.createScreenCaptureFromPath(someScreenshotPath).build());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		ExtentTest parentTest = extentReports.createTest(getClass().getName());
		parentThread.set(parentTest);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

	public static class ExtentManager {
		public static ExtentReports extentReports;
		static String extentReportFile = System.getProperty("user.dir") + "\\src\\test\\java\\package4\\extent.html";

		public static ExtentReports getInstance() {
			if (extentReports == null) {
				createInstance(extentReportFile);
			}

			return extentReports;
		}

		public static ExtentReports createInstance(String fileName) {

			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("TestNG Extent Report");
			htmlReporter.config().setEncoding("UTF-8");
			htmlReporter.config().setReportName("Extent Reports for TestNG Tests");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);

			extentReports = new ExtentReports();
			extentReports.setSystemInfo("Host", System.getProperty("host"));
			extentReports.setSystemInfo("IP Address", System.getProperty("ip"));
			extentReports.setSystemInfo("Username", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("Os Arch", System.getProperty("os.arch"));
			extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);
			extentReports.attachReporter(htmlReporter);

			return extentReports;
		}

	}

}
