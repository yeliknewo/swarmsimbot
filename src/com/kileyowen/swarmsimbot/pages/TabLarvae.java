
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.math.Cost.Resource;
import com.kileyowen.swarmsim.utils.ErrorMessages;
import com.kileyowen.swarmsim.utils.NullUtils;

class TabLarvae extends PageTab<TabLarvae, TabLarvae.Row> {

	private final static class Regex {

		static final String TAB_CURRENT = ".*/tab/larva.*";

	}

	static enum Row {
		Larva,
	}

	private final static class XPath {

		static final String TAB = "//a[contains(@ng-href, '#/tab/larva')]";

	}

	final static TabLarvae getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabLarvae.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabLarvae.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}

	}

	final static TabLarvae init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		final TabLarvae page = new TabLarvae(manager);
		manager.givePage(TabLarvae.class, page);
		page.init();
		return page;
	}

	private final By tab;

	private TabLarvae(final ManagerPage manager) throws ExceptionNull {
		super(manager);

		this.tab = PageBase.getByXPath(XPath.TAB);
	}

	@Override
	protected final TabLarvae getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabLarvae.getPage(this.getManager());
	}

	@Override
	protected final By getTabBy() {

		return this.tab;
	}

	@Override
	protected final boolean isTabCurrentTab() {

		return this.getDriver().getCurrentUrl().matches(Regex.TAB_CURRENT);
	}

	@Override
	protected final void setupRows() throws ExceptionImpossible, ExceptionNull {

		this.setRow(Row.Larva, TabLarvaeRowLarva.getPage(this.getManager()));

		this.setResourceToRow(Resource.LARVA, Row.Larva);
	}

	final TabMeat switchToTabMeat() throws ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabMeat) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Meat));
	}

	private final TabOptions switchToTabOptions() throws ExceptionNull, ExceptionNoSuchElement, ExceptionImpossible, ExceptionCountParse {

		this.clickMore();
		return (TabOptions) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Options));
	}

	final TabTerritory switchToTabTerritory() throws ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabTerritory) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Territory));
	}
}
