import java.util.*;
import java.io.*;

public class Battleship {
	public static void main(String[] args) {
    	System.out.println("Welcome to Battleship!");

    	// Declaration and initialization of variables
    	int boardLength = 5;
    	char water = '-';
    	char ship = '@';
    	char hit = 'X';
    	char miss = 'O';
    	int shipNum = 5;

    	//Creation of boards for each player
    	char[][] p1_board = generateBoard(boardLength, water); //two dimensional array representing the gameboard
    	char[][] p2_board = generateBoard(boardLength, water);

    	//Placing battleships for player 1
    	Scanner sc = new Scanner(System.in); //Initialize the scanner object - only required once
    	System.out.println("PLAYER 1, ENTER YOUR SHIPS’ COORDINATES.");
    	placeShips(p1_board, boardLength, ship, shipNum, sc); //Provide variables to method due to limited scope 

    	printBattleShip(p1_board);

    	//Adding 100 lines between each board via for-loop
    	for (int x = 0; x < 100; x++) {
    		System.out.println("");
    	}

    	//Placing battleships for player 2
    	System.out.println("PLAYER 2, ENTER YOUR SHIPS’ COORDINATES.");
    	placeShips(p2_board, boardLength, ship, shipNum, sc);

    	printBattleShip(p2_board);

    	/* Generate empty targe boards for each player 
    	This will keep track of each hit and miss. */
    	char[][] t1_board = generateBoard(boardLength, water);
    	char[][] t2_board = generateBoard(boardLength, water); 

    	//Playing the game via code below
    	int i = 1; //This value will transition back and forth from 1 to 2, signaling player i's turn
    	int p1_score = 0; //With each hit, this is incremented unitl either player 1 or player 2 reaches score of 5, signaling the end of the game
    	int p2_score = 0;
    	//do-while: do "play the game" while "there isn't a winners"
    	do {
    		if (i == 1) {
    			System.out.println("Player " + i + ", enter hit row/column:");
    			int x = sc.nextInt(); //accepting coordinates 
    			int y = sc.nextInt();
    			if (x >= boardLength || y >= boardLength) {
    				System.out.println("Invalid coordinates. Choose different coordinates.");
    			} else if (t1_board[x][y] == hit || t1_board[x][y] == miss) {
    				System.out.println("You already fired on this spot. Choose different coordinates.");
    			} else if (p2_board[x][y] == ship) {
    				t1_board[x][y] = hit; //Struck a ship
    				p1_score+=1;
    				System.out.println("PLAYER 1 HIT PLAYER 2’s SHIP!");
    				printBattleShip(t1_board);
    				i=2; //Switch to player 2
    			} else {
    				t1_board[x][y] = miss; //Miss a ship
    				System.out.println("PLAYER 1 MISSED!");
    				printBattleShip(t1_board);
    				i=2;
    			}
    		} else {
    			System.out.println("Player " + i + ", enter hit row/column:");
    			int x = sc.nextInt();
    			int y = sc.nextInt();
    			if (x >= boardLength || y >= boardLength) {
    				System.out.println("Invalid coordinates. Choose different coordinates.");
    			} else if (t2_board[x][y] == hit || t2_board[x][y] == miss) {
    				System.out.println("You already fired on this spot. Choose different coordinates.");
    			} else if (p1_board[x][y] == ship) {
    				t2_board[x][y] = hit; //Struck a ship
    				p2_score+=1;
    				System.out.println("PLAYER 2 HIT PLAYER 1’s SHIP!");
    				printBattleShip(t2_board);
    				i=1; //Switch to player 2
    			} else {
    				t2_board[x][y] = miss; //Miss a ship
    				System.out.println("PLAYER 2 MISSED!");
    				printBattleShip(t2_board);
    				i=1; //Switch back to player 1
    			}
    		}	
		}	
    	while (p1_score != 5 && p2_score != 5);
    		if (p1_score == 5) {
    			System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
    			System.out.println("Final boards:");
    			printBattleShip(interfaceBoards(p1_board, t2_board, water, boardLength)); //Interface boards
    			printBattleShip(t1_board);
    		} else {
    			System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
    			printBattleShip(interfaceBoards(p2_board, t1_board, water, boardLength)); //Interface boards
    			printBattleShip(t2_board);
    		}
    }

    //Combine target and player board for final boards
    public static char[][] interfaceBoards(char[][] playerBoard, char[][] targetBoard, char w, int l) {
    	//Looping through board
    	char[][] board = new char[l][l];
    	for (int row = 0; row < board.length; row++) {
    		for (int col = 0; col < board[row].length; col++) {
    			//the chars of target board should take precedence over chars of the game board when interfacing, hence order in if-conditional
    			if (targetBoard[row][col] != w) {
    				board[row][col] = targetBoard[row][col];
    			} else if (playerBoard[row][col] != w) {
    				board[row][col] = playerBoard[row][col];
    			} else {
    				board[row][col] = w;
    			}
    		}
    	}
    	return board;
    }

    // Method for generating blank boards 
    public static char[][] generateBoard(int l, char w) {
    	//Using the length to construct a board and fill boards with water
    	char[][] board = new char[l][l];
    	for (int row = 0; row < board.length; row++) {
    		for (int col = 0; col < board[row].length; col++) {
    			board[row][col] = w;
    		}
    	}
    	return board;
    }

    // Method for placing battleships
    public static char[][] placeShips(char[][] board, int l, char s, int n, Scanner input) {
    	int i = 1;
    	//do-while: do "plac ships" while "ships still need placing (tracked through the iteration of i)"
    	do {
  			System.out.print("Enter ship " + i + " location: ");
  			int x = input.nextInt();
    		int y = input.nextInt();
  			if (x >= l || y >= l) {
  				System.out.println("Invalid coordinates. Choose different coordinates."); //Prevent user from enter invalid coordinates (outside the bounds of the board's length)
  			} else if (board[x][y] == s) {
  				System.out.println("You already have a ship there. Choose different coordinates."); //Check if a ship is already at the input coordinates
  			} else {
  				board[x][y] = s;
  				i+=1;
  			}
		}
		while (i <= n);
  			return board;
    }

    // Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print(" ");
		//Re-generate board via for-loops (iterate through rows, then columnsss)
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}

}