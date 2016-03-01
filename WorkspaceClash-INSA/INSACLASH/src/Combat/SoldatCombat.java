package Combat;

import java.util.Vector;
import java.util.AbstractMap.SimpleEntry;

import Model.Soldat;

public class SoldatCombat extends EntiteCombat{

    private Soldat soldat;

    public SoldatCombat(Soldat soldat){//DONE
        this.x      = -1;
        this.y      = -1;
        this.soldat = soldat;
        this.taille = 1;
        this.PV     = soldat.getPointsDeVie();
        id=cpt++;
    }
    public Vector<BatimentCombat> trouverBatimentAPortee(Vector<BatimentCombat> batiments, int zoom){//CHK
        int nbMaxBatiments=1;
        int portee=4;//TODO: a mettre dans les classes

        Vector<BatimentCombat> out = new Vector<BatimentCombat>();
        for(BatimentCombat batiment:batiments){
            if( !batiment.estMort() && !batiment.estATuer() && getDistance(batiment) <= portee ){
                System.out.println("    J'ai un "+batiment.getBatiment().getTypeBatiment()+" a porte");
                out.add(batiment);
                if(out.size()==nbMaxBatiments)
                    return out;
            }
        }
        return out;
    }
    public void attaquer(Vector<BatimentCombat> batiments){//DONE
        for(BatimentCombat batiment : batiments)
                batiment.retirerPV(soldat.getPuissanceAttaque());
    }
    public Soldat getSoldat(){//DONE
        return soldat;
    }
    private double getDistance(int xcp, int ycp){//DONE
        return Math.sqrt( Math.pow(ycp-y, 2) + Math.pow(ycp-y, 2));
    }
    private double getDistance(BatimentCombat bat){//DONE
        int xBat=bat.getX();
        int yBat=bat.getY();

        return Math.sqrt( Math.pow(yBat-y, 2) + Math.pow(yBat-y, 2));
    }
    public Vector<Integer> ouAller(Vector<BatimentCombat> batiments, int tailleVillage){//WIP
        Vector<Integer> out = new Vector<Integer>();

        int deplacementMax = soldat.getVitesseDeplacement();

        int newX = -1;
        int newY = -1;
        int distMin = -1;

        BatimentCombat batProche = null;
        for(BatimentCombat batiment: batiments)
            if(batProche==null || (getDistance(batiment)<getDistance(batProche) && !batiment.estMort() && !batiment.estATuer()))
                    batProche = batiment;

        int myX=-1;
        int myY=-1;
        for(int dx=-deplacementMax; dx<=deplacementMax; dx++){
            for(int dy=-deplacementMax; dy<=deplacementMax; dy++){
                int tmpX=this.x+dx;
                int tmpY=this.y+dy;
            }
        }

        out.add(newX);
        out.add(newY);
        return out;
    }
}
