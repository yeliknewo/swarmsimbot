
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.math.Cost;
import com.kileyowen.math.Count;
import com.kileyowen.math.Cost.Resource;
import com.kileyowen.swarmsim.utils.ErrorMessages;

class TabTerritoryRowSwarmling extends PageBuyThree<TabTerritoryRowSwarmling> {

	private static final class Regex {

		static final String NAME = "Swarmling.*";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/territory/unit/swarmling']";
	}

	private static final Count getLarvaCost() {

		return Count.parse(1);
	}

	private static final Count getMeatCost() {

		return Count.parse(750);
	}

	final static TabTerritoryRowSwarmling getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabTerritoryRowSwarmling.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabTerritoryRowSwarmling.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabTerritoryRowSwarmling init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabTerritoryRowSwarmling page = new TabTerritoryRowSwarmling(manager);
		manager.givePage(TabTerritoryRowSwarmling.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabTerritoryRowSwarmling(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabTerritoryRowSwarmling.getMeatCost());
		this.cost.setCost(Resource.LARVA, TabTerritoryRowSwarmling.getLarvaCost());

		this.row = PageBase.getByXPath(XPath.ROW);
	}

	@Override
	Cost getCost() {

		return this.cost;
	}

	@Override
	protected TabTerritoryRowSwarmling getPage() throws ExceptionImpossible, ExceptionNull {

		return TabTerritoryRowSwarmling.getPage(this.getManager());
	}

	@Override
	protected String getRegexBuy() {

		return PageBuyThree.Regex.HATCH;
	}

	@Override
	protected By getRowBy() {

		return this.row;
	}

	@Override
	protected String getRowName() {

		return Regex.NAME;
	}

}
