package Combat;

import java.util.Vector;

import Model.Armee;
import Model.Soldat;

public class ArmeeCombat{
    Vector<SoldatCombat> soldats;

    public ArmeeCombat(Armee armee){//DONE
        soldats=new Vector<SoldatCombat>();
        for(Soldat soldat:armee.getSoldats())
            soldats.add(new SoldatCombat(soldat));
    }
    Vector<SoldatCombat> getSoldats(){//DONE
        return soldats;
    }
}

