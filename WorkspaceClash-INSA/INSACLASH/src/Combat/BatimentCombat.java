package Combat;

import Model.Batiment;

public class BatimentCombat extends EntiteCombat{
    private Batiment batiment;
    public BatimentCombat(Batiment batiment){//TODO
        this.x        = batiment.getX();
        this.y        = batiment.getY();
        this.batiment = batiment;
    }

    public void attaquer(EntiteCombat entite){//TODO
    }
    public Batiment getBatiment(){//DONE
        return batiment;
    }
}
