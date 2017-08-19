
package com.kileyowen.selenium.core;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.swarmsim.utils.NullUtils;

public class ManagerElement {

	public static final ManagerElement init(final WebDriver driver) {

		return new ManagerElement(driver);
	}

	private final WebDriver driver;

	private final HashMap<By, Element> elements;

	private ManagerElement(final WebDriver newDriver) {

		this.driver = newDriver;

		this.elements = new HashMap<>();

	}

	public final Element getElement(final By by) throws ExceptionNull, ExceptionImpossible {

		if (this.getElements().containsKey(by)) {

			final Element element = NullUtils.assertNotNull(this.getElements().get(by));

			if (element.isValid()) {

				return element;

			}

		}

		return this.setElementWithReturn(by);

	}

	public HashMap<By, Element> getElements() {

		return this.elements;

	}

	public final void setElement(final By by) throws ExceptionImpossible, ExceptionNull {

		this.setElementWithReturn(by);

	}

	private final Element setElementWithReturn(final By by) throws ExceptionImpossible, ExceptionNull {

		if (this.getElements().containsKey(by)) {

			final Element elementOld = NullUtils.assertNotNull(this.getElements().get(by));

			if (elementOld.isValid()) {

				throw new ExceptionImpossible("Element was valid after set element was called");

			}

			elementOld.update(this.driver);

			return elementOld;

		}

		final Element element = Element.make(this.driver, by);

		this.getElements().put(by, element);

		return element;

	}

}
