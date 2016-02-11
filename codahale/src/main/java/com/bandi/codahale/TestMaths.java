package com.bandi.codahale;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

import com.bandi.jmx.JMXReporterManager;
import com.bandi.logger.Loggable;
import com.bandi.util.Cache;
import com.bandi.util.Constants;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;

public class TestMaths {

	@Inject
	GraphiteReporter graphiteReporter;
	
	@Inject
	JmxReporter jmxReporter;

	public int multiply(int a, int b) {
		// System.out.println(graphiteReporter.toString());
		try {
			Thread.sleep(Constants.SLEEP);
		} catch (InterruptedException e) {
			Loggable.log(e);
		}
		// System.out.println(graphiteReporter.toString());
		Pair<Integer, Integer> pair = Pair.of(a, b);
		
		if(Cache.getCache().getIfPresent(pair) != null)
			Loggable.log("Found in Cache");
		else
			Loggable.log("Cache Miss");
		
		try {
			return Cache.getCache().get(pair);
		} catch (ExecutionException e) {
			Loggable.log(e);
		}
		return 0;

	}

	public int add(int a, int b) {
		try {
			Thread.sleep(Constants.SLEEP);
		} catch (InterruptedException e) {
			Loggable.log(e);
		}

		return a + b;
	}

}
