package se.liu.thela038.tetris;

import com.google.gson.JsonIOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private final static Map<SquareType, Color> COLOR_MAP = Map.of(SquareType.EMPTY, Color.BLACK,
								   SquareType.I, Color.LIGHT_GRAY,
								   SquareType.O, Color.YELLOW,
								   SquareType.T, Color.PINK,
								   SquareType.S, Color.GREEN,
								   SquareType.Z, Color.RED,
								   SquareType.J, Color.BLUE,
								   SquareType.L, Color.ORANGE,
								   SquareType.OUTSIDE, Color.ORANGE);
    private final static int TILE_SIZE = 40;

    public TetrisComponent(Board board) {
        this.board = board;
	final InputMap in = this.getInputMap();
	in.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
	in.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
	in.put(KeyStroke.getKeyStroke("UP"), "UP");
	in.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");

	final ActionMap act = this.getActionMap();
	act.put("LEFT", new MoveAction(Direction.LEFT));
	act.put("RIGHT", new MoveAction(Direction.RIGHT));
	act.put("UP", new RotateAction(Direction.LEFT));
	act.put("DOWN", new RotateAction(Direction.RIGHT));

    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(TILE_SIZE * board.getWidth(), TILE_SIZE * board.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int i = 0; i < board.getWidth(); i++) {
	    for (int j = 0; j < board.getHeight(); j++) {
		g2d.setColor(COLOR_MAP.get(board.getSquare(i, j)));
		g2d.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	    }
	}
	if (board.isPaused()){
	    showHighscores(g);
	}
	else showScore(g);
	showFallHandler(g);
    }

    private static EnumMap<SquareType, Color> createColorMap() {
        EnumMap<SquareType, Color> squareColorMap = new EnumMap<>(SquareType.class);
        squareColorMap.put(SquareType.EMPTY, Color.BLACK);
	squareColorMap.put(SquareType.I, Color.LIGHT_GRAY);
	squareColorMap.put(SquareType.O, Color.YELLOW);
	squareColorMap.put(SquareType.T, Color.PINK);
	squareColorMap.put(SquareType.S, Color.GREEN);
	squareColorMap.put(SquareType.Z, Color.RED);
	squareColorMap.put(SquareType.J, Color.BLUE);
	squareColorMap.put(SquareType.L, Color.ORANGE);
	squareColorMap.put(SquareType.OUTSIDE, Color.ORANGE);
        return squareColorMap;
    }

    public void showHighscores(Graphics g){
	List<Highscore> highscores = board.getHighscoreList().getHighscoreList();
	final int maximumScoreShown = 10;
	int scoreLoops = Math.min(highscores.size(), maximumScoreShown);

	final int stepBetweenScores = 20;
	for (int i = 0; i < scoreLoops; i++) {
	    g.setColor(Color.RED);
	    g.drawString(highscores.get(i).getName() + " : " + highscores.get(i).getScore(), 0, stepBetweenScores * (i + 1));
	}
    }

    public void showScore(Graphics g){
	final int posY = 10;
	g.setColor(Color.CYAN);
	g.drawString("Score: " + board.getScore(), 0, posY);
    }

    public void showFallHandler(Graphics g){
	final int posY = 30;
	g.setColor(Color.orange);
	g.drawString(board.getFallHandler().getDescription(), 0, posY);
    }

    @Override
    public void boardChanged() {
	this.repaint();
    }

    public enum HIGHSCOREMODES
    {
	READ, WRITE
    }
    public void tryHighscore(HIGHSCOREMODES mode) {
	try {
	    switch (mode){
		case READ -> board.setHighscoreList(HighscoreList.readHighscores());
		case WRITE -> {
		    String name = JOptionPane.showInputDialog("Name:");
		    Highscore highscore = new Highscore(name, board.getScore());
		    HighscoreList boardList = board.getHighscoreList();
		    boardList.addHighscore(highscore);
		    boardList.saveHighscores();
		}
	    }

	} catch (JsonIOException | IOException | HeadlessException e){
	    int dialogButton = JOptionPane.YES_NO_OPTION;
	    int dialogResult = JOptionPane.showConfirmDialog (null, e.getMessage(),"Error has occured, try again?", dialogButton);
	    if(dialogResult == JOptionPane.YES_OPTION){
		e.printStackTrace();
		tryHighscore(mode);
	    }
	    else System.exit(0);
	}
    }

    private class MoveAction extends AbstractAction{
	private final Direction direction;
	private MoveAction(Direction direction) {
	    this.direction = direction;
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
	    board.moveDirection(direction);
	}
    }

    private class RotateAction extends AbstractAction{
	private final Direction direction;
	private RotateAction(Direction direction) {
	    this.direction = direction;
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
	    board.rotate(direction);
	}
    }
}