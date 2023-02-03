package se.liu.thela038.tetris;

import java.util.Random;

public enum SquareType
{
    EMPTY, I, O, T, S, Z, J, L, OUTSIDE;

    public static void main(String[] args)
    {
	Random rnd = new Random();
	for (int i = 0; i < 26; i++) {
	    System.out.println(SquareType.values()[rnd.nextInt(SquareType.values().length)]);
	}
    }
}


