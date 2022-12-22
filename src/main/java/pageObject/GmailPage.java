package pageObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import baseClass.BaseClass;

public class GmailPage extends BaseClass {

	JavascriptExecutor jsExe = (JavascriptExecutor) driver;
	Actions act = new Actions(driver);
	
	private By emailTextBox = By.xpath("//*[@id='identifierId']");

	private By nextBtn = By.xpath("//span[text()='Next']");

	private By PasswordTextBox = By.xpath("//*[@type='password']");

	private By composeBtn = By.xpath("//*[text()='Compose']");

	private By EmailSubjectTextBox = By.xpath("//input[@name='subjectbox']/parent::div/input");

	private By EmailBodyTextArea = By.xpath("//div[@class='Am Al editable LW-avf tS-tW']");

	private By formatingBtn = By.xpath("//*[@data-tooltip='Formatting options']");

	private By formatingMoreButton = By.xpath("//*[@aria-label='More formatting options']");

	//private By BulletedList = By.xpath("//*[@command=\"+insertUnorderedList\"]/parent::td");
	private By BulletedList = By.xpath("//*[@class=\"eO  aaA aaB\"]");
	
	private By FileUpload = By.xpath("//input[@type='file']");

	private By saveAndCloseBtn = By.xpath("//*[@data-tooltip='Save & close']");

	private By draftBtn = By.xpath("//a[text()='Drafts']/parent::span");

	private By draftedMail = By
			.xpath("(//span[text()='Damco']/parent::span/ancestor::td/preceding-sibling::td[@role='gridcell'])[1]");

	private By draftedMail2 = By
			.xpath("(//span[text()='Damco']/parent::span/ancestor::td/preceding-sibling::td[@role='gridcell'])[2]");

	private By recipientsField = By.xpath("//div[text()='Recipients']/parent::div");

	private By toField = By.xpath("//input[@role='combobox']");

	private By sendBtn = By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']");

	private By inboxBtn = By.xpath("//a[text()='Inbox']/parent::span");

	private By receivedMail = By.xpath("//*[@class='bog']/span[text()='Damco']/ancestor::td/parent::tr");

	private By receivedMailTime = By
			.xpath("//*[@class='bog']/span[text()='Damco']/ancestor::td/following-sibling::td/span/span");

	private By inmailSubject = By
			.xpath("//*[@data-tooltip='Search for all messages with label Inbox']/ancestor::div[@class='ha']/h2");

	private By mailBody = By.xpath("//div[@dir='ltr']/ul");

	private By imglink = By.xpath("//img[@class='aQG aYB']");

	private By attachmentDownloadBtn = By.xpath("//*[@data-tooltip='Download']");

	private By openMailDeleteBtn = By.xpath("//div[@aria-label='Delete']/div");

	

	public void gmailLogin() {
		try {
			System.out.println();
			driver.findElement(emailTextBox).sendKeys("nitinsngh7868@gmail.com");
			driver.findElement(nextBtn).click();
			waitForVisibility(PasswordTextBox, 20);
			driver.findElement(PasswordTextBox).sendKeys("sngh!1234");
			driver.findElement(nextBtn).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void ComposeMail() {
		try {
			waitForVisibility(composeBtn, 20);
			driver.findElement(composeBtn).click();
			//action.moveToElement(driver.findElement(composeBtn)).click().build().perform();
			driver.findElement(EmailSubjectTextBox).click();
			driver.findElement(EmailSubjectTextBox).sendKeys("Damco");

			driver.findElement(EmailBodyTextArea).sendKeys("Line one" + "\r\n" + "Line two" + "\r\n" + "Line three");

			driver.findElement(EmailBodyTextArea).sendKeys(Keys.CONTROL + "A");
			waitForVisibility(formatingBtn, 20);
			driver.findElement(formatingBtn).click();
			waitForVisibility(formatingMoreButton, 20);
			driver.findElement(formatingMoreButton).click();
			driver.findElement(BulletedList).click();
			driver.findElement(formatingBtn).click();
			String filePath = System.getProperty("user.dir") + "\\testData\\Java_Logo.jpg";
			driver.findElement(FileUpload).sendKeys(filePath);
			waitForVisibility(saveAndCloseBtn, 20);
			driver.findElement(saveAndCloseBtn).click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void openDraftMailandSend() {
		try {
			driver.findElement(draftBtn).click();
			waitForElementClickable(draftedMail, 20);
			driver.findElement(draftedMail).click();
			waitForElementClickable(sendBtn, 20);
			driver.findElement(toField).sendKeys("nitinsngh7868@gmail.com");
			driver.findElement(toField).sendKeys(Keys.ENTER);
			driver.findElement(sendBtn).click();
		} catch (Exception e) {
			driver.findElement(draftBtn).click();
			waitForElementClickable(draftedMail2, 20);
			driver.findElement(draftedMail2).click();
			waitForElementClickable(sendBtn, 20);
			driver.findElement(toField).sendKeys("nitinsngh7868@gmail.com");
			driver.findElement(toField).sendKeys(Keys.ENTER);
			driver.findElement(sendBtn).click();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void moveToInbox() {
		try {
			waitForVisibility(inboxBtn, 20);
			driver.findElement(BulletedList).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public boolean verifyMailReceived() {
		boolean received = true;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime now = LocalDateTime.now();
			String time = dtf.format(now);
			if (driver.findElement(receivedMail).isDisplayed()
					&& driver.findElement(receivedMailTime).getText().equals(time)) {
				received = true;
			}
		} catch (Exception e) {
			return received;
		}
		return received;
	}

	public void openMain() {
		try {
			driver.findElement(receivedMail).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public boolean verifySubject() {
		boolean subjectMatched = false;
		try {
			if (driver.findElement(inmailSubject).isDisplayed()
					&& driver.findElement(inmailSubject).getText().equals("Damco")) {
				subjectMatched = true;
			}
		} catch (Exception e) {
			return subjectMatched;
		}
		return subjectMatched;
	}

	public boolean verifyMailBody() {
		boolean visible = false;
		try {
			if (driver.findElement(mailBody).isDisplayed()) {
				visible = true;
			}
		} catch (Exception e) {
			return visible;
		}
		return visible;
	}

	public boolean verifyAttachmentDownloaded() {
		boolean downloaded = false;
		try {
			act.moveToElement(driver.findElement(imglink)).build().perform();
			if (driver.findElement(attachmentDownloadBtn).isDisplayed()) {
				driver.findElement(attachmentDownloadBtn).click();
				downloaded = true;
			}
		} catch (Exception e) {
			return downloaded;
		}
		return downloaded;
	}

	public void deleteOpenedMail() {
		try {
//			openMailDeleteBtn.click();
			act.moveToElement(driver.findElement(openMailDeleteBtn)).build().perform();
			jsExe.executeScript("arguments[0].click();", openMailDeleteBtn);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
