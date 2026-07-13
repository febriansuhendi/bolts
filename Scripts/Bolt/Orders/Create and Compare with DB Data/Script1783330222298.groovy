import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import database.UsersQuery
import database.DBConnection

//login via web
WebUI.openBrowser('')
WebUI.navigateToUrl('https://e-commerce-microserv-nrvq.bolt.host/login')
CustomKeywords.'business.LoginFlow.login'()
CustomKeywords.'common.ClickHelper.smartClick'(findTestObject('bolt/Page_E-commerce Microservices Demo/a_Users'))

//create user
Map createUser = CustomKeywords.'business.CreateUser.createUserWithTimestamp'()

//get user from db
UsersQuery query = new UsersQuery()
DBConnection conn = new DBConnection()
Map user = query.getUserByName(createUser.name)
conn.closeDB()
println user.name
println user.email
println user.role
println user.id

//verify data
WebUI.verifyEqual(createUser.name, user.name)
WebUI.verifyEqual(createUser.email, user.email)