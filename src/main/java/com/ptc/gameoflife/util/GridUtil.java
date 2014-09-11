/**
 * 
 */
package com.ptc.gameoflife.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ptc.gameoflife.Cell;
import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.State;
import com.ptc.gameoflife.config.Configuration;
import com.ptc.gameoflife.exception.GameOfLifeException;

import static com.ptc.gameoflife.config.Configuration.*;

/**
 * @author sagar_borse
 * 
 */
public class GridUtil {

	/**
	 * If list of neighbor cells needed outside, we can make this public
	 * @param cell
	 * @param grid
	 * @return
	 */
	private static List<Cell> getNeighbourCells(Cell cell, Grid grid) {
		int cellRow = cell.getX();
		int cellColumn = cell.getY();
		List<Cell> aliveNeighbors = new ArrayList<Cell>();
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				int neighborRow = cellRow + i;
				int neighborColumn = cellColumn + j;
				if (i == 0 && j == 0) {
					// do nothing as it is same cell
				} else if (neighborRow < 0 || neighborRow >= ROW_MAX_SIZE ) {
					// do nothing as location is not part of grid
				} else if(neighborColumn < 0 || neighborColumn >= COLUMN_MAX_SIZE ) {
					// do nothing as location is not part of grid
				} else {
					Cell neighborCell = grid.getCell(neighborRow, neighborColumn);
					if (neighborCell.getState().equals(State.ALIVE))
						aliveNeighbors.add(neighborCell);
				}
			}
		return aliveNeighbors;
	}

	// FIXME Instead of int this method can return byte, as we know, number of neighbors will
	// never exceed 8. I should not increase space complexity, but seems okay for now.
	public static int getAliveNeighbourCount(Cell cell, Grid grid) {
		List<Cell> neighbours = getNeighbourCells(cell, grid);
		return neighbours.size();
	}

	public static void applyInitialInput(Grid grid, String initialInput) throws GameOfLifeException {
		if (isValidInput(initialInput)) {
			if (initialInput != null && !initialInput.isEmpty()) {
				String[] cellLocString = initialInput.split("\\|");
				for (int i = 0; i < cellLocString.length; i++) {
					String[] loc = cellLocString[i].split(",");
					int row = Integer.parseInt(loc[0]);
					int column = Integer.parseInt(loc[1]);
					Cell cell = grid.getCell(row, column);
					cell.setState(State.ALIVE);
				}
			}
		}
	}

	/**
	 * Check for valid input, if not, throw GameOfLifeException
	 * @param initialInput
	 * @return
	 * @throws GameOfLifeException
	 */
	private static boolean isValidInput(String initialInput) throws GameOfLifeException {
		boolean isValidInput = false;
		// If input is null or empty then it is one of the valid input
		if (null == initialInput || initialInput.isEmpty()) {
			isValidInput = true;
		} else {
			Pattern pattern = Pattern.compile(GRID_INPUT_PATTERN);
			Matcher matcher = pattern.matcher(initialInput);
			if (matcher.matches())
				isValidInput = true;
		}
		if (!isValidInput) {
			throw new GameOfLifeException(Configuration.INVALID_INPUT_MSG);
		}
		return true;
	}
	
	/**
	 * Resets the grid, makes all cell as dead
	 * @param grid
	 */
	public static void clearGrid(Grid grid){
		Cell[][] cells = grid.getCells();
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				cells[row][column].setState(State.DEAD);
			}
		}
		// Resetting generation counter
		Grid.generationCounter = 0;
	}
}
