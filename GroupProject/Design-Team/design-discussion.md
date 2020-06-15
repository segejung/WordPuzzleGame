# Design Discussion

## Design 1 [Roger Kiew]
![Design 1](../images/Design1.png)
### Pros
The cleanest looking UML from our team. It has a global variable that scores and settings are accessed from. All the int variables have default and ranges.

Not overly complex and is easy to understand.

The design has less UML classes as he consolidated a lot of attributes and features. In this way, he made the UML much clean for other people to understand the structure.

### Cons
The class diagram is broad and does not address a lot of the requirement details. The diagram could help with more detail on enterWord(), statistics, and initializeBoard. 

Consider using lists instead of arrays so that items can be dynamically added to the list of scores and words instead of arrays which have a fixed length. 

The game statistics can be made a separate class as it has more features and more ways that a player can interact with the game. In the current design, it was a little bit too simple because stats were only addressed through showScoreStatistics() and showWordStatistics() under Game.

## Design 2 [SeGe Jung]
![Design 2](../images/Design2.png)
### Pros
Well structured, listed both UI and the classes

Most detailed and did a good job of meeting the requirements. 
Compared to design 1, this design has a better overall structure. More classes were considered to show the relationship and interacction.

### Cons
UI could be consolidated into one Main UI. 

No global access to each game's score/settings etc.

Could remove simple methods such as subtractFivePoints and simply subtract five points from the score during a reroll. 

Under UI section, there were four classes. Three sub classes can all be included under the main UI so that the overall class structure is clearer and less confusing.

## Design 3 [Brian Hoang]
![Design 3](../images/Design3.png) 
### Pros
Concise and structured view of the classes. Address all of the functions of the wordgame application.

The classes contained all necessary fields/methods.

The design has clear structure while maintaing good amount of details. The method explanation near each relationship clearly states how different classes interact with each other.
  
### Cons
It would be worth articulating the enterWord() method as it is listed as one of the requirements. What constitutes a word? There is no information on what the board will consist of. Vowels, weights, letter ‘Qu’, or the size. Would like to see some more classes articulating this work. Aggregation of board can be classes letters and word.

The classes are interconnected so the structure is not as clear.

The right hand side of the design has similar unclear relationship/interaction among Statistics, GameScoreStatistics, and WordStatistics.

## Design 4 [Mo Yang]
![Design 4](../images/Design4.png) 
### Pros
Good broad overview of the application 

The design is simple, easy to read.

The text between classes help demonstrate their relationship and the flow of the design.

### Cons
All the arrows suggest same relationship between all classes whereas there might be association or aggregation relationships. 
The requirements of player entering words that are more than two letters are not reflected in the class diagram. 
There are two statistics: one is word and one is score. This is not reflected in the diagram.

The arrows are a little bit confusing. For example, which line does "minus 5 points" belong to?

A separate class for game settings may be ideal. More details on the methods of classes would help display how the design fulfills the requirements. 

## Team Design 
![Team Design](../images/TeamDesign.png) 

During the meeting, we went over individual UML class diagrams. Then we chose one design that everyone liked the most. Design 2 was chosen as the basis of team design. From that, everyone commented on things to improve. 

Reason for Design 2: Design 2 had all of the classes identified and reflected most of the requirements. UI interface was also reflected which is a bonus. The relationships between entities are clearly laid out. 

Improvements: There were couple of improvements from Design 2 that could be developed to the final team design. 
- Consolidated Play a Word Game UI, View Statistics UI, Adjust the Game Settings UI and placed it under Main UI to clean up the look of the diagram.
- Created association between class WordGame and Statistics so that Statistics is globally available to class WordGame. 
- Statistics and wordstatistics class diagrams were imported from Design 3 since it had detailed methods and operations. 

## Summary
All the team members had solid structure in their UML class diagrams. The level of detailed differed, however, by group effort, we maximized our pros and minimized our cons. 
This process was done through weekly meetings and threads in Microsoft Teams. 

We discussed our designs over a screen-share on Microsoft Teams and it was great to hear feedback from outside perspectives with constructive criticism. All the class diagrams had a good basis for their overall design and we were able to come to an agreement on the final design with little to no trouble. 

It was great to share each team member's thoughts on our last assigment and review all four designs together. We really learned a lot by examining each design's pros and cons. Then we summarized all the things into our final design, which shows a clear structure of classes while maintaing a good amount of details to indicate the relationship of how the game works.

All of our designs looked good at first but when we compared them to each other's design, there were some rooms for improvements. I am glad that we got a chance to see how other people will approach the requirements and we all learned from each other.
