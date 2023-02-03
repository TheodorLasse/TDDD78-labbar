package se.liu.thela038.tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisViewerOld
{
    private Board board;

    public TetrisViewerOld(Board board) {
        this.board = board;
    }

    public void show() {
        JFrame frame = new JFrame("Tetris");

        JTextArea textArea = new JTextArea(board.getHeight(), board.getWidth());
	String textBoard = new BoardToTextConverter().convertToText(board);
        textArea.setText(textBoard);

	frame.setLayout(new BorderLayout());
	frame.add(textArea, BorderLayout.CENTER);
	textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	frame.pack();
	frame.setVisible(true);
    }
}
