package Model;

public class Archer extends Soldat{
    public Archer(){//DONE
        ressourceNecessaire=TypeRessource.CHARBON;
        pointsDeVie=20;
        puissanceAttaque=7;
        vitesseDeplacement=4;
        typeAttaque=TypeAttaque.LOCALISE;
        coutUpgrade=50000;
        coutUnite=50;
        niveau=1;
        dureeFormation=20;
        placeOccupee=1;
    }
    
   
}
