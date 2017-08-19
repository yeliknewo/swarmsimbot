
package com.kileyowen.swarmsimbot.pages;

import org.openqa.selenium.By;

import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.swarmsim.utils.ErrorMessages;

class TabMeatRowMeat extends PageRow<TabMeatRowMeat> {

	private static final class Regex {

		static final String NAME = "Meat";
	}

	private static final class XPath {

		static final String ROW = "//a[@href='#/tab/meat/unit/meat']";
	}

	final static TabMeatRowMeat getPage(final ManagerPage manager) throws ExceptionImpossible, ExceptionNull {

		try {
			return manager.getPage(TabMeatRowMeat.class);
		} catch (final ExceptionPageNotInstantiated e) {
			try {
				return TabMeatRowMeat.init(manager);
			} catch (final ExceptionPageAlreadyPresent e1) {
				throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_NULL_THEN_NOT_NULL);
			}
		} catch (final ExceptionPageTypeMismatch e) {
			throw new ExceptionImpossible(ErrorMessages.ERROR_MESSAGE_PAGE_CLASS);
		}
	}

	private final static TabMeatRowMeat init(final ManagerPage manager) throws ExceptionPageAlreadyPresent, ExceptionNull {

		final TabMeatRowMeat page = new TabMeatRowMeat(manager);
		manager.givePage(TabMeatRowMeat.class, page);
		return page;
	}

	private final By row;

	protected TabMeatRowMeat(final ManagerPage newManager) throws ExceptionNull {
		super(newManager);

		this.row = PageBase.getByXPath(XPath.ROW);
	}

	@Override
	protected final TabMeatRowMeat getPage() throws ExceptionImpossible, ExceptionNull {

		return TabMeatRowMeat.getPage(this.getManager());
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
