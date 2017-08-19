
package com.kileyowen.swarmsimbot.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.exceptions.ExceptionTimeout;
import com.kileyowen.math.Cost;
import com.kileyowen.math.Count;
import com.kileyowen.math.Cost.Resource;
import com.kileyowen.swarmsim.utils.ErrorMessages;
import com.kileyowen.swarmsim.utils.NullUtils;

class TabTerritory extends PageTab<TabTerritory, TabTerritory.Row> {

	private final static class Regex {

		static final String TAB_CURRENT = ".*/tab/territory.*";

	}

	static enum Row {
		Territory, Swarmling, Stinger, Arachnomorph, Culicimorph, Locust, Roach, GiantArachnomorph, Chilopodomorph, Wasp, Devourer, Goon,
	}

	private final static class XPath {

		static final String TAB = "//a[contains(@ng-href, '#/tab/territory')]";

	}

	final static TabTerritory getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabTerritory.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabTerritory.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	final static TabTerritory init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		final TabTerritory page = new TabTerritory(manager);
		manager.givePage(TabTerritory.class, page);
		page.init();
		return page;
	}

	private final By tab;

	protected TabTerritory(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.tab = PageBase.getByXPath(XPath.TAB);
	}

	@Override
	protected TabTerritory getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabTerritory.getPage(this.getManager());
	}

	private final TabTerritoryRowSwarmling getRowSwarmling() throws ExceptionNull {

		return (TabTerritoryRowSwarmling) this.getRow(Row.Swarmling);
	}

	@Override
	protected By getTabBy() {

		return this.tab;
	}

	final void hatchSwarmlingsToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costSwarmling = this.getRowSwarmling().getCost();

		final boolean run = this.getCount(Resource.SWARMLING, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {
			this.buyUpgrades();

			final Cost stocks = new Cost();

			{
				final TabMeat meat = this.switchToTabMeat();
				stocks.setCost(Resource.MEAT, meat.getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
				final TabLarvae larvae = meat.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				larvae.switchToTabTerritory();
			}

			final List<Resource> demand = costSwarmling.getDemand(stocks);

			if (demand.contains(Resource.MEAT)) {
				final TabMeat meat = this.switchToTabMeat();
				meat.hatchDronesToCount(Count.parse(100), timeoutInSeconds, pollIntervalInMs);
				meat.switchToTabTerritory();
			}

			while (!this.getRowSwarmling().safelyBuyMax()) {

				try {
					this.getRowSwarmling().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}
		}
	}

	@Override
	protected final boolean isTabCurrentTab() {

		return this.getDriver().getCurrentUrl().matches(Regex.TAB_CURRENT);
	}

	@Override
	protected void setupRows() throws ExceptionImpossible, ExceptionNull {

		// TODO Add All Rows

		this.setRow(Row.Swarmling, TabTerritoryRowSwarmling.getPage(this.getManager()));

		this.setResourceToRow(Resource.SWARMLING, Row.Swarmling);
	}

	private final TabLarvae switchToTabLarvae() throws ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabLarvae) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Larvae));
	}

	private final TabMeat switchToTabMeat() throws ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabMeat) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Meat));
	}

	private final TabOptions switchToTabOptions() throws ExceptionNull, ExceptionNoSuchElement, ExceptionImpossible, ExceptionCountParse {

		this.clickMore();
		return (TabOptions) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Options));
	}
}
