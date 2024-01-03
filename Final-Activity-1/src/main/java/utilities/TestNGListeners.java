package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.BaseClass;

public class TestNGListeners extends BaseClass implements ITestListener {
	ExtentReporterManager ermObj = new ExtentReporterManager();
	ExtentTest parentTest;
	ExtentTest childTest;
	ExtentReports extent = ermObj.createInstanceExtentReports();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public String fileWithPath;
	public String filePath = System.getProperty("user.dir") + "\\extentReports\\screenshots\\";
	public static String methodName;
	public static String className;
	public static String SrcBase64String;
	public static LocalDateTime now = LocalDateTime.now();
	public static String dt = now.toString().replace(":", ".");

	public synchronized void onTestStart(ITestResult result) {
		methodName = "";
		methodName = result.getMethod().getMethodName();
		System.out.println("TEST START: " + result.getName());
		String className = result.getTestClass().getRealClass().getSimpleName();
		parentTest = extent.createTest(className);
		parentTest.assignCategory(BaseClass.category);
		childTest = parentTest.createNode(methodName);
		extentTest.set(childTest);
		
	}

	public synchronized void onTestSuccess(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		extentTest.get().pass(MarkupHelper.createLabel("PASSED: " + testMethodName, ExtentColor.GREEN));
	}
	
	public synchronized void onTestFailure(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		SrcBase64String = "";
		String SrcBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		SrcBase64String = SrcBase64;

		// copies screenshot to destLoc
		filePath = filePath + testMethodName + "_" + dt + ".png";
		File SrcFile = OutputType.FILE.convertFromBase64Png(SrcBase64);
		File DestFile = new File(filePath);
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.get().fail(result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(SrcBase64String).build());
		extentTest.get().fail(MarkupHelper.createLabel("FAILED: " + testMethodName, ExtentColor.RED));
	}
	

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: " + result.getName());
		extentTest.get().skip(result.getThrowable());
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public synchronized void onStart(ITestContext context) {
		System.out.println("ENGINE START: " + context.getName());
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println("ENGINE FINISH");
		extent.flush();
	}
}