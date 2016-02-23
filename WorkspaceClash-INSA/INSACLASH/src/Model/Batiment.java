package Model;

public abstract class Batiment{
    protected TypeRessource ressourceNecessaire;
    protected TypeBatiment typeBatiment;
    protected int pointsDeVie;
    protected int x=-1;
    protected int y=-1;
    protected int niveau;
    protected int tempsConstruction;
    protected int coutUpdate;
    protected int id;
    
    

    public int getId(){//DONE
		return id;
	}
	public void setId(int id){//DONE
		this.id = id;
	}
	public TypeBatiment getTypeBatiment(){//DONE
        return this.typeBatiment;
    }
    public void setPointsDeVie(int in){//DONE
        this.pointsDeVie = in;
    }

    public int getPointsDeVie(){//DONE
        return this.pointsDeVie;
    }

    public void setX(int in){//DONE
        this.x = in;
    }

    public int getX(){//DONE
        return this.x;
    }

    public void setY(int in){//DONE
        this.y = in;
    }

    public int getY(){//DONE
        return this.y;
    }

    public void setNiveau(int in){//DONE
        this.niveau = in;
    }

    public int getNiveau(){//DONE
        return this.niveau;
    }

    public void setTempsConstruction(int in){//DONE
        this.tempsConstruction = in;
    }

    public int getTempsConstruction(){//DONE
        return this.tempsConstruction;
    }

    public void setCoutUpdate(int in){//DONE
        this.coutUpdate = in;
    }

    public int getCoutUpdate(){//DONE
        return this.coutUpdate;
    }

    public void setRessourceNecessaire(TypeRessource in){//DONE
        this.ressourceNecessaire = in;
    }

    public TypeRessource getRessourceNecessaire(){//DONE
        return this.ressourceNecessaire;
    }

    public abstract Batiment clone();

    public void upgrade(){//DONE
        pointsDeVie+=40;
        tempsConstruction*=2;
        coutUpdate*=3;
        niveau++;
    }
}
