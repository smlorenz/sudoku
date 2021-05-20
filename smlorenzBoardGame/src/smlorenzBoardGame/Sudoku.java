package smlorenzBoardGame;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Sudoku {
	
	private String[][] board = new String[9][9];	
	private String b;
	
    
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
        b = filename;
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
    
	public void difficultyLevel() throws IOException {
    	if(b.equals("EasyPuzzle")) {
    		this.showSolution("EasyPuzzleSolutions");
    	} else if(b.equals("MediumPuzzle")) {
    		this.showSolution("MediumPuzzleSolutions");
    	} else if(b.equals("HardPuzzle")) {
    		this.showSolution("HardPuzzleSolutions");
    	} else if(b.equals("EvilPuzzle")) {
    		this.showSolution("EvilPuzzleSolutions");
    	}
    }
	
	public void addNumber(String n, int x, int y) { //trying to add number to certain row at certain column??
		for(int r=0; r<1; r++) {
			for(int c=0; c<9; c++) {
				if(board[r][c] == n) {
					System.out.println(board[r][c]);
					throw new IllegalArgumentException();
				}
				
			}
		}
		this.setNums(y, x, n);
	}
	
	public void clearNumber(int x, int y) {
		this.setNums(y, x, "");
	}
	
	public void showHint1() { //this was the first three hints for the puzzles I got
		if(b.equals("EasyPuzzle")) {
			this.setNums(1, 0, "2");
		}
		if(b.equals("MediumPuzzle")) {
			this.setNums(6, 6, "6"); //this was unintentional...I love the Lord
		}
		if(b.equals("HardPuzzle")) {
			this.setNums(3, 8, "3");
		}
		if(b.equals("EvilPuzzle")) {
			this.setNums(5, 3, "1");
		}
		
	}
	
	public void showHint2() { //this was the first three hints for the puzzles I got
		if(b.equals("EasyPuzzle")) {
			this.setNums(6, 6, "1");
		}
		if(b.equals("MediumPuzzle")) {
			this.setNums(4, 4, "4");
		}
		if(b.equals("HardPuzzle")) {
			this.setNums(6, 2, "3");
		}
		if(b.equals("EvilPuzzle")) {
			this.setNums(3, 4, "6");
		}
		
	}
	
	public void showHint3() { //this was the first three hints for the puzzles I got
		if(b.equals("EasyPuzzle")) {
			this.setNums(0, 5, "3");
		}
		if(b.equals("MediumPuzzle")) {
			this.setNums(6, 4, "5");
		}
		if(b.equals("HardPuzzle")) {
			this.setNums(0, 3, "6");
		}
		if(b.equals("EvilPuzzle")) {
			this.setNums(5, 2, "4");
		}
		
	}
}