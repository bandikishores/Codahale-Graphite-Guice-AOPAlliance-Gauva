package com.bandi.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.Data;

@Data
public class Cache {

	private static LoadingCache<Pair<Integer, Integer>, Integer> cache = 
			CacheBuilder.newBuilder()
			.maximumSize(500)
			.expireAfterWrite(60, TimeUnit.HOURS)
			.refreshAfterWrite(60, TimeUnit.MINUTES)
			.build(new CacheLoader<Pair<Integer, Integer>, Integer>() {

				@Override
				public Integer load(Pair<Integer, Integer> key) throws Exception {
					return key.getLeft() * key.getRight();
				}
			});

	public static LoadingCache<Pair<Integer, Integer>, Integer> getCache()
	{
		return cache;
	}
}
