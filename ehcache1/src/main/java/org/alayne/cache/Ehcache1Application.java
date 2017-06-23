package org.alayne.cache;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adrianla
 *
 */
@SpringBootApplication
@RestController
@EnableCaching
public class Ehcache1Application {
	
	@Resource
	private Greetings greeting;
	@Resource
	private EhCacheManagerFactoryBean ehCacheManagerFactoryBean;

	public static void main(String[] args) {
		SpringApplication.run(Ehcache1Application.class, args);
	}

	@RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
	public String greet(@PathVariable String name) {
		
		return greeting.greeting(name);
	}
	@RequestMapping(value = "/clear/{name}", method = RequestMethod.GET)
	public String clear(@PathVariable String name) {
		
		return greeting.evict(name);
	}

	@Bean
	public Greetings greeting() {
		return new Greetings();
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}

}
