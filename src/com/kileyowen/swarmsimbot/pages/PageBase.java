
package com.kileyowen.swarmsimbot.pages;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionTimeout;
import com.kileyowen.swarmsim.utils.NullUtils;

abstract class PageBase {

	final protected static By getByXPath(final String xpath) throws ExceptionNull {

		return NullUtils.assertNotNull(By.xpath(xpath));
	}

	private final ManagerPage manager;

	protected PageBase(final ManagerPage newManager) {
		this.manager = newManager;
	}

	final protected WebDriver getDriver() {

		return this.manager.getDriver();
	}

	final protected WebElement getElement(final By by) throws ExceptionNull, ExceptionNoSuchElement {

		try {
			return NullUtils.assertNotNull(this.getDriver().findElement(by));
		} catch (final NoSuchElementException e) {
			throw new ExceptionNoSuchElement("Unable to get element", e);
		}

	}

	final protected ManagerPage getManager() {

		return this.manager;
	}

	final protected void waitForTextMatching(final By by, final String regex, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionTimeout {

		try {
			new WebDriverWait(this.getDriver(), timeoutInSeconds).pollingEvery(pollIntervalInMs, TimeUnit.MILLISECONDS).until(ExpectedConditions.textMatches(by, Pattern.compile(regex)));
		} catch (final TimeoutException e) {
			throw new ExceptionTimeout("Waiting for text matching timed out", e);
		}
	}
}
