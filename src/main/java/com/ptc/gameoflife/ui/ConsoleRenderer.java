/**
 * 
 */
package com.ptc.gameoflife.ui;


/**
 * This class implements of display method from Irenderer interface, to display the content onto the console.
 * May be other implementations will render in file / stream / log / social media like twiter, FB etc.
 * 
 * @author sagar_borse
 * 
 */
public class ConsoleRenderer extends AbstractRenderer {

	/**
	 * Displays the text on the console.
	 * @see com.ptc.gameoflife.ui.IRenderer#display(java.lang.String)
	 */
	public void display(String text) {
		System.out.println(text);
	}

}
