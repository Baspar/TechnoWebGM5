package Model;

abstract class Defense extends Batiment{
    protected TypeAttaque typeDefense;
    protected int puissanceDefense;
    protected double attaqueParSeconde; // On ne le prendra pas en compte dans les calculs juste au cas ou

    public void setTypeDefense(TypeAttaque t){//DONE
        typeDefense=t;
    }

    public TypeAttaque getTypeDefense(){//DONE
        return typeDefense;
    }

    public void setPuissanceDefense(int puiss){//DONE
        puissanceDefense=puiss;
    }

    public int getPuissanceDefense(){//DONE
        return puissanceDefense;
    }

    public void setAttaqueParSeconde(double s){//DONE
        attaqueParSeconde=s;
    }

    public double getAttaqueParSeconde(){//DONE
        return attaqueParSeconde;
    }

    public void upgrade(){//DONE
        super.upgrade();
        puissanceDefense+=puissanceDefense/5;
        attaqueParSeconde+=0.1;
    }
}
