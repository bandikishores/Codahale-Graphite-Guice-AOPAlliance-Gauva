package com.bandi.listeners;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;

import com.bandi.interceptor.CustomMethodInterceptor;
import com.bandi.logger.Loggable;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class MethodListener implements TypeListener {
	private MetricRegistry metricRegistry;
	
	public MethodListener(MetricRegistry metricRegistry) {
		this.metricRegistry = metricRegistry;
	}
	
	public <I> void hear(TypeLiteral<I> literal, TypeEncounter<I> encounter) {/*
        Class<? super I> klass = literal.getRawType();

        do {
            for (Method method : klass.getDeclaredMethods()) {
            	final Meter meter = metricRegistry.meter(MetricRegistry.name(klass.toString(), method.toString()));
            	final Timer timer = metricRegistry.timer(MetricRegistry.name(klass.toString(), method.toString()));
            	final MethodInterceptor interceptor = new CustomMethodInterceptor(meter, timer);
                
                if (interceptor != null) {
                    Loggable.log("Binding interceptor for - " + method.getName() + " registry-"
                            + metricRegistry.getMetrics());
                    
                    encounter.bindInterceptor(Matchers.only(method), interceptor);
                }
            }
        } while ((klass = klass.getSuperclass()) != null);
    */}

}
