package pojo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	public WebElement username;
	public WebElement password;
	public WebElement loginbutton;
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
		this.username=driver.findElement(By.id("username"));
		this.password=driver.findElement(By.id("password"));
		this.loginbutton=driver.findElement(By.id("Login"));
	}
	
	
	public void Login(String username,String password) {
	    this.username.sendKeys(username);	
	    this.password.sendKeys(password);							
	    this.loginbutton.click();
	}
	
}
