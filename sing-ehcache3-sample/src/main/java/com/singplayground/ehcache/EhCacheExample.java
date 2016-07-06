package com.singplayground.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;

public class EhCacheExample {
	public static void main(String[] args) {
		//test1();
		test2();
		//value = myCache.get(1L);
		//System.out.println("value : " + value);

	}

	private static boolean test1() {
		System.out.println("00000000");
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))).build();
		cacheManager.init();

		System.out.println("********* 2");
		Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
		System.out.println("********* 3");
		Cache<Long, String> myCache = cacheManager.createCache("myCache",
				CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build());
		System.out.println("********* 4");
		myCache.put(1L, "da one!");
		String value = myCache.get(1L);
		System.out.println("value : " + value);
		System.out.println("********* 5");
		cacheManager.removeCache("preConfigured");
		System.out.println("********* 6");
		cacheManager.close();

		return true;
	}

	private static boolean test2() {
		System.out.println("********** 1");
		UserManagedCache<Long, String> userManagedCache = UserManagedCacheBuilder.newUserManagedCacheBuilder(Long.class, String.class).build(false);

		System.out.println("********** 2");
		userManagedCache.init();

		System.out.println("********** 3");
		userManagedCache.put(1L, "da one!");

		System.out.println("xxxxx " + userManagedCache.get(1L));
		System.out.println("********** 4");
		userManagedCache.close();
		return true;
	}

}
