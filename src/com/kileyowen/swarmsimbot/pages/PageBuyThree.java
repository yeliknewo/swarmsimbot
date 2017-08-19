
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionTimeout;
import com.kileyowen.math.Cost;

abstract class PageBuyThree<T> extends PageRow<T> {

	static final class Regex {

		static final String HATCH = ".*[hH]atch .";

		static final String BUY = ".*[bB]uy .";

		static final String BUILD = ".*[bB]uild .";
	}

	private final static class XPath {

		static final String BUY_ONE = "//a[@ng-click='buyResource({resource:resource, num:fullnum()})']";

		static final String BUY_QUARTER = "//a[@ng-click='buyMaxResource({resource:resource, percent:0.25})']";

		static final String BUY_MAX = "//a[@ng-click='buyMaxResource({resource:resource, percent:1})']";

	}

	private final By buyMax;

	private final By buyQuarter;

	private final By buyOne;

	protected PageBuyThree(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);
		this.buyMax = PageBase.getByXPath(XPath.BUY_MAX);
		this.buyQuarter = PageBase.getByXPath(XPath.BUY_QUARTER);
		this.buyOne = PageBase.getByXPath(XPath.BUY_ONE);

	}

	private final boolean buyMax() throws ExceptionNull {

		try {
			this.clickBuyMax();

			return true;
		} catch (final ExceptionNoSuchElement e) {
			return false;
		}

	}

	private final boolean buyOne() throws ExceptionNull {

		try {
			this.clickBuyOne();
			return true;
		} catch (final ExceptionNoSuchElement e) {
			return false;
		}
	}

	private final boolean buyQuarter() throws ExceptionNull {

		try {
			this.clickBuyQuarter();
			return true;
		} catch (final ExceptionNoSuchElement e) {
			return false;
		}
	}

	protected final boolean canBuy(final WebElement element) {

		return element.getText().matches(this.getRegexBuy());
	}

	//	private final boolean canBuyMax() throws ExceptionNull {
	//
	//		try {
	//			return this.canBuy(this.getBuyMax());
	//		} catch (final ExceptionNoSuchElement e) {
	//			return false;
	//		}
	//	}
	//
	//	private final boolean canBuyOne() throws ExceptionNull {
	//
	//		try {
	//			return this.canBuy(this.getBuyOne());
	//		} catch (final ExceptionNoSuchElement e) {
	//			return false;
	//		}
	//	}
	//
	//	private final boolean canBuyQuarter() throws ExceptionNull {
	//
	//		try {
	//			return this.canBuy(this.getBuyQuarter());
	//		} catch (final ExceptionNoSuchElement e) {
	//			return false;
	//		}
	//	}

	private final void clickBuyMax() throws ExceptionNull, ExceptionNoSuchElement {

		this.getBuyMax().click();
	}

	private final void clickBuyOne() throws ExceptionNull, ExceptionNoSuchElement {

		this.getBuyOne().click();
	}

	private final void clickBuyQuarter() throws ExceptionNull, ExceptionNoSuchElement {

		this.getBuyQuarter().click();
	}

	private final WebElement getBuyMax() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.buyMax);
	}

	private final WebElement getBuyOne() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.buyOne);
	}

	private final WebElement getBuyQuarter() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.buyQuarter);
	}

	abstract Cost getCost();

	protected abstract String getRegexBuy();

	final boolean safelyBuyMax() throws ExceptionNull {

		return this.buyMax() || this.buyQuarter() || this.buyOne();
	}

	final void waitForBuyMax(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionTimeout, ExceptionCountParse {

		this.switchToRow(timeoutInSeconds, pollIntervalInMs);
		this.waitForTextMatching(this.buyMax, this.getRegexBuy(), timeoutInSeconds, pollIntervalInMs);
	}

	final void waitForBuyOne(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionTimeout, ExceptionCountParse {

		this.switchToRow(timeoutInSeconds, pollIntervalInMs);
		this.waitForTextMatching(this.buyOne, this.getRegexBuy(), timeoutInSeconds, pollIntervalInMs);
	}

	final void waitForBuyQuarter(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionTimeout, ExceptionCountParse {

		this.switchToRow(timeoutInSeconds, pollIntervalInMs);
		this.waitForTextMatching(this.buyQuarter, this.getRegexBuy(), timeoutInSeconds, pollIntervalInMs);
	}
}
