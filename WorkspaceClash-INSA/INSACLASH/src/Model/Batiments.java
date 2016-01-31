package Model;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class Batiments{
    public Hashtable<TypeBatiment,Vector<Batiment>> batiments;
    public Hashtable<TypeBatiment,Batiment> modeleBatiments;

    public Batiments(){//DONE
        //Init
        modeleBatiments=new Hashtable<TypeBatiment, Batiment>();
        batiments=new Hashtable<TypeBatiment, Vector<Batiment>>();

        //Ajout modèles
        modeleBatiments.put(TypeBatiment.HDV, new HDV());
        modeleBatiments.put(TypeBatiment.Caserne, new Caserne());
        modeleBatiments.put(TypeBatiment.Canon, new Canon());
        modeleBatiments.put(TypeBatiment.Mortier, new Mortier());
        modeleBatiments.put(TypeBatiment.MineOr, new MineOr());
        modeleBatiments.put(TypeBatiment.MineCharbon, new MineCharbon());

        //Init batiments
        for(TypeBatiment s: getSetNomBatiment())
            batiments.put(s, new Vector<Batiment>());

        //Ajout HDV & caserne
        addNewBuilding(TypeBatiment.HDV);
        addNewBuilding(TypeBatiment.Caserne);
    }

    public Set<TypeBatiment> getSetNomBatiment(){//DONE
        return modeleBatiments.keySet();
    }

    public Vector<Batiment> getBatiments(TypeBatiment type){//DONE
        return batiments.get(type);
    }

    public boolean addNewBuilding(TypeBatiment type){//DONE
        HDV hdv=(HDV)batiments.get(TypeBatiment.HDV).get(0);
        //On teste si on a le droit de construire
        if(hdv.getQuotaBatiment(type) > batiments.get(type).size()){
            Batiment batiment = modeleBatiments.get(type).clone();
            batiment.upgrade();// Le batiment recupere est niveau 0, on le monte d'un niveau
            batiments.get(type).add(batiment);
            return true;
        }
        return false;
    }

	public Hashtable<TypeBatiment, Batiment> getModeleBatiments(){//DONE
		return modeleBatiments;
	}

	public void setModeleBatiments(Hashtable<TypeBatiment, Batiment> modeleBatiments){//DONE
		this.modeleBatiments = modeleBatiments;
	}
    
    
}