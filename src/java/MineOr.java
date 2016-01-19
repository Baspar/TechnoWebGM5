public class MineOr extends Ressource{

	public MineOr(){//WIP
		pointsDeVie=360;
		niveau=0;
		x=-1;
		y=-1;
		ressourceNecessaire=TypeRessource.CHARBON;

	}

	public void upgrade(){//WIP
		pointsDeVie+=40;
		niveau+=1;

	}

    public int calculerCoutUpgrade(){//TODO
        return 0;
    }
}
