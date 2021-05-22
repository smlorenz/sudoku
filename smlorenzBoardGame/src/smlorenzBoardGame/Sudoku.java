package smlorenzBoardGame;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Sudoku {
	
	private String[][] board = new String[9][9];	
	private String b;
	private Set<String> s = new HashSet<>();
	
    
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
    public void loadNumbers(String filename) //making the board the board
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
        b = filename; //initiallize!! we love her most of the time
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
        	throw new RuntimeException(e);
        } //
    }
    
    public void showSolution(String filename) throws IOException { //simple and sweet
    	//this.configureBoard();
    	this.loadNumbers(filename);
    }
    
	public void difficultyLevel() throws IOException { //helps show solutions
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
	
	public void addNumber(String n, int x, int y) {
		this.checkNumber(x,  y); //making our call.
		if(s.contains(n)) { //if our row or col or 3x3 has this number, throws this
			throw new IllegalNumberException("This number "+n+" already exists!"); //still ugly???
		}
		this.setNums(y, x, n);
	}
	
	public Set<String> checkNumber(int x, int y) {
		//initialize
		this.s = new HashSet<>();
		
		if(b.equals("EasyPuzzle") || b.equals("MediumPuzzle") || b.equals("HardPuzzle") || b.equals("EvilPuzzle")) {
		for(int i=0; i<9; i++) { //going through rows
			if(i == y) { //need what row we are at
				for(int k=0; k<9; k++) { //now we cycle through that row
					s.add(this.get(y, k)); //add whatever is at that row and those cols
					}
				}
			}
		
			for(int j=0; j<9; j++) { //going through col
				if(j == x) { //need what col we are at
					for(int l=0; l<9; l++) { //now we cycle through that col
						s.add(this.get(l, x)); //add whatever is at that col and those rows
						}
					}
				}
			int R = y/3;//need where we are
			int C = x/3;
			for(int i=R*3; i<(R*3)+3; i++) { //checks 3x3 row and col
				for(int j=C*3; j<(C*3)+3; j++) {
					s.add(this.get(i, j));
				}
			}
		}
		return s;
	}
	
	public boolean checkException(String n) {
		if(s.contains(n)) {
			return false;
		}
		return true;
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
	
	public void showHint2() { 
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
	
	public void showHint3() { //I chose to do this because I like the idea of knowing how many hints you actually have
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