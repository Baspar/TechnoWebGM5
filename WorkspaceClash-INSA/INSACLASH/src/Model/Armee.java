package Model;

import java.util.Vector;


public class Armee{
	private Vector<Soldat> soldat;
	
	public Vector<Soldat> getSoldat(){//DONE
		return soldat;
	}
	
	public void setSoldat(Vector<Soldat> soldat){//DONE
		this.soldat = soldat;
	}
	
	public Armee(){//DONE
		soldat=new Vector<Soldat>();
	}
	
	public int calculNbSoldat(){//DONE
		int taille=0;
		for(int i=0; i<soldat.size();i++)
			taille+=soldat.get(i).getPlaceOccupee();
		return taille;
	}
	
	public void ajouterSoldat(TypeSoldat s, int n){//DONE
		Soldat nvSoldat;
		if (s==TypeSoldat.ARCHER)
			nvSoldat=new Archer(n);
		else 
			nvSoldat=new Trebuchet(n);
		soldat.add(nvSoldat);
	}
}
