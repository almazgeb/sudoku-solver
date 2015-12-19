package sudoku.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to hold attributes of a Sudoku game
 * @author Anchal Agrawal
 */
public class Game {

	private static final int SIZE = 9;
	private static final int UNASSIGNED = -1;
	private Grid g;
	private Cell[][] grid;
	public int emptyX, emptyY;
	
	/**
	 * Constructor
	 */
	public Game() {
		g = new Grid();
		grid = g.getGrid();
		emptyX = 0;
		emptyY = 0;
	}
	
	/**
	 * Get sudoku grid
	 */
	public Cell[][] getGrid() {
		return grid;
	}
	
	/**
	 * Check 3x3 sub-grids for duplicate values.
	 * @param x  starting x coordinate
	 * @param y  starting y coordinate
	 * @return true  if sub-grid has duplicates
	 */
	public boolean subgridHasDups(int x, int y) {
		
		HashMap<Integer, Boolean> countMap = new HashMap<Integer, Boolean>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				Cell cell = g.getCell(x+i, y+j);
				int val = cell.getValue();
				if (val != UNASSIGNED) {
					if (countMap.containsKey(val)) {
						return true; // value already exists
					}
					countMap.put(val, true);
				}
			}
		}
		return false;
	}
	
	/**
	 * Check the grid's rows or columns for duplicate values.
	 * @param checkRows  true if rows are to be checked
	 * @return true  if row/column has duplicates
	 */
	public boolean hasDuplicates(boolean checkRows) {
		
		HashMap<Integer, Boolean> countMap = new HashMap<Integer, Boolean>();
		for (int i = 0; i < SIZE; i++) {

			countMap.clear();			
			for (int j = 0; j < SIZE; j++) {
				
				Cell cell;
				if (checkRows) {
					cell = g.getCell(i, j);
				}
				else {
					cell = g.getCell(j, i);
				}
				
				int val = cell.getValue();
				if (val != UNASSIGNED) {
					if (countMap.containsKey(val)) {
						return true; // value already exists
					}
					countMap.put(val, true);
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Check if the initial grid has duplicate values in rows/columns/subgrids.
	 * @return true  if there are duplicates
	 */
	public boolean gridHasDuplicates() {
		
		// check rows
		boolean checkRows = true;
		if (hasDuplicates(checkRows)) {
			return true;
		}
		
		// check columns
		checkRows = false;
		if (hasDuplicates(checkRows)) {
			return true;
		}
		
		// check 3x3 grids
		for (int xOffset = 0; xOffset < SIZE; xOffset += 3) {
			for (int yOffset = 0; yOffset < SIZE; yOffset += 3) {
				if (subgridHasDups(xOffset, yOffset)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Entry point to the Sudoku-solving algorithm.
	 * @return true  if grid is solvable
	 */
	public boolean solveSudoku() {
		
		if (gridHasDuplicates()) { // check for invalid initial config
			return false;
		}

		return fillGrid();
	}
	
	/**
	 * Fill all cells in the Sudoku grid.
	 * 1. Find an unassigned cell. If there are none, we're done
	 * 2. Find possible candidates for that cell
	 * 3. Try a candidate and recurse on the Sudoku grid
	 * @return true  if grid is solvable
	 */
	public boolean fillGrid() {
		
		if (!findEmptySpot()) { 
			return true; // solved when all cells are filled
		}
		
		// save empty cell's coordinates
		int x = emptyX;
		int y = emptyY;
		
		ArrayList<Integer> candidates = getCandidates(x, y);
		for (int i = 0; i < candidates.size(); i++) {
			
			int val = candidates.get(i);
			grid[x][y].setValue(val); // try a candidate
			boolean result = fillGrid();
			if (result) {
				return true; // candidate leads to a solved config
			}
			grid[x][y].resetValue(); // reset the value and try another candidate
		}
		return false;
	}
	
	/**
	 * Find an unassigned cell
	 * @return true  if there's an unassigned cell
	 */
	public boolean findEmptySpot() {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (grid[i][j].getValue() == UNASSIGNED) {
					emptyX = i;
					emptyY = j;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Find possible candidates in a cell
	 * @return candidates  ArrayList
	 */
	public ArrayList<Integer> getCandidates(int x, int y) {
		
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();		
		for (int i = 1; i <= SIZE; i++) {
			map.put(i, false);
		}
				
		// scan row
		for (int i = 0; i < SIZE; i++) {
			Cell currCell = g.getCell(x, i);
			int val = currCell.getValue();
			if (val != UNASSIGNED) {
				map.put(val, true);
			}
		}
		
		// scan column
		for (int i = 0; i < SIZE; i++) {
			Cell currCell = g.getCell(i, y);
			int val = currCell.getValue();
			if (val != UNASSIGNED) {
				map.put(val, true);
			}
		}
		
		// scan 3x3 grid		
		int xOffset = (x / 3) * 3;
		int yOffset = (y / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell currCell = g.getCell(xOffset+i, yOffset+j);
				int val = currCell.getValue();
				if (val != UNASSIGNED) {
					map.put(val, true);
				}
			}
		}
		
		// get candidates for cell
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for (HashMap.Entry<Integer, Boolean> entry : map.entrySet()) {
			if (!entry.getValue()) {
				candidates.add(entry.getKey());
			}
		}
		
		return candidates;
	}

}
