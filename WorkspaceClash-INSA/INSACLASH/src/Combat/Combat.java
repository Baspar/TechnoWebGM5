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
    public boolean estTermine(){//DONE
        boolean resteSoldats=(armee.getSoldats().size()>0);
        boolean resteBatiments=false;
        for(BatimentCombat batiment : village.getBatiments())
            if(batiment.getPV()>0){
                resteBatiments=true;
                break;
            }
        return (resteBatiments && resteSoldats);
    }
    private Vector<SoldatCombat> soldatsAPortee(BatimentCombat batiment){//TODO
        Vector<SoldatCombat> out = new Vector<SoldatCombat>();
        return out;
    }
    private BatimentCombat batimentAPortee(SoldatCombat soldat){//TODO
        return null;
    }
    private BatimentCombat batimentLePlusProche(SoldatCombat soldat){//TODO
        return null;
    }
    private void tourDeJeu(){//WIP
        for(BatimentCombat batiment:village.getBatiments()){
            if(!batiment.estMort()){
                Vector<SoldatCombat> soldatsTouches = soldatsAPortee(batiment);
                for(SoldatCombat soldat : soldatsTouches)
                    batiment.attaquer(soldat);
            }
        }
        checkMorts();
    }
    public void checkMorts(){//DONE
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).estATuer())
                armee.getSoldats().get(i).tuer();

        for(int i=0; i<village.getBatiments().size(); i++)
            if(village.getBatiments().get(i).estATuer())
                village.getBatiments().get(i).tuer();
    }
    public void combattre(){//DONE
        while(!estTermine())
            tourDeJeu();
    }
}
