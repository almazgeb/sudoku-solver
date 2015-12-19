package sudoku.model;

/**
 * Class to hold attributes of the Sudoku grid
 * @author Anchal Agrawal
 */
public class Grid {
	
	private static final int SIZE = 9;
	private Cell[][] grid;
	
	/**
	 * Constructor
	 */
	public Grid() {
		grid = new Cell[SIZE][SIZE];
		initGrid();
	}
	
	/**
	 * Initialize the grid with empty cells
	 */
	private void initGrid() {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}
	}
	
	/**
	 * Get Sudoku grid
	 * @return grid
	 */
	public Cell[][] getGrid() {
		return grid;
	}
	
	/**
	 * Get a grid cell
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @return Cell
	 * @throws IndexOutOfBoundsException
	 */
	public Cell getCell(int x, int y) {
		
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			throw new IndexOutOfBoundsException();
		}
		return grid[x][y];
	}
	
}
