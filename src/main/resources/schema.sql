--CREATE TABLE exercise(
--  id INT PRIMARY KEY,
--  tournament_id INT,
--  reward_amount INT,
--  player_id INT,
--  player_name VARCHAR
--);

--show columns FROM PLAYERS ;
-- show columns FROM TOURNAMENTS ;
--select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS ;

--org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Unique index or primary key violation: "PUBLIC.UQ_NAME_INDEX_4 ON PUBLIC.TOURNAMENTS(NAME) VALUES 3"; SQL statement:

DROP TABLE IF EXISTS TOURNAMENTS;
DROP TABLE IF EXISTS PLAYERS;

CREATE TABLE TOURNAMENTS (
ID INT NOT NULL auto_increment,
NAME VARCHAR(100) NOT NULL,
PRIZE INT NOT NULL,
CURRENCY VARCHAR(10) NOT NULL DEFAULT 'EUR',
PRIMARY KEY(ID)
);

CREATE TABLE PLAYERS (
ID INT NOT NULL auto_increment,
NAME VARCHAR(100) NOT NULL,
TOURNAMENT_ID INT NOT NULL,
CONSTRAINT FK_T FOREIGN KEY (TOURNAMENT_ID) REFERENCES TOURNAMENTS(ID),
PRIMARY KEY(ID)
);

ALTER TABLE TOURNAMENTS
  ADD CONSTRAINT uq_name UNIQUE(NAME);

ALTER TABLE PLAYERS
  ADD CONSTRAINT uq_2 UNIQUE(NAME, TOURNAMENT_ID);
