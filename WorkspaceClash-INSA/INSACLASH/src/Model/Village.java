package Model;

public class Village{
    private String nom;
    private Batiments batiments;
    private Armee armee;
    
    
    Village(){//DONE
    	batiments=new Batiments();
    	armee=new Armee();
    }
    
    Village(String n){//DONE
    	this();
    	nom=n;
    }
    
    public HDV getHDV(){//DONE
    	return (HDV) batiments.getBatiments(TypeBatiment.HDV).get(0);
    }
    
    public Caserne getCaserne(){//DONE
    	return (Caserne) batiments.getBatiments(TypeBatiment.Caserne).get(0);
    }

	public String getNom(){//DONE
		return nom;
	}

	public void setNom(String nom){//DONE
		this.nom = nom;
	}

	public Batiments getBatiments(){//DONE
		return batiments;
	}

	public void setBatiments(Batiments batiments){//DONE
		this.batiments = batiments;
	}

	public Armee getArmee(){//DONE
		return armee;
	}

	public void setArmee(Armee armee){//DONE
		this.armee = armee;
	}
    
    public boolean upgradeSoldat(TypeSoldat type){//DONE
    	Caserne c=this.getCaserne();
    	HDV h=this.getHDV();
    	//Assez d'argent et encore la possibilite d'améliorer
    	if( (c.getNiveauMax().get(type)>c.getNiveauActuel().get(type)) && (h.getQuantiteActuelle().get(TypeRessource.CHARBON)>=c.calculCoutUpgrade(type)) ){
    		int nb=c.calculCoutUpgrade(type);
    		h.utiliser(TypeRessource.CHARBON,nb);
    		c.upgradeSoldat(type);
    		return true;
    	}	
    	return false;
    }
    
    public boolean upgradeBatiment(TypeBatiment type, int i){//DONE
    	if(i<batiments.getBatiments(type).size()){
    		HDV h=this.getHDV();
    		Batiment b=batiments.getBatiments(type).get(i);
    		TypeRessource t=b.getRessourceNecessaire();
    		if(h.getQuantiteActuelle().get(t)>=b.getCoutConstruction()){
    			h.utiliser(t, b.getCoutConstruction());
    			b.upgrade();
    			return true;
    		}	
    	}
    	return false;
    }
    
    public int preleverMine(Ressource type, int i){//DONE
    	if(i<batiments.getBatiments(type.getTypeBatiment()).size()){
    		HDV h=this.getHDV();
    		Ressource mine=(Ressource) batiments.getBatiments(type.getTypeBatiment()).get(i);
    		if(h.getQuantiteActuelle().get(mine.getTypeRessource())!=h.getQuantiteMax().get(mine.getTypeRessource())){
    			int res=h.crediter(mine.getTypeRessource(), mine.calculProduction());
    			mine.prelever();
    			return res;
    		}
    		else 
    			return 0;
    	}
    	return 0;
    }
}
