package business

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
import common.ClickHelper
import common.FormHelper
import common.TableHelper

public class CreateUser {
	ClickHelper clickHelper = new ClickHelper()
	FormHelper formHelper = new FormHelper()
	TableHelper tableHelper = new TableHelper()
	
	@Keyword
	Map createUserWithTimestamp() {
		String timestamp = String.valueOf(System.currentTimeMillis())
		String email = "User_"+timestamp+"@gmail.com"
		String name = "User "+timestamp
		String password = "password"
		
		clickHelper.smartClick(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/button_AddUser'))
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_fullName'), name)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_email'), email)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_userPassword'), password)
		clickHelper.smartClick(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/button_submitNewUser'))
//		verify user created
		tableHelper.verifyDataVisible(name)
		
		return[
			name: name,
			email: email
			]
	}
	
	@Keyword
	Map createUserViaApi(String token) {
		String timestamp = String.valueOf(System.currentTimeMillis())
		String fullName = "User API "+timestamp
		String email = fullName.replaceAll("\\s+", "").toLowerCase()+"@gmail.com"
		String pass = "password"
		String role = "admin"
		
		RequestObject reqCreateUser = findTestObject('bolt/API/Postman/User Service/Create User',
			[
				('baseUrl') : GlobalVariable.baseUrl,
				('token') : token,
				('newEmail') : email,
				('newPassword') : pass,
				('role') : role,
				('newUser') : fullName
				]
				)
		println reqCreateUser.getHttpHeaderProperties()
		println reqCreateUser.getBodyContent()
		
		ResponseObject resCreateUser = WS.sendRequest(reqCreateUser)
		println resCreateUser.getBodyContent()
		
		WS.verifyResponseStatusCode(resCreateUser, 201)
		return[
			name : fullName,
			email : email,
			role : role
			]
	}

}
