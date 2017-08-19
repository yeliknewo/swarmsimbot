
package com.kileyowen.swarmsimbot.pages;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionImpossible;
import com.kileyowen.exceptions.ExceptionNoSuchElement;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.math.Count;
import com.kileyowen.swarmsimbot.pages.PageTab.Tab;

public final class Director {

	public static Director init(final ManagerPage manager, final int timeoutInSeconds, final int pollIntervalInMs) {

		return new Director(manager, timeoutInSeconds, pollIntervalInMs);
	}

	final static void setOptions(final PageTab<?, ?> tab, final String email, final String password) throws ExceptionNull, ExceptionNoSuchElement, ExceptionImpossible, ExceptionCountParse {

		final TabOptions options = (TabOptions) tab.switchToOtherTab(Tab.Options);
		options.setScientificNotation();
		options.login(email, password);
		options.switchToOtherTab(Tab.Meat);
	}

	private final ManagerPage manager;

	private final int timeoutInSeconds;

	private final int pollIntervalInMs;

	private Director(final ManagerPage newManager, final int newTimeoutInSeconds, final int newPollIntervalInMs) {
		this.manager = newManager;
		this.timeoutInSeconds = newTimeoutInSeconds;
		this.pollIntervalInMs = newPollIntervalInMs;

	}

	//	private final void aquire(final Cost cost) throws ExceptionNull {
	//
	//		final List<Resource> resources = cost.getResources();
	//
	//		final Cost stocks = this.getStocks(resources);
	//
	//		final List<Resource> demand = cost.getDemand(stocks);
	//
	//		final Cost difference = cost.getDifference(stocks);
	//
	//		for (final Resource resource : demand) {
	//			switch (resource) {
	//				case Drone:
	//					this.aquireDrones(difference.getCount(resource));
	//					break;
	//				case Larva:
	//					break;
	//				case Meat:
	//					break;
	//				case Queen:
	//					break;
	//				case Swarmling:
	//					break;
	//				default:
	//					break;
	//
	//			}
	//		}
	//
	//	}

	private void aquireDrones(final Count count) {

		// TODO
	}

	//	private Count getCount(final Resource resource) {
	//
	//		switch (resource) {
	//			case Drone:
	//				break;
	//			case Larva:
	//				break;
	//			case Meat:
	//				break;
	//			case Queen:
	//				break;
	//			case Swarmling:
	//				break;
	//			default:
	//				throw new Exception
	//				break;
	//
	//		}
	//	}

	private final TabMeat getStartingPage() throws ExceptionImpossible, ExceptionNull, ExceptionCountParse {

		return TabMeat.getPage(this.manager);
	}

	//	private Cost getStocks(final List<Resource> activeResources) {
	//
	//		final Cost stocks = new Cost();
	//
	//		for (final Resource resource : activeResources) {
	//			stocks.setCost(resource, this.getCount(resource));
	//		}
	//
	//		return stocks;
	//	}

	public final void go() throws ExceptionNull, ExceptionCountParse, ExceptionImpossible, ExceptionNoSuchElement {

		final PageTab<?, ?> tab = this.getStartingPage();
		final String email = "insuferior@gmail.com";
		final String password = "kappa123";

		Director.setOptions(tab, email, password);

		final TabMeat meat = (TabMeat) tab.switchToOtherTab(Tab.Meat);

		meat.hatchHiveQueenLazy(this.timeoutInSeconds, this.pollIntervalInMs);

		//		meat.hatchDronesToCount(Count.parse("1e1"), this.timeoutInSeconds, this.pollIntervalInMs);
		//
		//		meat.hatchQueensToCount(Count.parse("1e3"), this.timeoutInSeconds, this.pollIntervalInMs);
		//
		//		meat.hatchNestToCount(Count.parse("1e4"), this.timeoutInSeconds, this.pollIntervalInMs);
		//
		//		meat.hatchGreaterQueenToCount(Count.parse("1e5"), this.timeoutInSeconds, this.pollIntervalInMs);
		//
		//		meat.hatchHiveToCount(Count.parse("1e6"), this.timeoutInSeconds, this.pollIntervalInMs);
		//
		//		meat.hatchHiveQueenToCount(Count.parse("1e50"), this.timeoutInSeconds, this.pollIntervalInMs);

		//		final TabTerritory territory = meat.switchToTabTerritory();
		//
		//		territory.hatchSwarmlingsToCount(Count.parse("1e2"), this.timeoutInSeconds, this.pollIntervalInMs);
	}
}
