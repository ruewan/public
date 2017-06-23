package org.alayne.cache.test;

import javax.annotation.Resource;

import org.alayne.cache.Greetings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

/**
 * @author adrianla
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class Ehcache1ApplicationTests {

	@Resource
	private Greetings greetings;
	@Test
	public void testCache()
	{
		StopWatch sw = new StopWatch();
		sw.start();
		greetings.greeting("George");
		sw.stop();
		Assert.assertTrue(sw.getTotalTimeMillis()>4000);
		sw = new StopWatch();
		sw.start();
		greetings.greeting("George");
		sw.stop();
		Assert.assertTrue(sw.getTotalTimeMillis()<4000);
	}
	@Test
	public void contextLoads() {
	}

}
