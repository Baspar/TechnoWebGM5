package Model;

import java.util.Hashtable;

public class Caserne extends Batiment{
    private Armee armee;
    private Hashtable<TypeSoldat,Integer> niveauMax;
	private Hashtable<TypeSoldat,Integer> niveauActuel;
    
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
        typeBatiment=TypeBatiment.Caserne;
        
        niveauMax=new Hashtable<TypeSoldat,Integer>();
        niveauActuel= new Hashtable<TypeSoldat,Integer>();
        
        niveauMax.put(TypeSoldat.ARCHER, (Integer) 1);
        niveauMax.put(TypeSoldat.TREBUCHET, (Integer) 1);
        
        niveauActuel.put(TypeSoldat.ARCHER, (Integer) 1);
        niveauActuel.put(TypeSoldat.TREBUCHET, (Integer) 1);
    }
    
    public void upgrade(){//DONE
        super.upgrade();
        for(TypeSoldat type: TypeSoldat.values()){
        	    Integer niveau=niveauMax.get(type);
        	    niveau++;
                niveauMax.put(type, niveau);
        }        
    }
    
    public Caserne clone(){//DONE
        return new Caserne();
    }
}
