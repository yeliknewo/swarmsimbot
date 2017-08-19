
package com.kileyowen.selenium.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.swarmsim.utils.NullUtils;

public class Element {

	final static Element make(final WebDriver driver, final By by) throws ExceptionNull {

		return new Element(driver, by);
	}

	private WebElement webElement;

	private final By by;

	private Element(final WebDriver driver, final By newBy) throws ExceptionNull {

		this.by = newBy;

		this.webElement = NullUtils.assertNotNull(driver.findElement(this.by));

	}

	final boolean isValid() {

		return this.webElement.isDisplayed();

	}

	final void update(final WebDriver driver) throws ExceptionNull {

		this.webElement = NullUtils.assertNotNull(driver.findElement(this.by));

	}

}
