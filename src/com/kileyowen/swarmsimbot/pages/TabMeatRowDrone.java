
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

class TabMeatRowDrone extends PageBuyThree<TabMeatRowDrone> {

	private static final class Regex {

		static final String NAME = "Drone";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/drone']";
	}

	private final static Count getCostLarvae() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() {

		return Count.parse(10);
	}

	final static TabMeatRowDrone getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabMeatRowDrone.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowDrone.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowDrone init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabMeatRowDrone page = new TabMeatRowDrone(manager);
		manager.givePage(TabMeatRowDrone.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	private TabMeatRowDrone(final ManagerPage manager) throws ExceptionNull {
		super(manager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabMeatRowDrone.getCostMeat());
		this.cost.setCost(Resource.LARVA, TabMeatRowDrone.getCostLarvae());
	}

	@Override
	final Cost getCost() {

		return this.cost;
	}

	@Override
	protected final TabMeatRowDrone getPage() throws ExceptionImpossible, ExceptionNull {

		return TabMeatRowDrone.getPage(this.getManager());
	}

	@Override
	protected final String getRegexBuy() {

		return PageBuyThree.Regex.HATCH;
	}

	@Override
	protected final By getRowBy() {

		return this.row;
	}

	@Override
	protected final String getRowName() {

		return Regex.NAME;
	}

}
