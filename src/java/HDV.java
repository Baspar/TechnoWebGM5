import java.util.Hashtable;

public class HDV extends Batiment{
    private Hashtable<TypeBatiment, Integer> quotaBatiments;

    public HDV(){//WIP
        typeBatiment=TypeBatiment.HDV;
        quotaBatiments.put(TypeBatiment.HDV, 1);
        quotaBatiments.put(TypeBatiment.Caserne, 1);
        quotaBatiments.put(TypeBatiment.MineOr, 10);
        quotaBatiments.put(TypeBatiment.MineCharbon, 10);
        quotaBatiments.put(TypeBatiment.Mortier, 2);
        quotaBatiments.put(TypeBatiment.Canon, 2);
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
