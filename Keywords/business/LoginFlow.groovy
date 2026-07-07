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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import internal.GlobalVariable

public class LoginFlow {
	ClickHelper clickHelper = new ClickHelper()
	
	@Keyword
	def login() {
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), 'admin@test.com')
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), 'password123')
		clickHelper.smartClick(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
		WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)
	}
	
	@Keyword
	def loginWithExcel() {
		TestData data = findTestData("Data Files/LoginData")
		String email = data.getValue("email", 1)
		String password = data.getValue("password", 1)
		
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), email)
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), password)
		clickHelper.smartClick(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
		WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)
	}
	
	@Keyword
	def loginWithExcelMultiRow(String email, String password) {		
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), email)
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), password)
		clickHelper.smartClick(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
		WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)
	}
	
	@Keyword
	def loginWithCsv() {
		TestData data = findTestData("Data Files/LoginDataCSV")
		String email = data.getValue("email", 1)
		String password = data.getValue("password", 1)
		
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), email)
		WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), password)
		clickHelper.smartClick(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))
		WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)
	}
	
	@Keyword
	def loginViaApi(String email, String pass) {
		RequestObject reqLogin = findTestObject('bolt/API/Postman/Auth Service/Login',
			[
				('baseUrl') : GlobalVariable.baseUrl,
				('email') : email,
				('password') : pass
				]
				)
		println reqLogin.getHttpHeaderProperties()
		println reqLogin.getBodyContent().getText()
		
		ResponseObject resLogin = WS.sendRequest(reqLogin)
		println resLogin.getHeaderFields()
		println resLogin.getResponseBodyContent()
		
		WS.verifyResponseStatusCode(resLogin, 200)
		
		String token = WS.getElementPropertyValue(resLogin, 'token')
		return token
	}
}
