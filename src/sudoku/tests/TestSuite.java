package sudoku.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite class
 * @author Anchal Agrawal
 */

@RunWith(Suite.class)
@SuiteClasses({ CellTests.class, 
        GridTests.class, 
        GameTests.class })
public class TestSuite {

}
