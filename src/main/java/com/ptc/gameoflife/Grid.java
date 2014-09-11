/**
 * 
 */
package com.ptc.gameoflife;

import static com.ptc.gameoflife.config.Configuration.*;

import com.ptc.gameoflife.exception.GameOfLifeException;
import com.ptc.gameoflife.util.GridUtil;

/**
 * This is a singleton class, as there should be only one instance of the gaming arena i.e. grid
 * 
 * @author sagar_borse
 * 
 */
public class Grid {
	
	private static Grid grid;
	
	public static long generationCounter = 0;

	// Initially i tried using list, but too many calculations were involved in
	// mapping x-y coordinates of the cell in the grid, to the index of the list
	// private List<Cell> cells;
	private Cell[][] cells;

	// Using Singleton pattern so as to get same grid all the time
	private Grid() {
		cells = new Cell[ROW_MAX_SIZE][COLUMN_MAX_SIZE];
		initGrid();
	}

	/**
	 * Initialize the grid with all dead cells
	 */
	private void initGrid() {
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				cells[row][column] = new Cell(row, column, State.DEAD, 0);
			}
		}
	}

	public Cell getCell(int row, int column){
		return cells[row][column];
	}
	
	public static Grid getGrid() throws GameOfLifeException {
		return getGrid(null);
	}

	public static Grid getGrid(String initialInput) throws GameOfLifeException {
		if (grid == null) {
			grid = new Grid();
		}
		GridUtil.applyInitialInput(grid, initialInput);
		return grid;
	}

	public Cell[][] getCells() {
		return cells;
	}
}
