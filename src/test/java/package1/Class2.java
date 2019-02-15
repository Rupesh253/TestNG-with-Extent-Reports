package package1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Class2 {

	@BeforeSuite
	public void beforeSuite_in_Class2_in_package1() {
		System.out.println("beforeSuite_in_Class2_in_package1()");
	}

	@BeforeTest
	public void beforeTest_in_Class2_in_package1() {
		System.out.println("\t beforeTest_in_Class2_in_package1()");
	}

	@BeforeClass
	public void beforeClass_in_Class2_in_package1() {
		System.out.println("\t \t beforeClass_in_Class2_in_package1()");
	}

	@BeforeMethod
	public void beforeMethod_in_Class2_in_package1() {
		System.out.println("\t \t \t beforeMethod_in_Class2_in_package1()");
	}

	@Test(groups = { "Group1" })
	public void method1_in_Class2_in_package1() {
		System.out.println("\t \t \t \t method1_in_Class2_in_package1()");
	}

	@Test(groups = { "Group2" })
	public void method2_in_Class2_in_package1() {
		System.out.println("\t \t \t \t method2_in_Class2_in_package1()");
	}

	@Test(groups = { "Group3" })
	public void method3_in_Class2_in_package1() {
		System.out.println("\t \t \t \t method3_in_Class2_in_package1()");
	}

	@AfterMethod
	public void afterMethod_in_Class2_in_package1() {
		System.out.println("\t \t \t afterMethod_in_Class2_in_package1()");
	}

	@AfterClass
	public void afterClass_in_Class2_in_package1() {
		System.out.println("\t \t afterClass_in_Class2_in_package1()");
	}

	@AfterTest
	public void aftertest_in_Class2_in_package1() {
		System.out.println("\t afterTest_in_Class2_in_package1()");
	}

	@AfterSuite
	public void afterSuite_in_Class2_in_package1() {
		System.out.println("afterSuite_in_Class2_in_package1()");
	}

}
