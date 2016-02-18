package Combat;

import java.util.Vector;
import java.util.Set;

import Model.Village;
import Model.TypeBatiment;
import Model.Batiment;

public class VillageCombat{
    private Vector<BatimentCombat> batiments;

    public VillageCombat(Village village, int zoom){//DONE
        Set<TypeBatiment> typeBatiment=village.getBatiments().getSetNomBatiment();
        for(TypeBatiment type : typeBatiment)
            for(Batiment batiment : village.getBatiment(type))
                batiments.add(new BatimentCombat(batiment, zoom));
    }
    public Vector<BatimentCombat> getBatiments(){//DONE
        return batiments;
    }
}

