The *Game* class stores static information, such as overall game scores, all the words played and their counts, all players and their settings. 

The *Starter* class extends the *Game* class since it must have access to the global data. The *Starter* class will handle information of each game played. It also contains the settings and the board information. 

The *GameSettings* class contains the information of each setting and the *Board* class contains the information of the board. Note that the letters are stored as char[] but we can get it as String for convenience.

Below are the requirements and all of them are fulfilled as Note suggests:

1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.
   
   **Note:** The *Starter* class has *playWordGame()*, *viewStatistics()*, *adjustGameSettings()* method for each action.
2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.
   
   **Note:** The *GameSettings* class has fields for the end minutes, size of the board, weight of each letter, and each of them has corresponding getter/setter. The *Starter* class has a gameSettings instance to store their settings.
3. When choosing to play a word game, a player will:
    
    a. Be shown a `‘board’` of randomly generated letters.

    **Note:** The UI will display the letters, the value of the letters are retrieved from the board instance. The *initializeBoard* method will handle the logic and helper method can be applied.
    
    b. Be shown a timer counting down the number of minutes available for the game, as set in the settings.

    **Note:** The UI will handle the timer, the value will be retrieved from gameSettings instance.
    
    c. Start with 0 points, which is not required to be displayed during the game.

    **Note:** The value is stored in the starter instance.

    d. Until the game timer counts to zero and the game ends:

        i. Enter a unique `word` made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).
        or

        ii. Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values.
        or
        
        iii. Choose to exit the game early.

    **Note:** The user can use the UI to interact with *enterWord* method for requirement i, interact with *reroll* method for requirement ii, interact with *exitGame* method for requirement iii.

    e. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed.
    
    **Note:** The *isOver* method will handle the logic as the final score and the timer information is stored in the starter instance.

    f. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.
    
    **Note:** The *calculateScore* method will handle the logic, since the *timesBoardReset* field is class variable so we don't need to pass it to the method.

    g. After the player views the score, they will continue back to the main menu.

    **Note:** The logic is handled by the UI implementation.

4. Whenever the board is generated, or re-generated it will meet the following criteria:
    
    a. The board will consist of a square full of letters.  The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).
    
    b. ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants.
    
    c. The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).  
    
    d. The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings.  A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels).  In this way, more common letters can be set to appear more often.
    
    e. A letter may appear on the board more than once.

    **Note:** For all the points mentioned above, The *initializeBoard* method will handle the logic, corresponding helper methods may be applied.

5. When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics.
   
    **Note:** The scores and words are stored in the *Game* class as static variables, the UI will take care of it.
    
6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
        
    a. The final game score

    **Note:** The value is stored in the starter instance as *score* field.
    
    b. The number of times the board was reset

    **Note:** The value is stored in the starter instance as *timesBoardReset* field.
    
    c. The number of words entered in the game

    **Note:** The value is stored in the starter instance as *timeswordsEntered* field.
    
    The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed).

    **Note:** The information is stored in *Game* class as static fields, *scores*, *words*, *wordsCount* and *players*. Each of the *Starter* has the information of the settings and the board. 
7. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:
        
    i. The word

    **Note:** The information is stored in the *Game* class with static fields *scores* and *words*. To check the values, the user can interact with *showScoreStatistics()* method and *showWordStatistics()* method.
    
    ii. The number of times the word has been played, across all games

    **Note:** The information is stored in the *Game* class, with a *wordsCount* field. To improve the efficiency, I decided to use a map to have O(1) access time of the words' frequency. No need to iterate through the array to calculate the frequency. 
8. The user interface must be intuitive and responsive.
   
    **Note:** It is handled by the UI implementation.

9.  The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
    
    **Note:** See 7-ii.

10. For simplicity, you may assume there is a single system running the application.
    
    **Note:** Copied that.