package smlorenzBoardGame;
import java.awt.BorderLayout;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

	public class SudokuBoard extends JFrame {
	  //TODO: add in this class
	    private Sudoku sudoku; 
	    
		private static final long serialVersionUID = 1L;
		
		protected final int MARGIN_SIZE = 5;
	    protected final int DOUBLE_MARGIN_SIZE = MARGIN_SIZE*2;
	    protected int squareSize = 75;
	    private int numRows = 9;
	    private int numCols = 9;
	    
	    private int width = DOUBLE_MARGIN_SIZE + squareSize * numCols;    		
	    private int height = DOUBLE_MARGIN_SIZE + squareSize * numRows;   
	    private int x;
	    private int y;
	    
	    
	    private JPanel canvas;
	    private JMenuBar menuBar;
	    
		private void drawGrid(Graphics2D g) {
			g.setColor(Color.BLACK);
			Font font = new Font("Verdana", Font.BOLD, 40);
			g.setFont(font);
	        for (int r = 0; r < numRows; r++) {
	            for (int c = 0; c < numCols; c++) {
	                g.drawString(sudoku.get(r, c), 
	                		c * squareSize + MARGIN_SIZE + squareSize/3, 
	                		r * squareSize + MARGIN_SIZE + 2*squareSize/3);

	                g.drawLine(MARGIN_SIZE+c*squareSize,
	                        MARGIN_SIZE+r*squareSize,
	                        MARGIN_SIZE+(c+1)*squareSize,
	                        MARGIN_SIZE+r*squareSize);
	                g.drawLine(MARGIN_SIZE+c*squareSize, 
	                        MARGIN_SIZE+r*squareSize, 
	                        MARGIN_SIZE+c*squareSize, 
	                        MARGIN_SIZE+(r+1)*squareSize);
	                g.drawLine(MARGIN_SIZE+(c+1)*squareSize, 
	                        MARGIN_SIZE+r*squareSize, 
	                        MARGIN_SIZE+(c+1)*squareSize, 
	                        MARGIN_SIZE+(r+1)*squareSize);
	                g.drawLine(MARGIN_SIZE+c*squareSize, 
	                        MARGIN_SIZE+(r+1)*squareSize, 
	                        MARGIN_SIZE+(c+1)*squareSize, 
	                        MARGIN_SIZE+(r+1)*squareSize);
	            }
	        }
	        
	    }
		
		//TODO: new game? reset? hints?
	    
	    private void createMenuBar() {
	    	menuBar = new JMenuBar();
	        JMenu menu = new JMenu("Actions");
	        menuBar.add(menu);
	        
	        JMenuItem itemExit = new JMenuItem ("Exit");
	        menu.add(itemExit);
	        itemExit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String text=e.getActionCommand();
	                System.out.println(text);
	                System.exit(0);
	            }
	        });
	        
	        
	        JMenu difficultyMenu = new JMenu("Difficulty");
	        menuBar.add(difficultyMenu);
	        
	        addToMenu(difficultyMenu, "Easy", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
						sudoku.loadNumbers("EasyPuzzle");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                repaint();
	            }
	        });
	        
	        addToMenu(difficultyMenu, "Medium", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
						sudoku.loadNumbers("MediumPuzzle");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                repaint();
	            }
	        });
	        
	        addToMenu(difficultyMenu, "Hard", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
						sudoku.loadNumbers("HardPuzzle");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                repaint();
	            }
	        });
	        
	        addToMenu(difficultyMenu, "EVIL", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
						sudoku.loadNumbers("EvilPuzzle");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                repaint();
	            }
	        });
	        
	        JMenu hintMenu = new JMenu("Hints");
	        menuBar.add(hintMenu);
	        
	        addToMenu(hintMenu, "Hint 1", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                sudoku.showHint1();
	                repaint();
	            }
	        });
	        
	        addToMenu(hintMenu, "Hint 2", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                sudoku.showHint2();
	                repaint();
	            }
	        });
	        
	        addToMenu(hintMenu, "Hint 3", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                sudoku.showHint3();
	                repaint();
	            }
	        });
	        
	        JMenu solutionMenu = new JMenu("Solutions");
	        menuBar.add(solutionMenu);
	        
	        addToMenu(solutionMenu, "Show solution", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
						sudoku.difficultyLevel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        
	            	try {
						sudoku.difficultyLevel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	try {
						sudoku.difficultyLevel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	try {
						sudoku.difficultyLevel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            	repaint();
	            	}
	        });
	        
	        JMenu instructionMenu = new JMenu("How to play");
	        menuBar.add(instructionMenu);
	        
	        addToMenu(instructionMenu, "Instructions", new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	 JTextArea textArea = new JTextArea(10, 30);
	                 textArea.setText("Sudoku is a game of patience. Fill in the columns and rows with numbers 1-9. "
		                		+ "Make sure that there are NO repeat numbers in any row, column, or 3x3 box. "
		                		+ "To inut numbers, click on the box, then type the number you wish."
		                		+ "To change difficulty, go to the difficulty menu and select. For a hint, choose 1 hint up to 3. "
		                		+ "To revela solution, click on the solutions. To exit the game, go to the actions button. Most of all, h"
		                		+ "ave fun!");
	                 textArea.setEditable(false);
	                 JScrollPane scrollPane = new JScrollPane(textArea);
	                 JOptionPane.showMessageDialog(canvas, scrollPane);
	            }
	        });
	        this.setJMenuBar(menuBar);
	    }
	    
	    private void addToMenu(JMenu menu, String title, ActionListener listener) {
	    	JMenuItem menuItem = new JMenuItem(title);
	    	menu.add(menuItem);
	    	menuItem.addActionListener(listener);
	    }
	    
	    private void createKeyboardHandlers() {
	    	this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					System.out.println(e.getKeyChar());
				}
			});
	    }
	    
	    private void createMouseHandlers() {
	    	this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.printf("Mouse cliked at (%d, %d)\n", e.getX(), e.getY());
				}
			});
	    }
	    
	    private void initializeMouseListener() //trying to add stuff to get where we are
	    {
	    	//MouseAdapter a = new MouseAdapter() {
	    		canvas.addMouseListener(new MouseListener() {
	    			public void mouseClicked(MouseEvent e) {
	    				System.out.printf("Mouse cliked at (%d, %d)\n", e.getX(), e.getY());
	    				x = (e.getX()-MARGIN_SIZE)/squareSize;
	    				y = (e.getY()-MARGIN_SIZE)/squareSize;
//	    				System.out.println(x); //this is working fine, so why not for key :(
//	    				System.out.println(y);
	    				
	    				if(e.getButton()==MouseEvent.BUTTON1) {
	    					//?
	    				}
	    		}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
	    	});
	    		repaint();
	    }
	    
	    private void initializeKeyListener() //adding keys to input stuff in sudoku
	    {
	        this.addKeyListener(new KeyListener() {
	            public void keyPressed(KeyEvent e) {
	            	// Called when you push a key down
	            	System.out.println("key pressed: " + e.getKeyChar());
	            	if (e.getKeyChar() == KeyEvent.VK_1) {
	            		sudoku.addNumber("1", x, y);
	            		System.out.println("You have added 1."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_2) {
	            		sudoku.addNumber("2", x, y);
	            		System.out.println("You have added 2."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_3) {
	            		sudoku.addNumber("3", x, y);
	            		System.out.println("You have added 3."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_4) {
	            		sudoku.addNumber("4", x, y);
	            		System.out.println("You have added 4."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_5) {
	            		sudoku.addNumber("5", x, y);
	            		System.out.println("You have added 5."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_6) {
	            		sudoku.addNumber("6", x, y);
	            		System.out.println("You have added 6."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_7) {
	            		sudoku.addNumber("7", x, y);
	            		System.out.println("You have added 7."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_8) {
	            		sudoku.addNumber("8", x, y);
	            		System.out.println("You have added 8."); 
	            	}
	            	if (e.getKeyChar() == KeyEvent.VK_9) {
	            		sudoku.addNumber("9", x, y);
	            		System.out.println("You have added 9."); 
	            	}
	            	repaint();
	            }
	            public void keyReleased(KeyEvent e){
	            	// Called when you release a key and it goes up
	            	System.out.println("key released: " + e.getKeyChar());
	            }
	            public void keyTyped(KeyEvent e) {
	            	// Gets called when you push a key down and then release it,
	            	// without pushing any other keys in between
	            	System.out.println("key typed: " + e.getKeyChar());
	            	if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            		//TODO remove in box
	            		sudoku.clearNumber(x,  y);
	            	}
	            }
	        });
	    }
	    
	    public SudokuBoard() {
	        sudoku = new Sudoku("EasyPuzzle");
	        sudoku.configureBoard();
	        //initializeMouseListener();
	        //initializeKeyListener();
	        
	        setTitle("Sudoku!");

	        JFrame frame = this;
	        
	        canvas = new JPanel() {
	        	 /* (non-Javadoc)
	             * @see javax.swing.JComponent#getMinimumSize()
	             */
	            public Dimension getMinimumSize() {
	                return new Dimension(width, height);
	            }
	            
	            /* (non-Javadoc)
	             * @see javax.swing.JComponent#getMaximumSize()
	             */
	            public Dimension getMaximumSize() {
	                return new Dimension(width, height);
	            }
	            
	            /* (non-Javadoc)
	             * @see javax.swing.JComponent#getPreferredSize()
	             */
	            public Dimension getPreferredSize() {
	                return new Dimension(width, height);
	            }
	            
	            /* (non-Javadoc)
	             * @see java.awt.Component#isFocusable()
	             */
	            public boolean isFocusable() {
	                return true;
	            }

				@Override
	        	public void paint(Graphics graphics) {
	        		Graphics2D g = (Graphics2D)graphics;

	        		drawGrid(g);

	        		//frame.setPreferredSize(new Dimension(numRows*squareSize + MARGIN_SIZE, numCols*squareSize + MARGIN_SIZE));
	        		setPreferredSize(new Dimension((numCols+2)*squareSize + 2*MARGIN_SIZE, (numRows+2)*squareSize + 2*MARGIN_SIZE));
	        		frame.pack();
	        	}
	        };
	        
	        this.getContentPane().add(canvas, BorderLayout.CENTER);
	        this.setResizable(false);
	        this.pack();
	        this.setLocation(100,100);
	        this.setFocusable(true);
	        
	        this.initializeKeyListener();
	        this.initializeMouseListener();
	        
	        createMenuBar();
	        createKeyboardHandlers();
	        createMouseHandlers();
	        
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);
	            }
	        });

	        
	        repaint();
	    }
	    
	    public static void main(String[] args) {
	        SudokuBoard s = new SudokuBoard();
	        s.setVisible(true);
	    }

	}
