/**
 * Name: Shi Yang Zheng
 * Pennkey: syz
 * Execution: java Square
 *
 * Description: Stores characteristics of a square object and
 *              functions that modify them.
**/

public class Square {
    
    private boolean isRevealed = false;
    private boolean isMine = false;
    private int nearbyMines = 0;
    private double xPos;
    private double yPos;
    
    /**
    * Constructor: This creates a new instance of a Square object.
    */
    public Square(double xPos, double yPos) {
        this.xPos = (1.0 + 2 * xPos) / 18;
        this.yPos = (1.0 + 2 * yPos) / 18;
        PennDraw.square(this.xPos, this.yPos, 1.0 / 18);
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: sets a square to become a mine
    */
    public void setMine() {
        this.isMine = true;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: reveals a square
    */
    public void reveal() {
        this.isRevealed = true;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: redraws the square in a different color
     *              after it is revealed, draws a red circle
     *              if the square is a mine, or draws the number 
     *              of nearby mines if it is greater than 0.
    */
    public void update() {
        
        // redrawing the square in a different color
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledSquare(this.xPos, this.yPos, 1.0 / 18);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(this.xPos, this.yPos, 1.0 / 18);
        
        if (this.isMine) {
            // draws a red circle if the square is a mine
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledCircle(this.xPos, this.yPos, 1.0 / 20);
        } else if (this.nearbyMines > 0) {
            // draws the number of nearby mines if greater than 0
            PennDraw.setPenColor(255 / (this.nearbyMines + 2), 
                                 (this.nearbyMines) * 25, 
                                 255 - (this.nearbyMines) * 25);
            PennDraw.text(this.xPos, this.yPos, toString());
        } 
    }
    
    /**
     * Inputs: N/A
     * Outputs: the string representation of the number of nearby mines
     * Description: returns the number of nearby mines as a string
    */
    public String toString() {
        return "" + this.nearbyMines;
    }
    
    /**
     * Inputs: the number of nearby mines
     * Outputs: N/A
     * Description: sets the number of nearby mines around a square
    */
    public void setNearbyMines(int numberOfMines) {
        this.nearbyMines = numberOfMines;
    }
    
    /**
     * Inputs: N/A
     * Outputs: true if a square is revealed, false otherwise
     * Description: gets the square's isReavealed field
    */
    public boolean getIsRevealed() {
        return this.isRevealed;
    }
    
    /**
     * Inputs: N/A
     * Outputs: true if a square is a mine, false otherwise
     * Description: gets the square's isMine field
    */
    public boolean getIsMine() {
        return this.isMine;
    }
    
    /**
     * Inputs: N/A
     * Outputs: the number of mines around a square
     * Description: gets the number of nearby mines around a square
    */
    public int getNearbyMines() {
        return this.nearbyMines;
    }
    
    /**
     * Inputs: N/A
     * Outputs: the x position of a square
     * Description: gets the x position of a square
    */
    public double getXPos() {
        return this.xPos;
    }
    
    /**
     * Inputs: N/A
     * Outputs: the y position of a square
     * Description: gets the y position of a square
    */
    public double getYPos() {
        return this.yPos;
    }
}