package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClass.BaseClass;
import utilities.ExcelUtility;

public class SubmitForm extends BaseClass{
	
	@FindBy(id = "control-0")
	WebElement name;
	@FindBy(id = "control-1")
	WebElement email;
	@FindBy(id = "control-2")
	WebElement jobTitle;
	@FindBy(id = "control-3")
	WebElement compName;
	@FindBy(id = "control-5")
	WebElement comments;
	@FindBy(className = "cu-form__submit")
	WebElement submitBtn;
	@FindBy(xpath = "//*[@id='0']/following-sibling::h2")
	WebElement msg;
	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	WebElement captcha;
	@FindBy(xpath = "//div[@aria-haspopup='listbox']")
	WebElement dropdown;
	
	public SubmitForm()
	{
		PageFactory.initElements(driver, this);
	}
	public void verifyPageTitle()
	{
		String title = driver.getTitle();
		assertEquals(title, "Feel like your automation could be better? Get a second pair of eyes on your automation.");
	}
	public void fillDetails(String Data[]) {
		System.out.println(dataExcel.getRowCount());
		System.out.println(dataExcel.getColumnCount());
		name.sendKeys(Data[0]);
		email.sendKeys(Data[1]);
		jobTitle.sendKeys(Data[2]);
		compName.sendKeys(Data[3]);
		dropdown.click();
		driver.findElement(By.xpath("//*[contains(text(),' "+Data[4]+"')]")).click();
		comments.sendKeys(Data[5]);
		driver.switchTo().frame(captcha);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@aria-checked='true']")));
		driver.switchTo().defaultContent();
		submitBtn.click();
		wait.until(ExpectedConditions.visibilityOf(msg));
		assertEquals(msg.getText(), "Thank You!");
		
		
	}
}
