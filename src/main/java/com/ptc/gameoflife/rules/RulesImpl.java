/**
 * 
 */
package com.ptc.gameoflife.rules;

import com.ptc.gameoflife.Cell;
import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.State;
import com.ptc.gameoflife.util.GridUtil;

import static com.ptc.gameoflife.config.Configuration.*;

/**
 * Implements the rules to play the game
 * 
 * @author sagar_borse
 * 
 */
public class RulesImpl implements IRules {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ptc.gameoflife.rules.IRules#applyRule()
	 */
	public void applyRule(Grid grid) {
		// For each cell count the neighbors
		countNeighborsForEachCell(grid);
		
		Cell[][] cells = grid.getCells();
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				Cell cell = cells[row][column];
				
				if(State.ALIVE.equals(cell.getState()))
					ruleOfDeath(cell, grid);
				else
					ruleOfBirth(cell, grid);
			}
		}
		
		// Increment the generation counter
		Grid.generationCounter ++;
	}

	private void countNeighborsForEachCell(Grid grid) {
		Cell[][] cells = grid.getCells();
		for (int row = 0; row < ROW_MAX_SIZE; row++) {
			for (int column = 0; column < COLUMN_MAX_SIZE; column++) {
				Cell cell = cells[row][column];
				int neighbourCount = GridUtil.getAliveNeighbourCount(cell, grid);
				cell.setAliveNeighborCount(neighbourCount);
			}
		}
	}

	private void ruleOfDeath(Cell cell, Grid grid) {
		
		int neighbourCount = cell.getAliveNeighborCount();
		
		// Death due to isolation [neighbourCount < 2]
		// Death due to over population [neighbourCount > 3]
		if (neighbourCount < 2 || neighbourCount > 3) {
			cell.setState(State.DEAD);
		}
		// so the Else condition : Cell lives only if there are exactly 2 or 3 cells alive
	}
	
	private void ruleOfBirth(Cell cell, Grid grid) {
		
		int neighbourCount = cell.getAliveNeighborCount();
		
		// Cell reproduces if there are exactly 3 alive neighbor cells
		if (neighbourCount == 3) {
			cell.setState(State.ALIVE);
		}
	}

}
