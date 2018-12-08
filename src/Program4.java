
public class Program4
{
    private int        gameBoard[][] = new int[7][6];
    private String     playerReadIn;
    private static int userNumber;
    private String     choiceReadIn;
    private int        userChoice;

    private static int result;

    static ConnectFour connectFour   = new ConnectFour ();
    static Program4    program4      = new Program4 ();
    ConsoleReader      console       = new ConsoleReader (System.in);

    Program4 ()
    {

    }

    public static void main (String[] args)
    {
        program4.startNewGame ();
        connectFour.createNewGame ();
        // program4.showBoard ();

        while (result != -1)
        {
            if (userNumber == 2)
            {
                program4.receiveEntry (1);
                program4.showBoard ();
                if (result == 1)// receive 1 if the vertical line is full
                {
                    program4.receiveEntry (1);
                    program4.showBoard ();
                }
                if (result == -1)
                    return;

                program4.receiveEntry (2);
                program4.showBoard ();

                if (result == 1)// receive 1 if the vertical line is full
                {
                    program4.receiveEntry (1);
                    program4.showBoard ();
                }

                if (result == -1)
                    return;
            }

            if (userNumber == 1)
            {

                System.out.println ("AI move: ");
                program4.generateEntry ();
                program4.showBoard ();

                if (result == -1)
                    return;

                program4.receiveEntry (1);
                program4.showBoard ();

                if (result == 1)// receive 1 if the vertical line is full
                {
                    program4.receiveEntry (1);
                    program4.showBoard ();
                }

                if (result == -1)
                    return;

            }

        }

    }

    public void startNewGame ()
    {
        // System.out.println ("static instance " + (program4.connectFour == new
        // Program4 ().connectFour));
        // System.out.println ("static class " + (program4.connectFour ==
        // Program4.connectFour));
        //
        // System.out.println ("instance " + (program4.console == new Program4
        // ().console));

        System.out.println ("Connect Four\n");
        System.out.println ("1) One Player    2) Two Player ");
        System.out.print ("==>");
        playerReadIn = console.readLine ();

        userNumber = Integer.parseInt (playerReadIn);

    }

    public int getPlayerNum ()
    {
        return userNumber;
    }

    public void receiveEntry (int playerId)
    {
        System.out.println ("Player #" + playerId + " move: ");
        System.out.println ("Row # ==>");
        choiceReadIn = console.readLine ();
        userChoice = Integer.parseInt (choiceReadIn);

        result = connectFour.insertBoard (userChoice, playerId);

        if (result == -1)
        {
            System.out.println ("You won!");
            return;
        }
    }

    public void generateEntry ()
    {
        int aiMove = connectFour.nextMove ();
        result = connectFour.insertBoard (aiMove, 2);

        if (result == -1)
        {
            System.out.println ("AI won!");
            return;
        }
    }

    public void showBoard ()
    {
        gameBoard = connectFour.getBoard ();

        System.out.println ("\t [1] \t" + "[2] \t" + "[3] \t" + "[4] \t" + "[5] \t" + "[6] \t");

        for (int vertical = 0; vertical < 7; vertical++)
        {
            int v = vertical + 1;
            System.out.print ("[" + v + "] \t");
            for (int horizontal = 0; horizontal < 6; horizontal++)
            {
                System.out.print (" " + gameBoard[vertical][horizontal] + "\t");
            }
            System.out.println ("\n");
        }
    }

}
