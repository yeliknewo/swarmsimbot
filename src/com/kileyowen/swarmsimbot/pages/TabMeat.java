
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

class TabMeat extends PageTab<TabMeat, TabMeat.Row> {

	private static final class Regex {

		static final String TAB_CURRENT = ".*/tab/meat.*";
	}

	static enum Row {
		MEAT, DRONE, QUEEN, NEST, GREATER_QUEEN, HIVE, HIVE_QUEEN
	}

	private final static class XPath {

		static final String TAB = "//a[contains(@ng-href, '#/tab/meat')]";
	}

	final static TabMeat getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabMeat.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeat.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeat init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		final TabMeat page = new TabMeat(manager);
		manager.givePage(TabMeat.class, page);
		page.init();
		return page;
	}

	private final By tab;

	protected TabMeat(final ManagerPage manager) throws ExceptionNull {
		super(manager);

		this.tab = PageBase.getByXPath(XPath.TAB);

	}

	@Override
	protected final TabMeat getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabMeat.getPage(this.getManager());
	}

	private final TabMeatRowDrone getRowDrone() throws ExceptionNull {

		return (TabMeatRowDrone) this.getRow(Row.DRONE);
	}

	private final TabMeatRowGreaterQueen getRowGreaterQueen() throws ExceptionNull {

		return (TabMeatRowGreaterQueen) this.getRow(Row.GREATER_QUEEN);
	}

	private final TabMeatRowHive getRowHive() throws ExceptionNull {

		return (TabMeatRowHive) this.getRow(Row.HIVE);
	}

	private TabMeatRowHiveQueen getRowHiveQueen() throws ExceptionNull {

		return (TabMeatRowHiveQueen) this.getRow(Row.HIVE_QUEEN);
	}

	private final TabMeatRowNest getRowNest() throws ExceptionNull {

		return (TabMeatRowNest) this.getRow(Row.NEST);
	}

	private final TabMeatRowQueen getRowQueen() throws ExceptionNull {

		return (TabMeatRowQueen) this.getRow(Row.QUEEN);
	}

	@Override
	protected final By getTabBy() {

		return this.tab;
	}

	final void hatchDronesToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse, ExceptionTimeout {

		final TabMeatRowDrone drone = this.getRowDrone().switchToRow(timeoutInSeconds, pollIntervalInMs);

		boolean run = drone.getCount().lessThan(target);

		while (run) {
			this.buyUpgrades();

			while (!drone.safelyBuyMax()) {
				try {
					drone.waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			try {
				run = drone.getCount().lessThan(target);
			} catch (final ExceptionCountParse e) {
				e.printStackTrace();
			}
		}

	}

	final void hatchGreaterQueenToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costGreaterQueen = this.getRowGreaterQueen().getCost();

		boolean run = this.getCount(Resource.GREATER_QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {
			this.buyUpgrades();

			final Cost stocks = new Cost();

			stocks.setCost(Resource.NEST, this.getCount(Resource.NEST, timeoutInSeconds, pollIntervalInMs));
			{
				final TabLarvae larvae = this.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				stocks.setCost(Resource.MEAT, larvae.switchToTabMeat().getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
			}

			final List<Resource> demand = costGreaterQueen.getDemand(stocks);

			if (demand.contains(Resource.NEST)) {
				this.hatchNestToCount(costGreaterQueen.getCount(Resource.NEST), timeoutInSeconds, pollIntervalInMs);
				this.getRowGreaterQueen().switchToRow(timeoutInSeconds, pollIntervalInMs);
			}

			while (!this.getRowGreaterQueen().safelyBuyMax()) {
				try {
					this.getRowGreaterQueen().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			run = this.getCount(Resource.GREATER_QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);
		}
	}

	final void hatchHiveQueenLazy(final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionNoSuchElement, ExceptionImpossible, ExceptionCountParse {

		while (true) {
			this.buyUpgrades();

			while (!this.getRowHiveQueen().safelyBuyMax()) {
				try {
					this.getRowHiveQueen().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}
		}
	}

	final void hatchHiveQueenToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costHiveQueen = this.getRowHiveQueen().getCost();

		boolean run = this.getCount(Resource.HIVE_QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {
			this.buyUpgrades();

			final Cost stocks = new Cost();

			stocks.setCost(Resource.HIVE, this.getCount(Resource.HIVE, timeoutInSeconds, pollIntervalInMs));
			{
				final TabLarvae larvae = this.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				stocks.setCost(Resource.MEAT, larvae.switchToTabMeat().getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
			}

			final List<Resource> demand = costHiveQueen.getDemand(stocks);

			if (demand.contains(Resource.HIVE)) {
				this.hatchHiveToCount(costHiveQueen.getCount(Resource.HIVE), timeoutInSeconds, pollIntervalInMs);
				this.getRowHiveQueen().switchToRow(timeoutInSeconds, pollIntervalInMs);
			}

			while (!this.getRowHiveQueen().safelyBuyMax()) {
				try {
					this.getRowHiveQueen().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			run = this.getCount(Resource.HIVE_QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);
		}
	}

	final void hatchHiveToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costHive = this.getRowHive().getCost();

		boolean run = this.getCount(Resource.HIVE, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {
			this.buyUpgrades();

			final Cost stocks = new Cost();

			stocks.setCost(Resource.GREATER_QUEEN, this.getCount(Resource.GREATER_QUEEN, timeoutInSeconds, pollIntervalInMs));
			{
				final TabLarvae larvae = this.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				stocks.setCost(Resource.MEAT, larvae.switchToTabMeat().getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
			}

			final List<Resource> demand = costHive.getDemand(stocks);

			if (demand.contains(Resource.GREATER_QUEEN)) {
				this.hatchGreaterQueenToCount(costHive.getCount(Resource.GREATER_QUEEN), timeoutInSeconds, pollIntervalInMs);
				this.getRowHive().switchToRow(timeoutInSeconds, pollIntervalInMs);
			}

			while (!this.getRowHive().safelyBuyMax()) {
				try {
					this.getRowHive().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			run = this.getCount(Resource.HIVE, timeoutInSeconds, pollIntervalInMs).lessThan(target);
		}
	}

	final void hatchNestToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costNest = this.getRowNest().getCost();

		boolean run = this.getCount(Resource.NEST, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {
			this.buyUpgrades();

			final Cost stocks = new Cost();

			stocks.setCost(Resource.QUEEN, this.getCount(Resource.QUEEN, timeoutInSeconds, pollIntervalInMs));
			{
				final TabLarvae larvae = this.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				stocks.setCost(Resource.MEAT, larvae.switchToTabMeat().getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
			}

			final List<Resource> demand = costNest.getDemand(stocks);

			if (demand.contains(Resource.QUEEN)) {
				this.hatchQueensToCount(costNest.getCount(Resource.QUEEN), timeoutInSeconds, pollIntervalInMs);
				this.getRowNest().switchToRow(timeoutInSeconds, pollIntervalInMs);
			}

			while (!this.getRowNest().safelyBuyMax()) {
				try {
					this.getRowNest().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			run = this.getCount(Resource.NEST, timeoutInSeconds, pollIntervalInMs).lessThan(target);
		}
	}

	final void hatchQueensToCount(final Count target, final int timeoutInSeconds, final int pollIntervalInMs) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse, ExceptionNoSuchElement, ExceptionTimeout {

		final Cost costQueen = this.getRowQueen().getCost();

		boolean run = this.getCount(Resource.QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);

		while (run) {

			this.buyUpgrades();

			final Cost stocks = new Cost();

			stocks.setCost(Resource.DRONE, this.getCount(Resource.DRONE, timeoutInSeconds, pollIntervalInMs));
			{
				final TabLarvae larvae = this.switchToTabLarvae();
				stocks.setCost(Resource.LARVA, larvae.getCount(Resource.LARVA, timeoutInSeconds, pollIntervalInMs));
				stocks.setCost(Resource.MEAT, larvae.switchToTabMeat().getCount(Resource.MEAT, timeoutInSeconds, pollIntervalInMs));
			}

			if (this.getRowQueen().getCount().lessThan(stocks.getCount(Resource.LARVA))) {
				this.getRowDrone().switchToRow(timeoutInSeconds, pollIntervalInMs).safelyBuyMax();
			}

			final List<Resource> demand = costQueen.getDemand(stocks);

			if (demand.contains(Resource.DRONE)) {
				this.hatchDronesToCount(costQueen.getCount(Resource.DRONE), timeoutInSeconds, pollIntervalInMs);
				this.getRowQueen().switchToRow(timeoutInSeconds, pollIntervalInMs);
			}

			while (!this.getRowQueen().safelyBuyMax()) {
				try {
					this.getRowQueen().waitForBuyOne(timeoutInSeconds, pollIntervalInMs);
				} catch (final ExceptionTimeout e) {
					break;
				}
			}

			run = this.getCount(Resource.QUEEN, timeoutInSeconds, pollIntervalInMs).lessThan(target);
		}

	}

	@Override
	protected final boolean isTabCurrentTab() {

		return this.getDriver().getCurrentUrl().matches(Regex.TAB_CURRENT);
	}

	@Override
	protected final void setupRows() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		this.setRow(Row.MEAT, TabMeatRowMeat.getPage(this.getManager()));
		this.setRow(Row.DRONE, TabMeatRowDrone.getPage(this.getManager()));
		this.setRow(Row.QUEEN, TabMeatRowQueen.getPage(this.getManager()));
		this.setRow(Row.NEST, TabMeatRowNest.getPage(this.getManager()));
		this.setRow(Row.GREATER_QUEEN, TabMeatRowGreaterQueen.getPage(this.getManager()));
		this.setRow(Row.HIVE, TabMeatRowHive.getPage(this.getManager()));
		this.setRow(Row.HIVE_QUEEN, TabMeatRowHiveQueen.getPage(this.getManager()));

		this.setResourceToRow(Resource.MEAT, Row.MEAT);
		this.setResourceToRow(Resource.DRONE, Row.DRONE);
		this.setResourceToRow(Resource.QUEEN, Row.QUEEN);
		this.setResourceToRow(Resource.NEST, Row.NEST);
		this.setResourceToRow(Resource.GREATER_QUEEN, Row.GREATER_QUEEN);
		this.setResourceToRow(Resource.HIVE, Row.HIVE);
		this.setResourceToRow(Resource.HIVE_QUEEN, Row.HIVE_QUEEN);

	}

	final TabLarvae switchToTabLarvae() throws ExceptionImpossible, ExceptionNull, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabLarvae) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Larvae));
	}

	private final TabOptions switchToTabOptions() throws ExceptionNull, ExceptionNoSuchElement, ExceptionImpossible, ExceptionCountParse {

		this.clickMore();
		return (TabOptions) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Options));
	}

	final TabTerritory switchToTabTerritory() throws ExceptionNull, ExceptionImpossible, ExceptionNoSuchElement, ExceptionCountParse {

		return (TabTerritory) NullUtils.assertNotNull(this.switchToOtherTab(Tab.Territory));
	}

}
