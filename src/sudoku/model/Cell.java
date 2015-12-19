package sudoku.model;

/**
 * Class to store attributes of a cell in the Sudoku grid
 * @author Anchal Agrawal
 */
public class Cell {

	private static final int SIZE = 9;
	private static final int UNASSIGNED = -1;
	private int x;
	private int y;
	private int value;
	
	/**
	 * Constructor
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @throws IndexOutOfBoundsException
	 */
	public Cell(int x, int y) {
		
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			throw new IndexOutOfBoundsException();
		}
				
		this.x = x;
		this.y = y;
		value = UNASSIGNED;
	}
	
	/**
	 * Get x
	 * @return x  x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get y
	 * @return y  y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Get value
	 * @return value  number in cell
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Set value
	 * @param value  number in cell
	 */
	public void setValue(int value) {
		
		// value must be in [1, SIZE]
		if (value >= 1 && value <= SIZE) {
			this.value = value;
		}
	}
	
	/**
	 * Reset value
	 */
	public void resetValue() {		
		value = UNASSIGNED;
	}
	
}
