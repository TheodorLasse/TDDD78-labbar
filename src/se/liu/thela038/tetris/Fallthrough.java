package se.liu.thela038.tetris;

public class Fallthrough extends AbstractFallHandler
{
    @Override protected boolean condition(final SquareType fallingType, final SquareType boardType) {
	return fallingType != SquareType.EMPTY && boardType == SquareType.OUTSIDE;
    }

    @Override public String getDescription() {
	return "Fallthrough tetris block";
    }
}
