package com.bandi.logger;

public class Loggable {

	public static void log(String log){
		System.out.println(log);
	}
	
	public static void log(Exception ex){
		System.out.println(ex);
	}
	
	public static void log(Object obj){
		System.out.println(obj);
	}
	
}
