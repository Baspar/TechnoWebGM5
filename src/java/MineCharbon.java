import java.util.Date;

public class MineCharbon extends Ressource{

    public MineCharbon(){//WIP
        typeBatiment="MineCharbon";

        //Héritage de Batiment
        pointsDeVie=360;
        //On initialise au niveau 0 pour simplifier la gestion globale des batiments
        niveau=0;
        x=-1;
        y=-1;
        ressourceNecessaire=TypeRessource.OR;
        tempsConstruction=7;
        coutConstruction=75;

        //Héritage de Ressource
        typeRessource=TypeRessource.CHARBON;
        tauxParHeure=160;
        dateDerniereLevee=new Date();
        quantiteMaxStockee=250;
    }
    
    
    
    public MineCharbon clone(){//CHK
        return new MineCharbon();
    }
}
