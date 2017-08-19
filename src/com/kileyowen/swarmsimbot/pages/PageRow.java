
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionTimeout;
import com.kileyowen.swarmsim.utils.NullUtils;

abstract class PageRow<T> extends PageCount {

	private final static class XPath {

		static final String ROW_CURRENT = "/html/body/div[1]/div[6]/div/div/div/div[1]/h3/a/span[1]";
	}

	private final By rowCurrent;

	protected PageRow(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);
		this.rowCurrent = PageBase.getByXPath(XPath.ROW_CURRENT);
	}

	private final void clickRow() throws ExceptionNull, ExceptionNoSuchElement {

		this.getRow().click();
	}

	protected abstract T getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse;

	private final WebElement getRow() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.getRowBy());
	}

	protected abstract By getRowBy();

	private final WebElement getRowCurrent() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.rowCurrent);
	}

	protected String getRowCurrentText() throws ExceptionNull, ExceptionNoSuchElement {

		return NullUtils.assertNotNull(this.getRowCurrent().getText());
	}

	protected abstract String getRowName();

	private boolean isRowCurrentRow() throws ExceptionNull {

		try {
			return this.getRowCurrentText().equals(this.getRowName());
		} catch (final ExceptionNoSuchElement e) {
			return false;
		}
	}

	protected T switchToRow(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse, ExceptionTimeout {

		if (!this.isRowCurrentRow()) {
			this.clickRow();
		}

		this.waitForRowLoaded(timeoutInSeconds, pollIntervalInMs);

		return this.getPage();
	}

	private void waitForRowLoaded(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionTimeout {

		this.waitForTextMatching(this.rowCurrent, this.getRowName(), timeoutInSeconds, pollIntervalInMs);
	}
}
