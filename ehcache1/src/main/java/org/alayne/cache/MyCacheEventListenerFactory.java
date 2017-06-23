package org.alayne.cache;

import java.util.Properties;
import java.util.logging.Logger;

import net.sf.ehcache.distribution.RMICacheReplicatorFactory;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class MyCacheEventListenerFactory extends CacheEventListenerFactory {

	public MyCacheEventListenerFactory() {
		String className = MyCacheEventListenerFactory.class.getName();

			_log.fine("Instantiating " + className + " " + hashCode());

		try {
			_cacheEventListenerFactory = new RMICacheReplicatorFactory();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		return _cacheEventListenerFactory.createCacheEventListener(properties);
	}

	private static Logger _log = Logger.getLogger(MyCacheEventListenerFactory.class.getName());

	private CacheEventListenerFactory _cacheEventListenerFactory;

}