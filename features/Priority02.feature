#Author: paulmusson@me.com
#Keywords Summary : Priority 02 Tests for the BGL Test Site

## (Cucumber Feasture File for BGL Test Site Scenarios)

@featureTest
Feature: Priority 02 Tests for the BGL Test Site

@Secenario_02.01
Scenario: Test - Electricity Only - No Bill - British Gas - NA - Suppliers Are Not Same - Variable Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.01"
 When A valid postcode is entered - "PE2 6YS"
  And I "DoNot" have my bill
  And I want to compare "ElectricityOnly"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "British Gas" 
  And The Gas is supplied by "NA"
  And The Electricity Tariff is set as "NA"
  And The Gas Tariff is set as "NA" 
  And The Tariff is selected as "variable" and the Terms and Conditions are accepted
 Then The results table is displayed to the user

@Secenario_02.02
Scenario: Test - Gas Only - With Bill - NA - E.ON - Suppliers Are Not Same - Fixed Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.02"
 When A valid postcode is entered - "PE2 6YS"
  And I "Do" have my bill
  And I want to compare "GasOnly"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "NA" 
  And The Gas is supplied by "E.ON"
  And The Electricity Tariff is set as "NA"
  And The Gas Tariff is set as "GoGreen (Gas only)" 
  And The Tariff is selected as "fixed" and the Terms and Conditions are accepted
 Then The results table is displayed to the user

@Secenario_02.03
Scenario: Test - Gas And Electricity - With Bill - Avro Energy - SWEB - Suppliers Are Not Same - Fixed Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.03"
 When A valid postcode is entered - "PE2 6YS"
  And I "Do" have my bill
  And I want to compare "GasAndElectricity"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "Avro Energy" 
  And The Gas is supplied by "SWEB"
  And The Electricity Tariff is set as "Simple and Fix"
  And The Gas Tariff is set as "Standard" 
  And The Tariff is selected as "fixed" and the Terms and Conditions are accepted
 Then The results table is displayed to the user

@Secenario_02.04
Scenario: Test - Gas And Electricity - With Bill - Spark Energy - Spark Energy - Suppliers Are Same - Fixed Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.04"
 When A valid postcode is entered - "PE2 6YS"
  And I "Do" have my bill
  And I want to compare "GasAndElectricity"
  And The Gas and Electricity "Are" supplied by the same supplier
  And The Electricity is supplied by "Spark Energy" 
  And The Gas is supplied by "Spark Energy"
  And The Electricity Tariff is set as "Standard Plus 5"
  And The Gas Tariff is set as "Standard Plus 4" 
  And The Tariff is selected as "fixed" and the Terms and Conditions are accepted
 Then The results table is displayed to the user
 
@Secenario_02.05
Scenario: Test - Gas And Electricity - No Bill - SSE - EDF - Suppliers Are Not Same - All Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.05"
 When A valid postcode is entered - "PE2 6YS"
  And I "DoNot" have my bill
  And I want to compare "GasAndElectricity"
  And The Gas and Electricity "AreNot" supplied by the same supplier
  And The Electricity is supplied by "SSE" 
  And The Gas is supplied by "EDF"
  And The Electricity Tariff is set as "NA"
  And The Gas Tariff is set as "NA" 
  And The Tariff is selected as "all" and the Terms and Conditions are accepted
 Then The results table is displayed to the user
 
@Secenario_02.06
Scenario: Test - Gas And Electricity - No Bill - nPower - nPower - Suppliers Are Same - Variable Tariff
Given The BGL Test Site is opened in Firefox for "SCENARIO_02.06"
 When A valid postcode is entered - "PE2 6YS"
  And I "DoNot" have my bill
  And I want to compare "GasAndElectricity"
  And The Gas and Electricity "Are" supplied by the same supplier
  And The Electricity is supplied by "npower" 
  And The Gas is supplied by "npower"
  And The Electricity Tariff is set as "NA"
  And The Gas Tariff is set as "NA" 
  And The Tariff is selected as "var" and the Terms and Conditions are accepted
 Then The results table is displayed to the user