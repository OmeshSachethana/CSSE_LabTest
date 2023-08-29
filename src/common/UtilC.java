package common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

public class UtilC {

	// Initialize a logger for the UtilC class

	private static final Logger logger = Logger.getLogger(UtilC.class.getName());
	public static final Properties properties = new Properties();

	static {

		try {
			properties.load(UtilQ.class.getResourceAsStream("../config/config.properties"));

		} catch (IOException e) {
			// Log the exception
			logger.log(Level.SEVERE, e.getMessage());

		}
	}
}