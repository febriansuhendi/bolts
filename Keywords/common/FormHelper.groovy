package common

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
import com.kms.katalon.core.configuration.RunConfiguration
import internal.GlobalVariable

public class FormHelper {
	@Keyword
	def smartInput(TestObject to, String text) {
		int maxRetry = 2
		
		for(int i=1; i<=maxRetry; i++) {
			try {
				WebUI.waitForElementVisible(to, 5)
				WebUI.setText(to, text)
				return
			}
			catch(Exception e){
				WebUI.comment("Retry ${i} failed : ${e.getMessage()}")
				WebUI.takeScreenshot()
			}
		}
	}
	
	@Keyword
	def uploadImage(String path) {
		String projectDir = RunConfiguration.getProjectDir()
		String filePath = projectDir + path
		try {
			WebUI.waitForElementVisible(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_uploadImage'), 3)
			WebUI.uploadFile(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_uploadImage'), filePath)
			return
		}
		catch(Exception e) {
			WebUI.comment("Upload Image Failed!")
			WebUI.takeScreenshot()
		}
	}
}
