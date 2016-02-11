package com.bandi.interceptor;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.bandi.logger.Loggable;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class CustomMethodInterceptor implements MethodInterceptor {
	private MetricRegistry metricRegistry;
	/*private final Meter meter;
	private final Timer timer;*/

	/*public CustomMethodInterceptor(final Meter meter, final Timer timer) {
		this.meter = meter;
		this.timer = timer;
	}*/

	public CustomMethodInterceptor(MetricRegistry metricRegistry2) {
		this.metricRegistry = metricRegistry2;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Class clas = invocation.getClass();
		Method method = invocation.getMethod();
		
		Meter meter = metricRegistry.meter(MetricRegistry.name(clas, method.getName(), "meter"));
		Timer timer = metricRegistry.timer(MetricRegistry.name(clas, method.getName(), "timer"));
		Histogram histogram = metricRegistry.histogram(MetricRegistry.name(clas, method.getName(), "history"));
		histogram.update((long)(100 * Math.random()));
		final Timer.Context ctx = timer.time();
		
		meter.mark();
		
		try {
			return invocation.proceed();
		} finally {
			/*Loggable.log("Time taken for execution " + TimeUnit.MILLISECONDS.convert(ctx.stop(), TimeUnit.NANOSECONDS) + " ms ");
			Loggable.log("99th Percentile " + timer.getSnapshot().get99thPercentile());
			Loggable.log("Total number of times the API was hit " + meter.getCount());
			Loggable.log("One minute rate " + meter.getOneMinuteRate());
			Loggable.log("histogram " + histogram.getSnapshot().getStdDev());*/
			SortedMap<String, Gauge> gauges = metricRegistry.getGauges();
			if(gauges != null){
				for(String gaugeName : gauges.keySet()){
					Gauge gauge = gauges.get(gaugeName);
					// Loggable.log(gaugeName + "  :   " + gauge.getValue());
				}
			}
			/*Loggable.log("\n\n");*/
		}
	}

}
