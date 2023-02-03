package se.liu.thela038.tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Heavy extends AbstractFallHandler
{
    @Override public boolean hasCollision(final Board board, final int lastFallingX) {
        if (board.getFallingX() != lastFallingX) return super.hasCollision(board, lastFallingX);
	else{
	    Poly falling = board.getFalling();
	    int fallingSize = falling.getSize();
	    boolean collided = false;
	    List<Point> pushDown = new ArrayList<>();

	    for (int i = 0; i < fallingSize; i++) {
		for (int j = 0; j < fallingSize; j++) {
		    SquareType fallingType = falling.getSquare(i, j);

		    int y = board.getFallingY() + j;
		    int x = board.getFallingX() + i;
		    SquareType boardType = board.getBoardSquare(x, y);

		    if (condition(fallingType, boardType)){
		        boolean supported = isSupported(x, y + 1, board);
		        if (supported){
		            pushDown.add(new Point(x, y + 1));
			}
		        else collided = true;
		    }
		}
	    }
	    if(collided){
		return true;
	    }else{
		for (Point point : pushDown) {
		    pushColumn(point, board);
		}
		return false;
	    }
	}
    }

    @Override protected boolean condition(SquareType fallingType, SquareType boardType){
	return fallingType != SquareType.EMPTY && boardType != SquareType.EMPTY;
    }

    @Override public String getDescription() {
	return "Heavy tetris block";
    }


    private void pushColumn(Point point, Board board){
        int heighestEmpty = 0;
        for(int y = point.y; y < board.getHeight(); y++){
            if(board.getBoardSquare(point.x, y) == SquareType.EMPTY) {
		heighestEmpty = y;
		break;
	    }
        }

        for(int y = heighestEmpty; y > point.y - 1; y--) {
            board.setBoardSquare(point.x, y, board.getBoardSquare(point.x, y - 1));
        }
        board.setBoardSquare(point.x, point.y - 1, SquareType.EMPTY);

    }

    public boolean isSupported(int x, int startHeight, Board board){
	for(int y = startHeight; y < board.getHeight(); y++){
	    if(board.getBoardSquare(x, y) == SquareType.EMPTY){
		return true;
	    }
	}
	return false;
    }
}
