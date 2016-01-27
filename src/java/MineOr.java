import java.util.Date;

public class MineOr extends Ressource{

    public MineOr(){//CHK

        typeBatiment="MineOr";

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
        tauxParHeure=160;
        dateDerniereLevee=new Date();
        quantiteMaxStockee=250;
    }

    public void upgrade(){//CHK
        pointsDeVie+=40;
        niveau+=1;
        tempsConstruction*=2;
        coutConstruction*=2;
        tauxParHeure*=2;
        quantiteMaxStockee*=2;
    }

    public int calculProduction(){//CHK
        Date dateActuelle=new Date();
        /* double diffMinute =dateActuelle.getMinutes()-dateDerniereLevee.getMinutes();
           double diffHeure = dateActuelle.getHours() - dateDerniereLevee.getHours();
           double diffJour = dateActuelle.getDay() - dateDerniereLevee.getDay();
           double diffMois = dateActuelle.getMonth() - dateActuelle.getMonth();

        //On considéreras que tous les mois ont 30 jours, en moyenne cela avantagera le joueur
        diffJour += diffMois*30.0;
        diffHeure+= diffJour*24.0;
        diffHeure+= diffMinute/60.0;

        double prod=diffHeure*tauxParHeure;
        int production=(int) prod;*/


        //Nouvelle version avec getTime
        long date1= dateActuelle.getTime();
        System.out.println(date1);
        long date2 = dateDerniereLevee.getTime();
        long diff = (date1-date2)/(1000*60*60);
        long production = diff*tauxParHeure;
        if (production>quantiteMaxStockee)
            return quantiteMaxStockee;
        else
            return (int ) production;
    }

    public MineOr clone(){//DONE
        return new MineOr();
    }

}
