package Combat;

import Model.Soldat;

public class SoldatCombat extends EntiteCombat{

    private Soldat soldat;

    public SoldatCombat(Soldat soldat){//TODO
        this.x      = -1;
        this.y      = -1;
        this.soldat = soldat;
    }
    public void deplacer(){//TODO
    }
    public void attaquer(EntiteCombat entite){//TODO
    }
    public Soldat getSoldat(){//DONE
        return soldat;
    }
}
