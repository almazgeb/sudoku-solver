# sudoku-solver

A Java application that solves a Sudoku grid after the user enters an initial configuration. This application uses the MVC (model-view-controller) pattern. The GUI has 3 buttons - *Solve*, *Reset* and *Undo last cell* and a status label. The user can input numbers by clicking on cell buttons and pressing *Solve*. If the initial configuration is invalid (there are duplicate numbers in the same row/column/3x3 grid), the status label will display an error message. The *Reset* button clears all cells, and the *Undo last cell* button clears input cells one-by-one, starting from the last input cell.

![Solved](https://github.com/anchal-agrawal/sudoku-solver/blob/master/images/solved.png?raw=true)

### Algorithm

The Sudoku-solving algorithm follows a backtracking approach:

* Pick an unassigned (empty) cell. If all cells are filled, we're done.
* Find possible candidate numbers for that cell.
* Assign a candidate to that cell and recurse on the remaining grid.
* If the candidate doesn't lead to a valid solution, try the next one.

### Test Suite

There are 3 classes with several JUnit tests:

* sudoku/tests/CellTests.java - tests for the Cell class's methods.
* sudoku/tests/GridTests.java - tests for the Grid class's methods.
* sudoku/tests/GameTests.java - test the solving algorithm.

All tests can be run with the sudoku/tests/TestSuite.java class.

### Running the application

This repository can be imported as an Eclipse project. The sudoku/driver/Driver.java class can be run to launch the application.

### Sample run

* Initial GUI

![Start](https://github.com/anchal-agrawal/sudoku-solver/blob/master/images/start.png?raw=true)

* Enter values

![Input](https://github.com/anchal-agrawal/sudoku-solver/blob/master/images/input.png?raw=true)

* Press *Solve*

![Solved](https://github.com/anchal-agrawal/sudoku-solver/blob/master/images/solved.png?raw=true)

### External libraries

The application uses the [Apache Commons Lang](http://commons.apache.org/proper/commons-lang/) library to check if the user input is not numeric.