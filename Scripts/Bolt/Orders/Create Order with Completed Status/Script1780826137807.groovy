import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://e-commerce-microserv-nrvq.bolt.host/login')

WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_admintest.com'), 'admin@test.com')

WebUI.setEncryptedText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Enter your password'), '8SQVv/p9jVTHLrggi8kCzw==')

WebUI.click(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Sign in'))

WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'), 0)

WebUI.click(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Orders'))

WebUI.verifyElementPresent(findTestObject('bolt/Page_E-commerce Microservices Demo/table_Order CustomerTotalStatusActionsorder-1fe'), 
    0)

WebUI.click(findTestObject('bolt/Page_E-commerce Microservices Demo/button_New Order'))

WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_e.g. ORD-003'), 'ORD-747')

WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_Customer name'), 'Jim Root')

WebUI.setText(findTestObject('bolt/Page_E-commerce Microservices Demo/input_w-full px-3 py-2 border border-gray-300 r'), 
    '0100')

WebUI.selectOptionByValue(findTestObject('bolt/Page_E-commerce Microservices Demo/select_PendingProcessingCompletedCancelled'), 
    'processing', false)

WebUI.click(findTestObject('bolt/Page_E-commerce Microservices Demo/button_Save'))

WebUI.click(findTestObject('bolt/Page_E-commerce Microservices Demo/td_ORD-747'))

