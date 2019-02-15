package package5;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.google.common.collect.Lists;

public class RunTestNGProgramatically {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		String testngSuiteFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\package5\\testng_package5.xml";
		String testngFailedSuiteFilePath = System.getProperty("user.dir") + "\\test-output\\Failed suite [TestNG Suite]\\testng-failed.xml";
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testNG = new TestNG();
		testNG.addListener(tla);
		List<String> suites = Lists.newArrayList();
		suites.add(testngSuiteFilePath);
		testNG.setTestSuites(suites);
		testNG.run();
		
		/*suites.clear();
		suites.add(testngFailedSuiteFilePath);
		testNG.setTestSuites(suites);
		testNG.run();*/
	}

}
