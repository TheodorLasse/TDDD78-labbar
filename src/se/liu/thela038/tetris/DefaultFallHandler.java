package se.liu.thela038.tetris;

public class DefaultFallHandler extends AbstractFallHandler
{
    @Override protected boolean condition(SquareType fallingType, SquareType boardType){
	return fallingType != SquareType.EMPTY && boardType != SquareType.EMPTY;
    }

    @Override public String getDescription() {
	return "Regular tetris block";
    }
}
