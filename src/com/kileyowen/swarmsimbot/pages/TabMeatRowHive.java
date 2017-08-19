
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

public class TabMeatRowHive extends PageBuyThree<TabMeatRowHive> {

	private static final class Regex {

		static final String NAME = "Hive";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/hive']";
	}

	private final static Count getCostGreaterQueen() throws ExceptionCountParse, ExceptionNull {

		return Count.parse("1e5");
	}

	private final static Count getCostLarva() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() throws ExceptionCountParse, ExceptionNull {

		return Count.parse("5.9e8");
	}

	final static TabMeatRowHive getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabMeatRowHive.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowHive.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowHive init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull, ExceptionCountParse {

		final TabMeatRowHive page = new TabMeatRowHive(manager);
		manager.givePage(TabMeatRowHive.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabMeatRowHive(final ManagerPage newManager) throws ExceptionNull, ExceptionCountParse {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabMeatRowHive.getCostMeat());
		this.cost.setCost(Resource.GREATER_QUEEN, TabMeatRowHive.getCostGreaterQueen());
		this.cost.setCost(Resource.LARVA, TabMeatRowHive.getCostLarva());
	}

	@Override
	Cost getCost() {

		return this.cost;
	}

	@Override
	protected TabMeatRowHive getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabMeatRowHive.getPage(this.getManager());
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
