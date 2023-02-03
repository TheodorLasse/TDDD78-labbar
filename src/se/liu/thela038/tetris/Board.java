package se.liu.thela038.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board
{
    private final static Random RND = new Random();
    private static final int MARGIN = 4;
    private static final int DOUBLE_MARGIN = MARGIN * 2;
    private int width;
    private int height;
    private SquareType[][] squares;
    private final TetrominoMaker polyFactory;
    private List<BoardListener> boardListenerList;
    private Poly falling = null;
    private int lastFallingX;
    private int fallingX;
    private int fallingY;
    private int score;
    private HighscoreList highscoreList = null;
    private int timerDelay;
    private Timer clockTimer = null;
    private FallHandler fallHandler = null;
    private int polyCount;

    public enum GAMESTATES
    {
        GAME, PAUSE
    }

    private GAMESTATES currentState = GAMESTATES.GAME;

    public Board(int width, int height, HighscoreList highscoreList) {
        this.width = width;
        this.height = height;
        this.squares = new SquareType[height + DOUBLE_MARGIN][width + DOUBLE_MARGIN];
        this.polyFactory = new TetrominoMaker();
        this.boardListenerList = new ArrayList<>();

	newGame();
    }



    private void newGame(){
	highscoreListeners(TetrisComponent.HIGHSCOREMODES.READ);
        setGameState(GAMESTATES.GAME);
        fallHandler = new DefaultFallHandler();
        score = 0;
        timerDelay = 500;
        polyCount = 0;
	falling = null;
	lastFallingX = 0;
	for (int i = 0; i < width + DOUBLE_MARGIN; i++) {
	    for (int j = 0; j < height + DOUBLE_MARGIN; j++) {
		if (i < MARGIN || i >= width + MARGIN || j < MARGIN || j >= height + MARGIN){
		    squares[j][i] = SquareType.OUTSIDE;
		}
		else {
		    squares[j][i] = SquareType.EMPTY;
		}
	    }
	}
	setFalling();
    }

    public void start(){
	TetrisViewer viewer = new TetrisViewer(this);
	viewer.showPicture();
	try {
	    final int sleepTime = 1000;
	    Thread.sleep(sleepTime);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	this.addBoardListener(viewer.mainPanel);
	highscoreListeners(TetrisComponent.HIGHSCOREMODES.READ);

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		tick();
	    }
	};

	clockTimer = new Timer(timerDelay, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
	viewer.show();
    }

    public int getScore() {
	return score;
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() { return width; }

    public int getFallingX() { return fallingX; }

    public int getFallingY() { return fallingY; }

    public Poly getFalling() { return falling; }

    public FallHandler getFallHandler() {
	return fallHandler;
    }

    public HighscoreList getHighscoreList() {
	return highscoreList;
    }

    public void setHighscoreList(final HighscoreList highscoreList) {
	this.highscoreList = highscoreList;
    }

    public boolean isPaused() {
	if (currentState == GAMESTATES.PAUSE) return true;
	else return false;
    }

    public SquareType getBoardSquare(int width, int height){
        return squares[height + MARGIN][width + MARGIN];
    }

    public void setBoardSquare(int width, int height, SquareType squareType){
	squares[height + MARGIN][width + MARGIN] = squareType;
    }

    public SquareType getSquare(int width, int height) {
	/* width and height represent the (x,y) values of the board */
	//Function includes falling square
	if ( ((getFallingX() <= width) && (getFallingX() + falling.getSize() > width)) &&
	     ((getFallingY() <= height) && (getFallingY() + falling.getSize() > height)) ) {
	    SquareType polySquare = falling.getSquare(width - getFallingX(), height - getFallingY());
	    if (polySquare != SquareType.EMPTY) {
		return polySquare;
	    }
	}
	return squares[height + MARGIN][width + MARGIN];
    }




    public void addBoardListener(BoardListener bl){
        boardListenerList.add(bl);
    }

    private void notifyListeners() {
	for (BoardListener listener : boardListenerList) {
	    listener.boardChanged();
	}
    }

    private void highscoreListeners(TetrisComponent.HIGHSCOREMODES mode){
	for (BoardListener listener : boardListenerList) {
	    listener.tryHighscore(mode);
	}
    }

    public void tick() { //The game loop
        if (currentState == GAMESTATES.PAUSE) {
	    return;
	}

	fallingBlock();

	if (fallHandler.hasCollision(this, lastFallingX)) {
	    fallingY -= 1;
	    addFallingToBoard();
	}

	lastFallingX = fallingX;

	removeSquares();
	notifyListeners();
    }

    private void fallingBlock() {
        setFalling();
        moveFalling();
    }

    private void setFalling() {
	if (falling == null) {
	    polyCount += 1;
	    falling = polyFactory.getPoly(RND.nextInt(polyFactory.getNumberOfTypes()));
	    fallingX = RND.nextInt(width - falling.getSize());
	    fallingY = 0;
	    if (fallHandler.hasCollision(this, lastFallingX)) {
		onGameOver();
	    }
	}
    }

    public void setGameState(GAMESTATES state) {
	switch (state){
	    case PAUSE -> currentState = GAMESTATES.PAUSE;
	    case GAME -> currentState = GAMESTATES.GAME;
	}
    }

    public void setClockTimer(int timerDelay){
        clockTimer.setDelay(timerDelay);
        clockTimer.restart();
    }

    private void onGameOver(){
	setGameState(GAMESTATES.PAUSE);

	highscoreListeners(TetrisComponent.HIGHSCOREMODES.WRITE);
	notifyListeners();

	int dialogButton = JOptionPane.YES_NO_OPTION;
	int dialogResult = JOptionPane.showConfirmDialog (null, "Play again?","Tetris",dialogButton);
	if(dialogResult == JOptionPane.YES_OPTION){
	    newGame();
	}
	else System.exit(0);
    }

    private void moveFalling(){
	fallingY += 1;
    }

    private void addFallingToBoard() { //adds the 'falling' squares to the solid board squares
	for (int i = 0; i < falling.getSize(); i++) {
	    for (int j = 0; j < falling.getSize(); j++) {
		SquareType fallingType = falling.getSquare(i, j);
		if (fallingType != SquareType.EMPTY) {
		    int y = getFallingY() + j + MARGIN;
		    int x = getFallingX() + i + MARGIN;
		    squares[y][x] = fallingType;
		}
	    }
	}
	falling = null;
	final int fallthrough = 10;
	if (polyCount % fallthrough == 0) fallHandler = new Fallthrough();
	//if (polyCount % 20 == 0) fallHandler = new Heavy();
	else fallHandler = new Heavy();
	setFalling();
    }

    private void removeSquares(){ //finds any row of squares that have to be removed
        int rowsRemoved = 0;
	for (int i = MARGIN; i < height + MARGIN; i++) {
	    int j = MARGIN;
	    boolean rowFull = true;
	    while(j < width + MARGIN) {
	        if (squares[i][j] == SquareType.EMPTY) {
		    rowFull = false;
		    break;
		}
	        j++;
	    }
	    if (rowFull) {
	        shiftSquares(i);
	        rowsRemoved++;
	    }
	}
	addScore(rowsRemoved);
    }

    private static final Map<Integer, Integer> SCORE_MAP = Map.of(0, 0, 1, 100, 2, 300, 3, 500, 4, 800);
    private void addScore(final int rowsRemoved) {
        final int minimumTimerDelay = 250;
	score += SCORE_MAP.get(rowsRemoved);
        if (timerDelay > minimumTimerDelay && rowsRemoved >= 1) {
	    final int timerDelayReduction = 50;
	    timerDelay -= timerDelayReduction;
	    setClockTimer(timerDelay);
	}
    }

    private void shiftSquares(int row) { //removes the row of squares and shifts from given row
	int k = row;
	while (k >= MARGIN - 1) { //adjusted by 1 to match index values instead of human values
	    if (k == MARGIN - 1) {
		//Turns the top row that is not a margin row into an empty row
		for (int i = 0; i < width + DOUBLE_MARGIN; i++) {
		    if (i < MARGIN || i >= width + MARGIN){
			squares[k][i] = SquareType.OUTSIDE;
		    }
		    else {
			squares[k][i] = SquareType.EMPTY;
		    }
		}
	    }
	    else {
		//shifts everything down a row
		squares[k] = squares[k - 1];
	    }
	    k--;
	}
    }

    public void moveDirection(Direction direction) {
        //moves the falling squares in a given direction
        int dir;
        switch (direction) {
	    case LEFT -> {
	        dir = -1;
	    }
	    case RIGHT -> {
	        dir = 1;
	    }
	    default -> {dir = 0;}
	}

	fallingX += dir;
	if (fallHandler.hasCollision(this, lastFallingX)) {
	    fallingX -= dir;
	}

        notifyListeners();
    }

    public void rotate(Direction direction) {
        //rotates the falling squares in a given direction
        if (falling != null){
	    falling = falling.rotate(direction);
	    if (fallHandler.hasCollision(this, lastFallingX)){
		switch (direction) {
		    case LEFT -> {
			falling = falling.rotate(Direction.RIGHT);
		    }
		    case RIGHT -> {
			falling = falling.rotate(Direction.LEFT);
		    }
		}
	    }
	}
    }
}

