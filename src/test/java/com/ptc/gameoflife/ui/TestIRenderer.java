/**
 * 
 */
package com.ptc.gameoflife.ui;

import static com.ptc.gameoflife.config.Configuration.COLUMN_MAX_SIZE;
import static com.ptc.gameoflife.config.Configuration.DEAD_CHAR;
import static com.ptc.gameoflife.config.Configuration.LINE_SEPERATOR;
import static com.ptc.gameoflife.config.Configuration.ROW_MAX_SIZE;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.exception.GameOfLifeException;
import com.ptc.gameoflife.util.GridUtil;

/**
 * @author sagar_borse
 *
 */
public class TestIRenderer {

	private static IRenderer renderer;
	private static Grid grid;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		renderer = new ConsoleRenderer();
		grid = Grid.getGrid();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		grid = null;
		renderer = null;
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

	/**
	 * Test method for {@link com.ptc.gameoflife.ui.IRenderer#getGridAsMatrixString(com.ptc.gameoflife.Grid)}.
	 */
	@Test
	public void testGetGridAsMatrixString() {
		final String expectedGridMatrixText = getExpectedGridMatrixText(); 
		String gridMatrixText = renderer.getGridAsMatrixString(grid);
		assertEquals("Problem in getting string form of the grid", expectedGridMatrixText, gridMatrixText);
	}

	private String getExpectedGridMatrixText() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Generation : " + Grid.generationCounter + LINE_SEPERATOR);
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				buffer.append(DEAD_CHAR);
			}
			buffer.append(LINE_SEPERATOR);
		}
		return buffer.toString();
	}

	/**
	 * Test method for {@link com.ptc.gameoflife.ui.IRenderer#getNeighborCountGrid(com.ptc.gameoflife.Grid)}.
	 * @throws GameOfLifeException 
	 */
	@Test
	public void testGetNeighborCountGrid() throws GameOfLifeException {
		Grid grid = Grid.getGrid("0,0");
		String expectedString = getGetNeighborCountGrid();
		String actualString = renderer.getNeighborCountGrid(grid);
		assertEquals("Problem in getting neighbor count grid", expectedString, actualString);
	}
	
	private String getGetNeighborCountGrid() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				if((row == 0 && column ==1 ) || (row == 1 && column == 0) || (row == 1 && column == 1))
					buffer.append("1");
				else
					buffer.append("0");
			}
			buffer.append(LINE_SEPERATOR);
		}
		return buffer.toString();
	}

}
