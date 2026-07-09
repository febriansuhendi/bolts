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
import java.io.File
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory

public class Products {
	ClickHelper clickHelper = new ClickHelper()
	FormHelper formHelper = new FormHelper()
	TableHelper tableHelper = new TableHelper()
	
	@Keyword
	Map createProduct() {
		String timestamp = String.valueOf(System.currentTimeMillis())
		String name = "Product "+timestamp
		String category = "Electronic"
		String desc = "This is product description for "+ name
		String price = "10000"
		String stock= "20"
		
		clickHelper.smartClick(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/button_AddProduct'))
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_productName'), name)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_category'), category)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/rte_description'), desc)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_price'), price)
		formHelper.smartInput(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/input_stock'), stock)
		formHelper.uploadImage("/Include/image/AC-1-PK-Daikin-R32..jpg")
		clickHelper.smartClick(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/button_saveProduct'))
//		verify user created
		tableHelper.verifyDataVisible(name)
		
		return[
			name: name,
			category: category,
			desc: desc,
			price: price,
			stock: stock
			]
	}
	
	@Keyword
	Map downloadProductList() {
//		verify downloaded file
		String filePath = System.getProperty("user.home") + "/Downloads/products.csv"
		File file = new File(filePath)
		
		clickHelper.smartClick(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/button_downloadProduct'))
		
		int timeout = 30
		while (!file.exists() && timeout > 0) {
		    Thread.sleep(1000)
		    timeout--
		}
		
//		validasi file terdownload
		assert file.exists()
//		validasi nama file
		assert file.getName().equals("products.csv")
//		validasi ukuran file
		assert file.length() > 0
//		validasi isi file
		String content = file.text
		
		assert content.contains("Wireless Headphones")
		assert content.contains("Electronic")
		assert content.contains("149")
	}
	
	@Keyword
	def openDetailProduct(String productName) {
		WebDriver driver = DriverFactory.getWebDriver()
				// Simpan window utama
				String mainWindow = driver.getWindowHandle()
//		open detail product
				tableHelper.clickPreviewAction(productName)
				
				// Pindah ke window baru
				for (String window : driver.getWindowHandles()) {
					if (window != mainWindow) {
						driver.switchTo().window(window)
						break
					}
				}
		WebUI.waitForElementVisible(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/img_previewImage'), 5)
		WebUI.verifyElementVisible(findTestObject('Object Repository/bolt/Page_E-commerce Microservices Demo/img_previewImage'))
	}
}
