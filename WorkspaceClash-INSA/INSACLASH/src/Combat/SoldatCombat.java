package Combat;

import Model.Soldat;

public class SoldatCombat extends EntiteCombat{
    private int PV;
    private int puissance;
    private int vitesse;

    public SoldatCombat(Soldat soldat){//TODO
        this.x         = -1;
        this.y         = -1;
        this.type      = TypeEntite.SOLDAT;
        this.PV        = soldat.getPointsDeVie();
        this.vitesse   = soldat.getVitesseDeplacement();
        this.puissance = soldat.getPuissanceAttaque();
    }
    public void deplacer(){//TODO
    }
    public void attaquer(){//TODO
    }

    int getPV(){//DONE
        return PV;
    }
    int getPuissance(){//DONE
        return puissance;
    }
    int getVitesse(){//DONE
        return vitesse;
    }
}
