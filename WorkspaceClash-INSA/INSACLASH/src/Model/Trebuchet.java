package Model;

public class Trebuchet extends Soldat{
    Trebuchet(){//DONE
        id=cpt;
        cpt++;
        ressourceNecessaire=TypeRessource.CHARBON;
        type=TypeSoldat.TREBUCHET;
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

    public Trebuchet(int n){//DONE
        this();
        for(int i=1; i<n;i++)
            super.upgrade();
    }

}
