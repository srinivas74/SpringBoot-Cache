# SpringBoot-Cache
implementing cache in spring boot using ehcache 3

# Spring Boot + JPA + Echache + PostgreSql
                                                                                                                     
## Dependencies
 
 ```bash
	<dependency>       
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>javax.cache</groupId>
			<artifactId>cache-api</artifactId>
		</dependency>
		<dependency>
			<groupId>org.ehcache</groupId>
			<artifactId>ehcache</artifactId>
		</dependency>
```

## Things to consider

```bash
@Cacheable(value = "studentCache",key = "#studentName")
	public Student getStudent(@PathVariable("studentName") String studentName)
```
 
 Here key should match with parameter , format ===>  #<param>
 Cache name should match with the name given in ehcache.xml file
 
 ```bash
  <class>com.cache.configuration.CacheConfig</class>
 ```
 
 In ehcache.xml make sure it matches where CacheConfig(should implement CacheEventListener) loacted.
 
 ## for java configuration
 
 
 ```bash
 
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
 
 ```
 
 Use this to get hold of cache manager
 
 
 
  ```bash
 references : - https://springframework.guru/using-ehcache-3-in-spring-boot/
 ```
 
