create database insaclash;
use insaclash;

SET foreign_key_checks = 0;
drop table Joueur;
SET foreign_key_checks = 1;


SELECT "Suppression tables termine" as "Message";


create table Joueur(
    login varchar(20) not null,
    motDePasse varchar(56) not null,
    
    PRIMARY KEY(login)
);


    
