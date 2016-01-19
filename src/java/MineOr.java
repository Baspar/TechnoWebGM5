import java.util.Date;

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
		coutConstruction=75;
		
		//Héritage de Ressource
        typeRessource=TypeRessource.OR;
        tauxParHeure=100;
        dateDerniereLevee=new Date();
        quantiteMaxStockee=250;
	}

	public void upgrade(){//WIP
		pointsDeVie+=40;
		niveau+=1;
        tempsConstruction*=2;
        coutConstruction*=2;
        tauxParHeure*=2;
        quantiteMaxStockee*=2;
	}
	
	public int calculProduction(){//WIP
	    Date dateActuelle=new Date();
	    return 0;
	}

}
