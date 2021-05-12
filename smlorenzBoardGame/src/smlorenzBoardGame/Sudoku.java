package smlorenzBoardGame;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Sudoku {
	
	private String[][] board = new String[9][9];
    
    /**
     * Get the num at rc
     * @param row
     * @param col
     * @return The number at the given row and column.
     */
    public String get(int row, int col) {
        if (row<0 || col<0 || row>=board.length || col>=board.length) {
        	// should we throw an exception instead?
            return null; 
        }
        return board[row][col];
    }
    
    public void setNums(int r, int c, String n) {
        board[r][c] = n;
        if(n.equals("0")) {
        	return;
        }
    }
    
    /**
     * Load these numbers into a set.
     * @param filename Name of the file to read.
     * @throws IOException
     */
    
    //TODO: nested for loops (0-9,,,,0-9)
    public void loadNumbers(String filename)
    throws IOException
    {
        Scanner scan = new Scanner(new FileInputStream(filename));
        for (int r=0; r<9; r++) {
          for (int c=0; c<9; c++) {
        	  //while(scan.hasNext()) {
        		  String n = scan.next();
        		  if (!n.equals("0")) {
        			  this.setNums(r, c, n);
        		  	}
        		  if(n.equals("0")) {
        			  this.setNums(r, c, "");
        		  }
        	  	}
          	}
        }
   // }
    
    /**
     * Creating a new Sudoku board that is blank.
     * @param s is the numbers
     * @throws IOException
     */
    
    public void configureBoard() { //this is just making a blank board
        for (int i=0; i<9; i++) {
        	for (int j=0; j<9; j++) {
            board[i][j] = "";
        	}
        }
    }
    
    public Sudoku(String filename) //initialize board to blank (put 0 in every spot)
    {
        // load nums
        try {
        	loadNumbers(filename);
        } catch (IOException e) {
        	// no way to recover from this, so just repackage as runtime exception
        	throw new RuntimeException(e);
        }
        //configureBoard();
    }
    
    public void showSolution(String filename) throws IOException {
    	//this.configureBoard();
    	this.loadNumbers(filename);
    }
    
    public boolean difficultyLevel(String filename) {
    	if(filename.equals("EasyPuzzle")) {
    		return true;
    	} else if(filename.equals("MediumPuzzle")) {
    		return true;
    	} else if(filename.equals("HardPuzzle")) {
    		return true;
    	} else if(filename.equals("EvilPuzzle")) {
    		return true;
    	}
    	return false;
    }
    
  
//    public Collection<Integer> findValidWords()
//    {
//        Set<String> result = new TreeSet<>();
//        for (int r=0; r<4; r++) {
//        	for (int c=0; c<4; c++) {
//        		findHelper(r, c, new HashSet<String>(), "", result);
//        	}
//        }
//    	
//    	return result;
//    }
//    
//    private String toCoord(int row, int col) {
//    	return "" + row + col;
//    }
//    
//    private void findHelper(int row, int col, Set<String> visited, String prefix, Set<String> result)
//    {
//        if (visited.contains(toCoord(row, col))) {
//        	return;
//        }
//        
//        if (!trie.startsWith(prefix)) {
//        	return;
//        }
//        
//        if (row < 0 || col < 0 || row > 3 || col > 3) {
//        	return;
//        }
//        
//        visited.add(toCoord(row, col));
//    	
//    	// we found a word!
//        prefix += get(row, col);
//    	if (trie.contains(prefix)) {
//    		result.add(prefix);
//    	}
//    	
//    	// recursive steps!
//    	for (int r=row-1; r<=row+1; r++) {
//    		for (int c=col-1; c<=col+1; c++) {
//    			if (r == row && c == col) {
//    				continue;
//    			}
//    			// new HashSet<String>(visited): "copy constructor"
//    			findHelper(r, c, new HashSet<String>(visited), prefix, result);
//    		}
//    	}
//    }
//    
//    /**
//     * Shuffle the dice, assign them to a spot on the board, and roll them.  Use
//     * the given random number generator for shuffling and rolling the dice.
//     * 
//     * Passing the Random so that we can get predictable results
//     * 
//     * @param r
//     */
//    public void configureBoard() {
//        // Shuffle the dice
//        shuffleDice();
//        for (int i=0; i<dice.length; i++) {
//            int row = i / board.length;
//            int col = i % board.length;
//            board[row][col] = dice[i].roll(random);
//        }
//    }
//    
//    /* (non-Javadoc)
//     * @see java.lang.Object#toString()
//     */
//    public String toString() {
//        StringBuffer buf=new StringBuffer();
//        for (int row=0; row<board.length; row++) {
//            for (int col=0; col<3; col++) {
//                buf.append(board[row][col]);
//                buf.append("\t");
//            }
//            buf.append(board[row][3]);
//            buf.append("\n");
//        }
//        return buf.toString();
//    }

}
