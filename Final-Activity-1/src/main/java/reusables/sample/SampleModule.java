package reusables.sample;

import pages.sample.SamplePage;
import pages.sample.Sample2Page;

import utilities.UserHelper;
/*******************************
 * 
 * REUSABLE ACTIONS THAT INVOLVES MULTIPLE PAGE CLASSES
 * 
 * 
 * ********************************/

public class SampleModule extends UserHelper{
	private static SamplePage samplePage;
	private static Sample2Page sample2Page;

	public SampleModule() {
		samplePage = new SamplePage();
		sample2Page = new Sample2Page();

	}
	private void method1() {
		// DO METHOD
	}
	
	public void action(String username, String password){
		// DO METHOD
	}
}
