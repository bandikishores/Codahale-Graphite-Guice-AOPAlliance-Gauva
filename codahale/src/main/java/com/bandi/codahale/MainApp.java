package com.bandi.codahale;

import com.bandi.module.InjectionModule;
import com.bandi.util.Constants;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class MainApp {
	public static void main(String[] args) throws InterruptedException {

		Injector injector = Guice.createInjector(new InjectionModule());
		do {
			TestMaths testMath = injector.getInstance(TestMaths.class);
			System.out.println(testMath.multiply(1, 2));
			Thread.sleep(Constants.SLEEP);
			System.out.println(testMath.add(3, 2));
			/*
			 * System.out.println(testMath.multiply(3, 3));
			 * System.out.println(testMath.multiply(1, 1));
			 * System.out.println(testMath.multiply(4, 5));
			 * System.out.println(testMath.add(3, 1));
			 */
		} while (true);
	}
}
