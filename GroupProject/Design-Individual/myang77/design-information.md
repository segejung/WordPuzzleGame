# Design Info - Assignment 5


----
#### When the application is started, the player may choose to

All the following items are designed under a class called Menu:

1.  Play a word game - a boolean called playGameButton will be triggered when the player clicks on it.
2. View statistics - a boolean called viewStatistics will be triggered when the player clicks on it.
3. Adjust the game settings - a boolean called gameSettingButton will be triggered when the player clicks on it.

----
####When choosing to adjust the game settings, the player 

The following items are designed under a class called game.

(1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, - this is implemented through the attribute Timer: int

(2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and - this is implemented through the attribute BoardSize: int

(3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1 - this is implemented through the attribute WeightsOfTheLetters: dictionary

####When choosing to play a word game, a player will:
1. Be shown a ‘board’ of randomly generated letters.- this is implemented through the attribute Board: array. In more details, the code will be implemented to self-generate random boards based on the game setting.
2. Be shown a timer counting down the number of minutes available for the game, as set in the settings.
3. Start with 0 points, which is not required to be displayed during the game. -This is implemented by setting the score variable always as 0 initially and get updates on the scores as the game proceeds.

####Until the game timer counts to zero and the game ends:
1. Enter a unique word made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).

2. Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values - this is implemented through the method reRollBoard so that it updates the score by minus 5 and re-generate the board.
3. Choose to exit the game early - this is implemented through the method: EndGameEarly


4. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed - this is implemented through the game class's internal methods.

5. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted - this is implemented through a score_update method.

6. After the player views the score, they will continue back to the main menu - end of the game always triggers going back to the main menu.

####Rules Whenever the board is generated, or re-generated
These exceptions and if statements are currently not in my UML design and they can be easily coded out when the design is implemented.


####When choosing to view statistics, the player may view
1. game score statistics - there is a sub-item called Score under stats for this feature
2. word statistics. - there is a sub-item called word to show these stats

####For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
1. The final game score - this is under the score statistics. It will be showed when the user selects view stats
2. The number of times the board was reset - the number was record using the reset_number: int
3. The number of words entered in the game - I implemented a word_count attribute to count for this
4. The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed).
5. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:
The word
The number of times the word has been played, across all games
----
####The user interface must be intuitive and responsive.
This is not represented in my design, as it will be handled entirely within the GUI implementation.


####The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
This is not represented in my design, as it will be handled entirely within the code implementation.


####For simplicity, you may assume there is a single system running the application.
This is just the assumption for this assignment






