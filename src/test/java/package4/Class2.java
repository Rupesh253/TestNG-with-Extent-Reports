package package4;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;


public class Class2 {

	ThreadLocal<ExtentTest> childThread = CommonListeners.childThread;
	String someScreenshotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Windows.PNG";

	@Test(groups = { "Group1_in_package4", "X" }, description = "Rupesh", priority = 2)
	public void method1_in_Class2_in_package4(Method method) throws IOException {
		System.out.println("\t \t \t \t method1_in_Class2_in_package4()");
		childThread.get().assignAuthor(method.getAnnotation(Test.class).description());
		childThread.get().assignCategory(method.getAnnotation(Test.class).groups());
		childThread.get().pass("Step 1 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 2 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 3 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
	}

	@Test(groups = { "Group2_in_package4", "X" }, description = "Kiran", priority = 1)
	public void method2_in_Class2_in_package4(Method method) throws IOException {
		System.out.println("\t \t \t \t method2_in_Class2_in_package4()");
		childThread.get().assignAuthor(method.getAnnotation(Test.class).description());
		childThread.get().assignCategory(method.getAnnotation(Test.class).groups());
		childThread.get().pass("Step 1 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 2 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 3 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
	}

	@Test(groups = { "Group3_in_package4", "X" }, description = "Joseph", priority = 3)
	public void method3_in_Class2_in_package4(Method method) throws IOException {
		System.out.println("\t \t \t \t method3_in_Class2_in_package4()");
		childThread.get().assignAuthor(method.getAnnotation(Test.class).description());
		childThread.get().assignCategory(method.getAnnotation(Test.class).groups());
		childThread.get().pass("Step 1 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 2 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
		childThread.get().pass("Step 3 in " + method.getName() + " executed successfully!", MediaEntityBuilder
				.createScreenCaptureFromPath(someScreenshotPath, "Sample Screenshot attached.").build());
	}

}
