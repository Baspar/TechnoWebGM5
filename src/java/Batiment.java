abstract class Batiment{
    protected TypeRessource ressourceNecessaire;
    protected int pointsDeVie;
    protected int x;
    protected int y;
    protected int niveau;
    protected int tempsConstruction;

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

    public void setRessourceNecessaire(TypeRessource in){//DONE
        this.ressourceNecessaire = in;
    }

    public TypeRessource getRessourceNecessaire(){//DONE
        return this.ressourceNecessaire;
    }

}
