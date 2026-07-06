package business

import common.ClickHelper


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class LoginFlow {
	ClickHelper clickHelper = new ClickHelper()
	
	@Keyword
	def loginAsAdmin() {
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), GlobalVariable.email)
		WebUI.setEncryptedText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), GlobalVariable.password)
		clickHelper.smartClick(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
//		CustomKeywords.'common.ClickHelper.smartClick'(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
		WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)
	}
}
