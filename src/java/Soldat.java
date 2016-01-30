abstract class Soldat{
    protected TypeRessource ressourceNecessaire;
    protected int pointsDeVie;
    protected int puissanceAttaque;
    protected int vitesseDeplacement;
    protected TypeAttaque typeAttaque;
    protected int coutUpgrade;
    protected int coutUnite;
    protected int niveau;

    public void update(){//DONE
        pointsDeVie+=5;
        puissanceAttaque+=4;
        coutUpgrade*=2;
        niveau++;
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
    
    
}
