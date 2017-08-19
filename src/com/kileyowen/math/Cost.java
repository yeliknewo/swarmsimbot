
package com.kileyowen.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.swarmsim.utils.NullUtils;

public class Cost {

	public static enum Resource {
		MEAT, DRONE, QUEEN, NEST, GREATER_QUEEN, HIVE, HIVE_QUEEN, LARVA, SWARMLING
	}

	private static final long DEFAULT_PRECISION = 10;

	private final HashMap<Resource, Count> counts;

	public Cost() {
		this.counts = new HashMap<>();
	}

	public boolean canBuy(final Cost stocks) throws ExceptionNull {

		final List<Resource> list = this.getDemand(stocks);

		return list.isEmpty();
	}

	public Count getCount(final Resource resource) throws ExceptionNull {

		return NullUtils.assertNotNull(this.counts.get(resource));
	}

	public List<Resource> getDemand(final Cost stocks) throws ExceptionNull {

		final List<Resource> list = new ArrayList<>();

		for (final Resource key : stocks.counts.keySet()) {
			if (this.counts.containsKey(key) && NullUtils.assertNotNull(stocks.counts.get(key)).lessThan(this.counts.get(key))) {
				list.add(key);
			}
		}

		return list;

	}

	public final Cost getDifference(final Cost stocks) throws ExceptionNull {

		final Cost cost = new Cost();

		final List<Resource> demand = this.getDemand(stocks);

		for (final Resource resource : demand) {
			cost.setCost(resource, this.getCount(resource).difference(stocks.getCount(resource), Cost.DEFAULT_PRECISION));
		}

		return cost;
	}

	public List<Resource> getResources() {

		final List<Resource> list = new ArrayList<>();

		for (final Resource key : this.counts.keySet()) {
			list.add(key);
		}

		return list;
	}

	public void setCost(final Resource resource, final Count count) {

		if (this.counts.containsKey(resource)) {
			this.counts.replace(resource, count);
		} else {
			this.counts.put(resource, count);
		}
	}
}
