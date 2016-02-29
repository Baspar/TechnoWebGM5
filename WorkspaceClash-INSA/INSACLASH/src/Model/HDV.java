package Model;

import java.util.Hashtable;

public class HDV extends Batiment{
    private Hashtable<TypeBatiment,Integer> quotaBatiments;
    private Hashtable<TypeBatiment,Integer> niveauMaxBatiment;
    private Hashtable<TypeRessource,Integer> quantiteMax;
    private Hashtable<TypeRessource,Integer> quantiteActuelle;

    public HDV(){//DONE
    	id=1;
    	
        typeBatiment=TypeBatiment.HDV;

        quotaBatiments = new Hashtable<TypeBatiment, Integer>();
        niveauMaxBatiment = new Hashtable<TypeBatiment, Integer>();
        quantiteMax = new Hashtable<TypeRessource,Integer>();
        quantiteActuelle = new Hashtable<TypeRessource,Integer>();


        quotaBatiments.put(TypeBatiment.HDV,(Integer) 1);
        quotaBatiments.put(TypeBatiment.CASERNE,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MINEOR,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MINECHARBON,(Integer) 1);
        quotaBatiments.put(TypeBatiment.MORTIER,(Integer) 1);
        quotaBatiments.put(TypeBatiment.CANON,(Integer) 1);

        for(TypeBatiment t : TypeBatiment.values()){
            niveauMaxBatiment.put(t, 1);
        }
        quantiteMax.put(TypeRessource.OR, 50000);
        quantiteMax.put(TypeRessource.CHARBON, 50000);

        quantiteActuelle.put(TypeRessource.OR,100000);
        quantiteActuelle.put(TypeRessource.CHARBON, 100000);

        ressourceNecessaire=TypeRessource.OR;
        coutUpdate=1000;
    }

    public Integer getQuotaBatiment(TypeBatiment typeBatiment){//DONE
        return quotaBatiments.get(typeBatiment);
    }


    public Integer getNiveauMaxBatiment(TypeBatiment t){//DONE
        return niveauMaxBatiment.get(t);
    }

    public void setNiveauMaxBatiment(Hashtable<TypeBatiment, Integer> niveauMaxBatiment){//DONE
        this.niveauMaxBatiment = niveauMaxBatiment;
    }

    public void upgrade(){//DONE
        super.upgrade();

        for(TypeRessource type: TypeRessource.values()){
            Integer i=quantiteMax.get(type);
            i*=2;
            quantiteMax.put(type,i);
        }

        for(TypeBatiment type: TypeBatiment.values()){
            if(type!=TypeBatiment.HDV && type!=TypeBatiment.CASERNE){
                Integer quota=quotaBatiments.get(type);
                quota*=2;
                quotaBatiments.put(type, quota); }
            int niveau=niveauMaxBatiment.get(type);
            niveau++;
            niveauMaxBatiment.put(type, niveau);
        }

    }

    public void utiliser(TypeRessource type, int nb){//DONE
        int temp=quantiteActuelle.get(type)-nb;
        quantiteActuelle.put(type, temp);
    }

    public int crediter(TypeRessource type, int nb){//DONE
   // 	System.out.println(quantiteActuelle);
        int temp= quantiteActuelle.get(type)+nb;
   //     System.out.println(quantiteActuelle);
        temp=Math.min(temp, quantiteMax.get(type));
   //     System.out.println(quantiteMax);
        quantiteActuelle.put(type, temp);
        return temp;
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
