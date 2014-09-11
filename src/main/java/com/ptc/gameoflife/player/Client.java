/**
 * 
 */
package com.ptc.gameoflife.player;

import com.ptc.gameoflife.exception.GameOfLifeException;
import com.ptc.gameoflife.game.Game;

/**
 * @author sagar_borse
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Here input can be read from UI (if any) / command line / file / DB or any other external source.
		
		// Glider pattern input : http://en.wikipedia.org/wiki/Conway's_Game_of_Life#Examples_of_patterns
		String initialInput = "0,1|1,2|2,0|2,1|2,2";
		
		// Initializing game engine
		Game game = new Game();
		try {
			game.startGame(initialInput);
		} catch (GameOfLifeException e) {
			// Can be logged using a logger
			System.out.println("Problem in running game : " + e.getMessage());
			//e.printStackTrace();
		}
	}
}
