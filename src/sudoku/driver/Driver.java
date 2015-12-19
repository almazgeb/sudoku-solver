package sudoku.driver;

import javax.swing.JFrame;

import sudoku.model.*;
import sudoku.controller.*;
import sudoku.view.*;

/**
 * Entry point to Sudoku
 * @author Anchal Agrawal
 */
public class Driver {

	public static void main(String[] args) {

		Game game = new Game(); // model
		View view = new View(); // view
		Controller controller = new Controller(game, view); // controller

		JFrame frame = new JFrame("Sudoku Solver");
		frame.add(view.getOuterPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);		
	}
}
