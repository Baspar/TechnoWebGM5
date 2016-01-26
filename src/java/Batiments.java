import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Batiments{
    public Map<String,Vector<Batiment>> batiments;
    public Map<String,Batiment> modeleBatiments;
    public Batiments(){//DONE
        modeleBatiments.put("HDV", new HDV());
        modeleBatiments.put("Caserne", new Caserne());
        modeleBatiments.put("Canon", new Canon());
        modeleBatiments.put("Mortier", new Mortier());
        modeleBatiments.put("MineOr", new MineOr());
        modeleBatiments.put("MineCharbon", new MineCharbon());

        for(String s: getSetNomBatiment())
            batiments.put(s, new Vector<Batiment>());
    }
    public Set<String> getSetNomBatiment(){//DONE
        return modeleBatiments.keySet();
    }
    public void addNewBuilding(String type){//TODO
        if(getSetNomBatiment().contains(type)){
            Batiment batiment = modeleBatiments.get(type).clone();
            batiment.upgrade();// Le batiment recupere est niveau 0, on le monte d'un niveau
            batiments.get(type).add(batiment);
        }
    }
}
