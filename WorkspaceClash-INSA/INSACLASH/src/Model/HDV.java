package Model;

import java.util.Hashtable;

public class HDV extends Batiment{
    private Hashtable<TypeBatiment,Integer> quotaBatiments;
    public int quantiteOrMax=100;
    public int quantiteOr=100;

    public HDV(){//WIP
        typeBatiment=TypeBatiment.HDV;

        quotaBatiments = new Hashtable<TypeBatiment, Integer>();

        quotaBatiments.put(TypeBatiment.HDV,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Caserne,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MineOr,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MineCharbon,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Mortier,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Canon,(Integer) 1);

        upgrade();
    }
    public Integer getQuotaBatiment(TypeBatiment typeBatiment){//DONE
        return quotaBatiments.get(typeBatiment);
    }
    public void upgrade(){//DONE
        super.upgrade();
        for(TypeBatiment type: TypeBatiment.values())
            if(type!=TypeBatiment.HDV && type!=TypeBatiment.Caserne){
                Integer quota=quotaBatiments.get(type);
                quota*=2;
                quotaBatiments.put(type, quota);
            }
        quantiteOrMax*=2;
    }
    public int getQuantiteOrMax(){//DONE
        return quantiteOrMax;
    }
    public void ajouterOrCaisse(int quantiteOr){//DONE
        this.quantiteOr=Math.min(quantiteOrMax, this.quantiteOr+quantiteOr);
    }
    public HDV clone(){//DONE
        return new HDV();
    }
}