package com.bandi.graphite;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.bandi.logger.Loggable;
import com.bandi.util.Constants;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.google.inject.Provider;

public class GraphiteReporterManager implements Provider<GraphiteReporter> {
	private GraphiteReporter reporter;
	private MetricRegistry metricRegistry;

	public GraphiteReporterManager(MetricRegistry metricRegistry) {
		this.metricRegistry = metricRegistry;
	}

	//@Override
	public GraphiteReporter get() {
		Graphite graphite = new Graphite(Constants.HOST, Constants.PORT);
		MetricFilter metricFilter = new CustomMetricFilter();
		
		this.reporter = GraphiteReporter.forRegistry(metricRegistry).prefixedWith(Constants.PREFIX).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).filter(metricFilter).build(graphite);
		
        reporter.start(Constants.PUBLISH_INTERVAL, TimeUnit.SECONDS);
		
		return reporter;
	}

    @PostConstruct
    void start() { 
    }

}
