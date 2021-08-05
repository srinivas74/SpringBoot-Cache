package com.cache.configuration;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
/**
 * This class enables caching and logs cache events
 *
 */
@EnableCaching
@Configuration
public class CacheConfig implements CacheEventListener<Object, Object> {
	
	  /**
	   * @description this methods logs event details when cache event is triggered
	   * @param cacheEvent    this class has cache event details
	   */
	  @Override
	  public void onEvent(CacheEvent<?, ?> cacheEvent) {
	    System.out.println("CACHE EVENT TRIGGERED => Cache Event : "+ cacheEvent.getType() +
	    		"||  Key :"+cacheEvent.getKey()+"|| Old value :"+cacheEvent.getOldValue()+
	    		"||  New Value  :"+cacheEvent.getNewValue());
	  }
	  
}
