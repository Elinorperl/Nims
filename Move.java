/**
 * The Move class
 *
 * @author Elinor Perl
 */
public class Move {
    private int inRow;
    /**
     * The row on which the move is performed
     */
    private int inLeft;
    /**
     * The left bound of the sequence to mark.
     */
    private int inRight;
    /** The right bound of the sequence to mark. */

    /**
     * Constructs a Move object with the given parameters
     */
    Move(int inRow, int inLeft, int inRight) {
        this.inRow = inRow;
        this.inLeft = inLeft;
        this.inRight = inRight;
    }

    /**
     * The left bound of the stick sequence to mark.
     */
    public int getLeftBound() {
        return inLeft;
    }

    /**
     * The right bound of the stick sequence to mark.
     */
    public int getRightBound() {
        return inRight;
    }

    /**
     * The row on which the move is performed.
     */
    public int getRow() {
        return inRow;
    }

    /**
     * a string representation of the move.
     */
    public String toString() {
        return getRow() + ":" + getLeftBound() + "-" + getRightBound();
    }
}

