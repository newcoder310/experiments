#KALAH-KING
   Kalah-king is the rest api implementation for playing the age old game Mancala. 
   
###RULES
The rules for the game can be found in the wikipedia page [Kalah Rules](https://en.wikipedia.org/wiki/Kalah) 

For the assignment, a 6 stone/pit kalah has been considered. 

Each of the two players has ​ **​ six pits​ ** ​ in front of him/her. To the right of the six pits, each player has a larger pit, his Kalah or house.

At the start of the game, six stones are put in each pit.

The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in each of the following pits, including his own Kalah. No stones are put in the opponent's' Kalah. If the players last stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other player's turn.

When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the other players' pit) and puts them in his own Kalah.

The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her pits keeps them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.


###DESIGN DECISIONS
Below are a few design decisions and considerations as part of this implementation.
- <b>In-memory storage without persistence</b>: Since this a quick implementation, an in-memory storage of the games is implemented using a `ConcurrentHashMap`
- <b>Design Patterns</b>: A flavour of [Memento](https://en.wikipedia.org/wiki/Memento_pattern), [DTO](https://en.wikipedia.org/wiki/Data_transfer_object), [Singleton](https://en.wikipedia.org/wiki/Singleton_pattern), [Builder](https://en.wikipedia.org/wiki/Builder_pattern),[MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) patterns
  have been used according to the context of the implementation.
- <b>Stones per pit</b>: Even though the assignment, requires 6 stone implementation, a provision has been provided for configuring the stones per pit at application start.
- <b>Exception Handling</b>: A custom `KalahException` is thrown on an exception during the play. Game data is not saved on exception.
- <b>Response</b>: Based on the requirements, `MoveResponse` is sent on successful requests. However, a slight modification was considered for improving gaming experience.
    `GameCompletionResponse` is sent on game completion to display the winner. `MoveFailureResponse` is thrown when something fails in the application.

###DESIGN DETAILS
Below are a few details about the low level-design
- <b>GameCenterRepository</b>: A repository class that load on appliation start and contains a `Map` of games. Initially, a `List` was considered to store the games, but changed to a `Map` to improve search efficiency. 
- <b>Game</b>: The actualy `Game` object that can also be converted to an `Entity` if a persistence mechanism is introduced.
- <b>KalahResource</b>: `RestController` class that provides the rest endpoints to connect to.
- <b>GameActions</b>: Collects the list of `Actions` that need to be performed. An `Action` class that is highly cohesive built for a specific purpose in the game. 
- <b>GameOrchestrator</b>: Contains a play method that executes `Action` class and decides the next step.
- <b>GameController</b>: Controls the flow of the application.

###RUNNING THE APPLICAITON
#####PRE-REQUISITES: 
    - Maven,
    - Java 1.8
    
- To the run application, there are 2 ways
    - Run `mvn clean install` to build the jar file and run `java -jar kalah-0.0.1-SNAPSHOT.jar` or `java -jar kalah-0.0.1-SNAPSHOT.jar --server.port=<port>`
    - Run `mvn spring-boot:run`
    
######HOW TO PLAY
There are a couple of scripts for the users convenience. Usage is embedded in the scripts. Can copy and execute the scripts from anywhere
    - <b>createGame.sh</b>: Run this to create a game
    - <b>playGame.sh</b>: Run this to make a move.   
    
######TEST COVERAGE
The application has more than 80% test coverage with a range of unit and integration tests.      