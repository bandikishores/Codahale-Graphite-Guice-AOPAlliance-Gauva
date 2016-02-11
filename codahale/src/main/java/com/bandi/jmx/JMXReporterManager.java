package com.bandi.jmx;

import java.util.concurrent.TimeUnit;

import com.bandi.graphite.CustomMetricFilter;
import com.bandi.util.Constants;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.Provider;

public class JMXReporterManager implements Provider<JmxReporter> {
	private JmxReporter reporter;
	private MetricRegistry metricRegistry;

	public JMXReporterManager(MetricRegistry metricRegistry) {
		this.metricRegistry = metricRegistry;
	}

	public JmxReporter get() {
		
		MetricFilter metricFilter = new CustomMetricFilter();
		
		reporter = JmxReporter.forRegistry(metricRegistry).build();
        reporter.start();
		
		return reporter;
	}
}
