package Combat;

import java.util.Vector;
import java.util.AbstractMap.SimpleEntry;

import Model.Soldat;

public class SoldatCombat extends EntiteCombat{

    private Soldat soldat;

    public SoldatCombat(Soldat soldat){//DONE
        this.x      = 5;
        this.y      = 1;
        this.soldat = soldat;
        this.taille = 1;
        this.PV     = soldat.getPointsDeVie();
        id=cpt++;
    }
    public Vector<BatimentCombat> trouverBatimentAPortee(Vector<BatimentCombat> batiments, int zoom){//CHK
        int nbMaxBatiments=1;
        int portee=2;//TODO: a mettre dans les classes

        Vector<BatimentCombat> out = new Vector<BatimentCombat>();
        for(BatimentCombat batiment:batiments)
            if( Math.abs(x-batiment.getX()*zoom+1)+Math.abs(y-batiment.getY()*zoom+1) <= portee ){
                out.add(batiment);
                System.out.println("    J'attaque un "+batiment.getBatiment().getTypeBatiment()+" ("+(Math.abs(x-batiment.getX()*zoom+1))+"eeee"+(Math.abs(y-batiment.getY()*zoom+1))+") ["+batiment.getPV()+"] -"+(batiment.getX()*zoom+1)+","+(batiment.getY()*zoom+1)+"-");
                if(out.size()==nbMaxBatiments)
                    return out;
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
    public Vector<Integer> ouAller(Vector<BatimentCombat> batiments, Vector<Vector<SimpleEntry<Integer, BatimentCombat>>> terrainDistance){//WIP
        Vector<Integer> out = new Vector<Integer>();

        int deplacementMax = soldat.getVitesseDeplacement();

        int newX = -1;
        int newY = -1;
        int distMin = -1;
        int trueTailleCarte = terrainDistance.size();

        for(int dx=-deplacementMax; dx<=deplacementMax; dx++)
            for(int dy=-deplacementMax; dy<=deplacementMax; dy++){
                if( y+dy>=0
                    && y+dy<trueTailleCarte
                    && y+dy>=0
                    && y+dy<trueTailleCarte
                    && terrainDistance.get(x+dx).get(y+dy).getKey() != -1
                    && ( distMin == -1
                        || distMin > terrainDistance.get(x).get(y).getKey()
                        )
                    ){
                        newX=x+dx;
                        newY=y+dy;
                        distMin=terrainDistance.get(x).get(y).getKey();
                    }
                }
        out.add(newX);
        out.add(newY);
        return out;
    }
}
