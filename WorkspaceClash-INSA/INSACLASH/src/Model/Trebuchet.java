package Model;

public class Trebuchet extends Soldat{
    Trebuchet(){//DONE
        ressourceNecessaire=TypeRessource.CHARBON;
        pointsDeVie=300;
        puissanceAttaque=11;
        vitesseDeplacement=1;
        typeAttaque=TypeAttaque.ZONE;
        coutUpgrade=100000;
        coutUnite=500;
        niveau=1;
        dureeFormation=120;
        placeOccupee=5;
    }
   
   
}
