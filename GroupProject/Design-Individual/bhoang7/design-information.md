# Requirements
1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.  
    - **To realize this requirement, I created a MainMenu class with a selectOption method that will allow the user to choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings which are all also in the form of classes that will be utilitized if selected.**
2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.
    - **To realize this requirement, I created a GameSettings class with variables that can be set for minutes as an integer, the size of the squareBoard as an integer, and an array of integers for the weight of each character. The word game will grab the settings set in this class.**
3. When choosing to play a word game, a player will:
    - Be shown a ‘board’ of randomly generated letters.
        - **To realize this requirement, I created a WordGame class that contains a two dimensional array of characters for the board and will call the setupBoard() method that will randomly generate the board utilizing the size of the board acquired from the GameSettings class and the weights for each letter as well.** 
    - Be shown a timer counting down the number of minutes available for the game, as set in the settings.
        - **To realize this requirement, get the minutes set in GameSettings and at the end up the setupBoard() method start the timer as that is when the game will begin.** 
    - Start with 0 points, which is not required to be displayed during the game.
        -   **To realize this requirement, I added a point variable as an Integer that will be set to 0 by default in the WordGame class.**
    - Until the game timer counts to zero and the game ends:
        - Enter a unique word made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).
or
            - **To realize this requirement, I created an enterWord method that will take in the user's input of a word that will check to see if the word is two or more letters and the letters are adjacent to eachother according to the row and columns from the two dimensional array board. It will also prevent a single letter on the board from being used twice and if all these cases are true then add it to the words list.** 
        - Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values.
or
            - **To realize this requirement, I added a reroll() method in the WordGame class that will call the setupBoard() method that randomizes and replaces each letter in the board and will deduct 5 points from the total points.**
        - Choose to exit the game early.
            - **To realize this requirement, I added an exit() method in the WordGame class that will display the final score of the game and then return back to the main menu.**  
    - At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed.
        - **To realize this requirement, I added a displayScore() method that will display the current points when the timer has run out or the play chooses to exit.** 
    - Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.
        - **To realize this requirement, each word inputted will be checked for validity in the enterWord() method and each letter within the inputted word will be counted as 1 point. In the reroll() method when the board is reset 5 points will be subtracted from the total.** 
    - After the player views the score, they will continue back to the main menu.
        - **To realize this requirement, in the displayScore() method this will follow up with exiting from the word game through the exit() method and then returning back to the main menu.** 
4. Whenever the board is generated, or re-generated it will meet the following criteria:
    - The board will consist of a square full of letters.  The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).  
        - **To realize this requirement, I will utilize the two dimensional array of characters that I created for the board. The board will acquire the size from the game settings and will apply this size to both the rows and columns of the board.** 
    - ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants.
        - **This requirement will be handled in the logic of the setupBoard() method.**
    - The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).  
        - **This requirement will aslo be handled in the setupBoard method that will treat "Q" as a special case and display it as "Qu."** 
    - The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings.  A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels).  In this way, more common letters can be set to appear more often.
        - **This requirement will be realized within the logic of the setupBoard method that will factor in the weights of each letter acquired from the game settings and will randomly select letters to be added to the board. The GameSettings class has an array of weights in ints for each letter that will be used to determine their probablity of showing up on the board more often.**
    - A letter may appear on the board more than once.
        - **This requirement will be realized in the setupBoard method as there will be no code that prevents a letter from appearing more than once.**
5. When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics.
    - **To realize this requirement, I added a Statistics class that will be utilized if the user chooses to view statistics from the main menu. This will allow the user to choose between game score statistics, or word statistics in their respective classes that I have created as well.**
6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
    - The final game score
        - **To realize this requirement, I created a finalScores list of Integers in the GameScoreStatistics class that will retrieve the final scores at the end of each word game. When displaying the statistics through the displayStatistics method the finalScores list will be sorted in descending order along with the other game score statistics as well.**
    - The number of times the board was reset
        - **This requirement will be realized by a resetCounts list of Integers that will be updated by the rerollCount from each word game.**
    - The number of words entered in the game
        - **This requirement will be realized by a wordCounts list of Integers that will be updated by the count from the list of words from the WordGame class.**
- The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed).
    - **To realize this requirement, I added a viewStatistics() method that will be called when the user selects any of the game scores from the list. This will display the game's board size that is acquired from the updateGameStatistics() method in the WordGame class that gets the size from game settings and is put in a sizeList, the number of minutes from the updateGameStatistics() method and gets the minutes from the WordGame class, and also the highest scoring word played in the game that will be retrieved from the words list from WordGame that has the most letters.** 
7. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:
    - The word
        - **To realize this requirement, I added a WordStatistics class that will contains a hashmap called wordMap with Strings of words as the keys and integers of their amount of times used as the values. In the updateWordStatistics method in WordGame it will add a word to the wordMap if it is not already in there.** 
    - The number of times the word has been played, across all games
        - **To realize this requirement, each time a word is accounted for in the updateWordStatistics method the amount for it will be incremented and that amount is set as the value in the wordMap for each word. With the amounts added to the hash map the wordMap will be sorted starting from the most frequently played and displayed through the displayStatistics method in the WordStatistics class.** 
8. The user interface must be intuitive and responsive.
    - **This is not represented in my design, as it will be handled entirely within the GUI implementation.**
9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
    - **To realize this requirement, I designed my system to not be overly complex in order to reduce any unnecessary processing. Making it so that code is not repeated will be useful in reducing the overall complexity which will reduce lag between actions and the response of the application.**
10. For simplicity, you may assume there is a single system running the application.
    - **This requirement is realized in the design as all the classes are integrated with eachother all within a single system in order to run the application.**