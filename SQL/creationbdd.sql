create database insaclash;
use insaclash;

SET foreign_key_checks = 0;
drop table Joueur;
SET foreign_key_checks = 1;


SELECT "Suppression tables termine" as "Message";


create table Joueur(
    login VARCHAR(20) not null,
    motDePasse VARCHAR(56) not null,

    PRIMARY KEY(login)
);

create table Ressouce{
    id INTEGER not null,
    niveau INTEGER not null,
    loginJoueur VARCHAR(20) not null,
    typeRessouce ENUM('MineCharbon', 'MineOr')
    derniereLevee TIMESTAMP not null,
    x INTEGER not null,
    y INTEGER not null,

    PRIMARY KEY(loginJoueur, typeRessource, id),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
}
create table Defense{
    id INTEGER not null,
    niveau INTEGER not null,
    loginJoueur VARCHAR(20) not null,
    typeDefense ENUM('Canon', 'Mortier')
    x INTEGER not null,
    y INTEGER not null,

    PRIMARY KEY(loginJoueur, typeDefense, id),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
}
create table HDV{
    niveau INTEGER not null,
    loginJoueur VARCHAR(20) not null,
    x INTEGER not null,
    y INTEGER not null,

    PRIMARY KEY(loginJoueur),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
}
create table Caserne{
    niveau INTEGER not null,
    loginJoueur VARCHAR(20) not null,
    x INTEGER not null,
    y INTEGER not null,

    PRIMARY KEY(loginJoueur),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
}
create table Soldat{
    id INTEGER not null,
    niveau INTEGER not null,
    loginJoueur VARCHAR(20) not null,
    typeSoldat ENUM('Trebuchet', 'Archer')

    PRIMARY KEY(loginJoueur, typeSoldat, id),
    FOREIGN KEY(loginJoueur) REFERENCES Joueur(login)
}
