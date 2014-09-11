/**
 * 
 */
package com.ptc.gameoflife.rules;

import com.ptc.gameoflife.Grid;

/**
 * Interface provides the contract to apply the game rules
 * 
 * @author sagar_borse
 *
 */
public interface IRules {
	public void applyRule(Grid grid);
}
