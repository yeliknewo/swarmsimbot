
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

class TabMeatRowQueen extends PageBuyThree<TabMeatRowQueen> {

	private static final class Regex {

		static final String NAME = "Queen";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/queen']";
	}

	private final static Count getCostDrone() {

		return Count.parse(100);
	}

	private final static Count getCostLarva() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() {

		return Count.parse(810);
	}

	final static TabMeatRowQueen getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabMeatRowQueen.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowQueen.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowQueen init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabMeatRowQueen page = new TabMeatRowQueen(manager);
		manager.givePage(TabMeatRowQueen.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabMeatRowQueen(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.DRONE, TabMeatRowQueen.getCostDrone());
		this.cost.setCost(Resource.LARVA, TabMeatRowQueen.getCostLarva());
		this.cost.setCost(Resource.MEAT, TabMeatRowQueen.getCostMeat());

	}

	@Override
	final Cost getCost() {

		return this.cost;
	}

	@Override
	protected final TabMeatRowQueen getPage() throws ExceptionImpossible, ExceptionNull {

		return TabMeatRowQueen.getPage(this.getManager());
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
