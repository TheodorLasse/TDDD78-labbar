package se.liu.thela038.tetris;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Highscore>
{

    public int compare(Highscore o1, Highscore o2) {
	return Integer.compare(o2.getScore(), o1.getScore());
    }
}
