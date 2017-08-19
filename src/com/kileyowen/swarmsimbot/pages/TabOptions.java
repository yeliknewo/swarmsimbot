
package com.kileyowen.swarmsimbot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.swarmsim.utils.ErrorMessages;

class TabOptions extends PageTab<TabOptions, TabOptions.Row> {

	private static final class Regex {

		static final String TAB_CURRENT = ".*/options.*";
	}

	static enum Row {
		// No Rows on this page
	}

	private static final class XPath {

		static final String TAB = "//a[@href='#/options']";

		static final String SCIENTIFIC_NOTATION = "//label[text()='Scientific-E']/input";

		static final String LOGIN_EMAIL = "//input[@name='playfab-email']";

		static final String LOGIN_PASSWORD = "//input[@name='playfab-password']";

		static final String LOGIN_BUTTON = "//button[@ng-click='runLogin()']";

		static final String PULL = "//button[@ng-click='pull()']";
	}

	final static TabOptions getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabOptions.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabOptions.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	final static TabOptions init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		final TabOptions page = new TabOptions(manager);
		manager.givePage(TabOptions.class, page);
		page.init();
		return page;
	}

	private final By pull;

	private final By tab;

	private final By scientificNotation;

	private final By loginEmail;

	private final By loginPassword;

	private final By loginButton;

	protected TabOptions(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.tab = PageBase.getByXPath(XPath.TAB);

		this.scientificNotation = PageBase.getByXPath(XPath.SCIENTIFIC_NOTATION);

		this.loginEmail = PageBase.getByXPath(XPath.LOGIN_EMAIL);

		this.loginPassword = PageBase.getByXPath(XPath.LOGIN_PASSWORD);

		this.loginButton = PageBase.getByXPath(XPath.LOGIN_BUTTON);

		this.pull = PageBase.getByXPath(XPath.PULL);

	}

	private final void clickLoginButton() throws ExceptionNull, ExceptionNoSuchElement {

		this.getLoginButton().click();
	}

	private void clickPull() throws ExceptionNull, ExceptionNoSuchElement {

		this.getPull().click();

	}

	private final void clickScientificNotation() throws ExceptionNull, ExceptionNoSuchElement {

		this.getScientificNotation().click();
	}

	private void enterEmail(final String email) throws ExceptionNull, ExceptionNoSuchElement {

		this.selectLoginEmail();
		new Actions(this.getDriver()).sendKeys(email).perform();

	}

	private void enterPassword(final String password) throws ExceptionNull, ExceptionNoSuchElement {

		this.selectLoginPassword();
		new Actions(this.getDriver()).sendKeys(password).perform();

	}

	private final WebElement getLoginButton() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.loginButton);
	}

	private final WebElement getLoginEmail() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.loginEmail);
	}

	private final WebElement getLoginPassword() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.loginPassword);
	}

	@Override
	protected TabOptions getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabOptions.getPage(this.getManager());
	}

	private WebElement getPull() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.pull);
	}

	private final WebElement getScientificNotation() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.scientificNotation);
	}

	@Override
	protected By getTabBy() {

		return this.tab;
	}

	@Override
	protected boolean isTabCurrentTab() {

		return this.getDriver().getCurrentUrl().matches(Regex.TAB_CURRENT);
	}

	final void login(final String email, final String password) throws ExceptionNull, ExceptionNoSuchElement {

		this.enterEmail(email);
		this.enterPassword(password);
		this.clickLoginButton();
		this.waitForPull();
		this.clickPull();
	}

	private final void selectLoginEmail() throws ExceptionNull, ExceptionNoSuchElement {

		this.getLoginEmail().click();
	}

	private final void selectLoginPassword() throws ExceptionNull, ExceptionNoSuchElement {

		this.getLoginPassword().click();
	}

	final void setScientificNotation() throws ExceptionNull, ExceptionNoSuchElement {

		this.clickScientificNotation();
	}

	@Override
	protected void setupRows() {
		// No Rows on Tab

	}

	@Override
	protected TabOptions switchToTab() throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse {

		this.clickMore();
		return super.switchToTab();
	}

	private void waitForPull() {

		new WebDriverWait(this.getDriver(), 0).withTimeout(5, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.elementToBeClickable(this.pull));

	}
}
