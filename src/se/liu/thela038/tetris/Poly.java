package se.liu.thela038.tetris;


public class Poly
{
    private SquareType[][] array;
    private int size;
    private static final int FULL_TURN = 3;
    public Poly(SquareType[][] array){
        this.array = array;
        this.size = array.length;
    }

    public int getSize() {
        return size;
    }

    public SquareType getSquare(int width, int height) {
        return array[height][width];
    }

    private Poly rotateRight(Poly poly) {

        Poly newPoly = new Poly(new SquareType[size][size]);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++){
                newPoly.array[c][size-1-r] = poly.array[r][c];
            }
        }

        return newPoly;
    }

    private Poly rotateLeft(Poly poly) {
        for (int i = 0; i < FULL_TURN; i++) {
            poly = rotateRight(poly);
        }
        return poly;
    }

    public Poly rotate(Direction direction){
        switch (direction){
            case LEFT -> {
                return rotateLeft(this);
            }
            case RIGHT -> {
                return rotateRight(this);
            }
            default -> {
                return this;
            }
        }
    }
}
