package Model;

import java.util.Vector;

public class Village{
    private final static int tailleVillage=25;
    private String nom;
    private Batiments batiments;
    private Armee armee;
    private Vector<Vector<Batiment>> carte;


    Village(){//DONE
        batiments=new Batiments();
        armee=this.getCaserne().getArmee();

        carte=new Vector<Vector<Batiment>>();
        for(int i=0; i<tailleVillage; i++){
            carte.add(new Vector<Batiment>());
            for(int j=0; j<tailleVillage; j++)
                carte.get(i).add(null);
        }
    }

    Village(String n){//DONE
        this();
        nom=n;
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

    public boolean ajouterBatiment(TypeBatiment type, int x, int y){//DONE
        HDV h=this.getHDV();
        int i=h.getQuantiteActuelle().get(batiments.getModeleBatiments().get(type).getRessourceNecessaire());
        if(i>batiments.getModeleBatiments().get(type).getCoutUpdate() && getBatiment(x, y)==null){
            boolean bool=batiments.addNewBuilding(type);
            Batiment b=batiments.getBatiments(type).get(batiments.getBatiments(type).size()-1);
            if(bool==true) {
                b.setX(x);
                b.setY(y);
            }
            return bool;
        } else
            return false;
    }

    public boolean deplacerBatiment(TypeBatiment type, int i, int x, int y){//DONE
        if(getBatiment(x, y)==null){
            this.getBatiment(type, i).setX(x);
            this.getBatiment(type, i).setY(y);
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
}
