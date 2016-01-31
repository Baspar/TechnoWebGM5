package Model;

import java.util.Vector;


public class Armee{
	private Vector<Soldat> soldats;
	
	public Vector<Soldat> getSoldats(){//DONE
		return soldats;
	}
	
	public void setSoldats(Vector<Soldat> soldat){//DONE
		this.soldats = soldat;
	}
	
	public Armee(){//DONE
		soldats=new Vector<Soldat>();
	}
	
	public int calculNbSoldat(){//DONE
		int taille=0;
		for(int i=0; i<soldats.size();i++)
			taille+=soldats.get(i).getPlaceOccupee();
		return taille;
	}
	
	public void ajouterSoldat(TypeSoldat s, int n){//DONE
		Soldat nvSoldat;
		if (s==TypeSoldat.ARCHER)
			nvSoldat=new Archer(n);
		else 
			nvSoldat=new Trebuchet(n);
		soldats.add(nvSoldat);
	}
}
