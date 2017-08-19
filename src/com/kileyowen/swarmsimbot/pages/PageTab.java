
package com.kileyowen.swarmsimbot.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionTimeout;
import com.kileyowen.math.Count;
import com.kileyowen.math.Cost.Resource;
import com.kileyowen.swarmsim.utils.NullUtils;

abstract class PageTab<T extends PageTab<T, R>, R> extends PageBase {

	protected static enum Tab {
		Meat, Larvae, Options, Territory
	}

	private static final class XPath {

		static final String MORE = "//li[@role='tab' and @class='dropdown']/a[1]";

		static final String BUY_ALL_UPGRADES = "//a[@ng-click='buyAllUpgrades()']";

	}

	private final HashMap<Tab, PageTab<?, ?>> tabs;

	private final HashMap<R, PageRow<?>> rows;

	private final HashMap<Resource, R> resourceToRow;

	private final By more;

	private final By buyAllUpgrades;

	protected PageTab(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.tabs = new HashMap<>();
		this.rows = new HashMap<>();
		this.resourceToRow = new HashMap<>();

		this.more = PageBase.getByXPath(XPath.MORE);

		this.buyAllUpgrades = PageBase.getByXPath(XPath.BUY_ALL_UPGRADES);

	}

	protected final void buyUpgrades() throws ExceptionNull, ExceptionNoSuchElement {

		this.clickMore();
		this.clickBuyAllUpgrades();
		//		this.clickMore();
	}

	private final void clickBuyAllUpgrades() throws ExceptionNull, ExceptionNoSuchElement {

		this.getBuyAllUpgrades().click();
	}

	protected final void clickMore() throws ExceptionNull, ExceptionNoSuchElement {

		this.getMore().click();
	}

	private final void clickTab() throws ExceptionNull, ExceptionNoSuchElement {

		this.getTab().click();
	}

	private final WebElement getBuyAllUpgrades() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.buyAllUpgrades);
	}

	protected final Count getCount(final Resource resource, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionCountParse, ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final PageRow<?> row = this.getRowByResource(resource);
		row.switchToRow(timeoutInSeconds, pollIntervalInMs);
		return row.getCount();
	}

	private final WebElement getMore() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.more);
	}

	private final PageTab<?, ?> getOtherTab(final Tab tab) throws ExceptionNull {

		return NullUtils.assertNotNull(this.tabs.get(tab));
	}

	protected abstract T getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse;

	protected final PageRow<?> getRow(final R row) throws ExceptionNull {

		return NullUtils.assertNotNull(this.rows.get(row));
	}

	private final PageRow<?> getRowByResource(final Resource resource) throws ExceptionNull {

		return this.getRow(this.resourceToRow.get(resource));
	}

	protected final WebElement getTab() throws ExceptionNull, ExceptionNoSuchElement {

		return this.getElement(this.getTabBy());
	}

	protected abstract By getTabBy();

	protected final void init() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		this.setupRows();
		this.setupTabs();
	}

	protected abstract boolean isTabCurrentTab();

	protected final void setResourceToRow(final Resource key, final R value) {

		if (this.resourceToRow.containsKey(key)) {
			this.resourceToRow.replace(key, value);
		} else {
			this.resourceToRow.put(key, value);
		}
	}

	protected final void setRow(final R key, final PageRow<?> value) {

		if (this.rows.containsKey(key)) {
			this.rows.replace(key, value);
		} else {
			this.rows.put(key, value);
		}
	}

	private final void setTab(final Tab key, final PageTab<?, ?> value) {

		if (this.tabs.containsKey(key)) {
			this.tabs.replace(key, value);
		} else {
			this.tabs.put(key, value);
		}
	}

	protected abstract void setupRows() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse;

	private final void setupTabs() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		this.setTab(Tab.Meat, TabMeat.getPage(this.getManager()));
		this.setTab(Tab.Larvae, TabLarvae.getPage(this.getManager()));
		this.setTab(Tab.Options, TabOptions.getPage(this.getManager()));
		this.setTab(Tab.Territory, TabTerritory.getPage(this.getManager()));
	}

	protected final PageTab<?, ?> switchToOtherTab(final Tab tab) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse {

		return NullUtils.assertNotNull(this.getOtherTab(tab).switchToTab());
	}

	protected T switchToTab() throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse {

		if (!this.isTabCurrentTab()) {
			this.clickTab();
		}

		return this.getPage();
	}

}
