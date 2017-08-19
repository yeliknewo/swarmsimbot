
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

public class TabMeatRowHiveQueen extends PageBuyThree<TabMeatRowHiveQueen> {

	private static final class Regex {

		static final String NAME = "Hive Queen";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/hive queen']";
	}

	private final static Count getCostHive() throws ExceptionCountParse, ExceptionNull {

		return Count.parse("1e6");
	}

	private final static Count getCostLarva() {

		return Count.parse(1);
	}

	private final static Count getCostMeat() throws ExceptionCountParse, ExceptionNull {

		return Count.parse("5.31e10");
	}

	final static TabMeatRowHiveQueen getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		try {
			return manager.getPage(TabMeatRowHiveQueen.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowHiveQueen.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowHiveQueen init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull, ExceptionCountParse {

		final TabMeatRowHiveQueen page = new TabMeatRowHiveQueen(manager);
		manager.givePage(TabMeatRowHiveQueen.class, page);
		return page;
	}

	private final Cost cost;

	private final By row;

	protected TabMeatRowHiveQueen(final ManagerPage newManager) throws ExceptionNull, ExceptionCountParse {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);

		this.cost = new Cost();
		this.cost.setCost(Resource.MEAT, TabMeatRowHiveQueen.getCostMeat());
		this.cost.setCost(Resource.HIVE, TabMeatRowHiveQueen.getCostHive());
		this.cost.setCost(Resource.LARVA, TabMeatRowHiveQueen.getCostLarva());
	}

	@Override
	Cost getCost() {

		return this.cost;
	}

	@Override
	protected TabMeatRowHiveQueen getPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabMeatRowHiveQueen.getPage(this.getManager());
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
