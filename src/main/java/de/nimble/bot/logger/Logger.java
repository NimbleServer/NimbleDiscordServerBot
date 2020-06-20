package de.nimble.bot.logger;

import de.nimble.bot.Utils;

public class Logger {

	private static Logger logger;

	public static Logger getInstance() {
		if(logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	private Logger() {

	}

	public void log(String msg) {
    if (Utils.DEBUG){
    	System.out.println("DEBUG: " + msg);
    }
	}

}