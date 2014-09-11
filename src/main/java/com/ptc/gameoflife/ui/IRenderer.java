/**
 * 
 */
package com.ptc.gameoflife.ui;

import com.ptc.gameoflife.Grid;

/**
 * Interface provides the contract for rendering related api's
 * 
 * @author sagar_borse
 *
 */
public interface IRenderer {

	public String getGridAsMatrixString(Grid grid);
	
	public String getNeighborCountGrid(Grid grid);
	
	public void display(String text);
}
