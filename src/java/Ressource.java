import java.util.Date;

abstract class Ressource extends Batiment{
    protected TypeRessource typeRessource;
    protected int tauxParHeure;
    //Represente le dernier moment ou on a vide la mine
    protected Date dateDerniereLevee;
    //Represente la quantite a partir de laquelle on considére que la mine est pleine
    protected int quantiteMaxStockee;

    public int calculProduction(){//DONE
        Date dateActuelle=new Date();
        //Avec GetTime
        long date1= dateActuelle.getTime();
        long date2 = dateDerniereLevee.getTime();
        long diff = (date1-date2)/(1000*60*60);
        long production = diff*tauxParHeure;
        if (production>quantiteMaxStockee)
            return quantiteMaxStockee;
        else
            return (int ) production;
    }
    
     public void upgrade(){//DONE
        super.upgrade();
        tauxParHeure*=2;
        quantiteMaxStockee*=2;
    }

    public void setTypeRessource(TypeRessource in){//DONE
        this.typeRessource = in;
    }

    public TypeRessource getTypeRessource(){//DONE
        return this.typeRessource;
    }

    public void setTauxParHeure(int in){//DONE
        this.tauxParHeure = in;
    }

    public int getTauxParHeure(){//DONE
        return this.tauxParHeure;
    }


    public void setDateDerniereLevee(Date in){//DONE
        this.dateDerniereLevee = in;
    }

    public Date getDateDerniereLevee(){//DONE
        return this.dateDerniereLevee;
    }

    public void setQuantiteMaxStockee(int in){//DONE
        this.quantiteMaxStockee=in;
    }

    public int getQuantiteMaxStockee(){//DONE
        return this.quantiteMaxStockee;
    }
}
