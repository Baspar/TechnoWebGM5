package Combat;

import java.util.Vector;
import java.util.Iterator;

import Model.Batiment;
import Model.Defense;
import Model.TypeBatiment;
import Model.TypeAttaque;

public class BatimentCombat extends EntiteCombat{
    private Batiment batiment;
    public BatimentCombat(Batiment batiment, int zoom){//DONE
        this.x        = batiment.getX();
        this.y        = batiment.getY();
        this.batiment = batiment;
        this.taille   = zoom;
        id=cpt++;
    }
    public void attaquer(Vector<SoldatCombat> soldats){//DONE
        TypeBatiment type = batiment.getTypeBatiment();
        if(type==TypeBatiment.CANON || type==TypeBatiment.MORTIER)
            for(SoldatCombat soldat: soldats)
                    soldat.retirerPV(((Defense)batiment).getPuissanceDefense());
    }
    public double distanceMin(int xo, int yo){//DONE
        // Calcul de la ditance mini d'un point a un carre (#)
        // Division du calcul en 8 zones
        //
        //           |   |
        //         1 | 5 | 2
        //           |   |
        //        ---#####---
        //           #####
        //         7 ##### 8
        //           #####
        //        ---#####---
        //           |   |
        //         4 | 6 | 3
        //           |   |

        if(xo<x && yo<y) //Zone 1
            return Math.sqrt( Math.pow((x-xo), 2) + Math.pow((y-yo), 2));
        else if(xo<x && yo>y+taille) //Zone 2
            return Math.sqrt( Math.pow((x-xo), 2) + Math.pow((y+taille-yo), 2));
        else if(xo>x+taille && yo>y+taille) //Zone 3
            return Math.sqrt( Math.pow((x+taille-xo), 2) + Math.pow((y+taille-yo), 2));
        else if(xo>x+taille && yo<y) //Zone 4
            return Math.sqrt( Math.pow((x+taille-xo), 2) + Math.pow((y-yo), 2));
        else if(xo<x) //Zone 5
            return x-xo;
        else if(xo>x+taille) //Zone 6
            return xo-x-taille;
        else if(yo<y) //Zone 7
            return y-yo;
        else if(yo>y+taille) //Zone 8
            return yo-y-taille;
        else
            return 0;
    }
    private Vector<SoldatCombat> trouverSoldatAPorteeLOCALISE(Vector<SoldatCombat> soldats){//DONE
        int distance=5; //TODO: ajouter portee Defense dans leur carac
        Vector<SoldatCombat> out=new Vector<SoldatCombat>();
        for(SoldatCombat soldat:soldats)
            if(distanceMin(soldat.getX(), soldat.getY()) < distance){
                out.add(soldat);
                return out;
            }
        return out;
    }
    private Vector<SoldatCombat> trouverSoldatAPorteeZONE(Vector<SoldatCombat> soldats){//DONE
        int distance=5; //TODO: ajouter portee Defense dans leur carac
        Vector<SoldatCombat> out=new Vector<SoldatCombat>();
        Iterator<SoldatCombat> it = soldats.iterator();
        while(it.hasNext()){
            SoldatCombat soldat=it.next();
            if(distanceMin(soldat.getX(), soldat.getY()) < distance)
                out.add(soldat);
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
