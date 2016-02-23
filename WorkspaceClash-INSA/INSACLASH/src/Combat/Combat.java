package Combat;

import java.util.Vector;
import java.util.Hashtable;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

import Model.TypeBatiment;
import Model.Armee;
import Model.Village;

public class Combat{
    private VillageCombat village;
    private ArmeeCombat armee;
    private Vector<Vector<Hashtable<Integer, EntiteCombat>>> terrain;
    private int tailleVillage;
    private int zoom=2;
    private Vector<Vector<SimpleEntry<Integer, BatimentCombat>>> terrainDistance;

    // Note terrain :
    // Si le Model.village a une taille de X, le terrain aura une taille de X*zoom, plus deux ligne et colonnes sur les bords permettant le placement des soldats
    // En clair, les batiments font 1x1 dans Model.Village, mais font Zoom*Zoom dans VillageCombat

    public Combat(Village tVillage, Armee tArmee){//DONE
        //Creation village et armee
        armee=new ArmeeCombat(tArmee);
        village=new VillageCombat(tVillage, zoom);

        //Recuperation taillevillage
        tailleVillage=tVillage.getTailleVillage()*zoom;

        //Creation terrain vierge
        terrain=new Vector<Vector<Hashtable<Integer, EntiteCombat>>>();
        terrainDistance=new Vector<Vector<SimpleEntry<Integer, BatimentCombat>>>();
        for(int i=0; i<tailleVillage+2; i++){
            terrain.add(new Vector<Hashtable<Integer, EntiteCombat>>());
            terrainDistance.add(new Vector<SimpleEntry<Integer, BatimentCombat>>());
            for(int j=0; j<tailleVillage+2; j++){
                terrain.get(i).add(new Hashtable<Integer, EntiteCombat>());
                terrainDistance.get(i).add(new SimpleEntry<Integer, BatimentCombat>(-1, null));
            }
        }

        //Placement batiments sur terrain
        for(BatimentCombat batiment:village.getBatiments()){
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++)
                    terrain.get(batiment.getX()*zoom+i+1).get(batiment.getY()*zoom+j+1).put(batiment.getId(), batiment);
        }
    }
    private void updateTerrainDistance(){//DONE
        //Remise a zero terrain distance
        terrainDistance=new Vector<Vector<SimpleEntry<Integer, BatimentCombat>>>();
        for(int i=0; i<tailleVillage+2; i++){
            terrainDistance.add(new Vector<SimpleEntry<Integer, BatimentCombat>>());
            for(int j=0; j<tailleVillage+2; j++)
                terrainDistance.get(i).add(new SimpleEntry<Integer, BatimentCombat>(-1, null));
        }

        // Boucle ajout batment sur carte (distance = 0)
        for(BatimentCombat batiment : village.getBatiments()){
            if(batiment.estATuer() || batiment.estMort()){
                int xBat=batiment.getX()*zoom;
                int yBat=batiment.getY()*zoom;
                LinkedList<SimpleEntry<SimpleEntry<Integer, Integer>, Integer>> fifo = new LinkedList<SimpleEntry<SimpleEntry<Integer, Integer>, Integer>>();

                // Ajout ligne haut et bas
                for(int i=0; i<zoom; i++){
                    fifo.add(new SimpleEntry<SimpleEntry<Integer, Integer>, Integer>(new SimpleEntry<Integer, Integer>(xBat+i, yBat), 0));
                    fifo.add(new SimpleEntry<SimpleEntry<Integer, Integer>, Integer>(new SimpleEntry<Integer, Integer>(xBat+i, yBat+zoom-1), 0));
                }

                // Ajout colonne droite et gauche
                for(int i=1; i<zoom-1; i++){
                    fifo.add(new SimpleEntry<SimpleEntry<Integer, Integer>, Integer>(new SimpleEntry<Integer, Integer>(xBat, yBat+i), 0));
                    fifo.add(new SimpleEntry<SimpleEntry<Integer, Integer>, Integer>(new SimpleEntry<Integer, Integer>(xBat+zoom-1, yBat+i), 0));
                }

                while(!fifo.isEmpty()){
                    SimpleEntry<SimpleEntry<Integer, Integer>, Integer> cell = fifo.removeFirst();
                    int x = cell.getKey().getKey();
                    int y = cell.getKey().getValue();
                    int dist = cell.getValue();
                    dist++;
                    for(int dx=-1; dx<2; dx+=2)
                        for(int dy=-1; dy<2; dy+=2)
                            if( x+dx >= 0
                                && y+dy >= 0
                                && x+dx < tailleVillage
                                && y+dy < tailleVillage
                                && (terrainDistance.get(x+dx).get(y+dy).getKey() == -1
                                    || dist < terrainDistance.get(x+dx).get(y+dy).getKey()
                                    )
                              ){
                                    terrainDistance.get(x+dx).set(y+dy, new SimpleEntry<Integer, BatimentCombat>(dist, batiment));
                                    fifo.add(new SimpleEntry<SimpleEntry<Integer, Integer>, Integer>(new SimpleEntry<Integer, Integer>(x+dx, y+dy), dist));
                              }
                }
            }
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
    private void deplacementSoldat(SoldatCombat soldat){//DONE
        Vector<Integer> result = soldat.ouAller(village.getBatiments(), terrainDistance);

        int id = soldat.getId();
        int newX = result.get(0);
        int newY = result.get(1);
        int oldX = soldat.getX();
        int oldY = soldat.getY();

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
    private void checkMorts(){//WIP
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).estATuer()){
                armee.getSoldats().get(i).tuer();
                int x = armee.getSoldats().get(i).getX();
                int y = armee.getSoldats().get(i).getY();
                int id = armee.getSoldats().get(i).getId();
             //   System.out.println(id);
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
            updateTerrainDistance();
            afficherCombat();
        }
    }
    private void afficherCombat(){//DONE
        String out="";

        out +="+";
        for(int i=0; i<tailleVillage; i++)
            out += "---+";
        out += "\n";

        for(int x=0; x<tailleVillage; x++){
            out +="+";
            for(int y=0; y<tailleVillage; y++){
                int nbSol=0;
                for(SoldatCombat soldat : armee.getSoldats())
                    if (soldat.getX() == x && soldat.getY() == y)
                        nbSol++;
                if(nbSol != 0)
                    out +=" "+nbSol+" ";
                else{
                    TypeBatiment typeBat=null;
                    for(BatimentCombat bat : village.getBatiments())
                        if (bat.getX() == x && bat.getY() == y)
                            typeBat = bat.getBatiment().getTypeBatiment();
                    if(typeBat != null)
                        out+= typeBat==TypeBatiment.CASERNE?"CAS":typeBat==TypeBatiment.HDV?"HDV":typeBat==TypeBatiment.MORTIER?"MOR": typeBat==TypeBatiment.MINEOR?"MiO": typeBat==TypeBatiment.MINECHARBON?"MiC":"   ";
                }
                out +="+";
            }
            out += "\n";
        }

        out +="+";
        for(int i=0; i<tailleVillage; i++)
            out += "---+";
        out += "\n";

        System.out.println(out);
    }
}
