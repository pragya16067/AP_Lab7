//NAME : PRAGYA PRAKASH
//ROLL NO. : 2016067

package Lab7Code;

import	org.junit.runner.JUnitCore;	
import	org.junit.runner.Result;	
import	org.junit.runner.notification.Failure;	


public class TestRunner	{	
	public static void main(String[] args)
	{	
	
		Result result =	JUnitCore.runClasses(TestingLab7.class);	
		for	(Failure f : result.getFailures())	
		{	
			System.out.println(f.toString());	
		}
		System.out.println(result.wasSuccessful());	
		}
	
}	

