CREATE DATABASE insaclash;
USE insaclash;

SET foreign_key_checks = 0;
DROP TABLE Joueur;
DROP TABLE Ressource;
DROP TABLE Defense;
DROP TABLE HDV;
DROP TABLE Caserne;
SET foreign_key_checks = 1;


SELECT "Suppression TABLEs termine" as "Message";


CREATE TABLE Joueur(
    login VARCHAR(20) NOT NULL,
    motDePasse VARCHAR(56) NOT NULL,

    PRIMARY KEY(login)
);
CREATE TABLE Ressource(
    id INTEGER NOT NULL,
    niveau INTEGER NOT NULL,
    loginJoueur VARCHAR(20) NOT NULL,
    typeRessource ENUM('MineCharbon', 'MineOr'),
    derniereLevee DATETIME NOT NULL,
    x INTEGER,
    y INTEGER,

    PRIMARY KEY(loginJoueur, typeRessource, id),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
);
CREATE TABLE Defense(
    id INTEGER NOT NULL,
    niveau INTEGER NOT NULL,
    loginJoueur VARCHAR(20) NOT NULL,
    typeDefense ENUM('Canon', 'Mortier'),
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,

    PRIMARY KEY(loginJoueur, typeDefense, id),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
);
CREATE TABLE HDV(
    niveau INTEGER NOT NULL,
    loginJoueur VARCHAR(20) NOT NULL,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    quantiteOr INTEGER NOT NULL,
    quantiteCharbon INTEGER NOT NULL,

    PRIMARY KEY(loginJoueur),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
);
CREATE TABLE Caserne(
    niveau INTEGER NOT NULL,
    loginJoueur VARCHAR(20) NOT NULL,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    nombreArcher INTEGER NOT NULL, 
    niveauArcher INTEGER NOT NULL, 
    nombreTrebuchet INTEGER NOT NULL,
    niveauTrebuchet INTEGER NOT NULL,

    PRIMARY KEY(loginJoueur),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
);
