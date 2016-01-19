abstract class Batiment{
    protected TypeRessource ressourceNecessaire;
    protected int pointsDeVie;
    protected int x;
    protected int y;
    protected int niveau;

	public void setPointsDeVie(int in){
		this.pointsDeVie = in;
	}

	public int getPointsDeVie(){
		return this.pointsDeVie;
	}
	
	public void setX(int in){
		this.x = in;
	}

	public int getX(){
		return this.x;
	}

	public void setY(int in){
		this.y = in;
	}
	
	public int getY(){
		return this.y;
	}

	public void setNiveau(int in){
		this.niveau = in;
	}

	public int getNiveau(){
		return this.niveau;
	}

	public void setRessourceNecessaire(TypeRessource in){
		this.ressourceNecessaire = in;
	}

	public TypeRessource getRessourceNecessaire(){
		return this.ressourceNecessaire;
	}

    abstract int calculerCoutUpgrade();
}
