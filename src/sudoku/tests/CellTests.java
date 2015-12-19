package sudoku.tests;

import sudoku.model.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the Cell class
 * @author Anchal Agrawal
 */
public class CellTests {
	
	private static final int UNASSIGNED = -1;
	private Grid sudoku;
	
	/**
	 * Setup test suite
	 */
	@Before
	public void setupTests() {
		sudoku = new Grid();
	}
	
	/**
	 * Test the setValue() method with invalid cell values
	 */
	@Test
	public void testInvalidValues() {
		
		Cell cell = sudoku.getCell(0, 0);
		assertEquals(cell.getValue(), UNASSIGNED);
		
		// test invalid integers
		cell.setValue(-4);
		assertEquals(cell.getValue(), UNASSIGNED);
		
		cell.setValue(10);
		assertEquals(cell.getValue(), UNASSIGNED);
		
		// test characters
		cell.setValue('a');
		assertEquals(cell.getValue(), UNASSIGNED);
		
		cell.setValue('X');
		assertEquals(cell.getValue(), UNASSIGNED);
	}
	
	/**
	 * Test the setValue() method with valid cell values
	 */
	@Test
	public void testValidValues() {
		
		Cell cell = sudoku.getCell(5, 6);
		
		cell.setValue(4);
		assertEquals(cell.getValue(), 4);
		
		cell.setValue(1);
		assertEquals(cell.getValue(), 1);
	}
	
	/**
	 * Test constructor with invalid coordinates
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInvalidInit() {
		
		Cell cell = new Cell(-1, 9);
	}
}
