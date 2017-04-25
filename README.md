# BGL_TechTest
Tech Test for BGL

------------------------------------------------------------------------
README
------------------------------------------------------------------------

------------------------------------------------------------------------
Author
------------------------------------------------------------------------
Name	: Paul Musson
Email	: paulmusson@me.com
Tel	  : 07799 862939


------------------------------------------------------------------------
Overview
------------------------------------------------------------------------
This readme is for the SDET Tech Test framework provided by Paul Musson.

Below are deatils of the automated tests created to test the Compare the Market User Stories web site.


------------------------------------------------------------------------
Framework
------------------------------------------------------------------------
An automated framework was created using the following tools (versions)

- Selenium 	(2.53.0)
- Eclipse	(Kepler Service Release 2)
- Cucumber	(1.2.2)
- Maven		(4.0.0)
- Java (JRE)	(1.8.0_131)
- JUnit		(4.11)


------------------------------------------------------------------------
Cucumber Scenarios
------------------------------------------------------------------------
The following Cucumber Scenarios were created -


3 High Priority Scenarios - 
NOTE: These scenarios are defined in the '..\features\Priority01.feature' file

- @Secenario_01.01 - Scenario: Test - Gas And Electricity - With Bill - E.ON - nPower - Suppliers Are Not Same - 
- @Secenario_01.02 - Scenario: Test - Gas Only - No Bill - NA - Scottish Power - Suppliers Are Not Same - Variable Tariff
- @Secenario_01.03 - Scenario: Test - Electricity Only - With Bill - British Gas - NA - Suppliers Are Not Same - Fixed Tariff



and 6 additional Lower Priority Scenarios
NOTE: These scenarios are defined in the '..\features\Priority02.feature' file

- @Secenario_02.01 - Scenario: Test - Electricity Only - No Bill - British Gas - NA - Suppliers Are Not Same - Variable Tariff
- @Secenario_02.02 - Scenario: Test - Gas Only - With Bill - NA - E.ON - Suppliers Are Not Same - Fixed Tariff
- @Secenario_02.03 - Scenario: Test - Gas And Electricity - With Bill - Avro Energy - SWEB - Suppliers Are Not Same - Fixed Tariff
- @Secenario_02.04 - Scenario: Test - Gas And Electricity - With Bill - Spark Energy - Spark Energy - Suppliers Are Same - Fixed Tariff
- @Secenario_02.05 - Scenario: Test - Gas And Electricity - No Bill - SSE - EDF - Suppliers Are Not Same - All Tariff
- @Secenario_02.06 - Scenario: Test - Gas And Electricity - No Bill - nPower - nPower - Suppliers Are Same - Variable Tariff



------------------------------------------------------------------------
Cucumber Scenario Format
------------------------------------------------------------------------
Each scenario follows the same Cucumber format using parameters to control the inputs to each test.
Additional scenarios can be defined in the same format by setting the Cucumber parameters.


Given The BGL Test Site is opened in Firefox for "Scenario Name"

 When A valid postcode is entered - "Postcode"
 
  And I "Do or Do Not" have my bill (available options Do | DoNot)
  
  And I want to compare "Gas, Electricity or both"  (available options 'GasAndElectricity' | 'GasOnly' | 'ElectricityOnly')
  
  And The Gas and Electricity "Are or Are Not" supplied by the same supplier (available options 'Are' | 'AreNot')
  
  And The Electricity is supplied by "Electricity Supplier Name" 
  
  And The Gas is supplied by "Gas Supplier Name"
  
  And The Electricity Tariff is set as "Electricity Supplier Tariff"
  
  And The Gas Tariff is set as "Gas Supplier Tariff" 
  
  And The Tariff is selected as "Tariff to search for" and the Terms and Conditions are accepted (available options 'all' | 'variable' | 'fixed')
  
 Then The results table is displayed to the user
 


------------------------------------------------------------------------
Test Description
------------------------------------------------------------------------
Each scenario follows a defined work flow based on the input parameters defined in the 'feature' files (..\features\*.feature)


(1)  Opens the Firefox browser and navigates to the BGL test site

(2)  Selects the Bill option (Has Bill or Does Not Have Bill)

(3)  Selects the Search Options (Gas and Electricity, Gas Only or Electricity Only)

(4)  Selects the Same Supplier option (if both Gas and Electricty are provided by the same supplier or not)

(5)  Selects the Electricity Supplier (if specified)

(6)  Selects the Gas Supplier (if specified)

(7)  Selects the Electricity Tariff (if specified)

(8)  Selects the Gas Tariff (if specified)

(9)  Selects the Tariff type to search for (Fixed, Varaible or All)

(10) Displays the search results and checks that the main tables are displayed 

(11) Performs individual checks on the Current Tarrif details, checks the Electric/Gas Suppliers and the Electric/Gas Tariffs

(12) Closes the Firefox browser


------------------------------------------------------------------------
Test Checks
------------------------------------------------------------------------
Whilst running through the scenarios, the testing tool will perform checks to ensure that the required fields are available and are in the correct state (e.g. editable, visible). If any of these checks fails, the step will fail and this will also fail the main scenario.
Some of the values entered throughout the scenario are specified in the scenario definition and other values are randomly set (e.g. most of the check boxes are randomly set to one of the available options)
The tests end at the Result page. On this page additional checks are made to ensure that the main areas of the screen are displayed and also checks are made to ensure that the Current Tariff details are displayed as expected. Again, if any of these checks fail, the step and scenario will be flagged as failed.


If running the test using the executable jar (see below for instructions) then the command window will display a summary of the results at the end of the complete test run.
This summary will show the number of scenarios and steps executed and the number that have passed/failed
E.g. 


> 9 scenarios (9 passed)

> 99 steps (99 passed)

If running the test within Eclipe, the results will be displayed within the JUnit window.


------------------------------------------------------------------------
Test Execution Prerequisites
------------------------------------------------------------------------
> Mac OS X El Capitan or Windows 7 (tested environments)
> JRE Version 1.8.0_131 or higher
> Firefox up to version 46.0 (tested)
> 50MB of free disc space (for executable Jar, screenshots and test reports)


------------------------------------------------------------------------
Running the Tests - Executable Java File
------------------------------------------------------------------------
One way to quickly run the automated tests is to run the executable jar file.
An executable Java file has been included so that the automated tests can be run directly from a Windows or Mac command line.
To run the executable Java file, follow these steps -


(1) Copy the 'BGL_Executable_Files.zip' file to a local directory

(2) Extract the files, this will provide the following files and directories

..\features\Priority01.feature	

..\features\Priority02.feature	

..\screenshots

..\target

..\BGL01.jar

(3) Open a command prompt at this level

(4) Type 'java - jar BGL01.jar' and press enter to run the executable jar file

(5) The executable jar will run and the scenarios will be executed in order as specified in the feature files


NOTE: all 9 of the tests will be executed as both of 'Priority...' features files are included in the features folder (if you only want to run the Priority 1 scenarios, remove the Priority02.feature file from this folder.


------------------------------------------------------------------------
Running the Tests - Eclipse / Maven
------------------------------------------------------------------------
The complete Eclipse / Maven Project has been uploaded to this GIT Repository
To Run the tests from Eclipse / Maven, import the project files into Eclipse.
Then run the test in one of the following ways 

Java:
- Right click on the src/test/java/runner/TestRunnerJava.java file, select Run As and then select Java Application

JUnit:
- Right click on the src/test/java/runner/TestRunnerMain.java file, select Run As and then select JUnit Test

NOTE: all 9 of the tests will be executed as both of 'Priority...' features files are included in the features folder (if you only want to run the Priority 1 scenarios, remove the Priority02.feature file from this folder.


------------------------------------------------------------------------
Report
------------------------------------------------------------------------
The automated test will produce a report of the results, this will be created as the following location -

- ..\target\cucumber-html-report\index.html

Open this html file at the end of the test to view the Scenario Pass/Fail results.


------------------------------------------------------------------------
Screen Shots
------------------------------------------------------------------------
Each automated test (scenario) will produce a screen shot of the results page, this will be saved in the ..\screenshots folder.
The naming format for these screenshots is -

*Scenario Name*BGL_TEST_*year*-*month*-*day*-*hour*-*minute*-*second*_*browser*.png

E.g. SCENARIO_01.01BGL_TEST_2017-04-25-19-14-21_FIREFOX.png

The purpose of these screenshots is give extra confirmation that the scenarios are passing and to allow for additional manual checking of the results if needed.


------------------------------------------------------------------------
Future Development
------------------------------------------------------------------------

The following are considerations for future developments and improvements to the current tests


(1) Add support for other browsers (e.g. IE, Chrome, Safari) and enable this as a parameter

(2) Add more scenarios

(3) Integrate into Jenkins or other automated server framework

(4) Upgrade the version of Selenium used (currently using 2.53, version 3 is available now)

(5) Allow for extra configuration settings to be set such as turning screenshots on/off

(6) Additional reporting with pass/fail for each individual check on the Results Page

