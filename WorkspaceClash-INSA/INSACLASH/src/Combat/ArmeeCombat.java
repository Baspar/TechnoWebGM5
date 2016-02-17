package Combat;

import java.util.Vector;

import Model.Armee;
import Model.Soldat;

public class ArmeeCombat{
    private Vector<SoldatCombat> soldats;

    public ArmeeCombat(Armee armee){//DONE
        soldats=new Vector<SoldatCombat>();
        for(Soldat soldat:armee.getSoldats())
            soldats.add(new SoldatCombat(soldat));
    }
    public Vector<SoldatCombat> getSoldats(){//DONE
        return soldats;
    }
}

