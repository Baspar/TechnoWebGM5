abstract class Soldat{
    protected TypeRessource ressourceNecessaire;
    protected int pointsDeVie;
    protected int puissanceAttaque;
    protected TypeAttaque typeAttaque;
    protected int coutUpgrade;

    abstract void update();
    
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
    
    public void setCoutUpgrade(int c){//DONE
        coutUpgrade=c;
    }        
}
