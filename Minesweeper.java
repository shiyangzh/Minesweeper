/**
 * Name: Shi Yang Zheng
 * Pennkey: syz
 * Execution: java Minesweeper
 *
 * Description: Simulates the game Minesweeper.
**/

public class Minesweeper {
    
    public static void main(String[] args) {
        
        // initializing the game
        Board board = new Board();
        PennDraw.enableAnimation(60);
        boolean firstTime = true;
        
        while (true) {
            if (PennDraw.mousePressed()) {
                
                if (firstTime) {
                    // initialize the board if tits the first time clicking
                    firstTime = false;
                    board.initialize(PennDraw.mouseX(), PennDraw.mouseY());
                    board.update();
                } else {
                    // otherwise update the board with each click
                    board.reveal(PennDraw.mouseX(), PennDraw.mouseY());
                    board.update();
                }
            }
            
            // check if the game is over
            board.checkOutcome();
                
            while (board.getWin()) {
                
                /* if the player wins, 
                 * display the right message and stop animation
                */ 
                PennDraw.setPenColor(PennDraw.GREEN);
                PennDraw.text(0.5, 0.5, 
                              "You win! Press any key to replay the game!");
                PennDraw.disableAnimation();
                    
                if (PennDraw.hasNextKeyTyped()) {
                    // restart the game by pressing any key
                    PennDraw.nextKeyTyped();
                    board = new Board();
                    board.setTotalMines();
                    board.setWin();
                    firstTime = true;
                    PennDraw.enableAnimation(60);
                }
            } 
            
            while (board.getLose()) {
                
                /* if the player loses, 
                 * display the right message and stop animation
                */ 
                PennDraw.setPenColor(PennDraw.GREEN);
                PennDraw.text(0.5, 0.5, 
                              "You lose! Press any key to replay the game!");
                PennDraw.disableAnimation();
                    
                if (PennDraw.hasNextKeyTyped()) {
                    // restart the game by pressing any key
                    PennDraw.nextKeyTyped();
                    board = new Board();
                    board.setTotalMines();
                    board.setLose();
                    firstTime = true;
                    PennDraw.enableAnimation(60);
                }
            } 
            PennDraw.advance();
        }
    }
}