package sudoku.tests;

import sudoku.model.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the Game class
 * @author Anchal Agrawal
 */
public class GameTests {

	private static final int SIZE = 9;
	private Game game;
	private Cell[][] sudokuGrid;
	
	/**
	 * Setup test suite
	 */
	@Before
	public void setupTests() {
		game = new Game();
		sudokuGrid = game.getGrid();
	}
	
	/**
	 * Test candidate logic
	 */
	@Test
	public void testCandidates() {
		
		// all spots empty in the initial grid
		assertEquals(game.findEmptySpot(), true);
		
		// check first empty spot's coordinates
		assertEquals(game.emptyX, 0);
		assertEquals(game.emptyY, 0);
		
		// init the grid with values in the first row
		sudokuGrid[0][1].setValue(2);
		sudokuGrid[0][2].setValue(3);
		sudokuGrid[0][3].setValue(4);
		sudokuGrid[0][4].setValue(5);
		sudokuGrid[0][5].setValue(6);
		sudokuGrid[0][6].setValue(7);
		sudokuGrid[0][7].setValue(8);
		sudokuGrid[0][8].setValue(9);
		
		// get candidates
		ArrayList<Integer> candidates = game.getCandidates(0, 0);
		
		// candidates = [1]
		int size = candidates.size();
		int val = candidates.get(0);
		assertEquals(size == 1 && val == 1, true);
	}
	
	/**
	 * Test row duplicate logic
	 */
	@Test
	public void testRowDuplicates() {
		
		assertEquals(game.hasDuplicates(true), false); // no duplicates initially
		sudokuGrid[0][0].setValue(1);
		sudokuGrid[0][1].setValue(1);
		assertEquals(game.hasDuplicates(true), true);
	}
	
	/**
	 * Test column duplicate logic
	 */
	@Test
	public void testColumnDuplicates() {
		
		assertEquals(game.hasDuplicates(false), false); // no duplicates initially
		sudokuGrid[0][3].setValue(6);
		sudokuGrid[1][3].setValue(6);
		assertEquals(game.hasDuplicates(false), true);
	}
	
	/**
	 * Test 3x3 sub-grid duplicate logic
	 */
	@Test
	public void checkSubgridDuplicates() {
		
		assertEquals(game.subgridHasDups(0, 0), false); // no duplicates initially
		sudokuGrid[0][0].setValue(7);
		sudokuGrid[2][2].setValue(7);
		assertEquals(game.subgridHasDups(0, 0), true);
	}
	
	/**
	 * Test Sudoku solving algorithm
	 */
	@Test
	public void testSudokuAlgorithm() {
		
		assertEquals(game.fillGrid(), true); // blank grid is solvable
		
		// reset all cells for the test below
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sudokuGrid[i][j].resetValue();
			}
		}
		
		/* Set values in some cells
		 * (from telegraph.co.uk/news/science/science-news/9359579/Worlds-hardest-sudoku-can-you-crack-it.html)
		 */
		sudokuGrid[0][0].setValue(8);
		sudokuGrid[1][2].setValue(3);
		sudokuGrid[1][3].setValue(6);
		sudokuGrid[2][1].setValue(7);
		sudokuGrid[2][4].setValue(9);
		sudokuGrid[2][6].setValue(2);
		sudokuGrid[3][1].setValue(5);
		sudokuGrid[3][5].setValue(7);
		sudokuGrid[4][4].setValue(4);
		sudokuGrid[4][5].setValue(5);
		sudokuGrid[4][6].setValue(7);
		sudokuGrid[5][3].setValue(1);
		sudokuGrid[5][7].setValue(3);
		sudokuGrid[6][2].setValue(1);
		sudokuGrid[6][7].setValue(6);
		sudokuGrid[6][8].setValue(8);
		sudokuGrid[7][2].setValue(8);
		sudokuGrid[7][3].setValue(5);
		sudokuGrid[7][7].setValue(1);
		sudokuGrid[8][1].setValue(9);
		sudokuGrid[8][6].setValue(4);
		
		// check that the grid can be filled
		assertEquals(game.fillGrid(), true);
		
		// check that the solution grid has no duplicates
		assertEquals(game.gridHasDuplicates(), false);	
	}
}
