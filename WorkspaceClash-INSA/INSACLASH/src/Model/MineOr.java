package Model;

import java.util.Date;

public class MineOr extends Ressource{

    public MineOr(){//DONE

        typeBatiment=TypeBatiment.MINEOR;

        //Héritage de Batiment
        pointsDeVie=360;
        //On initialise au niveau 0 pour simplifier la gestion globale des batiments
        niveau=0;
        ressourceNecessaire=TypeRessource.CHARBON;
        tempsConstruction=7;
        coutUpdate=75;

        //Héritage de Ressource
        typeRessource=TypeRessource.OR;
        tauxParHeure=160;
        dateDerniereLevee=new Date();
        quantiteMaxStockee=250;
    }


    public MineOr clone(){//DONE
        return new MineOr();
    }

}
