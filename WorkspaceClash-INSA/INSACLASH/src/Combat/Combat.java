package Combat;

import java.util.Vector;

import Model.Armee;
import Model.Village;

public class Combat{
    public VillageCombat village;
    public ArmeeCombat armee;
    public Vector<Vector<EntiteCombat>> terrain;
    public int tailleVillage;
    public int zoom=5;

    public Combat(Village tVillage, Armee tArmee){//TODO
        //Creation village et armee
        armee=new ArmeeCombat(tArmee);
        village=new VillageCombat(tVillage);

        //Recuperation taillevillage
        tailleVillage=tVillage.getTailleVillage();

        //Creation terrain vierge
        terrain=new Vector<Vector<EntiteCombat>>();
        for(int i=0; i<tailleVillage*zoom; i++){
            terrain.add(new Vector<EntiteCombat>());
            for(int j=0; j<tailleVillage*zoom; j++)
                terrain.get(i).add(null);
        }

        //Placement batiments sur terrain
        for(BatimentCombat batiment:village.getbatiments()){
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++)
                    terrain.get(batiment.getX()+i).set(batiment.getY()+j, batiment);
        }

    }

    void checkMorts(){//DONE
        Vector<Integer> morts=new Vector<Integer>();
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).getPV()<=0)
                morts.add(i);

        for(Integer mort:morts){
            SoldatCombat soldat=armee.getSoldats().get(mort);
            terrain.get(soldat.getX()).set(soldat.getY(), null);
        }

    }
}
