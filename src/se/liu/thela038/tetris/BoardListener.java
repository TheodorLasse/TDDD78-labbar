package se.liu.thela038.tetris;

public interface BoardListener
{
    public void boardChanged();
    public void tryHighscore(TetrisComponent.HIGHSCOREMODES mode);
}
