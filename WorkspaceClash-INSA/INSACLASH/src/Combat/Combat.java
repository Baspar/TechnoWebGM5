package Combat;

import java.util.Vector;
import java.util.Hashtable;

import Model.Armee;
import Model.Village;

public class Combat{
    public VillageCombat village;
    public ArmeeCombat armee;
    public Vector<Vector<Hashtable<Integer, EntiteCombat>>> terrain;
    public int tailleVillage;
    public int zoom=5;

    // Note terrain :
    // Si le Model.village a une taille de X, le terrain aura une taille de X*zoom, plus deux ligne et colonnes sur les bords permettant le placement des soldats
    // En clair, les batiments font 1x1 dans Model.Village, mais font Zoom*Zoom dans VillageCombat

    public Combat(Village tVillage, Armee tArmee){//WIP
        //Creation village et armee
        armee=new ArmeeCombat(tArmee);
        village=new VillageCombat(tVillage);

        //Recuperation taillevillage
        tailleVillage=tVillage.getTailleVillage();

        //Creation terrain vierge
        terrain=new Vector<Vector<Hashtable<Integer, EntiteCombat>>>();
        for(int i=0; i<tailleVillage*zoom+2; i++){
            terrain.add(new Vector<Hashtable<Integer, EntiteCombat>>());
            for(int j=0; j<tailleVillage*zoom+2; j++)
                terrain.get(i).add(new Hashtable<Integer, EntiteCombat>());
        }

        //Placement batiments sur terrain
        for(BatimentCombat batiment:village.getBatiments()){
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++)
                    terrain.get(batiment.getX()*zoom+i+1).get(batiment.getY()*zoom+j+1).put(batiment.getId(), batiment);
        }
    }
    private boolean estTermine(){//DONE
        boolean resteSoldats=(armee.getSoldats().size()>0);
        boolean resteBatiments=false;
        for(BatimentCombat batiment : village.getBatiments())
            if(batiment.getPV()>0){
                resteBatiments=true;
                break;
            }
        return (resteBatiments && resteSoldats);
    }
    private Vector<SoldatCombat> trouverSoldatsAPortee(BatimentCombat batiment){//TODO
        Vector<SoldatCombat> out = new Vector<SoldatCombat>();
        return out;
    }
    private BatimentCombat trouverBatimentAPortee(SoldatCombat soldat){//TODO
        int x = soldat.getX();
        int y = soldat.getY();

        for(int i=-1; i<2; i++)
            for(int j=-1; j<2; j++)
                if( i!=0 || j!=0 )
                    if( (x+i>=0) && (y+j>=0) && (x+i<tailleVillage*zoom+2) && (y+j<tailleVillage*zoom+2) )
                        for(Integer idEntite : terrain.get(x+i).get(y+j).keySet())
                            if(terrain.get(x+i).get(y+j).get(idEntite).getType()==TypeEntite.BATIMENT)
                                return (BatimentCombat)terrain.get(x+i).get(y+j).get(idEntite);

        return null;
    }
    private BatimentCombat trouverBatimentLePlusProche(SoldatCombat soldat){//TODO
        return null;
    }
    private void tourBatiment(){//DONE
        for(BatimentCombat batiment:village.getBatiments()){
            if(!batiment.estMort()){
                Vector<SoldatCombat> soldatsAPortee= trouverSoldatsAPortee(batiment);
                for(SoldatCombat soldat : soldatsAPortee)
                    batiment.attaquer(soldat);
            }
        }
    }
    private double distance(int x1, int y1, int x2, int y2){//DONE
        return Math.sqrt( Math.pow((y1-y2), 2) + Math.pow((y1-y2), 2));
    }
    private void deplacementSoldat(SoldatCombat soldat){//WIP
        int deplacementMax = soldat.getSoldat().getVitesseDeplacement();
        int id = soldat.getId();
        int oldX = soldat.getX();
        int oldY = soldat.getY();
        int newX = 0;
        int newY = 0;

        //TODO: Trouver où deplacer ce fichu soldat a la con
        // Même pas sur que batimentleplusproche() soit utile, puisque les batiment font zoom*zoom et non pas 1*1
        // BatimentCombat batimentLePlusProche=trouverBatimentLePlusProche(soldat);

        soldat.setX(newX);
        soldat.setY(newY);
        terrain.get(oldX).get(oldY).remove(id);
        terrain.get(newX).get(newY).put(id, soldat);

    }
    private void tourSoldat(){//DONE
        for(SoldatCombat soldat : armee.getSoldats()){
            if(!soldat.estMort()){
                BatimentCombat batimentAPortee = trouverBatimentAPortee(soldat);
                if(batimentAPortee == null){
                    deplacementSoldat(soldat);
                } else
                    soldat.attaquer(batimentAPortee);
            }
        }
    }
    private void tourDeJeu(){//DONE
        tourBatiment();
        tourSoldat();
        checkMorts();
    }
    private void checkMorts(){//DONE
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).estATuer()){
                armee.getSoldats().get(i).tuer();
                int x = armee.getSoldats().get(i).getX();
                int y = armee.getSoldats().get(i).getY();
                int id = armee.getSoldats().get(i).getId();
                terrain.get(x).get(y).remove(id);
            }

        for(int i=0; i<village.getBatiments().size(); i++)
            if(village.getBatiments().get(i).estATuer()){
                village.getBatiments().get(i).tuer();
                int x = village.getBatiments().get(i).getX();
                int y = village.getBatiments().get(i).getY();
                int id = village.getBatiments().get(i).getId();
                terrain.get(x).get(y).remove(id);
            }
    }
    public void combattre(){//DONE
        while(!estTermine())
            tourDeJeu();
    }
}
