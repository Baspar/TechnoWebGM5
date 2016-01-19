public class MineOr extends Ressource{

    public MineOr(){//WIP
        //Héritage de Batiment
        pointsDeVie=360;
        //On initialise au niveau 0 pour simplifier la gestion globale des batiments
        niveau=0;
        x=-1;
        y=-1;
        ressourceNecessaire=TypeRessource.CHARBON;
        tempsConstruction=7;

        //Héritage de Ressource
        typeRessource=TypeRessource.OR;
        tauxParHeure=100;
    }

    public void upgrade(){//WIP
        pointsDeVie+=40;
        niveau+=1;
    }
}
