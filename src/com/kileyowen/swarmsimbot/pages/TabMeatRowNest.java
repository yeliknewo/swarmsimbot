
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

public class TabMeatRowNest extends PageBuyThree<TabMeatRowNest> {

	private static final class Regex {

		static final String NAME = "Nest";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/nest']";
	}

	private final static Count getCostLarva() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() {

		return Count.parse(72900);
	}

	private final static Count getCostQueen() {

		return Count.parse(1000);
	}

	final static TabMeatRowNest getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabMeatRowNest.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowNest.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowNest init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabMeatRowNest page = new TabMeatRowNest(manager);
		manager.givePage(TabMeatRowNest.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabMeatRowNest(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabMeatRowNest.getCostMeat());
		this.cost.setCost(Resource.QUEEN, TabMeatRowNest.getCostQueen());
		this.cost.setCost(Resource.LARVA, TabMeatRowNest.getCostLarva());
	}

	@Override
	Cost getCost() {

		return this.cost;
	}

	@Override
	protected TabMeatRowNest getPage() throws ExceptionImpossible, ExceptionNull {

		return TabMeatRowNest.getPage(this.getManager());
	}

	@Override
	protected String getRegexBuy() {

		return PageBuyThree.Regex.BUILD;
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
