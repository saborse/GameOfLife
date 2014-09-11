/**
 * 
 */
package com.ptc.gameoflife.rules;

import static com.ptc.gameoflife.config.Configuration.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.exception.GameOfLifeException;
import com.ptc.gameoflife.ui.ConsoleRenderer;
import com.ptc.gameoflife.ui.IRenderer;
import com.ptc.gameoflife.util.GridUtil;

/**
 * @author sagar_borse
 *
 */
public class TestIRules {
	
	private static IRules rules;
	private static Grid grid;
	private static IRenderer renderer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = new RulesImpl();
		grid = Grid.getGrid();
		renderer = new ConsoleRenderer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		grid = null;
		rules = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		GridUtil.clearGrid(grid);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	// ***********************************************************************************************************************************************
	// ***********************************************************************************************************************************************
	// ******** Please uncomment following method call to displyOnConsole() method to view output in console and get better understanding. ***********
	// ***********************************************************************************************************************************************
	// ***********************************************************************************************************************************************

	/**
	 * Cell dies if there are too few [less than 2] cell nearby
	 * 
	 * Test method for {@link com.ptc.gameoflife.rules.IRules#applyRule(com.ptc.gameoflife.Grid)}.
	 * @throws GameOfLifeException 
	 */
	@Test
	public void testApplyRule_DeathDueToIsolation() throws GameOfLifeException {
		grid =  Grid.getGrid("0,0|1,1");
		String gridStateInitial = renderer.getGridAsMatrixString(grid);
		String gridStateExpected = getExpectedGridAsMatrixString(grid, "");
		rules.applyRule(grid);
		String gridStateActual = renderer.getGridAsMatrixString(grid);
		assertEquals("Death due to isolation fails", gridStateExpected, gridStateActual);
		
		// It is being a bit hard to understand the grid places are being manipulated depending on their location
		//displyOnConsole("Death Due to Isolation", gridStateInitial, gridStateExpected, gridStateActual);
	}
	
	@Test
	public void testApplyRule_DeathDueToOverCrowding() throws GameOfLifeException {
		grid =  Grid.getGrid("0,0|0,2|1,1|2,0|2,2");
		String gridStateInitial = renderer.getGridAsMatrixString(grid);
		
		String gridStateExpected = getExpectedGridAsMatrixString(grid, "0,1|1,0|1,2|2,1");
		
		rules.applyRule(grid);
		
		String gridStateActual = renderer.getGridAsMatrixString(grid);
		assertEquals("Death due to over crowding fails", gridStateExpected, gridStateActual);
		
		// It is being a bit hard to understand the grid places are being manipulated depending on their location
		//displyOnConsole("Death Due to Over crowding", gridStateInitial, gridStateExpected, gridStateActual);
	}
	
	@Test
	public void testApplyRule_BirthDueToExact3Neighbors() throws GameOfLifeException {
		grid =  Grid.getGrid("0,0|0,2|2,0");
		String gridStateInitial = renderer.getGridAsMatrixString(grid);
		
		String gridStateExpected = getExpectedGridAsMatrixString(grid, "1,1");
		
		rules.applyRule(grid);
		
		String gridStateActual = renderer.getGridAsMatrixString(grid);
		assertEquals("Death due to over crowding fails", gridStateExpected, gridStateActual);
		
		// It is being a bit hard to understand the grid places are being manipulated depending on their location
		//displyOnConsole("Birth due to 3 alive cells in the neighbor", gridStateInitial, gridStateExpected, gridStateActual);
	}


	//************** I know this is very complex to understand :) *****************
	private String getExpectedGridAsMatrixString(Grid grid, String aliveCellInputString) {
		Set<String> set = getBarSplitedSetOfInputString(aliveCellInputString);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Generation : 1" + LINE_SEPERATOR);
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				if(set.contains(row+","+column))
					buffer.append(ALIVE_CHAR);
				else
					buffer.append(DEAD_CHAR);
			}
			buffer.append(LINE_SEPERATOR);
		}
		return buffer.toString();
	}

	private Set<String> getBarSplitedSetOfInputString(String aliveCellInputString) {
		Set<String> set = new HashSet<String>();
		String[] cordinates = aliveCellInputString.split("\\|");
		for (String locationStr : cordinates) {
			set.add(locationStr);
			//String[] locInts = loc.split(",");
			//int gridRow = Integer.parseInt(locInts[0]);
			//int gridColumn = Integer.parseInt(locInts[1]);
		}
		return set;
	}
	
	private void displyOnConsole(String testCaseName, String initialState, String gridStateExpected, String gridStateActual) {
		System.out.println();
		System.out.println("TEST CASE : " + testCaseName);
		System.out.println("Initial : " + LINE_SEPERATOR + initialState);
		System.out.println("Expected : " + LINE_SEPERATOR + gridStateExpected);
		System.out.println("Actual : " + LINE_SEPERATOR + gridStateActual);
		System.out.println();
	}

}
