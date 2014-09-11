/**
 * 
 */
package com.ptc.gameoflife;

/**
 * @author sagar_borse
 *
 */
public class Cell {
	
	private int x;
	private int y;
	private State state;
	private int aliveNeighborCount;
	
	public Cell(int x, int y, State status, int aliveNeighbourCount) {
		super();
		this.x = x;
		this.y = y;
		this.state = status;
		this.aliveNeighborCount = aliveNeighbourCount;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getAliveNeighborCount() {
		return aliveNeighborCount;
	}
	public void setAliveNeighborCount(int aliveNeighborCount) {
		this.aliveNeighborCount = aliveNeighborCount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", status=" + state + "]";
	}
}
