
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.math.Count;
import com.kileyowen.swarmsim.utils.NullUtils;

abstract class PageCount extends PageBase {

	private final static class XPath {

		static final String COUNTER = "//unit/p[1]/*[1]";
	}

	private final By counter;

	protected PageCount(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);
		this.counter = PageBase.getByXPath(XPath.COUNTER);
	}

	final Count getCount() throws ExceptionCountParse, ExceptionNull, ExceptionNoSuchElement {

		return Count.parse(this.getCounterText());
	}

	private final WebElement getCounter() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.counter);
	}

	private String getCounterText() throws ExceptionNull, ExceptionNoSuchElement {

		return NullUtils.assertNotNull(this.getCounter().getText());
	}
}
