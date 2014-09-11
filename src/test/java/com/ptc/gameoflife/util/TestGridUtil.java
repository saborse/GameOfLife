/**
 * 
 */
package com.ptc.gameoflife.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ptc.gameoflife.Cell;
import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.State;
import com.ptc.gameoflife.config.Configuration;
import com.ptc.gameoflife.exception.GameOfLifeException;

/**
 * @author sagar_borse
 *
 */
public class TestGridUtil {
	
	private static Grid grid;
	private static final String initialInput = "0,0|0,1";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grid = Grid.getGrid();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		grid = null;
	}

	/**
	 * Clearing the game arena before starting new test case
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
	 * Test method for {@link com.ptc.gameoflife.util.GridUtil#getAliveNeighbourCount(com.ptc.gameoflife.Cell, com.ptc.gameoflife.Grid)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetAliveNeighbourCount() throws Exception {
		grid = Grid.getGrid(initialInput);
		
		final int expectedCount = 1;
		Cell cell = grid.getCell(0, 0);
		int neighborCount = GridUtil.getAliveNeighbourCount(cell, grid);
		assertEquals("Neighbor count is incorrect",expectedCount, neighborCount);
	}

	/**
	 * Test method for {@link com.ptc.gameoflife.util.GridUtil#applyInitialInput(com.ptc.gameoflife.Grid, java.lang.String)}.
	 */
	@Test
	public void testApplyInitialInput() {
		try {
			Cell cell00 = grid.getCell(0, 0);
			Cell cell01 = grid.getCell(0, 1);
			assertEquals(State.DEAD, cell00.getState());
			assertEquals(State.DEAD, cell01.getState());

			GridUtil.applyInitialInput(grid, initialInput);
			
			cell00 = grid.getCell(0, 0);
			cell01 = grid.getCell(0, 1);
			assertEquals("Inital input is not applied correctly", State.ALIVE, cell00.getState());
			assertEquals("Inital input is not applied correctly", State.ALIVE, cell01.getState());
			
		} catch (GameOfLifeException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testApplyInitialInput_WithInvalidInput() {
		try {
			final String invalidInput = "abcd";
			GridUtil.applyInitialInput(grid, invalidInput);
		} catch (GameOfLifeException e) {
			assertEquals(Configuration.INVALID_INPUT_MSG, e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.ptc.gameoflife.util.GridUtil#clearGrid(com.ptc.gameoflife.Grid)}.
	 * @throws GameOfLifeException 
	 */
	@Test
	public void testClearGrid() throws GameOfLifeException {
		Grid grid = Grid.getGrid();
		Cell[][] cells = grid.getCells();
		for (Cell[] cells2 : cells) {
			for (Cell cell : cells2) {
				assertEquals("Grid is not cleared correctly", State.DEAD , cell.getState());
			}
		}
	}

}
