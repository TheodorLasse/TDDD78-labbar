package se.liu.thela038.tetris;

public abstract class AbstractFallHandler implements FallHandler
{
    public boolean hasCollision(Board board, int lastFallingX) { //check if the current board finds a collision between
	//the falling squares and the solid board squares
	Poly falling = board.getFalling();
	for (int i = 0; i < falling.getSize(); i++) {
	    for (int j = 0; j < falling.getSize(); j++) {
		SquareType fallingType = falling.getSquare(i, j);

		int y = board.getFallingY() + j;
		int x = board.getFallingX() + i;
		SquareType boardType = board.getBoardSquare(x, y);

		if (condition(fallingType, boardType)){
		    return true;
		}
	    }
	}
	return false;
    }

    protected abstract boolean condition(SquareType fallingType, SquareType boardType);
}
