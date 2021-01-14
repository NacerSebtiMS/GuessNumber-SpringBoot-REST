# GuessNumber-SpringBoot-REST

A Spring Boot REST server that facilitates playing a number playing game known as "Bulls and Cows".

The Spring Boot application uses JDBC Template to access the database.



The endpoints of the REST server are the following :
+ "/begin" - POST : Starts a game. Returns a 201 CREATED message ;
+ "/guess" - POST : Makes a guess by passing a JSON with the game ID and a guess number. The server will respond with a Trial object ;
+ "/game" - GET : Returns a list of games. Games in-progress will have their answer hidden ;
+ "/game/{gameId}" - GET : Return a specific game. Games in-progress will have their answer hidden ;
+ "/rounds/{gameId}" - GET : Returns a list of rounds for the specified game sorted by time.

The product delivered at the end of the project contains :
+ Java project [GuessNumber](https://github.com/NacerSebtiMS/GuessNumber-SpringBoot-REST/tree/main/GuessNumber) ;
+ ERD and SQL init files for the database [Database files](https://github.com/NacerSebtiMS/GuessNumber-SpringBoot-REST/tree/main/Database%20files).
