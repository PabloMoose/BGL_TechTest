#Author: paulmusson@me.com
#Keywords Summary : Priority 01 Tests for the BGL Test Site

## (Cucumber Feasture File for BGL Test Site Scenarios)

@featureTest
Feature: Priority 01 Tests for the BGL Test Site

@Secenario_01.01
Scenario: Test - Gas And Electricity - With Bill - E.ON - nPower - Suppliers Are Not Same - All Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_01.01"
 When A valid postcode is entered - "PE2 6YS"
  And I "Do" have my bill
  And I want to compare "GasAndElectricity"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "E.ON" 
  And The Gas is supplied by "npower"
  And The Electricity Tariff is set as "Age UK Fixed 1 year"
  And The Gas Tariff is set as "Feel Good Fix June 2018" 
  And The Tariff is selected as "all" and the Terms and Conditions are accepted
 Then The results table is displayed to the user

@Secenario_01.02
Scenario: Test - Gas Only - No Bill - NA - Scottish Power - Suppliers Are Not Same - Variable Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_01.02"
 When A valid postcode is entered - "PE2 6YS"
  And I "DoNot" have my bill
  And I want to compare "GasOnly"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "NA"
  And The Gas is supplied by "Scottish Power"
  And The Electricity Tariff is set as "NA"
  And The Gas Tariff is set as "NA" 
  And The Tariff is selected as "variable" and the Terms and Conditions are accepted
 Then The results table is displayed to the user

@Secenario_01.03
Scenario: Test - Electricity Only - With Bill - British Gas - NA - Suppliers Are Not Same - Fixed Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_01.03"
 When A valid postcode is entered - "PE2 6YS"
  And I "Do" have my bill
  And I want to compare "ElectricityOnly"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "British Gas" 
  And The Gas is supplied by "NA"
  And The Electricity Tariff is set as "HomeEnergy Fixed Dec 2018"
  And The Gas Tariff is set as "NA" 
  And The Tariff is selected as "fixed" and the Terms and Conditions are accepted
 Then The results table is displayed to the user