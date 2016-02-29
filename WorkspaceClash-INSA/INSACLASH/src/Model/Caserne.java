package Model;

import java.util.Hashtable;

public class Caserne extends Batiment{
    private Armee armee;
    private Hashtable<TypeSoldat,Integer> niveauMax;
    private Hashtable<TypeSoldat,Integer> niveauActuel;
    private int tailleTotaleArmee;

    public int getNombreArcher(){
    	int nb=0;
    	for(int i=0; i<armee.getSoldats().size();i++)
    		if(armee.getSoldats().get(i).getType()==TypeSoldat.ARCHER)
    			nb++;
    	return nb;
    }
    
    public int getNombreTrebuchet(){
    	int nb=0;
    	for(int i=0; i<armee.getSoldats().size();i++)
    		if(armee.getSoldats().get(i).getType()==TypeSoldat.TREBUCHET)
    			nb++;
    	return nb;
    }
    public int getTailleTotaleArmee(){//DONE
        return tailleTotaleArmee;
    }

    public void setTailleTotaleArmee(int tailleTotaleArmee){//DONE
        this.tailleTotaleArmee = tailleTotaleArmee;
    }

    public Armee getArmee(){//DONE
        return armee;
    }

    public void setArmee(Armee armee){//DONE
        this.armee = armee;
    }

    public Hashtable<TypeSoldat, Integer> getNiveauMax(){//DONE
        return niveauMax;
    }

    public void setNiveauMax(Hashtable<TypeSoldat, Integer> niveauMax){//DONE
        this.niveauMax = niveauMax;
    }

    public Hashtable<TypeSoldat, Integer> getNiveauActuel(){//DONE
        return niveauActuel;
    }

    public void setNiveauActuel(Hashtable<TypeSoldat, Integer> niveauActuel){//DONE
        this.niveauActuel = niveauActuel;
    }

    public Caserne(){//DONE
    	id=1;
        typeBatiment=TypeBatiment.CASERNE;

        niveauMax=new Hashtable<TypeSoldat,Integer>();
        niveauActuel= new Hashtable<TypeSoldat,Integer>();

        niveauMax.put(TypeSoldat.ARCHER, (Integer) 1);
        niveauMax.put(TypeSoldat.TREBUCHET, (Integer) 1);

        niveauActuel.put(TypeSoldat.ARCHER, (Integer) 1);
        niveauActuel.put(TypeSoldat.TREBUCHET, (Integer) 1);

        armee=new Armee();
        ressourceNecessaire=TypeRessource.CHARBON;
        tailleTotaleArmee=30;
        coutUpdate=500;
    }

    public void upgrade(){//DONE
        super.upgrade();
        for(TypeSoldat type: TypeSoldat.values()){
            Integer niveau=niveauMax.get(type);
            niveau++;
            niveauMax.put(type, niveau);
        }
        tailleTotaleArmee+=10;
    }

    public Caserne clone(){//DONE
        return new Caserne();
    }

    public int calculCoutUpgrade(TypeSoldat s){//DONE
        Soldat nvSoldat;
        if (s==TypeSoldat.ARCHER)
            nvSoldat=new Archer(niveauActuel.get(s));
        else
            nvSoldat=new Trebuchet(niveauActuel.get(s));
        return nvSoldat.getCoutUpgrade();
    }

    public int calculCoutFormation(TypeSoldat s){//DONE
        Soldat nvSoldat;
        if (s==TypeSoldat.ARCHER)
            nvSoldat=new Archer(niveauActuel.get(s));
        else
            nvSoldat=new Trebuchet(niveauActuel.get(s));
        return nvSoldat.getCoutUnite();
    }

    public void upgradeSoldat(TypeSoldat s){//DONE
        int lvl=niveauActuel.get(s);
        lvl++;
        niveauActuel.put(s, lvl);
        for(Soldat sold: armee.getSoldats()){
            if(sold.getType()==s)
                sold.upgrade();
        }
    }

}

