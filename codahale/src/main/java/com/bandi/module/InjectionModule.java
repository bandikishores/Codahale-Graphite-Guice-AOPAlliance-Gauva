package com.bandi.module;

import com.bandi.graphite.GraphiteReporterManager;
import com.bandi.interceptor.CustomMethodInterceptor;
import com.bandi.jmx.JMXReporterManager;
import com.bandi.listeners.MethodListener;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class InjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		MetricRegistry metricRegistry = new MetricRegistry();
		metricRegistry.register(MetricRegistry.name("jvm", "memory"), new MemoryUsageGaugeSet());
		metricRegistry.register(MetricRegistry.name("jvm", "thread-states"), new ThreadStatesGaugeSet());

		bind(GraphiteReporter.class).toProvider(new GraphiteReporterManager(metricRegistry)).asEagerSingleton();
		bind(JmxReporter.class).toProvider(new JMXReporterManager(metricRegistry)).asEagerSingleton();
		// bindListener(Matchers.any(), new MethodListener(metricRegistry));
		bindInterceptor(Matchers.any(), Matchers.any(), new CustomMethodInterceptor(metricRegistry));
	}

}
