package Combat;

import java.util.Vector;
import java.util.Iterator;

import Model.Batiment;
import Model.Defense;
import Model.TypeBatiment;
import Model.TypeAttaque;

public class BatimentCombat extends EntiteCombat{
    private Batiment batiment;
    private Integer zoom;
    public BatimentCombat(Batiment batiment, int zoom){//DONE
        this.zoom     = zoom;
        this.x        = batiment.getX();
        this.y        = batiment.getY();
        this.batiment = batiment;
        this.taille   = zoom;
        this.PV       = batiment.getPointsDeVie();
        id=cpt++;
    }
    public void attaquer(Vector<SoldatCombat> soldats){//DONE
        TypeBatiment type = batiment.getTypeBatiment();
        if(type==TypeBatiment.CANON || type==TypeBatiment.MORTIER){
            //System.out.println("J'attaque, moi "+type+" ! ("+(x*zoom+1)+"x"+(y*zoom+1)+")");
            for(SoldatCombat soldat: soldats){
                    //System.out.println("  "+soldat.getSoldat().getType()+" ["+soldat.getPV()+"] ("+soldat.getX()+"x"+soldat.getY()+")");
                    //System.out.println(((Defense)batiment).getPuissanceDefense());
                    soldat.retirerPV(((Defense)batiment).getPuissanceDefense());
            }
        }
    }
    private Vector<SoldatCombat> trouverSoldatAPorteeLOCALISE(Vector<SoldatCombat> soldats){//DONE
        int distance=6; //TODO: ajouter portee Defense dans leur carac
        Vector<SoldatCombat> out=new Vector<SoldatCombat>();
        for(SoldatCombat soldat : soldats){
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++){
                    if(Math.sqrt( Math.pow(x*zoom+i+1-soldat.getX(),2) + Math.pow(y*zoom+j+1-soldat.getY(),2)) < distance){
                        out.add(soldat);
                        return out;
                    }
                }
        }
        return out;
    }
    private Vector<SoldatCombat> trouverSoldatAPorteeZONE(Vector<SoldatCombat> soldats){//DONE
        int distance=6; //TODO: ajouter portee Defense dans leur carac
        Vector<SoldatCombat> out=new Vector<SoldatCombat>();
        for(SoldatCombat soldat : soldats){
            boucle:
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++){
                    if(Math.sqrt( Math.pow(x*zoom+i+1-soldat.getX(),2) + Math.pow(y*zoom+j+1-soldat.getY(),2)) < distance){
                        out.add(soldat);
                        break boucle;
                    }
                }
        }
        return out;
    }
    public Vector<SoldatCombat> trouverSoldatAPortee(Vector<SoldatCombat> soldats){//DONE
        TypeBatiment type = batiment.getTypeBatiment();
        if(type==TypeBatiment.CANON || type==TypeBatiment.MORTIER){
            TypeAttaque typeAttaque = ((Defense)batiment).getTypeDefense();
            if(typeAttaque == TypeAttaque.ZONE){
                return trouverSoldatAPorteeZONE(soldats);
            } else if(typeAttaque == TypeAttaque.LOCALISE){
                return trouverSoldatAPorteeLOCALISE(soldats);
            }
        }
        return new Vector<SoldatCombat>();
    }
    public Batiment getBatiment(){//DONE
        return batiment;
    }
}
