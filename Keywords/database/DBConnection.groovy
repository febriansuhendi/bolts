package database

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
import java.sql.Connection
import java.sql.DriverManager
import internal.GlobalVariable

public class DBConnection {
	private static Connection connection = null
	
		@Keyword
		Connection connectDB() {
	
			if (connection != null && !connection.isClosed()) {
				return connection
			}
	
			String url = "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres"
			String username = "postgres.edvxgxnfelfrkkbqulda"
			String password = "YOY4DxfcsWohE0bA"
	
			Class.forName("org.postgresql.Driver")
	
			connection = DriverManager.getConnection(url, username, password)
	
			println("Database Connected")
	
			return connection
		}
	
		@Keyword
		void closeDB() {
	
			if (connection != null && !connection.isClosed()) {
				connection.close()
				println("Database Closed")
			}
		}
}
