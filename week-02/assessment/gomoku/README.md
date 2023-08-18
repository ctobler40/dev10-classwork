# Dev10 Gomoku Outline

4 Game Classes
- ***Board***: Prints out and updates the board with all the information after a move is played
- ***Gomoku***: Primary class. Checks for winner along with any faults like misplaced pieces
- ***Result***: Determines the outcome of each move
- ***Stone***: Contains the location and color of each stone placed

1 Interface Class
- ***Player***
    - Human Player: Will have moves determined by human interaction
    - Random Player: Will do random moves as a random AI

1 App Class
- ***Main***
    - All we want to do in this class is call the PlayGomokuFunction, which will control everything
- ***playGomoku***
    - ***Type: Void***
    - ***Params: None***
    - Instantiate all of our main variables
    - Set up and print the initial game board
    - Set up the first random player (gotten from Gomoku class)
    - While gomoku game is not over
        - If the current player is P1
            - Update result with a call to Player 1's turn
        - Else
            - Update result with a call to Player 2's turn
        - ***SIDE NOTE: These two lines above could call the same method with just a different player param!***
        - If the result is a success
            Print out the board, as a move has successfully been made!
    - Now out the while loop, we are completed and so we just look to play again or not
- ***playAgain***
    - ***Type: Void***
    - ***Params: Result***
    - Print out the winner and ask the user if they want to play again or not
    - If the console gets a 'y'
        - Simply rerun the playGomoku call
- ***playerTurn***
    - ***Type: Result***
    - ***Params: Gomoku, Board, Player, Result***
    - Probably will be the main beef of the App class
    - If the player arg is an instance of HumanPlayer
        - Get input from the console for row and column
    - Else
        - The player then has to be an instance of a Random player
        - Simply assign row and column to a random value between 0 and 15
    - Now that we have row and column, we see if it is playable
    - Create a new Stone with the parameters row, col, and Gomoku.isBlacksTurn
    - ***Updating Board***: If the stone is in range on the board
        - If the spot is not already taken on the board
            - We can set the Board with the new row and col values so that it prints the respected B or W
    - ***Updating Gmoku***: Set the Result param to Gomoku.place, as this class method returns a Result, also use the created Stone for an arg
    - If the result in not a success, meaning at least one issue occurred
        - Print out the result.getMessage to the console
    - Return the result to where it will be assigned and checked back in playGonomku
- ***setUpPlayer***
    - ***Type: Player***
    - ***Params: int***
    - while true
        - This will keep running until the player enters a valid input to which we will call return inside the while loop
        - Print out options for player and get input
        - Switch all inputs
            - 1: Human Player, have them enter their name and then return to break out of the while loop
            - 2: Random Player, create a new random player and assign it to p1 or p2 respectfully, then return to break out of the while loop
            - Default: Need a 1 or 2 response, simply break so that the while loop will continue