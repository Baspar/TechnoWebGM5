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

    public Combat(Village tVillage, Armee tArmee){//DONE
        //Creation village et armee
        armee=new ArmeeCombat(tArmee);
        village=new VillageCombat(tVillage, zoom);

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
    private double distance(int x1, int y1, int x2, int y2){//DONE
        return Math.sqrt( Math.pow((y1-y2), 2) + Math.pow((y1-y2), 2));
    }
    private void deplacementSoldat(SoldatCombat soldat){//CHK
        int deplacementMax = soldat.getSoldat().getVitesseDeplacement();
        int id = soldat.getId();
        int oldX = soldat.getX();
        int oldY = soldat.getY();
        Vector<Integer> result = soldat.ouAller(village.getBatiments());
        int newX = result.get(0);
        int newY = result.get(1);

        soldat.setX(newX);
        soldat.setY(newY);
        terrain.get(oldX).get(oldY).remove(id);
        terrain.get(newX).get(newY).put(id, soldat);

    }
    private void tourBatiment(){//DONE
        for(BatimentCombat batiment:village.getBatiments()){
            if(!batiment.estMort()){
                Vector<SoldatCombat> soldats = batiment.trouverSoldatAPortee(armee.getSoldats());
                if(soldats.size()!=0)
                    batiment.attaquer(soldats);
            }
        }
    }
    private void tourSoldat(){//DONE
        for(SoldatCombat soldat : armee.getSoldats()){
            if(!soldat.estMort()){
                Vector<BatimentCombat> batiments = soldat.trouverBatimentAPortee(village.getBatiments());
                if(batiments.size()!=0)
                    soldat.attaquer(batiments);
                else
                    deplacementSoldat(soldat);
            }
        }
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
        while(!estTermine()){
            tourBatiment();
            tourSoldat();
            checkMorts();
        }
    }
}
