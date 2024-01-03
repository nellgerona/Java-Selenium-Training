package utilities;

import java.time.LocalDateTime;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterManager {
	static ExtentReports extent;
	ReadConfig readConfigObj = new ReadConfig();
	public static Properties prop;
	public static String filePath = "\\config.properties";
	public String reportName = readConfigObj.getReportName();
	public String docTitle = readConfigObj.getDocumentTitle();
	public String tester = readConfigObj.getTester();
	public String env = readConfigObj.getEnvironment();
	public String testType = readConfigObj.getTestType();

	public ExtentReports createInstanceExtentReports() { // data driven

		LocalDateTime now = LocalDateTime.now();
		String date = now.toString().replace(":", ".");
		String fileName = "Automation Report_" + date + ".html";
		String path = System.getProperty("user.dir") + "\\extentReports\\" + fileName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName(reportName);
		reporter.config().setDocumentTitle(docTitle);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", tester);
		extent.setSystemInfo("Environment Tested", env);
		extent.setSystemInfo("Test Type", testType);
		return extent;

	}
}