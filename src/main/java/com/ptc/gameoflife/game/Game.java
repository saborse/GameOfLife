/**
 * 
 */
package com.ptc.gameoflife.game;

import static com.ptc.gameoflife.config.Configuration.DISPLAY_DELAY_SLOW;

import com.ptc.gameoflife.Grid;
import com.ptc.gameoflife.exception.GameOfLifeException;
import com.ptc.gameoflife.rules.IRules;
import com.ptc.gameoflife.rules.RulesImpl;
import com.ptc.gameoflife.ui.ConsoleRenderer;
import com.ptc.gameoflife.ui.IRenderer;

/**
 * @author sagar_borse
 * 
 */
public class Game {

	public void startGame() throws GameOfLifeException {
		startGame(null);
	}

	public void startGame(String initialInput) throws GameOfLifeException {
		// Create the game arena
		Grid grid = Grid.getGrid(initialInput);
		
		// Renderer to display the output
		IRenderer renderer = new ConsoleRenderer();
		String gridString = renderer.getGridAsMatrixString(grid);
		renderer.display(gridString);
		
		// Load Rule Engine
		IRules rulesOfDeathAndBirth = new RulesImpl();
		
		while(true){
			// Apply the rules
			rulesOfDeathAndBirth.applyRule(grid);
			gridString = renderer.getGridAsMatrixString(grid);
			renderer.display(gridString);
			
			try {
				Thread.sleep(DISPLAY_DELAY_SLOW);
			} catch (InterruptedException e) {
				throw new GameOfLifeException(e);
			}
		}
	}
}
