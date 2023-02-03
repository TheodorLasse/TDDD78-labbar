package se.liu.thela038.tetris;

public class BoardTester
{
    public static void main(String[] args) {
        HighscoreList highscoreList = new HighscoreList();

	Board board = new Board(10, 15, highscoreList);
	board.start();
    }
}
