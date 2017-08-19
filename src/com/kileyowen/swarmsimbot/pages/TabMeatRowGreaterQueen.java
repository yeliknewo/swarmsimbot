
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.math.Cost;
import com.kileyowen.math.Count;
import com.kileyowen.math.Cost.Resource;
import com.kileyowen.swarmsim.utils.ErrorMessages;

public class TabMeatRowGreaterQueen extends PageBuyThree<TabMeatRowGreaterQueen> {

	private static final class Regex {

		static final String NAME = "Greater Queen";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/greater queen']";
	}

	private final static Count getCostLarva() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() throws ExceptionCountParse, ExceptionNull {

		return Count.parse("6.56e7");
	}

	private final static Count getCostNest() {

		return Count.parse(10000);
	}

	final static TabMeatRowGreaterQueen getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabMeatRowGreaterQueen.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowGreaterQueen.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowGreaterQueen init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull, ExceptionCountParse {

		final TabMeatRowGreaterQueen page = new TabMeatRowGreaterQueen(manager);
		manager.givePage(TabMeatRowGreaterQueen.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabMeatRowGreaterQueen(final ManagerPage newManager) throws ExceptionNull, ExceptionCountParse {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabMeatRowGreaterQueen.getCostMeat());
		this.cost.setCost(Resource.NEST, TabMeatRowGreaterQueen.getCostNest());
		this.cost.setCost(Resource.LARVA, TabMeatRowGreaterQueen.getCostLarva());
	}

	@Override
	Cost getCost() {

		return this.cost;
	}

	@Override
	protected TabMeatRowGreaterQueen getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabMeatRowGreaterQueen.getPage(this.getManager());
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
