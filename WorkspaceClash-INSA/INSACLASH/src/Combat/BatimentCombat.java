package Combat;

import Model.Batiment;

public class BatimentCombat extends EntiteCombat{
    public BatimentCombat(Batiment batiment){//TODO
        this.x         = batiment.getX();
        this.y         = batiment.getY();
        this.type      = TypeEntite.BATIMENT;
    }
}
