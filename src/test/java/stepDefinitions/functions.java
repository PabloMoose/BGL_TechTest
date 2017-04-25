package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class functions {

	   public static void screenShot(WebDriver driver, String title, String browserName) throws IOException, InterruptedException {
		    Thread.sleep(2000);
		   	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File("./screenshots/" + title + "_" + functions.GetTime() + "_" + browserName + ".png"));		   		
		   }

	   public static String GetTime()
	   {
	      int day, month, year;
	      int second, minute, hour;
	      GregorianCalendar date = new GregorianCalendar();

	      day = date.get(Calendar.DAY_OF_MONTH);
	      month = date.get(Calendar.MONTH);
	      year = date.get(Calendar.YEAR);
	      String dateString = String.format("%04d-%02d-%02d", year, month+1, day);
	      
	      second = date.get(Calendar.SECOND);
	      minute = date.get(Calendar.MINUTE);
	      hour = date.get(Calendar.HOUR_OF_DAY);
	      String timeString = String.format("%02d-%02d-%02d", hour, minute, second);
	      
	      String test1 = "" + dateString + "-" + timeString;
	      return test1;
	   }
	
}
