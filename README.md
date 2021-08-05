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
 
 ```bash
 references : - https://springframework.guru/using-ehcache-3-in-spring-boot/
 ```
 
