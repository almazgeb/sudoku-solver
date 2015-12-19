package sudoku.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Class for the Sudoku GUI
 * @author Anchal Agrawal
 */
public class View {
	
	private static final int SIZE = 9;
	private JPanel outer;
	private JPanel sudokuPanel;
	private JPanel topPanel, bottomPanel;
	public JButton[][] numButtons; 
	public JButton solveButton, resetButton, undoButton;
	public JLabel msgLabel;
	
	/**
	 * Default constructor
	 */
    public View() {
    	
    	try {
    	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	outer = new JPanel(new BorderLayout(3, 3));
    	setupTopPanel();
    	setupBottomPanel();
   
    	numButtons = new JButton[9][9]; // sudoku buttons
    	sudokuPanel = new JPanel(new GridLayout(0, 9)); 	
        outer.add(sudokuPanel);
        
        buildSudoku();
    }
       
    /**
     * Init the top panel and add buttons
     */
    private void setupTopPanel() {
    	
    	// top panel with action buttons
    	topPanel = new JPanel();
    	topPanel.setPreferredSize(new Dimension(outer.getWidth(), 30));
    	solveButton = new JButton("Solve");
    	resetButton = new JButton("Reset");
    	undoButton = new JButton("Undo last cell");
    	topPanel.add(solveButton);
    	topPanel.add(resetButton);
    	topPanel.add(undoButton);
    	outer.add(topPanel, BorderLayout.NORTH);
    }
    
    /**
     * Init the bottom panel and add a label
     */
    private void setupBottomPanel() {
    	
    	// bottom panel with status label
    	bottomPanel = new JPanel(new BorderLayout());
    	bottomPanel.setPreferredSize(new Dimension(outer.getWidth(), 30));  	
    	msgLabel = new JLabel();
    	msgLabel.setHorizontalAlignment(JLabel.CENTER);
    	bottomPanel.add(msgLabel, BorderLayout.CENTER); 	
    	outer.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Add buttons to the Sudoku grid
     */
    private void buildSudoku() {
    	
    	Insets buttonMargin = new Insets(25, 25, 25, 25);
    	for (int i = 0; i < SIZE; i++) {
    		for (int j = 0; j < SIZE; j++) {
    			
    			JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setBackground(Color.WHITE);
                b.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));   
                numButtons[j][i] = b;
                sudokuPanel.add(numButtons[j][i]);
    		}
    	}
    }
    
    /**
     * Get the outer panel
     * @return outer panel
     */
	public final JComponent getOuterPanel() {
		return outer;
	}
	
}
