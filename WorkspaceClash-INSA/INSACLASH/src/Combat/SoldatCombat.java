package Combat;

import Model.Soldat;

public class SoldatCombat{
    private int PV;
    private int puissance;
    private int vitesse;
    private int x;
    private int y;

    public SoldatCombat(Soldat soldat){//TODO
        this.x         = -1;
        this.y         = -1;
        this.PV        = soldat.getPointsDeVie();
        this.vitesse   = soldat.getVitesseDeplacement();
        this.puissance = soldat.getPuissanceAttaque();
    }
    public void deplacer(){//TODO
    }
    public void attaquer(){//TODO
    }
}
