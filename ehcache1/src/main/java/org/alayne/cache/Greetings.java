package org.alayne.cache;
import java.util.logging.Logger;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
/**
 * @author adrianla
 *
 */
@Component
public class Greetings {
	private final static Logger logger = Logger.getLogger(Greetings.class.getName());
	@Cacheable(value="greetingCache")
	public String greeting(String name)
	{
		logger.info("getting "+name+" without cache");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello "+name;
	}
	
	@CacheEvict(value="greetingCache")
	public String evict(String name)
	{
		return name;
	}
}
