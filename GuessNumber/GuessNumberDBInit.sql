DROP DATABASE IF EXISTS GuessNumberDB;
CREATE DATABASE GuessNumberDB;
USE GuessNumberDB;

CREATE TABLE Game (
	GameId INT PRIMARY KEY AUTO_INCREMENT,
    MysteryNumber CHAR(4) NOT NULL,
    Finished BOOLEAN NOT NULL
);

CREATE TABLE Trial (
	TrialId INT PRIMARY KEY AUTO_INCREMENT,
	GameId INT NOT NULL,
    PlayerNumber CHAR(4) NOT NULL,
    Exact INT NOT NULL,
    Partial INT NOT NULL,
    TryDate DATETIME NOT NULL,
    foreign key fk_Trial_GameId (GameId)
		references Game(GameId)
);