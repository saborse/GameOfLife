/**
 * 
 */
package com.ptc.gameoflife.config;

/**
 * @author sagar_borse
 * 
 * Can also be named as Constants.java
 * Stores the configuration which is externalized
 * Can be read from properties file or from DB or any other external source.
 */
public class Configuration {

	public static final String LINE_SEPERATOR = System.getProperty("line.separator");
	
	public static final int COLUMN_MAX_SIZE = 40;
	public static final int ROW_MAX_SIZE = 10;
	
	public static final String DEAD_CHAR = "-";
	public static final String ALIVE_CHAR = "+";
	
	// Delay in msec
	public static final int DISPLAY_DELAY_SLOW = 500;
	public static final int DISPLAY_DELAY_AVG = 200;
	public static final int DISPLAY_DELAY_FAST = 100;
	
	// Regular expression to check against the user input for initial state of the grid
	public static final String GRID_INPUT_PATTERN = "((\\d*,\\d*)(?:|\\|))*";
	
	// Messages can be externalized to support i18n
	public static final String INVALID_INPUT_MSG = "Invalid Input";
}
