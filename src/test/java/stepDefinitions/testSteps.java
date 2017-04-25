package stepDefinitions;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stepDefinitions.functions;


public class testSteps {

	//Initialise Web Driver
	WebDriver driver;
	
	//Declare global variables 
	public static String globalScenarioName;
	public static String globalHaveBill;
	public static String globalGasElectricity;
	public static String globalSameSupplier;
	public static String globalElecSupplier;
	public static String globalGasSupplier;
	public static String globalElecTariff;
	public static String globalGasTariff;
	
	
	@Given("^The BGL Test Site is opened in Firefox for \"([^\"]*)\"$")
	public void The_BGL_Test_Site_is_opened_in_Firefox(String scenarioName) throws Throwable {
	
		//set the Global Scenario Name
		globalScenarioName = scenarioName;
		
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://energy.comparethemarket.com/energy/v2/yourEnergy?AFFCLIE=TSTT");		
	}

	
	@When("^A valid postcode is entered - \"([^\"]*)\"$")
	public void A_valid_postcode_is_entered_(String postcode) throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		//Synchronize by waiting for the Postcode field to become visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("your-postcode")));					
		//Enter the Postcode
		driver.findElement(By.id("your-postcode")).sendKeys(postcode);
		driver.findElement(By.id("find-postcode")).click();	
		//Synchronize by waiting until the postcode has been accepted (the 'Change Postcode' link is displayed)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change-postcode")));					
	}
	
	
	@When("^I \"([^\"]*)\" have my bill$")
	public void I_have_my_bill(String haveBill) throws Throwable {
		
		//Set the Global variable 'globalHaveBill' to use throughout the class
		globalHaveBill = haveBill;
		
		//Check if the user has their bill or not (based on the input parameter 'haveBill')
		if (haveBill.equalsIgnoreCase("Do")){
			driver.findElement(By.id("have-bill-label")).click();
		}
		else if (haveBill.equalsIgnoreCase("DoNot")){
			driver.findElement(By.id("no-bill-label")).click();			
		}
	}

	
	@When("^I want to compare \"([^\"]*)\"$")
	public void I_want_to_compare(String gasElectricity) throws Throwable {

		//Set the Global variable 'globalGasElectricity' to use throughout the class
		globalGasElectricity = gasElectricity;

		//Select the 'Gas and Electricity', 'Electricity Only' or 'Gas Only' option depending on the 'GasElectricity' value
		if (globalGasElectricity.equalsIgnoreCase("ElectricityOnly")) {
			driver.findElement(By.id("compare-electricity-label")).click();
		}
		else if (globalGasElectricity.equalsIgnoreCase("GasOnly")) {
			driver.findElement(By.id("compare-gas-label")).click();
		}
		else {
			driver.findElement(By.id("compare-both-label")).click();
		}
	}

	
	@When("^The Gas and Electricity \"([^\"]*)\" supplied by the same supplier$")
	public void The_Gas_and_Electricity_supplied_by_the_same_supplier(String sameSupplier) throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		//Set the Global variable 'globalSameSupplier' to use throughout the class
		//If globalHaveBill = DoNot, then set this to 'AreNot' for the 'Your Energy' screen
		if (globalHaveBill.equalsIgnoreCase("DoNot")){
			globalSameSupplier = "AreNot";
		}
		else{
			globalSameSupplier = sameSupplier;
		}
		
		//If this test has selected 'Gas and Electricity' AND 'I have my bill' the 'Same Supplier' question will be displayed, otherwise it will not be displayed
		if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") && (globalHaveBill.equalsIgnoreCase("Do"))){
			
			//Wait for the 'No' check box to appear for the 'Is your gas & electricity from the same supplier?' question
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='same-supplier-question']/div/div/label[2]/span")));
			
			//Once the 'Yes' / 'No' check boxes are displayed, select one based on the 'sameSupplier' value
			if (sameSupplier.equalsIgnoreCase("Are")){
				driver.findElement(By.xpath(".//*[@id='same-supplier-question']/div/div/label[1]/span")).click();
			}
			else if (sameSupplier.equalsIgnoreCase("AreNot")){
				driver.findElement(By.xpath(".//*[@id='same-supplier-question']/div/div/label[2]/span")).click();
			}
		}
	}
	
	
	@When("^The Electricity is supplied by \"(.*?)\"$")
	public void the_Electricity_is_supplied_by(String elecSupplier) throws Throwable {

		String fieldSetValue;

		//Set the Global variable 'globalElecSupplier' to use throughout the class
		globalElecSupplier = elecSupplier;
		
		//Select the Electricity Supplier
		//------------------------------------------------------------------------------------------------------------------------------	
		if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("ElectricityOnly"))){
			//If the Electricity and Gas supplied by the same supplier, set the fieldSetValue to '2' (for Joint Energy xPath values)
			if (globalSameSupplier.equalsIgnoreCase("Are")){
				fieldSetValue = "2";
			}else{
				fieldSetValue = "3";
			}			
			
			if (elecSupplier.equalsIgnoreCase("British Gas")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[1]/span")).click();
			}
			else if (elecSupplier.equalsIgnoreCase("EDF")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[2]/span")).click();
			}
			else if (elecSupplier.equalsIgnoreCase("E.ON")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[3]/span")).click();			
			}
			else if (elecSupplier.equalsIgnoreCase("nPower")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[4]/span")).click();						
			}
			else if (elecSupplier.equalsIgnoreCase("Scottish Power")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[5]/span")).click();						
			}
			else if (elecSupplier.equalsIgnoreCase("SSE")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[6]/span")).click();			
			}
			else if (elecSupplier.equalsIgnoreCase("NA")){
				//No selection Needed
			}
			//Else select from the drop down list
			else{
				if (globalSameSupplier.equalsIgnoreCase("Are")){
					Select dropdownElecSupp = new Select(driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div/div/select")));
					dropdownElecSupp.selectByVisibleText(elecSupplier);					
				}
				else{
					Select dropdownElecSupp = new Select(driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/div/div/select")));
					dropdownElecSupp.selectByVisibleText(elecSupplier);
				}
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	
	}

	
	@When("^The Gas is supplied by \"(.*?)\"$")
	public void the_Gas_is_supplied_by(String gasSupplier) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		String fieldSetValue;
		
		//Set the Global variable 'globalGasSupplier' to use throughout the class
		globalGasSupplier = gasSupplier;

		//Select the Gas Supplier
		//------------------------------------------------------------------------------------------------------------------------------	
		if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("GasOnly"))){
			//If the Electricity and Gas supplied by the same supplier, set the fieldSetValue to '2' (for Joint Energy xPath values)
			if (globalSameSupplier.equalsIgnoreCase("Are")){
				fieldSetValue = "2";
			}else{
				fieldSetValue = "4";
			}
	 		
			if (gasSupplier.equalsIgnoreCase("British Gas")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[1]/span")).click();
			}
			else if (gasSupplier.equalsIgnoreCase("EDF")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[2]/span")).click();
			}
			else if (gasSupplier.equalsIgnoreCase("E.ON")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[3]/span")).click();			
			}
			else if (gasSupplier.equalsIgnoreCase("nPower")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[4]/span")).click();						
			}
			else if (gasSupplier.equalsIgnoreCase("Scottish Power")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[5]/span")).click();						
			}
			else if (gasSupplier.equalsIgnoreCase("SSE")){
				driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/label[6]/span")).click();			
			}
			else if (gasSupplier.equalsIgnoreCase("NA")){
				//No selection Needed
			}
			//Else select from the drop down list
			else{
				if (globalSameSupplier.equalsIgnoreCase("Are")){
					Select dropdownGasSupp = new Select(driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div/div/select")));
					dropdownGasSupp.selectByVisibleText(gasSupplier);					
				}
				else{
					Select dropdownGasSupp = new Select(driver.findElement(By.xpath("html/body/div[1]/div/main/section[2]/fieldset[" + fieldSetValue + "]/div/div/div[1]/div/select")));
					dropdownGasSupp.selectByVisibleText(gasSupplier);
				}
			}			
		}
		//------------------------------------------------------------------------------------------------------------------------------	
		
		//Click the Next Button to complete the 'Your Supplier' details
		driver.findElement(By.id("goto-your-supplier-details")).click();
		
		//Synchronize
		//If the 'Do Not Have Bill' selection has been made
		if (globalHaveBill.equalsIgnoreCase("DoNot")){
			//Synchronize by waiting until the 'Pre-Pay Meter' check box is displayed
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='prepay-yes-no']/div/div/label[1]/span")));			
		}
		//Otherwise, if the 'Have Bills' selection has been made
		else{
			
			//If the Electricity Energy screen is expected
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || globalGasElectricity.equalsIgnoreCase("ElectricityOnly")){
				//Synchronize by waiting until the 'Economy 7' check box is displayed
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='economy-7-question']/div/div/label[1]/span")));			
			}
			
			//Otherwise, if the Gas Energy screen is expected
			else if (globalGasElectricity.equalsIgnoreCase("GasOnly")){
				//Synchronize by waiting until the 'Gas Source' check box is displayed
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='gas-main-heating-source-question']/div/div/label[1]/span")));			
			}	
		}
		
	}	
	
	
	@When("^The Electricity Tariff is set as \"(.*?)\"$")
	public void the_Electricity_Tariff_is_set_as(String elecTariff) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		//Set the Global variable 'globalElecSupplier' to use throughout the class
		globalElecTariff = elecTariff;

		//Generate random numbers to select random values
		Random rn = new Random();
		int economy7 = rn.nextInt(2) + 1;
		int mainSource = rn.nextInt(2) + 1;
		int kWhUsage = rn.nextInt(2) + 1;
		int prePay = rn.nextInt(2) + 1;

		//Enter the 'Energy' Details (if 'No Bill' selected)
		//------------------------------------------------------------------------------------------------------------------------------	
		if (globalHaveBill.equalsIgnoreCase("DoNot")){

			//Set the Pre-Pay Meter Value (based on the random value generated previously) - 1 = Yes / 2 = No
			driver.findElement(By.xpath(".//*[@id='prepay-yes-no']/div/div/label[" + prePay + "]/span")).click();

			//Set the Electricity values if needed
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("ElectricityOnly"))){
				//Set the Electricity Economy 7 Value (based on the random value generated previously) - 1 = Yes / 2 = No
				driver.findElement(By.xpath(".//*[@id='economy-7-question']/div/div/label[" + economy7 + "]/span")).click();				
				//Synchronize by waiting for the Electricity Current Spend field
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("electricity-current-spend")));					
				//Enter the Electricity Current Spend Value
				driver.findElement(By.id("electricity-current-spend")).sendKeys("345");
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	

		//Enter the 'Electricity' Details (if 'Has Bill' selected)
		//------------------------------------------------------------------------------------------------------------------------------	
		else if (globalHaveBill.equalsIgnoreCase("Do")){
			
			//If this test has selected 'Gas and Electricity' or the 'Electricity' option, then the 'Electricity' energy details need to be entered
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("ElectricityOnly"))){
				
				//Select the Electricity Tariff (based on the 'elecTariff' input parameter)
				Select dropdownElecTariff = new Select(driver.findElement(By.id("electricity-tariff-additional-info")));
				dropdownElecTariff.selectByVisibleText(elecTariff);

				//Set the Electricity Economy 7 Value (based on the random value generated previously) - 1 = Yes / 2 = No
				driver.findElement(By.xpath(".//*[@id='economy-7-question']/div/div/label[" + economy7 + "]/span")).click();

				//Leave the Payment Method as the default
				
				//Set the Electricity Main Source Value (based on the random value generated previously) - 1 = Yes / 2 = No
				driver.findElement(By.xpath(".//*[@id='electricity-main-heating-source-question']/div/div/label[" + mainSource + "]/span")).click();

				//Set the Electricity Usage Value (based on the random value generated previously) - 1 = kWh / 2 = £
				driver.findElement(By.xpath(".//*[@id='electricity-usage-question']/div/div/div[1]/label[" + kWhUsage + "]/span")).click();

				//Set the Economy and Usage values based on the check box selections made
				if ((economy7 == 1) && (kWhUsage == 1)){
					
					//Synchronize by waiting for the Economy 7 Day Value field
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("economy-7-day-usage")));					
					//Enter the Economy 7 Day Value
					driver.findElement(By.id("economy-7-day-usage")).sendKeys("123");
					//Enter the Economy 7 Night Value
					driver.findElement(By.id("economy-7-night-usage")).sendKeys("100");
					//Enter the Electricity Bill Day
					if (driver.findElement(By.xpath(".//*[@id='electricity-bill-date-field']/div/div/span[1]")).isDisplayed()){
						driver.findElement(By.xpath(".//*[@id='electricity-bill-date-field']/div/div/span[1]")).click();
						driver.findElement(By.xpath(".//*[@id='electricity-bill-day_root']/div/div/div/div/div[2]/button[1]")).click();						
					}

				}
				else if (((economy7 == 1) && (kWhUsage == 2)) || ((economy7 == 2) && (kWhUsage == 2))){
					
					//Synchronize by waiting for the Electricity Spend field
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("electricity-spend")));					
					//Enter the Electricity Spend
					driver.findElement(By.id("electricity-spend")).sendKeys("220");
					//Enter the Electricity Bill Day
					if (driver.findElement(By.xpath(".//*[@id='electricity-bill-date-field']/div/div/span[1]")).isDisplayed()){
						driver.findElement(By.xpath(".//*[@id='electricity-bill-date-field']/div/div/span[1]")).click();
						driver.findElement(By.xpath(".//*[@id='electricity-bill-day_root']/div/div/div/div/div[2]/button[1]")).click();
					}
				}
				else if ((economy7 == 2) && (kWhUsage == 1)){

					//Synchronize by waiting for the Electricity Usage field
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("electricity-usage")));					
					//Enter the Electricity Usage
					driver.findElement(By.id("electricity-usage")).sendKeys("3100");
				}
				
				//click the 'Next' button
				driver.findElement(By.id("goto-your-energy")).click();
				
				if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity")){
					//Synchronize by waiting until the 'Gas Source' check box is displayed
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='gas-main-heating-source-question']/div/div/label[1]/span")));			
				}
				else{
					//Synchronize by waiting until the 'Fixed Tariff' Button is displayed
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='tariff-selection-question']/div/label[1]/span/span")));					
				}
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	

	}

	
	@When("^The Gas Tariff is set as \"(.*?)\"$")
	public void the_Gas_Tariff_is_set_as(String gasTariff) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		//Set the Global variable 'globalElecSupplier' to use throughout the class
		globalGasTariff = gasTariff;

		//Generate random numbers to select random values
		Random rn = new Random();
		int mainSource = rn.nextInt(2) + 1;
		int kWhUsage = rn.nextInt(2) + 1;
		
		//Enter the 'Energy' Details (if 'No Bill' selected)
		//------------------------------------------------------------------------------------------------------------------------------	
		if (globalHaveBill.equalsIgnoreCase("DoNot")){

			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("GasOnly"))){
				//Synchronize by waiting for the Gas Current Spend field
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gas-current-spend")));					
				//Enter the Gas Current Spend Value
				driver.findElement(By.id("gas-current-spend")).sendKeys("267");
			}
			
			//Click the Next Button to complete the 'Your Energy' details
			driver.findElement(By.id("goto-your-energy")).click();
			//Synchronize by waiting until the 'Fixed Tariff' Button is displayed
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='tariff-selection-question']/div/label[1]/span/span")));	
		}
		//------------------------------------------------------------------------------------------------------------------------------	

		//Enter the 'Gas' Details (if 'Has Bill' selected)
		//------------------------------------------------------------------------------------------------------------------------------	
		else if (globalHaveBill.equalsIgnoreCase("Do")){
		
			//If this test has selected 'Gas and Electricity' or the 'Gas' option, then the 'Gas' energy details need to be entered
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity") || (globalGasElectricity.equalsIgnoreCase("GasOnly"))){
	
				//Check if the 'Gas Tariff' field is enabled (sometimes this is disabled)
				if (driver.findElement(By.id("gas-tariff-additional-info")).isEnabled()){
					//Select the Gas Tariff (based on the 'gasTariff' input parameter)
					Select dropdownGasTariff = new Select(driver.findElement(By.id("gas-tariff-additional-info")));
					dropdownGasTariff.selectByVisibleText(gasTariff);
				}
				
				//Set the Gas Main Source (based on the random value generated previously) - 1 = Yes / 2 = No
				driver.findElement(By.xpath(".//*[@id='gas-main-heating-source-question']/div/div/label[" + mainSource + "]/span")).click();
				
				//Set the Gas Usage Value (based on the random value generated previously) - 1 = kWh / 2 = £
				driver.findElement(By.xpath(".//*[@id='gas-type-of-bill-question']/div/div/div[1]/label[" + kWhUsage + "]/span")).click();
	
				//Set the Usage values based on the check box selections made
				if (kWhUsage == 1){
					//Synchronize by waiting for the Gas Usage field
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gas-usage")));					
					//Enter the Gas Usage
					driver.findElement(By.id("gas-usage")).sendKeys("2400");
				}
				else if (kWhUsage == 2){
					//Synchronize by waiting for the Gas Spend field
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gas-spend")));					
					//Enter the Gas Spend
					driver.findElement(By.id("gas-spend")).sendKeys("630");
				}
				
				//If the Gas Bill Day field is displayed, set as todays date
				if (driver.findElement(By.id("gas-bill-day")).isDisplayed()){
					//Enter the Gas Bill Day
					driver.findElement(By.xpath(".//*[@id='gas-bill-date-field']/div/div/span[1]")).click();
					driver.findElement(By.xpath(".//*[@id='gas-bill-day_root']/div/div/div/div/div[2]/button[1]")).click();
				}
				
				//Click the Next Button to complete the 'Your Energy' details
				driver.findElement(By.id("goto-your-energy")).click();
				
				//Synchronize by waiting until the 'Fixed Tariff' Button is displayed
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='tariff-selection-question']/div/label[1]/span/span")));	
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	
	}			

	
	@When("^The Tariff is selected as \"([^\"]*)\" and the Terms and Conditions are accepted$")
	public void The_Tariff_is_selected_as_and_the_Terms_and_Conditions_are_accepted(String tariff) throws Throwable {
		
		//Select the Tariff based on the 'tariff' value ('fixed', 'variable' or 'all') 
		if (tariff.equalsIgnoreCase("fixed")){
			driver.findElement(By.xpath(".//*[@id='tariff-selection-question']/div/label[1]/span/span")).click();
		}
		else if (tariff.equalsIgnoreCase("variable")){
			driver.findElement(By.xpath(".//*[@id='tariff-selection-question']/div/label[2]/span/span")).click();
		}
		else if (tariff.equalsIgnoreCase("all")){
			driver.findElement(By.xpath(".//*[@id='tariff-selection-question']/div/label[3]/span/span")).click();
		}
		else {
			driver.findElement(By.xpath(".//*[@id='tariff-selection-question']/div/label[1]/span/span")).click();
		}
		
		//First check if the Payment Type section is displayed (otherwise, skip this section)
		if (driver.findElement(By.xpath(".//*[@id='payment-selection-question']/div/label[1]/span")).isDisplayed()){
			//Generate random numbers to select random values
			Random rn = new Random();
			int paymentType = rn.nextInt(4) + 1;
			//Select a random payment type based on the randomly generated 'paymentType' variable
			driver.findElement(By.xpath(".//*[@id='payment-selection-question']/div/label[" + paymentType + "]/span")).click();			
		}
		
		//Enter a test email address
		driver.findElement(By.id("Email")).sendKeys("test@email.com");
		
		//Select the Marketing check box (Generate random numbers to select or not)
		Random rn = new Random();
		int random = rn.nextInt(2) + 1;
		//If random = '1', select check box, otherwise leave blank
		if (random == 1){
			driver.findElement(By.xpath(".//*[@id='marketingT']/div/div[2]/label/span[2]")).click();
		}

		//Select the Terms and Conditions check box
		driver.findElement(By.xpath(".//*[@id='terms-label']/span[2]")).click();
		
		//Click 'Go To Prices'
		driver.findElement(By.id("email-submit")).click();
		
		//Wait for the Searching window to disappear
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='interstitial-wrapper']/div[2]/header/h3")));	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='interstitial-wrapper']/div[2]/div[3]/img")));			
		
		//Synchronize by waiting until the 'Current Tariff and Usage' heading
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div/main/section[1]/div/h2")));
		
		//Take a screenshot of the results screen
		functions.screenShot(driver, globalScenarioName + "BGL_TEST","FIREFOX");

	}

	
	@Then("^The results table is displayed to the user$")
	public void The_results_table_is_displayed_to_the_user() throws Throwable {
		
		//Wait until the following 'Results Page' elements are displayed
		WebDriverWait wait = new WebDriverWait(driver, 10);

		//------------------------------------------------------------------------------------------------------------------------------	
		//Check that the 3 main areas of the Results Screen are displayed
		
		//Current Tariff and Usage Table
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div/main/section[1]/div")));	
		//Cheapest Option Section
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div/main/section[2]/div[1]")));	
		//Your Results Table
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div/main/section[3]")));	
		//------------------------------------------------------------------------------------------------------------------------------	
		
		//------------------------------------------------------------------------------------------------------------------------------	
		//Check the 'Current Tariff and Usage' values are populated correctly on the Results screen
		
		if (globalHaveBill.equalsIgnoreCase("DoNot")){
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity")){
				String bodyText1 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div"))).getText();
				Assert.assertTrue("Results Screen - Electricity Supplier Not Correct - Set as '" + bodyText1 + "'", bodyText1.equalsIgnoreCase(globalElecSupplier));				
				String bodyText2 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div"))).getText();
				Assert.assertTrue("Results Screen - Electricity Supplier Not Correct - Set as '" + bodyText2 + "'", bodyText2.equalsIgnoreCase(globalElecSupplier));								
			}
			else if (globalGasElectricity.equalsIgnoreCase("ElectricityOnly")){
				String bodyText = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div"))).getText();
				Assert.assertTrue("Results Screen - Electricity Supplier Not Correct - Set as '" + bodyText + "'", bodyText.equalsIgnoreCase(globalElecSupplier));				
			}
			else if (globalGasElectricity.equalsIgnoreCase("GasOnly")){
				String bodyText = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div"))).getText();
				Assert.assertTrue("Results Screen - Gas Supplier Not Correct - Set as '" + bodyText + "'", bodyText.equalsIgnoreCase(globalGasSupplier));				
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	
		else if (globalHaveBill.equalsIgnoreCase("Do")){
			if (globalGasElectricity.equalsIgnoreCase("GasAndElectricity")){
				String bodyText1 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[1]"))).getText();
				Assert.assertTrue("Results Screen - Electricity Supplier Not Correct - Set as '" + bodyText1 + "'", bodyText1.equalsIgnoreCase(globalElecSupplier));				
				String bodyText2 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[2]/div[1]"))).getText();
				Assert.assertTrue("Results Screen - Gas Supplier Not Correct - Set as '" + bodyText2 + "'", bodyText2.equalsIgnoreCase(globalGasSupplier));								
				String bodyText3 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[2]"))).getText();
				Assert.assertTrue("Results Screen - Electricity Tariff Not Correct - Set as '" + bodyText3 + "'", bodyText3.equalsIgnoreCase(globalElecTariff));				
				String bodyText4 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[2]/div[2]"))).getText();
				Assert.assertTrue("Results Screen - Gas Tariff Not Correct - Set as '" + bodyText4 + "'", bodyText4.equalsIgnoreCase(globalGasTariff));								
			}
			else if (globalGasElectricity.equalsIgnoreCase("ElectricityOnly")){
				String bodyText1 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[1]"))).getText();
				Assert.assertTrue("Results Screen - Electricity Supplier Not Correct - Set as '" + bodyText1 + "'", bodyText1.equalsIgnoreCase(globalElecSupplier));				
				String bodyText3 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[2]"))).getText();
				Assert.assertTrue("Results Screen - Electricity Tariff Not Correct - Set as '" + bodyText3 + "'", bodyText3.equalsIgnoreCase(globalElecTariff));				
			}
			else if (globalGasElectricity.equalsIgnoreCase("GasOnly")){
				String bodyText1 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[1]"))).getText();
				Assert.assertTrue("Results Screen - Gas Supplier Not Correct - Set as '" + bodyText1 + "'", bodyText1.equalsIgnoreCase(globalGasSupplier));				
				String bodyText3 = driver.findElement((By.xpath("html/body/div[1]/div/main/section[1]/div/ul/li[1]/div[2]"))).getText();
				Assert.assertTrue("Results Screen - Gas Tariff Not Correct - Set as '" + bodyText3 + "'", bodyText3.equalsIgnoreCase(globalGasTariff));				
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------	

		//Close the Web Page
		driver.close();
		driver.quit();
	}
}
