package Model;

abstract class Soldat{
	protected TypeSoldat type;
	protected TypeRessource ressourceNecessaire;
    protected int pointsDeVie;
    protected int puissanceAttaque;
    protected int vitesseDeplacement;
    protected TypeAttaque typeAttaque;
    protected int coutUpgrade;
    protected int coutUnite;
    protected int niveau;
    protected int dureeFormation;
    protected int placeOccupee;

    public void upgrade(){//DONE
        pointsDeVie+=5;
        puissanceAttaque+=4;
        coutUpgrade*=2;
        coutUnite+=coutUnite/2;
        niveau++;
    }
    
    public TypeSoldat getType() {//DONE
  		return type;
  	}

  	public void setType(TypeSoldat type) {//DONE
  		this.type = type;
  	}
    
    public TypeRessource getRessourceNecessaire(){//DONE
        return ressourceNecessaire;
    }
    
    public void setRessourceNecessaire(TypeRessource t){//DONE
        ressourceNecessaire=t;
    }

    public int getPointsDeVie(){//DONE
        return pointsDeVie;
    }
    
    public void setPointsDeVie(int i){//DONE
        pointsDeVie=i;
    }

    public int getPuissanceAttaque(){//DONE
        return puissanceAttaque;
    }
    
    public void setPuissanceAttaque(int i){//DONE
        puissanceAttaque=i;
    }
    
    public void setTypeAttaque(TypeAttaque t){//DONE
        typeAttaque=t;
    }
    
    public TypeAttaque getTypeAttaque(){//DONE
        return typeAttaque;
    }
    
    public int getCoutUpgrade(){//DONE
        return coutUpgrade;
    }
    
    public void setCoutUnite(int c){//DONE
        coutUnite=c;
    }
    
    public int getCoutUnite(){//DONE
        return coutUnite;
    }
    
    public void setCoutUpgrade(int c){//DONE
        coutUpgrade=c;
    }
    
    public int getVitesseDeplacement(){//DONE
        return vitesseDeplacement;
    }        
    
    public void setVitesseDeplacement(int v){//DONE
        vitesseDeplacement=v;
    }
    
    public int getNiveau(){//DONE
        return niveau;
    }        
    
    public void setNiveau(int v){//DONE
        niveau=v;
    }
    
    public int getDureeFormation(){//DONE
        return dureeFormation;
    }        
    
    public void setDureeFormation(int v){//DONE
        dureeFormation=v;
    } 
    
    public int getPlaceOccupee(){//DONE
        return placeOccupee;
    }        
    
    public void setPlaceOccupee(int v){//DONE
        placeOccupee=v;
    }
}
