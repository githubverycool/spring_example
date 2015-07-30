package spring.log.impl;

import org.apache.log4j.Logger;

import spring.log.ILog;

public class LogImpl implements ILog {

	@Override
	public Logger getLogger() {
		final Logger log = Logger.getLogger(this.getClass());
		return log;
	}

}
