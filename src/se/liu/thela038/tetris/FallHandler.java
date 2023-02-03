package se.liu.thela038.tetris;

public interface FallHandler
{
    boolean hasCollision(Board board, int lastFallingX);
    String getDescription();
}
