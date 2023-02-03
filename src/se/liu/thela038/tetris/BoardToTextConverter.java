package se.liu.thela038.tetris;

public class BoardToTextConverter
{
    public String convertToText(Board board){
	int width = board.getWidth();
	int height = board.getHeight();
	StringBuilder builder = new StringBuilder();

	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		switch (board.getSquare(j, i)){
		    case EMPTY:
		        builder.append("-");
		        break;
		    case I:
			builder.append("*");
			break;
		    case O:
			builder.append("#");
			break;
		    case T:
			builder.append("¤");
			break;
		    case S:
			builder.append("%");
			break;
		    case Z:
			builder.append("&");
			break;
		    case J:
			builder.append("?");
			break;
		    case L:
			builder.append("+");
			break;
		}
	    }
	    builder.append("\n");
	}
	return builder.toString();
    }
}
