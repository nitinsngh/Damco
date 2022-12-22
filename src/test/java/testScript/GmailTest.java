package testScript;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.GmailPage;

public class GmailTest extends BaseClass {

	GmailPage ob;

	@Test(priority = 1, enabled = true)
	@Parameters(value = { "browser", "url" })
	public void gmailLogin() {
		try {
		ob = new GmailPage();
		ob.gmailLogin(); //method for sending email and password credentials
		ob.ComposeMail();// method for composing message and save it into draft section
		ob.openDraftMailandSend(); // method for open draft mail and send to inbox after putting recipient mail 
		ob.moveToInbox();// method for open the inbox
		Assert.assertTrue(ob.verifyMailReceived(), "Mail received");//Damco Mail received
		ob.openMain();
		Assert.assertTrue(ob.verifySubject(), "subject verified");
		Assert.assertTrue(ob.verifyMailBody(), "mail body verified");
		Assert.assertTrue(ob.verifyAttachmentDownloaded(), "attachment downloaded successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
	}

}
