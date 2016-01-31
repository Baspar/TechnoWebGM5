package Model;

import java.util.Hashtable;

public class HDV extends Batiment{
    private Hashtable<TypeBatiment,Integer> quotaBatiments;
    private Hashtable<TypeRessource,Integer> quantiteMax;
    private Hashtable<TypeRessource,Integer> quantiteActuelle;
    
    public HDV(){//WIP
        typeBatiment=TypeBatiment.HDV;

        quotaBatiments = new Hashtable<TypeBatiment, Integer>();

        quotaBatiments.put(TypeBatiment.HDV,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Caserne,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MineOr,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MineCharbon,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Mortier,(Integer) 1);
        quotaBatiments.put(TypeBatiment.Canon,(Integer) 1);

        quantiteMax.put(TypeRessource.OR, 1000);
        quantiteMax.put(TypeRessource.CHARBON, 1000);
        
        quantiteActuelle.put(TypeRessource.OR,1000);
        quantiteActuelle.put(TypeRessource.CHARBON, 1000);
        upgrade();
    }
    
    public Integer getQuotaBatiment(TypeBatiment typeBatiment){//DONE
        return quotaBatiments.get(typeBatiment);
    }

    public void upgrade(){//DONE
        super.upgrade();

        for(TypeRessource type: TypeRessource.values()){
        	Integer i=quantiteMax.get(type);
        	i*=2;
        	quantiteMax.put(type,i);
        }
        
        for(TypeBatiment type: TypeBatiment.values()){
            if(type!=TypeBatiment.HDV && type!=TypeBatiment.Caserne){
                Integer quota=quotaBatiments.get(type);
                quota*=2;
                quotaBatiments.put(type, quota); }}    
        
    }
    
    public void utiliser(TypeRessource type, int nb){//DONE
    	int temp=quantiteActuelle.get(type)-nb;
    	quantiteActuelle.put(type, temp);
    }
    
    public HDV clone(){//DONE
        return new HDV();
    }

	public Hashtable<TypeBatiment, Integer> getQuotaBatiments(){//DONE
		return quotaBatiments;
	}

	public void setQuotaBatiments(Hashtable<TypeBatiment, Integer> quotaBatiments){//DONE
		this.quotaBatiments = quotaBatiments;
	}

	public Hashtable<TypeRessource, Integer> getQuantiteMax(){//DONE
		return quantiteMax;
	}

	public void setQuantiteMax(Hashtable<TypeRessource, Integer> quantiteMax){//DONE
		this.quantiteMax = quantiteMax;
	}

	public Hashtable<TypeRessource, Integer> getQuantiteActuelle(){//DONE
		return quantiteActuelle;
	}

	public void setQuantiteActuelle(Hashtable<TypeRessource, Integer> quantiteActuelle){//DONE
		this.quantiteActuelle = quantiteActuelle;
	}
}