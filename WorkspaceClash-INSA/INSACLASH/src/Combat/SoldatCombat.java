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
    }
    public Vector<BatimentCombat> trouverBatimentAPortee(Vector<BatimentCombat> batiments){//CHK
        int nbMaxBatiments=2; //TODO: a mettre dans les classes
        int portee=5;//TODO: a mettre dans les classes

        Vector<BatimentCombat> out = new Vector<BatimentCombat>();
        for(BatimentCombat batiment:batiments)
            if( batiment.distanceMin(x, y) < portee ){
                out.add(batiment);
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
    public Vector<Integer> ouAller(Vector<BatimentCombat> batiments){//TODO
        Vector<Integer> out = new Vector<Integer>();
        out.add(0);
        out.add(0);
        return out;
    }
}
