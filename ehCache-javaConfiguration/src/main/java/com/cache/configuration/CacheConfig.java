package com.cache.configuration;

import java.net.URISyntaxException;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
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
	  
	  
	    @Bean
	    public JCacheCacheManager jCacheCacheManager(JCacheManagerFactoryBean jCacheManagerFactoryBean){
	        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager();
	        jCacheCacheManager.setCacheManager(jCacheManagerFactoryBean.getObject());
	        return jCacheCacheManager;
	    }

	    @Bean
	    public JCacheManagerFactoryBean jCacheManagerFactoryBean() throws URISyntaxException {
	        JCacheManagerFactoryBean jCacheManagerFactoryBean = new JCacheManagerFactoryBean();
	        jCacheManagerFactoryBean.setCacheManagerUri(getClass().getResource("/ehcache.xml").toURI());
	        return jCacheManagerFactoryBean;
	    }

}
