package se.liu.thela038.tetris;


public class TetrominoMaker
{
    private static final int NUMBER_OF_TYPES = SquareType.values().length - 2;

    public int getNumberOfTypes() {
        return NUMBER_OF_TYPES;
    }
    public Poly getPoly(int n) {
	SquareType[][] polyArray = {};
	SquareType e = SquareType.EMPTY;
	SquareType b;
	/* the variables 'e' and 'b' represent an empty or occupied position, the SquareType of the block
	* is determined within the individual cases */
        switch (n){
	    case 0:
	        /* The 'I' block */
		b = SquareType.I;
		polyArray = new SquareType[][] { { e, e, e, e },
					         { b, b, b, b },
						 { e, e, e, e },
						 { e, e, e, e } };
		break;
	    case 1:
		/* The 'O' block */
		b = SquareType.O;
		polyArray = new SquareType[][] { { b, b },
						 { b, b } };
		break;
	    case 2:
		/* The 'T' block */
		b = SquareType.T;
		polyArray = new SquareType[][] { { e, b, e },
						 { b, b, b },
						 { e, e, e } };
		break;
	    case 3:
		/* The 'S' block */
		b = SquareType.S;
		polyArray = new SquareType[][] { { e, b, b },
						 { b, b, e },
						 { e, e, e } };
		break;
	    case 4:
		/* The 'Z' block */
		b = SquareType.Z;
		polyArray = new SquareType[][] { { b, b, e },
						 { e, b, b },
			 			 { e, e, e } };
		break;
	    case 5:
		/* The 'J' block */
		b = SquareType.J;
		polyArray = new SquareType[][] { { b, e, e },
						 { b, b, b },
						 { e, e, e } };
		break;
	    case 6:
		/* The 'L' block */
		b = SquareType.L;
		polyArray = new SquareType[][] { { e, e, b },
						 { b, b, b },
						 { e, e, e } };
		break;
	    default:
		throw new IllegalArgumentException("Invalid index: " + n);

	}
	return new Poly(polyArray);
    }
}
