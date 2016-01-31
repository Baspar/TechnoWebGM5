package Model;

import java.util.Hashtable;

public class HDV extends Batiment{
    private Hashtable<TypeBatiment, Integer> quotaBatiments;

    public HDV(){//WIP
        typeBatiment=TypeBatiment.HDV;

        quotaBatiments = new Hashtable<TypeBatiment, Integer>();

        quotaBatiments.put(TypeBatiment.HDV,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Caserne,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MineOr,(Integer) 10);
        quotaBatiments.put(TypeBatiment.MineCharbon,(Integer) 10);
        quotaBatiments.put(TypeBatiment.Mortier,(Integer) 2);
        quotaBatiments.put(TypeBatiment.Canon,(Integer) 2);
    }
    public Integer getQuotaBatiment(TypeBatiment typeBatiment){//DONE
        return quotaBatiments.get(typeBatiment);
    }
    public void upgrade(){//TODO
        super.upgrade();
    }
    public HDV clone(){//DONE
        return new HDV();
    }
}
