/**
 * Name: Shi Yang Zheng
 * Pennkey: syz
 * Execution: java Board
 *
 * Description: Stores characteristics of a board object
 *              and functions that modify them.
**/

public class Board {
    
    private static Square[][] squares = new Square[9][9];
    private int totalMines = 0;
    private boolean win = false;
    private boolean lose = false;
    
    /**
    * Constructor: This creates a new instance of a board object.
    */
    public Board() {
        // initializing board
        PennDraw.clear(PennDraw.DARK_GRAY);
        PennDraw.setPenColor(PennDraw.BLACK);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }
    
    /**
     * Inputs: the x and y coordinate of the mouse's position
     * Outputs: N/A
     * Description: reveals a square and initializes the board
    */
    public void initialize(double mouseX, double mouseY) {
        
        // if the player clicks on the edge of the board, ignore the click
        if (mouseX == 1 || mouseY == 1) {
            return;
        }
        
        //reveal the square that was clicked on
        reveal(mouseX, mouseY);
        
        // setting which squares contain mines
        while (totalMines < 10) {
            double random = Math.random();
            double random1 = Math.random();
            if (!squares[(int) (9 * random)][(int) (9 * random1)].getIsMine() && 
                !squares[(int) (9 * random)][(int) (9 * random1)].getIsRevealed()) {
                squares[(int) (9 * random)][(int) (9 * random1)].setMine();
                totalMines++;
            }
        }
        
        // for each square, check the number of nearby mines
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < squares.length && y >= 0 &&
                            y < squares[i].length && squares[x][y].getIsMine()) {
                            squares[i][j].setNearbyMines(
                                squares[i][j].getNearbyMines() + 1);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Inputs: N/A
     * Outputs: true if a mine is revealed, false otherwise
     * Description: checks if a square is revealed
    */
    public boolean minesRevealed() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (squares[i][j].getIsRevealed() && squares[i][j].getIsMine()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Inputs: the x and y coordinate of the mouse's position
     * Outputs: N/A
     * Description: reveals a square and initializes the board
    */
    public void reveal(double mouseX, double mouseY) {
        
        // if the player clicks on the edge of the board, ignore the click
        if (mouseX == 1 || mouseY == 1) {
            return;
        }
        
        /*
         * getting the coordinates of the mouse position 
         * to match a value from the array
        */  
        int row = (int) (9 * mouseX);
        int col = (int) (9 * mouseY);
        
        // reveal the square if its not already revealed
        if (!squares[row][col].getIsRevealed()) {
            squares[row][col].reveal();
        }
        
        // dont reveal adjacent squares if mines aren't initialized
        if (totalMines == 0) {
            return;
        }
        
        // revealing adjacent squares
        if (squares[row][col].getNearbyMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < squares.length && j >= 0 &&
                        j < squares[i].length && !squares[i][j].getIsRevealed()) {
                        reveal(1.0 * i / 9, 1.0 * j / 9);
                    }
                }
            }
        }
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: redraws the squares that are revealed
    */
    public void update() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].getIsRevealed()) {
                    squares[i][j].update();
                }
            }
        }
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: checks if the player wins or loses
    */
    public void checkOutcome() {
        
        if (minesRevealed()) {
            lose = true;
        } else {
            // check that all non-mines are revealed
            int numberRevealed = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (squares[i][j].getIsRevealed() &&
                        !squares[i][j].getIsMine()) {
                        numberRevealed++;
                    }
                }
            }
            if (numberRevealed == 71) {
                win = true;
            }
        }
    }
    
    /**
     * Inputs: N/A
     * Outputs: true if the player wins, false otherwise
     * Description: gets the board's win field
    */
    public boolean getWin() {
        return this.win;
    }
    
    /**
     * Inputs: N/A
     * Outputs: true if the player loses, false otherwise
     * Description: gets the board's lose field
    */
    public boolean getLose() {
        return this.lose;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: sets the win field to false
    */
    public void setWin() {
        this.win = false;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: sets the lose field to false
    */
    public void setLose() {
        this.lose = false;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: sets the total number of mines to 0
    */
    public void setTotalMines() {
        this.totalMines = 0;
    }
}