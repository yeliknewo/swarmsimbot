
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.math.Cost;
import com.kileyowen.swarmsim.utils.ErrorMessages;

class TabLarvaeRowLarva extends PageBuyThree<TabLarvaeRowLarva> {

	private static final class Regex {

		static final String NAME = "Larva";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/larva/unit/larva']";
	}

	final static TabLarvaeRowLarva getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabLarvaeRowLarva.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabLarvaeRowLarva.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {

			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabLarvaeRowLarva init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabLarvaeRowLarva page = new TabLarvaeRowLarva(manager);
		manager.givePage(TabLarvaeRowLarva.class, page);
		return page;
	}

	private final By row;

	protected TabLarvaeRowLarva(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);
	}

	@Override
	final Cost getCost() {

		return new Cost();
	}

	@Override
	protected final TabLarvaeRowLarva getPage() throws ExceptionImpossible, ExceptionNull {

		return TabLarvaeRowLarva.getPage(this.getManager());
	}

	@Override
	protected final String getRegexBuy() {

		return PageBuyThree.Regex.BUY;
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
