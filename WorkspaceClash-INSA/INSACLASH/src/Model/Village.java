package Model;

import java.util.Vector;

public class Village{
    private final static int tailleVillage=14;
    private String nom;
    private Batiments batiments;
    private Armee armee;
    private Vector<Vector<Batiment>> carte;


    public Village(){//DONE
        batiments=new Batiments();
        armee=this.getCaserne().getArmee();

        carte=new Vector<Vector<Batiment>>();
        for(int i=0; i<tailleVillage; i++){
            carte.add(new Vector<Batiment>());
            for(int j=0; j<tailleVillage; j++)
                carte.get(i).add(null);
        }
        deplacerBatiment(TypeBatiment.HDV, 0, 0, 0);
        deplacerBatiment(TypeBatiment.CASERNE, 0, 0, 1);
    }

    public Village(String n){//DONE
        this();
        nom=n;
    }
    
    public Vector<Vector<Batiment>> getCarte() {//DONE
		return carte;
	}

	public HDV getHDV(){//DONE
        return (HDV) batiments.getBatiments(TypeBatiment.HDV).get(0);
    }

    public Caserne getCaserne(){//DONE
        return (Caserne) batiments.getBatiments(TypeBatiment.CASERNE).get(0);
    }

    public String getNom(){//DONE
        return nom;
    }

    public void setNom(String nom){//DONE
        this.nom = nom;
    }

    public Batiments getBatiments(){//DONE
        return batiments;
    }

    public void setBatiments(Batiments batiments){//DONE
        this.batiments = batiments;
    }

    public Armee getArmee(){//DONE
        return armee;
    }

    public void setArmee(Armee armee){//DONE
        this.armee = armee;
    }

    public boolean upgradeSoldat(TypeSoldat type){//DONE
        Caserne c=this.getCaserne();
        HDV h=this.getHDV();
        //Assez d'argent et encore la possibilite d'améliorer
        if( (c.getNiveauMax().get(type)>c.getNiveauActuel().get(type)) && (h.getQuantiteActuelle().get(TypeRessource.CHARBON)>=c.calculCoutUpgrade(type)) ){
            int nb=c.calculCoutUpgrade(type);
            h.utiliser(TypeRessource.CHARBON,nb);
            c.upgradeSoldat(type);
            return true;
        }
        return false;
    }

    public boolean upgradeBatiment(TypeBatiment type, int i){//DONE
        HDV h=this.getHDV();
        if(i<batiments.getBatiments(type).size()&&batiments.getBatiments(type).get(i).getNiveau()<h.getNiveauMaxBatiment(type)){
            Batiment b=batiments.getBatiments(type).get(i);
            TypeRessource t=b.getRessourceNecessaire();
            if(h.getQuantiteActuelle().get(t)>=b.getCoutUpdate()){
            	//System.out.println("ok");
                h.utiliser(t, b.getCoutUpdate());
                b.upgrade();
                return true;
            }
        }
        return false;
    }

    public int preleverMine(Ressource type, int i){//DONE
        if(i<batiments.getBatiments(type.getTypeBatiment()).size()){
            HDV h=this.getHDV();
            Ressource mine=(Ressource) batiments.getBatiments(type.getTypeBatiment()).get(i);
            if(h.getQuantiteActuelle().get(mine.getTypeRessource())!=h.getQuantiteMax().get(mine.getTypeRessource())){
                int res=h.crediter(mine.getTypeRessource(), mine.calculProduction());
                mine.prelever();
                return res;
            }
        }
        return 0;
    }

    public boolean quantiteBatimentAtteinte(TypeBatiment type){//DONE
        HDV h=this.getHDV();
        int quantiteActuelle=getBatiments().getBatiments(type).size();
        int quantiteMax=h.getQuotaBatiment(type);
        return (quantiteActuelle >= quantiteMax);
    }

    public boolean ajouterBatiment(TypeBatiment type, int x, int y){//DONE
        if(!quantiteBatimentAtteinte(type) && getBatiment(x, y)==null){
        	//Essai de construction de batiment.
        	//Peut ne pas marcher si fonds insuffisants.
            boolean constructionOK=batiments.addNewBuilding(type);

            //Si construction OK, on deplace
            if(constructionOK){
				deplacerBatiment(type, batiments.getBatiments(type).size()-1, x, y);
				return true;
            }
        }
		return false;
    }
    
    public boolean ajouterBatiment(TypeBatiment type){//DONE
        if(!quantiteBatimentAtteinte(type) ){
        	//Essai de construction de batiment.
        	//Peut ne pas marcher si fonds insuffisants.
            boolean constructionOK=batiments.addNewBuilding(type);
            HDV h=this.getHDV();
           // System.out.println("ok");
            h.utiliser(batiments.getModeleBatiments().get(type).getRessourceNecessaire(), batiments.getModeleBatiments().get(type).getCoutUpdate());
            return true;
        }
		return false;
    }

    public boolean deplacerBatiment(TypeBatiment type, int i, int x, int y){//DONE
    	int oldX=getBatiment(type, i).getX();
    	int oldY=getBatiment(type, i).getY();

        if(getBatiment(x, y)==null){
        	//Modification coords au niveau du batiment
            this.getBatiment(type, i).setX(x);
            this.getBatiment(type, i).setY(y);

            //Retrait batiment anciennes coords (si coordonnees != (-1, -1))
            if(oldX!=-1 && oldY!=-1)
				carte.get(oldX).set(oldY, null);

            //Placement batiment nouvelles coords
            carte.get(x).set(y, getBatiment(type, i));

            return true;
        } else
            return false;
    }

    public boolean ajouterSoldat(TypeSoldat type ){//DONE
        Caserne c=this.getCaserne();
        HDV h=this.getHDV();
        int tailleArmee=armee.calculNbSoldat();
        if(type==TypeSoldat.ARCHER){
            Archer a =new Archer();
            tailleArmee+=a.getPlaceOccupee();
        }
        else{
            Trebuchet t=new Trebuchet();
            tailleArmee+=t.getPlaceOccupee();
        }
        if(c.calculCoutFormation(type)<h.getQuantiteActuelle().get(TypeRessource.CHARBON)){
            if(tailleArmee<=c.getTailleTotaleArmee()){
                h.utiliser(TypeRessource.CHARBON, c.calculCoutFormation(type));
                armee.ajouterSoldat(type, c.getNiveauActuel().get(type));
                return true;
            }
        }
        return false;
    }

    public boolean supprimerSoldat(TypeSoldat type ){//DONE
        Caserne c=this.getCaserne();
        int nb;
        if(type==TypeSoldat.ARCHER)
        	nb=c.getNombreArcher();
        else
        	nb=c.getNombreTrebuchet();
        if(nb<1)
        return false;
        else{
        	boolean b=false;
        	for(int i=0; i<c.getArmee().getSoldats().size() && b==false ;i++)
        		if(c.getArmee().getSoldats().get(i).getType()==type){
        			c.getArmee().getSoldats().remove(i);
        			b=true;
        		}	
        	return true;
        }
    }
    
    public Batiment getBatiment(int x, int y){//DONE
        return carte.get(x).get(y);
    }

    public Batiment getBatiment(TypeBatiment t, int i){//DONE
        return batiments.getBatiments(t).get(i);
    }

    public Vector<Batiment> getBatiment(TypeBatiment t){//DONE
        return batiments.getBatiments(t);
    }

    public Vector<Soldat> getSoldats(){//DONE
        return armee.getSoldats();
    }

    public String affichageVillage(){//DONE
        String out="";

        //Affichage nom village
        out += "Nom village : " +getNom()+"\n";

        //Affichage grille village
        out +="+";
        for(int i=0; i<tailleVillage; i++)
            out += "---+";
        out += "\n";
        for(Vector<Batiment> ligne:carte){
            out += "|";
            for(Batiment batiment:ligne){
                if(batiment!=null){
                    TypeBatiment type=batiment.getTypeBatiment();
                    if (type == TypeBatiment.CANON)
                        out += " C ";
                    else if(type == TypeBatiment.MORTIER)
                        out += " M ";
                    else if(type == TypeBatiment.MINEOR)
                        out += "MiO";
                    else if(type == TypeBatiment.MINECHARBON)
                        out += "MiC";
                    else if(type == TypeBatiment.HDV)
                        out += "HDV";
                    else if(type == TypeBatiment.CASERNE)
                        out += "Cas";
                } else
                    out += "   ";
                out += "|";
            }
            out += "\n";
            out +="+";
            for(int i=0; i<tailleVillage; i++)
                out += "---+";
            out += "\n";
        }
        out += "\n";

        //Affichage composition village
        out += "#####BATIMENTS#####\n";
        for(TypeBatiment type: TypeBatiment.values()){
            out += "  "+type+"["+getBatiments().getBatiments(type).size()+"]:\n";
            for(Batiment batiment:getBatiments().getBatiments(type))
                out += "    * lvl"+batiment.getNiveau()+"  x="+batiment.getX()+"   y="+batiment.getY()+"\n";
        }

        //Affichage armee
        out += "#####ARMEE#####\n";
        out += "  Taille: "+getArmee().calculNbSoldat()+"\n";
        for(int i=0;i<getArmee().getSoldats().size();i++)
            out += "    * " +getArmee().getSoldats().get(i).getType()+" lvl"+getArmee().getSoldats().get(i).getNiveau()+"\n";
        out += "\n";



        return out;
    }
    public int getTailleVillage(){//DONE
        return tailleVillage;
    }
}
