# Test Plan

**Author**: Team112
**Version**: 1.0
**Date**: Feb 18 2020

**Version**: 1.1
**Date**: Feb 21 2020

## 1 Testing Strategy

### 1.1 Overall strategy

*We will use the tools and knowledge we learned from the past modules from this class to help test our software. We will break down the entire test process into the following parts

1. Unit Testing - we will develope corner cases, boundary conditions, and error cases to test out our codes.
2. Integration Testing - we will focus on the functionality test of a group of components together
3. Regression Testing - build up the framework for automated test scripts so that we test against expected outputs for each build. we will also track the error vs. each build record to catch any potential bugs.
4. System Testing  - this evaluates the software system as a complete system. we will consider things like system performance, system limits, error handling, and legitimate usage.

*We will probably utilize the JUnit and Espresso to help build our automation testing scripts.


### 1.2 Test Selection

*We will start from our use case models to derive common test cases/values to test each unit and integration. Then we will further include corner cases, boundary conditions, and error handling scenarios based on our software design and process flow

### 1.3 Adequacy Criterion

*Adequacy Criterion consists of,
1. if the software takes in the user input correctly.
2. if the software outputs correct/expected results based on the requirements/specs
3. if the complete software has the meaningful and functional User Interface.

### 1.4 Bug Tracking

*We will use JIRA system to help us track the bugs during our software development. In order to do that, we create a JIRA ticket to report the bug whenever we encounter it during development/during testing. The information will include,

i. bug definition
ii. bug description
iii. software build version and dependency
iv. other details - like screen capture, possible root causes, steps to show the bug, etc.

### 1.5 Technology

*As mentioned earlier, JUnit and Espresso will be used to as the technology for our software testing. We will leverage the things we learned and the codes we developed from our assigment 4 to help build our test process flow.

## 2 Test Cases

| Test#   |      Test Name      |  Detailed Steps |  Correct/Expected Values |PASS/FAIL|
|:--:|:-------------:|------|------|:-------------:|
| 1 |  test game setting | input various game settings into the software, 5x size, 5x weight, 3x minutes |the generated game should match the input values, UI updated | PASS |
| 2 |    view statistics - word   |   first run through different games. then enable statistics view button | the output result should match the currect saved word, UI updated. The number of times the word has been played, across all games| PASS |
| 3 | view statistics - game |    first run through different games. then enable statistics view button | the UI will show the list of scores, in descending order by final game score. The values have to match. also test the highest score and check for the right value| PASS |
|4| play game| click on play game | board generated and UI updated| PASS |
|5| enter word and validate word| user to enter a input as the word for the game | the attribute gets updated and UI is also updated. The score should be updated as well| PASS |
|6| re-roll test| user to click on re-roll button | a new board should be initialized, UI updated and score goes down by 5 points| PASS |
|7| exit game| user to click on exit button| the attributes all get updated. UI updated to display the final score| PASS |
|8|Test Qu | Game to include Qu cases| it should be counted as 1 letter| PASS |
|9| view statistics - setting |    first run through different games. then enable statistics view button and check the corresponding setting | the UI will show the correcct setting information used during that game | PASS |
|10|validate word in more details| user to enter ten different words. Check if the words meet all the requirements. | Then check if the software updates to the right score or error msg| PASS |










