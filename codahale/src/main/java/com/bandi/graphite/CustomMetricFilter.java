package com.bandi.graphite;

import com.bandi.logger.Loggable;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

public class CustomMetricFilter implements MetricFilter {

	public boolean matches(String name, Metric metric) {
		Loggable.log(" Graphite " + name + " metric " + metric);
		return true;
	}

}
