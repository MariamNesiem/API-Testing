package steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import pojo.*;
 
public class Hooks {
	static String AccessToken;
	WebDriver driver;
	LoginPage loginPage;
	String firstKey;
	String sessionToken;
	LocalStorage LS;
	
	@Before
    public void beforeScenario() throws InterruptedException{
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\mnesiem\\Downloads\\chromedriver\\chromedriver.exe");					
			driver=new ChromeDriver();        
			driver.navigate().to("https://platform-qa.aamlive.com/");
			loginPage=new LoginPage(driver); 
			loginPage.Login("aam-integrantdevgroup@aamlive.com",".NetCore@999");
			waitForPageLoaded(driver);
			LS=new LocalStorage(driver);
		    firstKey=LS.getKeyFromSessionStorage(0);
			 sessionToken=LS.getItemFromSessionStorage(firstKey);
		//	 firstKey=LS.getKeyFromLocalStorage(0);
		//	 sessionToken=LS.getItemFromLocalStorage(firstKey);
			//AccessToken="Bearer "+betweenStrings(sessionToken,"\"access_token\":\"","\",\"token_type\"");
			fileLocalStorage(sessionToken,firstKey);   
			LS.clearSessionStorage();
		    LS.clearLocalStorage();
    } 
 

	@After
    public void afterScenario(){
		
    LS.clearSessionStorage();
    LS.clearLocalStorage();
    driver.close();
 
    }
 
 public void waitForPageLoaded(WebDriver driver) {
     ExpectedCondition<Boolean> pageLoadCondition = new
             ExpectedCondition<Boolean>() {
                 public Boolean apply(WebDriver driver) {
                     return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                 }
             };
     WebDriverWait wait = new WebDriverWait(driver, 30);
     wait.until(pageLoadCondition);
 }
 
 public void fileLocalStorage(String sessionToken,String firstKey) {
	 
 File file = new File("Storage.data");							
 try		
 {	  
     // Delete old file if exists
	 file.delete();		
     file.createNewFile();			
     FileWriter fileWrite = new FileWriter(file);							
     BufferedWriter Bwrite = new BufferedWriter(fileWrite);									
		
         Bwrite.write("Access Token: "+AccessToken);																									
         Bwrite.newLine();             
         Bwrite.write("First Key: "+firstKey);	
         Bwrite.newLine();             
         Bwrite.write("sessionStorage: "+sessionToken);
     Bwrite.close();			
     fileWrite.close();	
     
 }
 catch(Exception ex)					
 {		
     ex.printStackTrace();			
 }	
 
 }	

 public String betweenStrings(String text, String start, String end)
 {
		String[] parts = text.split(start);
		String[] parts2 = parts[1].split(end);
        return parts2[0];                      
 }
}		

