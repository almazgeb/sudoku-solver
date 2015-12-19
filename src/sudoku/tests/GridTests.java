package sudoku.tests;

import sudoku.model.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the Grid class
 * @author Anchal Agrawal
 */
public class GridTests {
	
	private Grid sudoku;
	
	/**
	 * Setup test suite
	 */
	@Before
	public void setupTests() {
		sudoku = new Grid();
	}
	
	/**
	 * Test the getCell() method with an invalid cell location
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInvalidLocation() {
		
		Cell invalidCell = sudoku.getCell(-4, 10);
	}
	
	/**
	 * Test the getCell() method with a valid cell location
	 */
	@Test
	public void testValidLocation() {
		
		Cell validCell = sudoku.getCell(1, 7);
		assertNotEquals(validCell, null);
	}
	
}
