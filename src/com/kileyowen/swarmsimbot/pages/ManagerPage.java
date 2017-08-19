
package com.kileyowen.swarmsimbot.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.exceptions.ExceptionPageAlreadyPresent;
import com.kileyowen.exceptions.ExceptionPageNotInstantiated;
import com.kileyowen.exceptions.ExceptionPageTypeMismatch;
import com.kileyowen.swarmsim.utils.ErrorMessages;
import com.kileyowen.swarmsim.utils.NullUtils;

public final class ManagerPage implements AutoCloseable {

	public final static ManagerPage init(final WebDriver driver) {

		return new ManagerPage(driver);
	}

	private final WebDriver driver;

	private final HashMap<Class<? extends PageBase>, PageBase> pages;

	private ManagerPage(final WebDriver newDriver) {
		this.driver = newDriver;
		this.pages = new HashMap<>();
	}

	@Override
	public final void close() {

		this.getDriver().close();
	}

	final WebDriver getDriver() {

		return this.driver;
	}

	final <P extends PageBase> P getPage(final Class<P> cls) throws ExceptionPageNotInstantiated, ExceptionPageTypeMismatch, ExceptionNull {

		if (this.pages.containsKey(cls)) {
			final PageBase page = NullUtils.assertNotNull(this.pages.get(cls));

			if (cls.isInstance(page)) {
				return NullUtils.assertNotNull(cls.cast(page));
			}
			throw new ExceptionPageTypeMismatch(ErrorMessages.THE_PAGE_IS_HAVING_TYPE_ERRORS);
		}
		throw new ExceptionPageNotInstantiated(ErrorMessages.THE_PAGE_IS_NOT_INSTANTIATED);
	}

	final <P extends PageBase> void givePage(final Class<P> cls, final P page) throws ExceptionPageAlreadyPresent {

		if (!this.pages.containsKey(cls)) {
			this.pages.put(cls, page);
		} else {
			throw new ExceptionPageAlreadyPresent(ErrorMessages.GIVING_A_PAGE_THAT_IS_ALREADY_ON_THE_MAP);
		}
	}
}
