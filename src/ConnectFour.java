
public class ConnectFour
{

    private int[][]   connectFour;

    private final int ZERO          = 0;
    private final int ONE           = 1;
    private final int TWO           = 2;

    private final int NEGATIVE      = 20;
    private final int POSITIVE      = 30;
    private final int PERPENDICULAR = 50;
    private final int LATERAL       = 70;

    private int       horizontal;
    private int       vertical;

    private int       aiMove;

    private int       playerNum;

    public void createNewGame ()
    {
        connectFour = new int[7][6];

        // initialized the game board
        for (vertical = 0; vertical < 7; vertical++)
        {
            for (horizontal = 0; horizontal < 6; horizontal++)
            {
                connectFour[vertical][horizontal] = ZERO;
            }
        }

    }

    /*
     * Column is the user input – 1 to 7
     * Value is either 1 or 2 depending on the player #
     * RETURN – 1 if it was the winning move and 0 if it was not.
     */
    public int insertBoard (int column, int value)
    {

        int currentPositionV = 6;
        int currentPositionVCopy = 6;

        int currentPositionH = 0;

        int matchFlag = 0;
        column--;          // User will enter the column# based on 1-6. For the
                           // index, it needs to be decremented by 1.

        for (vertical = 6; vertical >= 0; vertical--)
        {
            if (connectFour[vertical][column] == ZERO)
            {
                connectFour[vertical][column] = value;
                // System.out.println ("Inside of for loop: currentPositionV " +
                // currentPositionV);

                break;
            }
            if (vertical == 0)
            {
                return 1;
            }

            currentPositionV--;

        }

        // System.out.println ("currentPosition" + currentPositionV);
        // System.out.println ("Inserted at [" + vertical + 1 + "][" + column +
        // 1 + "] " + connectFour[vertical][column]);

        currentPositionVCopy = currentPositionV;
        currentPositionH = column;

        boolean found = false;
        while (!found)
        {
            // Check the match horizontally --- check the left of
            // current column
            while (currentPositionH - 1 >= 0)
            {
                if (connectFour[currentPositionV][currentPositionH] == connectFour[currentPositionV][--currentPositionH])
                    matchFlag += LATERAL;
                else
                    break;
            }

            currentPositionH = column;

            // Check the match horizontally --- check the right of
            // current column
            while (currentPositionH + 1 <= 5)
            {
                if (connectFour[currentPositionV][column] == connectFour[currentPositionV][++currentPositionH])
                    matchFlag += LATERAL;
                else
                    break;
            }

            // if the match flag shows LATERAL(70) * 4 then we have a
            // winner.
            if (matchFlag >= LATERAL * 3)
            {
                found = true;
                return -1;

            }

            // Check the current column vertically --- check bottom rows
            // of current row
            matchFlag = 0;
            currentPositionH = column;

            while (currentPositionVCopy + 1 <= 6)
            {
                if (connectFour[currentPositionV][column] == connectFour[++currentPositionVCopy][currentPositionH])
                    matchFlag += PERPENDICULAR;
                else
                    break;
            }

            currentPositionVCopy = currentPositionV;

            // Check the current column vertically --- check upper rows
            // of current row
            while (currentPositionVCopy - 1 >= 0)
            {
                if (connectFour[currentPositionV][column] == connectFour[--currentPositionVCopy][currentPositionH])
                    matchFlag += PERPENDICULAR;
                else
                    break;
            }

            if (matchFlag >= PERPENDICULAR * 3)
            {
                found = true;
                return -1;
            }

            // Check the negative slop match --- go to north/west
            matchFlag = 0;
            currentPositionVCopy = currentPositionV;
            currentPositionH = column;

            while (currentPositionVCopy - 1 >= 0 && currentPositionH - 1 >= 0)
            {
                if (connectFour[currentPositionV][column] == connectFour[--currentPositionVCopy][--currentPositionH])
                    matchFlag += NEGATIVE;
                else
                    break;
            }

            // Check the negative slope match -- go to south/east
            currentPositionVCopy = currentPositionV;
            currentPositionH = column;

            while (currentPositionVCopy + 1 <= 6 && currentPositionH + 1 <= 5)
            {
                if (connectFour[currentPositionV][column] == connectFour[++currentPositionVCopy][++currentPositionH])
                    matchFlag += NEGATIVE;
                else
                    break;
            }

            // Check if negative slope matched all 5.
            if (matchFlag >= NEGATIVE * 3)
            {
                found = true;
                return -1;
            }

            // Check the positive slope match -- go to south/east
            matchFlag = 0;
            currentPositionVCopy = currentPositionV;
            currentPositionH = column;

            while (currentPositionVCopy + 1 <= 6 && currentPositionH - 1 >= 0)
            {
                if (connectFour[currentPositionV][column] == connectFour[++currentPositionVCopy][--currentPositionH])
                    matchFlag += POSITIVE;
                else
                    break;
            }

            // Check the positive slope match -- go to north/west
            currentPositionVCopy = currentPositionV;
            currentPositionH = column;

            while (currentPositionVCopy - 1 >= 0 && currentPositionH + 1 <= 5)
            {
                if (connectFour[currentPositionV][column] == connectFour[--currentPositionVCopy][++currentPositionH])
                    matchFlag += POSITIVE;
                else
                    break;
            }

            // Check if positive slope matched all 5.
            if (matchFlag >= POSITIVE * 3)
            {
                found = true;
                return -1;
            }

            // if it reached to the end of loop, we have checked all matching
            // possibilities, so we must exit the loop.
            found = true;
        }

        return 0;
    }

    public int nextMove () /*
                            * You will have your game strategy logic within this
                            * method
                            */
    {

        for (vertical = 6; vertical >= 0; vertical--)
        {
            for (horizontal = 0; horizontal <= 5; horizontal++)
            {
                if (connectFour[vertical][horizontal] == 0)
                    return ++horizontal;
            }
        }
        return -1;
    }

    public int[][] getBoard ()
    {
        return connectFour;
    }

}

/*
 * 
 * You are responsible to implement the Connect Four game
 * You will prompt for the user for the number of players, either 1 or 2.
 * For the 2 players case, you will prompt each user to make a move alternately
 * on
 * the column # (1-7). You will display the board after each move. The board
 * will
 * be drawn on the command line as a 6 x 7 matrix of 1’s and 2’s. Put a 0 on an
 * empty spot. Player 1 will always have 1’s and player 2 will always have 2’s.
 * 
 * For example:
 * After 3 moves - player 1 inputted 4; player 2 inputted 5; player1 inputted 5
 * 
 * After 3 moves:
 * 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0
 * 0 0 0 0 1 0 0
 * 0 0 0 1 2 0 0
 * 
 * Once a player has 4 1’s or 2’s in a row (horizontally, vertically, or
 * diagonally).
 * Then you declare the winner.
 * 
 * For the 1 player versus the computer scenario, computer will always make the
 * first move.
 * Thus computer has 1’s and player has 2’s. Since the computer moves first,
 * your program should not lose to the human player in the first 20 moves.
 * Otherwise 10 points will be taken off.
 * 
 * Program Specification:
 * 
 * Your Connect Four board is a 7 x 6 matrix of integers that you initialized to
 * 0s.
 * Int connect_4 [7][6];
 * Int insert_board (int column, int value)
 * Column is the user input – 1 to 7
 * Value is either 1 or 2 depending on the player #
 * RETURN – 1 if it was the winning move and 0 if it was not.
 * Int next_move () /* You will have your game strategy logic within this method
 */
/*
 * RETURN – the next column # when the computer is ready for the next move
 * 
 * /*
 * Program Checklist:
 * You will submit connect4.java, p4.java and readme.p4.
 * Use the ConsoleReader for input so you can pipe an input file of alternating
 * moves to the board.
 */
