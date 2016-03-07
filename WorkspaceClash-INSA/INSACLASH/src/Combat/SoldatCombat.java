package Combat;

import java.util.Vector;

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
    public Vector<BatimentCombat> trouverBatimentAPortee(Vector<BatimentCombat> batiments, int zoom){//DONE
        int nbMaxBatiments=1;
        int portee=1;//TODO: a mettre dans les classes

        Vector<BatimentCombat> out = new Vector<BatimentCombat>();
        for(BatimentCombat batiment:batiments){
            if( !batiment.estMort() && !batiment.estATuer() && getDistance(batiment, zoom) <= portee ){
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
        return Math.sqrt( Math.pow(xcp-x, 2) + Math.pow(ycp-y, 2));
    }
    private double getDistance(BatimentCombat bat,int xcp, int ycp, int zoom){//DONE
        int xBat=bat.getX()*zoom+1;
        int yBat=bat.getY()*zoom+1;

        double dist = Math.sqrt( Math.pow(xBat-xcp, 2) + Math.pow(yBat-ycp, 2));

        for(int i=0; i<zoom; i++)
            for(int j=0; j<zoom; j++)
                if(dist > Math.sqrt( Math.pow(xBat-xcp-i, 2) + Math.pow(yBat-ycp-j, 2)))
                    dist = Math.sqrt( Math.pow(xBat-xcp-i, 2) + Math.pow(yBat-ycp-j, 2));

        return dist;
    }
    private double getDistance(BatimentCombat bat, int zoom){//DONE
        int xBat=bat.getX()*zoom+1;
        int yBat=bat.getY()*zoom+1;

        double dist = Math.sqrt( Math.pow(xBat-x, 2) + Math.pow(yBat-y, 2));

        for(int i=0; i<zoom; i++)
            for(int j=0; j<zoom; j++)
                if(dist > Math.sqrt( Math.pow(xBat-x-i, 2) + Math.pow(yBat-y-j, 2)))
                    dist = Math.sqrt( Math.pow(xBat-x-i, 2) + Math.pow(yBat-y-j, 2));

        return dist;
    }
    public Vector<Integer> ouAller(Vector<BatimentCombat> batiments, int tailleVillage, int zoom){//DONE
        Vector<Integer> out = new Vector<Integer>();

        int deplacementMax = soldat.getVitesseDeplacement();

        int newX = -1;
        int newY = -1;
        double distMin = -1;

        Vector<Vector<Boolean>> estLibre = new Vector<Vector<Boolean>>();
        for(int i=0; i<tailleVillage+2; i++){
            estLibre.add(new Vector<Boolean>());
            for(int j=0; j<tailleVillage+2; j++)
                estLibre.get(i).add(true);
        }

        for(BatimentCombat batiment: batiments)
            if(!batiment.estMort() && !batiment.estATuer())
                for(int i=0; i<zoom; i++)
                    for(int j=0; j<zoom; j++)
                        estLibre.get(1+batiment.getX()*zoom+i).set(1+batiment.getY()*zoom+j, false);

        for(BatimentCombat batiment: batiments)
            if(!batiment.estMort() && !batiment.estATuer())
                for(int i=1; i<tailleVillage+1; i++)
                    for(int j=1; j<tailleVillage+1; j++)
                        if(getDistance(i, j) < deplacementMax && estLibre.get(i).get(j))
                            if(distMin == -1 ||getDistance(batiment, i, j, zoom) < distMin){
                                distMin = getDistance(batiment, i, j, zoom);
                                newX = i;
                                newY = j;
                            }

        //System.out.println("Soldat ("+x+"x"+y+"), je suis proche d'un "+batProche.getBatiment().getTypeBatiment()+" ("+batProche.getX()+"x"+batProche.getY()+")");

        if(newX != -1)
            out.add(newX);
        else
            out.add(x);

        if(newY != -1)
            out.add(newY);
        else
            out.add(y);

        return out;
    }
}
